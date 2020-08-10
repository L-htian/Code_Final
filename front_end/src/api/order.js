import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) { // Ԥ���Ƶ�
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() { // ��ȡ���ж���
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) { // ��ȡ�û������ж���
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) { // ��������
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
export function hotelOrdersAPI(data) { // ��ȡ�û���ĳ���Ƶ�����ж���
    return axios({
        url:`${api.orderPre}/${data.userId}/getHotelOrders`,
        method:'GET',
        params: {hotelId: data.hotelId},
    })
}
export function rateHotelAPI(data) { // ���۾Ƶ�
    return axios({
        url: `${api.orderPre}/${data.id}/rateHotel`,
        method:'POST',
        data,
    })
}
export function getOrderByIdAPI(params) { // ͨ��id��ȡ����
    return axios({
        url: `${api.orderPre}/${params.id}/orderDetail`,
        method: 'GET',
    })
}
export function getAllOrdersByManagerIdAPI(managerId) { // ��ȡ�Ƶ������Ա����ľƵ�����ж���
    return axios({
        url:`${api.orderPre}/${managerId}/getAllOrdersByManagerId`,
        method:'GET',
    })
}
export function getCommentsByHotelId(hotelId) { // ��ȡ�Ƶ����������
    return axios({
        url:`${api.orderPre}/${hotelId}/getCommentsByHotelId`,
        method:'GET',
    })
}
