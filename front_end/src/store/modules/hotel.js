import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
} from '@/api/hotel'
import {
    reserveHotelAPI,
    hotelOrdersAPI,
    getCommentsByHotelId,
} from '@/api/order'
import {
    orderMatchCouponsAPI,
} from '@/api/coupon'

const hotel = {
    state: {
        hotelList: [
            
        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {

        },
        orderModalVisible: false,
        currentOrderRoom: {

        },
        orderMatchCouponList: [

        ],
        hotelOrders: [

        ],
        hotelComments:[

        ]
    },
    mutations: {
        set_hotelList: function(state, data) { //更新酒店列表
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) { //更新酒店数据
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) { //刷新酒店列表
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {  //获得当前酒店id
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) { //获得当前酒店数据
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function(state, data) {  //订单模块可视化
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) { //获得最近的订单房间
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) { //获得与订单相符的优惠策略
            state.orderMatchCouponList = data
        },
        set_hotelOrders: function (state, data) { //获得当前酒店的订单
            state.hotelOrders = data
        },
        set_hotelComments: function (state, data) { //获得酒店评论
            state.hotelComments = data
        }
    },

    actions: {
        getHotelList: async({commit, state}) => { //获得酒店列表
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        getHotelById: async({commit, state}) => { //根据id获得酒店信息
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        addOrder: async({ state, dispatch ,commit }, data) => { //添加订单
            const res = await reserveHotelAPI(data)
            console.log(res)
            if(res){
                commit('set_orderModalVisible', false)
                dispatch('getHotelOrders')
                message.success('预定成功')
            }
            else{
                message.error('预定失败')
            }
        },
        getOrderMatchCoupons: async({ state, commit }, data) => { //获得与订单相符的优惠券
            const res = await orderMatchCouponsAPI(data)
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        },
        getHotelOrders: async ({ state, commit}, userId) => { //获得对应酒店的订单
            const data = {
                userId: Number(userId),
                hotelId: Number(state.currentHotelId)
            }
            const res = await hotelOrdersAPI(data)
            if(res){
                commit('set_hotelOrders', res)
            }else {
                console.log("获取order失败")
            }
        },
        getHotelComments: async ({ state, commit}) => { //获得对应酒店的评论
            const res = await getCommentsByHotelId(state.currentHotelId)
            if(res){
                commit('set_hotelComments', res)
            }
        }
    }
}

export default hotel