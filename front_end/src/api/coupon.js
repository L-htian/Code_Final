import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelTargetMoneyAPI(data) { // ��ӾƵ������Ż�
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}

export function hotelMultiplyRoomsAPI(data) { // ��ӾƵ����Ż�
    return axios({
        url: `${api.couponPre}/hotelMultiplyRooms`,
        method: 'POST',
        data,
    })
}

export function hotelFestivalAPI(data) { // ��ӾƵ���ʱ�Ż�
    return axios({
        url: `${api.couponPre}/hotelFestival`,
        method: 'POST',
        data,
    })
}

export function hotelBirthdayAPI(data) { // ��ӾƵ������Ż�
    return axios({
        url: `${api.couponPre}/hotelBirthday`,
        method: 'POST',
        data,
    })
}

export function hotelAllCouponsAPI(hotelId) { // ��ȡ�Ƶ������Ż�
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}

export function orderMatchCouponsAPI(params) { // ��ȡ��ǰ�����������������Ż�
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}