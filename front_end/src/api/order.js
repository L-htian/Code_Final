import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) { // 预定酒店
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() { // 获取所有订单
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) { // 获取用户的所有订单
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) { // 撤销订单
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
export function hotelOrdersAPI(data) { // 获取用户在某个酒店的所有订单
    return axios({
        url:`${api.orderPre}/${data.userId}/getHotelOrders`,
        method:'GET',
        params: {hotelId: data.hotelId},
    })
}
export function rateHotelAPI(data) { // 评价酒店
    return axios({
        url: `${api.orderPre}/${data.id}/rateHotel`,
        method:'POST',
        data,
    })
}
export function getOrderByIdAPI(params) { // 通过id获取订单
    return axios({
        url: `${api.orderPre}/${params.id}/orderDetail`,
        method: 'GET',
    })
}
export function getAllOrdersByManagerIdAPI(managerId) { // 获取酒店管理人员管理的酒店的所有订单
    return axios({
        url:`${api.orderPre}/${managerId}/getAllOrdersByManagerId`,
        method:'GET',
    })
}
export function getCommentsByHotelId(hotelId) { // 获取酒店的所有评论
    return axios({
        url:`${api.orderPre}/${hotelId}/getCommentsByHotelId`,
        method:'GET',
    })
}
