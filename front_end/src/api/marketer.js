import { axios } from '@/utils/request'

const api = {
    marketerPre:'/api/user'
}

export function creditDepositAPI(data) { // 信用充值
    console.log(data)
    return axios({
        url:`${api.marketerPre}/creditDeposit`,
        method:`POST`,
        data
    })
}
export function setVipAPI(data) { // 会员充值
    return axios({
        url:`${api.marketerPre}/setVip`,
        method:`POST`,
        data
    })
}