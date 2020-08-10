import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function addRoomAPI(data) { // ��ӷ���
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) { // ��ӾƵ�
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}
export function deleteHotelAPI(hotelId) { // ɾ���Ƶ�
    return axios({
        url:`${api.hotelPre}/${hotelId}/deleteHotel`,
        method: 'GET',
    })
}

