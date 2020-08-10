import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function addRoomAPI(data) { // Ìí¼Ó·¿¼ä
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) { // Ìí¼Ó¾Æµê
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}
export function deleteHotelAPI(hotelId) { // É¾³ý¾Æµê
    return axios({
        url:`${api.hotelPre}/${hotelId}/deleteHotel`,
        method: 'GET',
    })
}

