<!--管理用户组件
    可以对网站使用人员的账户进行管理-->
<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店管理人员账户管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager"><a-icon type="plus" />添加用户</a-button>
                </div>
                <a-table
                    :columns="columnsManager"
                    :dataSource="managerList"
                    bordered
                 >
                        <span slot="action" slot-scope="record">
                            <a-popconfirm
                                    title="你确定删除该用户吗？"
                                    @confirm="confirmDeleteManager(record.id)"
                                    @cancel="cancelDeleteUser"
                                    okText="确定"
                                    cancelText="取消"
                            >
                            <a-button type="danger" size="small">删除用户</a-button>
                        </a-popconfirm>
                        </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="用户账户管理" key="2">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addUser"><a-icon type="plus" />添加用户</a-button>
                </div>
                <a-table
                        :columns="columnsUser"
                        :dataSource="userList"
                        bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                                title="你确定删除该用户吗？"
                                @confirm="confirmDeleteUser(record.id)"
                                @cancel="cancelDeleteUser"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除用户</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="网站营销人员管理" key="3">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addMarketer"><a-icon type="plus" />添加用户</a-button>
                </div>
                <a-table
                        :columns="columnsManager"
                        :dataSource="marketerList"
                        bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                                title="你确定删除该用户吗？"
                                @confirm="confirmDeleteMarketer(record.id)"
                                @cancel="cancelDeleteUser"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除用户</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
    </a-tabs>
        <AddManagerModal></AddManagerModal>
        <AddUserModal></AddUserModal>
        <AddMarketerModal></AddMarketerModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddManagerModal from './components/addManagerModal'
import AddUserModal from './components/addUserModal'
import AddMarketerModal from './components/addMarketerModal'
const columnsManager = [
    {  
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];

const columnsUser = [
    {
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '信用值',
        dataIndex: 'credit',
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columnsUser,
            columnsManager,
            data: [],
        }
    },
    components: {
        AddManagerModal,
        AddUserModal,
        AddMarketerModal
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'managerList',
            'addUserModalVisible',
            'userList',
            'addMarketerModalVisible',
            'marketerList'
        ])
    },
    mounted() {
        this.getManagerList()
        this.getUserList()
        this.getMarketerList()
    },
    methods: {
        ...mapActions([
            'getManagerList',
            'getUserList',
            'getMarketerList',
            'deleteUser',
            'deleteManager',
            'deleteMarketer'
        ]),
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_addUserModalVisible',
            'set_addMarketerModalVisible'
        ]),
        addManager(){  //添加管理人员
            this.set_addManagerModalVisible(true)
        },
        addUser(){  //添加用户
            this.set_addUserModalVisible(true)
        },
        addMarketer(){ //添加营销人员
            this.set_addMarketerModalVisible(true)
        },
        confirmDeleteUser(userId){  //确定删除用户后的操作
            this.deleteUser(userId)
        },
        confirmDeleteManager(userId){ //确定删除管理人员后的操作
            this.deleteManager(userId)
        },
        confirmDeleteMarketer(userId){ //确定删除营销人员后的操作
            this.deleteMarketer(userId)
        },
        cancelDeleteUser(){

        }
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>

<style lang="less">
    
</style>