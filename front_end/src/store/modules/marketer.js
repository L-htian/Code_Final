import { message } from 'ant-design-vue'
import {creditDepositAPI,setVipAPI} from '@/api/marketer'
import {
    hotelAllCouponsAPI,
    hotelFestivalAPI
} from "@/api/coupon"

const marketer = {
    state:{
        // 营销人员的hotelId为-1
        marketerHotelId:-1,
        // 网站优惠策略
        marketerCouponList:[],
        addWebStrategyModalVisible:false
    },
    mutations: {
        set_marketerCouponList: function(state, data) {
            state.marketerCouponList = data
        },
        set_addWebStrategyModalVisible: function(state, data) {
            state.addWebStrategyModalVisible = data
        }
    },
    actions: {
        creditDeposit: async ({ dispatch, commit }, data) => {
            const res = await creditDepositAPI(data)
            if(res){
                message.success('信用充值成功')
            }else{
                message.error('信用充值失败')
            }
        },
        setVip: async({dispatch,commit},data) =>{
            const res = await setVipAPI(data)
            if(res){
                message.success('VIP充值成功')
            }else{
                message.error('VIP充值失败')
            }
        },
        getMarketerCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.marketerHotelId)
            if(res) {
                commit('set_marketerCouponList',res)
            }
        },
        addWebTimeCoupon:async ({ commit, dispatch }, data)=>{
            const res = await hotelFestivalAPI(data);
            if(res) {
                commit('set_addWebStrategyModalVisible', false)
                message.success('添加成功')
                dispatch('getMarketerCoupon')
            }else{
                message.error('添加失败')
            }
        }
    }
}

export default marketer