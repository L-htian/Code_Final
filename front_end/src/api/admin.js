import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(){   //获得工作人员信息
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {  //添加工作人员
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function getUserListAPI() {   //获得用户信息
    return axios({
        url:`${api.adminPre}/getAllUsers`,
        method: 'POST'
    })
}
export function addUserAPI(data) {   //添加用户
    return axios({
        url:`${api.adminPre}/addUser`,
        method: 'POST',
        data
    })
}
export function getMarketerListAPI() {   //获得营销人员
    return axios({
        url:`${api.adminPre}/getAllMarketers`,
        method:'POST'
    })
}
export function addMarketerAPI(data) {  //添加营销人员
    return axios({
        url:`${api.adminPre}/addMarketer`,
        method:'POST',
        data
    })
}
export function deleteUserAPI(userId) {  //删除用户
    return axios({
        url:`${api.adminPre}/${userId}/deleteUser`,
        method:'GET',
    })
}