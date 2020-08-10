import {
    getManagerListAPI,
    addManagerAPI,
    addUserAPI,
    getUserListAPI,
    getMarketerListAPI,
    addMarketerAPI,
    deleteUserAPI
} from '@/api/admin'
import { message } from 'ant-design-vue'
const admin = {
    state: {
        managerList: [],
        addManagerModalVisible: false,
        addManagerParams: {
            email:'',
            password:''
        },
        userList: [],
        addUserModalVisible: false,
        addUserParams: {
            email:'',
            password:'',
            username:'',
            phoneNumber:'',
            credit:'',
        },
        marketerList:[],
        addMarketerModalVisible:false,
        addMarketerParams:{
            email:'',
            password:''
        },
    },
    mutations: {
        set_managerList: function(state, data) {  //更新管理人员数据
            state.managerList = data
        },
        set_userList:function (state,data) {  //更新用户数据
            state.userList = data
        },
        set_marketerList: function (state,data) {  //更新营销人员数据
            state.marketerList = data
        },
        set_addManagerModalVisible: function(state, data) { //管理人员模块可视化
            state.addManagerModalVisible = data
        },
        set_addUserModalVisible: function (state,data) {  //用户模块可视化
            state.addUserModalVisible = data
        },
        set_addMarketerModalVisible:function (state,data) { //营销人员模块可视化
            state.addMarketerModalVisible = data
        },
        set_addManagerParams: function(state, data) {  //添加管理人员数据
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_addUserParams: function (state,data) {     //添加用户数据
            state.addUserParams = {
                ...state.addUserParams,
                ...data,
            }
        },
        set_addMarketerParams:function (state,data) {   //添加营销人员数据
            state.addMarketerParams = {
                ...state.addMarketerParams,
                ...data,
            }
        }
    },
    actions: {
        getManagerList: async({ commit }) => {  //获得管理人员数据
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        getUserList: async ({commit})=>{    //获得用户数据
            const res = await getUserListAPI()
            if(res){
                commit('set_userList',res)
            }
        },
        getMarketerList:async({commit}) => {   //获得营销人员数据
            const res = await getMarketerListAPI()
            if(res){
                commit(`set_marketerList`,res)
            }
        },
        addManager: async({ state, commit, dispatch }) => {  //添加管理人员
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    email:'',
                    password:''
                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        addUser: async ({state,commit,dispatch}) => {  //添加用户
            const res = await addUserAPI(state.addUserParams)
            if(res){
                commit('set_addUserParams',{
                    email:'',
                    password:'',
                    username:'',
                    phoneNumber:'',
                    credit:''
                })
                commit('set_addUserModalVisible',false)
                message.success(('添加成功'))
                dispatch('getUserList')
            }else{
                message.error('添加失败')
            }
        },
        addMarketer: async({ state, commit, dispatch }) => { //添加营销人员
            const res = await addMarketerAPI(state.addMarketerParams)
            if(res) {
                commit('set_addMarketerParams',{
                    email:'',
                    password:''
                })
                commit('set_addMarketerModalVisible', false)
                message.success('添加成功')
                dispatch('getMarketerList')
            }else{
                message.error('添加失败')
            }
        },
        deleteUser: async({ state, dispatch }, userId) => { //删除用户
            const res = await deleteUserAPI(userId)
            if(res) {
                dispatch('getUserList')
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        },
        deleteManager: async({ state, dispatch }, userId) => { //删除管理人员
            const res = await deleteUserAPI(userId)
            if(res) {
                dispatch('getManagerList')
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        },
        deleteMarketer: async({ state, dispatch }, userId) => { //删除营销人员
            const res = await deleteUserAPI(userId)
            if(res) {
                dispatch('getMarketerList')
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        }
    }
}
export default admin