import {
    addRoomAPI,
    addHotelAPI,
    deleteHotelAPI,
} from '@/api/hotelManager'
import {
    getAllOrdersByManagerIdAPI,
    cancelOrderAPI,
} from '@/api/order'
import {
    hotelAllCouponsAPI,
    hotelTargetMoneyAPI,
    hotelMultiplyRoomsAPI,
    hotelFestivalAPI,
    hotelBirthdayAPI,
} from '@/api/coupon'
import {
    getHotelsByManagerIdAPI,
} from "@/api/hotel"
import { message } from 'ant-design-vue'


const hotelManager = {
    state: {
        activeManagerId: 0,
        orderList: [],
        // 添加酒店参数
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'XiDan',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
            hotelService: [],
        },
        addHotelModalVisible: false,
        // 添加房间参数
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            // roomService: [],
            total: 0,
            curNum: 0,
        },
        addRoomModalVisible: false,
        couponVisible: false,
        addCouponVisible: false,
        // 当前操作的酒店id
        activeHotelId: 0,
        // 当前操作的酒店的所有coupon
        couponList: [],
        hotelVisible:false,
    },
    mutations: {
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function(state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_hotelVisible: function(state,data){
          state.hotelVisible = data
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible = data
        },
        set_activeManagerId: function (state, data) {
            state.activeManagerId= data
        }
    },
    actions: {
        // 获取这个酒店管理员所管辖的酒店的所有订单
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersByManagerIdAPI(state.activeManagerId)
            if(res){
                commit('set_orderList', res)
            }
        },
        addHotel: async({ state, dispatch, commit }) => {
            const res = await addHotelAPI(state.addHotelParams)
            if(res){
                dispatch('getHotels')
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion:'XiDan',
                    hotelStar:'',
                    rate: 0,
                    description:'',
                    phoneNum:'',
                    managerId:'',
                    hotelService: []
                })
                commit('set_addHotelModalVisible', false)
                message.success('添加成功')
            }
            else{
                message.error('添加失败')
            }
        },
        // 获取这个管理员管辖的所有酒店
        getHotels: async({ state, commit }) => {
            const res = await getHotelsByManagerIdAPI(state.activeManagerId)
            if(res){
                commit('set_hotelList', res)
                console.log(state.hotelList)
            }
        },
        deleteHotel: async({ state, dispatch }, hotelId) => {
            const res = await deleteHotelAPI(hotelId)
            if(res) {
                dispatch('getHotels')
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        },
        deleteOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getAllOrders')
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            const res = await addRoomAPI(state.addRoomParams)
            if(res){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    // roomService: [],
                    total: 0,
                    curNum: 0,
                })
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        // 获取当前酒店的所有coupon
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId)
            if(res) {
                commit('set_couponList',res)
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
            }
        },
        addHotelTargetMoneyCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetMoneyAPI(data)
            if(res){
                commit('set_addCouponVisible',false)
                message.success('添加成功')
                dispatch('getHotelCoupon')
                commit('set_couponVisible',true)
            }else{
                message.error('添加失败')
            }
        },
        addHotelMultiplyRoomsCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelMultiplyRoomsAPI(data)
            if(res){
                commit('set_addCouponVisible',false)
                message.success('添加成功')
                dispatch('getHotelCoupon')
                commit('set_couponVisible',true)
            }else{
                message.error('添加失败')
            }
        },
        addHotelFestivalCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelFestivalAPI(data)
            if(res){
                commit('set_addCouponVisible',false)
                message.success('添加成功')
                dispatch('getHotelCoupon')
                commit('set_couponVisible',true)
            }else{
                message.error('添加失败')
            }
        },
        addHotelBirthdayCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelBirthdayAPI(data)
            if(res){
                commit('set_addCouponVisible',false)
                message.success('添加成功')
                dispatch('getHotelCoupon')
                commit('set_couponVisible',true)
            }else{
                message.error('添加失败')
            }
        }
    }
}
export default hotelManager