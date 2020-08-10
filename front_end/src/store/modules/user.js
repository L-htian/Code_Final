import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { message } from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
} from '@/api/user'
import {
    getUserOrdersAPI,
    cancelOrderAPI,
    rateHotelAPI,
    getOrderByIdAPI
} from '@/api/order'

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {

        },
        // 用于存放user的所有订单
        userOrderList: [

        ],
        // 用于标记目前评价的订单id
        rateOrderId: 0,
        rateHotelVisibleOfUser: false,
        orderDetailVisibleOfUser: false,
        // 用于标记目前查看的订单id
        currentOrderId: '',
        // 目前查看的订单信息
        currentOrderInfo: {

        }
    }
}

const user = {
    state : getDefaultState(),

    mutations: {
        reset_state: function(state) {
            state.token = '',
            state.userId = '',
            state.userInfo = {
                
            },
            state.userOrderList = []
        },
        set_token: function(state, token){
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        set_rateHotelVisibleOfUser: (state, data) =>{
            state.rateHotelVisibleOfUser = data
        },
        set_orderDetailVisibleOfUser: (state, data) =>{
            state.orderDetailVisibleOfUser = data
        },
        set_rateOrderId: (state, data) =>{
            state.rateOrderId = data
        },
        set_currentOrderId: (state, data) =>{
            state.currentOrderId = data
        },
        set_currentOrderInfo: (state, data) =>{
            state.currentOrderInfo = {
                ...state.currentOrderInfo,
                ...data
            }
        }
    },

    actions: {
        login: async ({ dispatch, commit, state }, userData) => {
            const res = await loginAPI(userData)
            if(res){
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo').then(() =>
                {
                    // 在获取用户个人信息之后，如果是client跳转至酒店列表
                    // 其他的用户跳转至welcome页面
                    if(state.userInfo.userType=='Client'){ router.push('/hotel/hotelList') }
                    else{ router.push('/admin/welcome') }
                })
            }
            else{
                message.error('用户名或密码错误')
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }else{
                message.error('修改失败')
            }
        },
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if(res){
                commit('set_userOrderList', res)
                console.log(state.userOrderList)
            }
        },
        // 撤销订单
        cancelOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
        // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
        rateHotel: async ({ state ,commit },data) => {
            const res = rateHotelAPI(data)
            if(res){
                commit('set_rateHotelVisibleOfUser',false)
                message.success('评价成功')
            }else{
                message.error('评价失败')
            }
        },
        // 查看订单时调用，根据id获取订单
        getOrderById: async({ state, commit }) => {
            const data = {
                id: Number(state.currentOrderId)
            }
            const res = await getOrderByIdAPI(data)
            if(res){
                commit('set_currentOrderInfo', res)
            }
        },
    }
}

export default user