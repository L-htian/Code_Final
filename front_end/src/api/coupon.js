import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelTargetMoneyAPI(data) { // 添加酒店满减优惠
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}

export function hotelMultiplyRoomsAPI(data) { // 添加酒店多间优惠
    return axios({
        url: `${api.couponPre}/hotelMultiplyRooms`,
        method: 'POST',
        data,
    })
}

export function hotelFestivalAPI(data) { // 添加酒店限时优惠
    return axios({
        url: `${api.couponPre}/hotelFestival`,
        method: 'POST',
        data,
    })
}

export function hotelBirthdayAPI(data) { // 添加酒店生日优惠
    return axios({
        url: `${api.couponPre}/hotelBirthday`,
        method: 'POST',
        data,
    })
}

export function hotelAllCouponsAPI(hotelId) { // 获取酒店所有优惠
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}

export function orderMatchCouponsAPI(params) { // 获取当前订单所满足条件的优惠
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}