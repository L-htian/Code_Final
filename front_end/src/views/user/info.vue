<!--
    用户个人中心页面
    可以使用tab在“我的信息”“我的订单”页面来回切换
-->
<template>
    <div class="info-wrapper">
        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">
                <!--
                    我的信息页面是一个表单
                    包含显示个人信息和更改个人信息（只有用户名，手机号，密码可以更改）
                -->
                <a-form :form="form" style="margin-top: 30px">
                    
                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                            placeholder="请填写用户名"
                            v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.email }}</span>
                    </a-form-item>
                    
                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                            placeholder="请填写手机号"
                            v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modify">
                        <a-input
                            placeholder="请输入新密码"
                            v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }, { validator: this.handlePassword }], validateTrigger: 'blur'}]"
                        />
                    </a-form-item>
                    <a-form-item label="确认密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modify">
                        <a-input
                            placeholder="确认密码"
                            v-decorator="['passwordConfirm', {rules: [{ required: true, message: '请输入确认新密码' }, { validator: this.handlePasswordCheck }], validateTrigger: 'blur'}]"
                        >
                        </a-input>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                     <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                        <a-button type="primary" @click="modifyInfo">
                            修改信息
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2">
                <!--
                    我的订单页面是一个列表
                    显示该用户的所有订单信息
                    同时可以对订单进行查看，撤销，评分操作
                -->
                <a-table
                    :columns="columns"
                    :dataSource="userOrderList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                        <span v-if="text == 'Business'">商务房</span>
                        <span v-if="text == 'President'">豪华总统套房</span>
                        <span v-if="text == 'Loft'">阁楼房</span>
                        <span v-if="text == 'ESports'">电竞房</span>
                        <span v-if="text == 'Hour'">钟点房</span>
                        <span v-if="text == 'OceanView'">海景房</span>
                        <span v-if="text == 'RiverView'">江景房</span>
                        <span v-if="text == 'LakeView'">湖景房</span>
                        <span v-if="text == 'Lovers'">情侣房</span>
                    </span>
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="checkOrderDetail(record.id)">查看</a-button>
                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>
                        <a-popconfirm
                            title="你确定撤销该笔订单吗？"
                            @confirm="confirmCancelOrder(record.id)"
                            @cancel="cancelCancelOrder"
                            okText="确定"
                            cancelText="取消"
                            v-if="record.orderState == '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical" v-if="record.orderState == '已执行' && !record.isRating"></a-divider>
                        <a-button type="primary" size="small" v-if="record.orderState == '已执行' && !record.isRating" @click="rateHotelById(record.id)">评价</a-button>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <OrderDetail></OrderDetail>
        <RateHotel></RateHotel>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import OrderDetail from './components/orderDetail'
import RateHotel from './components/rateHotel'
const columns = [
    {  
        title: '订单号',
        dataIndex: 'id',
    },
    {  
        title: '酒店名',
        dataIndex: 'hotelName',
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        dataIndex: 'price',
    },
    {
        // 对状态进行筛选
        title: '状态',
        filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已执行', value: '已执行' }],
        onFilter: (value, record) => record.orderState.includes(value),
        dataIndex: 'orderState',
        scopedSlots: { customRender: 'orderState' }
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name: 'info',
    data(){
        return {
            modify: false,
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
            form: this.$form.createForm(this, { name: 'coordinated' }),
        }
    },
    components: {
        OrderDetail,
        RateHotel
    },
    computed: {
        ...mapGetters([
            'userId',
            'userInfo',
            'userOrderList'
        ])
    },
    async mounted() {
        await this.getUserInfo()
        await this.getUserOrders()
    },
    methods: {
        ...mapMutations([
            'set_rateHotelVisibleOfUser',
            'set_rateOrderId',
            'set_orderDetailVisibleOfUser',
            'set_currentOrderId',
            'set_currentOrderInfo'
        ]),
        ...mapActions([
            'getUserInfo',
            'getUserOrders',
            'updateUserInfo',
            'cancelOrder',
            'getOrderById'
        ]),
        // 更改密码时，对新密码的检查
        handlePassword(rule, value, callback) {
            if (value.length < 6) {
                callback(new Error('密码长度至少6位'))
            }
            callback()
        },
        // 更改密码时，对确认新密码的检查
        handlePasswordCheck (rule, value, callback) {
            const password = this.form.getFieldValue('registerPassword')
            if (value === undefined) {
                callback(new Error('请输入确认新密码'))
            }
            if (value && password && value.trim() !== password.trim()) {
                callback(new Error('两次密码不一致'))
            }
            callback()
        },
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        userName: this.form.getFieldValue('userName'),
                        phoneNumber: this.form.getFieldValue('phoneNumber'),
                        password: this.form.getFieldValue('password')
                    }
                    this.updateUserInfo(data).then(()=>{
                        this.modify = false
                    })
                }
            });
        },
        modifyInfo() {
            setTimeout(() => {
                this.form.setFieldsValue({
                    'userName': this.userInfo.userName,
                    'phoneNumber': this.userInfo.phoneNumber,
                })
            }, 0)
            this.modify = true
        },
        cancelModify() {
            this.modify = false
        },
        confirmCancelOrder(orderId){
            this.cancelOrder(orderId)
        },
        // 取消撤销订单
        cancelCancelOrder() {

        },
        checkOrderDetail(orderId){
            this.set_currentOrderId(Number(orderId))
            this.getOrderById()
            this.set_orderDetailVisibleOfUser(true)
        },
        rateHotelById(orderId){
            this.set_rateOrderId(Number(orderId))
            this.set_rateHotelVisibleOfUser(true)
        }
    }
}
</script>
<style scoped lang="less">
    .info-wrapper {
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
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>