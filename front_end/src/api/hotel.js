import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {   //获得酒店列表
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'GET',
    })
}
export function getHotelByIdAPI(param) {  //根据id获得酒店
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}
export function getHotelsByManagerIdAPI(managerId) {  //根据工作人员id获得酒店
    return axios({
        url: `${api.hotelPre}/${managerId}/getHotelsByManagerId`,
        method: 'GET',
    })
}

