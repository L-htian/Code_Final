import { axios } from '@/utils/request'

const api = {
    marketerPre:'/api/user'
}

export function creditDepositAPI(data) { // ���ó�ֵ
    console.log(data)
    return axios({
        url:`${api.marketerPre}/creditDeposit`,
        method:`POST`,
        data
    })
}
export function setVipAPI(data) { // ��Ա��ֵ
    return axios({
        url:`${api.marketerPre}/setVip`,
        method:`POST`,
        data
    })
}