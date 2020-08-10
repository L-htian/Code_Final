<!--
    酒店管理人员管理页面
    使用tab可以在酒店管理，订单管理页面来回切换
-->
<template>
    <div class="manageHotel-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店管理" key="1">
                <!--
                    酒店管理页面
                    可以添加酒店
                    可以查看属于该管理员管辖的所有酒店
                    对酒店可以录入房间或更改现有房间、查看优惠策略、删除
                -->
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"><a-icon type="plus" />添加酒店</a-button>
                </div>
                 <a-table
                    :columns="columns1"
                    :dataSource="hotelList"
                    bordered
                >
                     <span slot="hotelStar" slot-scope="text">
                         <span>{{text=='Five'?'五星级':text=='Four'?'四星级':'三星级'}}</span>
                     </span>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="addRoom(record)">录入房间或更改现有房间</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="info" size="small" @click="showCoupon(record)">优惠策略</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想删除该酒店吗？"
                            @confirm="confirmDeleteHotel(record.id)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除酒店</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="订单管理" key="2">
                <!--
                    订单管理页面
                    可以查看属于该酒店管理员管辖的所有酒店的所有订单
                    对于这些订单可以查看详情、删除
                -->
                <a-table
                    :columns="columns2"
                    :dataSource="orderList"
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
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="checkOrderDetail(record.id)">订单详情</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想删除该订单吗？"
                            @confirm="confirmDeleteOrder(record.id)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除订单</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddHotelModal></AddHotelModal>
        <AddRoomModal></AddRoomModal>
        <Coupon></Coupon>
        <OrderDetail></OrderDetail>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddHotelModal from './components/addHotelModal'
import AddRoomModal from './components/addRoomModal'
import Coupon from './components/coupon'
import OrderDetail from '../user/components/orderDetail'
const columns1 = [
    {  
        title: '酒店名',
        dataIndex: 'name',
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
    },
    {
        title: '地址',
        dataIndex: 'address',
    },
    {
        title: '酒店星级',
        dataIndex: 'hotelStar',
        key:'hotelStar',
        scopedSlots:{customRender:'hotelStar'},
    },
    {
        title: '评分',
        dataIndex: 'rate',
    },
    {
        title: '简介',
        dataIndex: 'description',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
const columns2 = [
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
        key:'roomType',
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
        key:'price',
        scopedSlots: { customRender: 'price'},
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
            columns1,
            columns2,
            form: this.$form.createForm(this, { name: 'manageHotel' }),
        }
    },
    components: {
        AddHotelModal,
        AddRoomModal,
        Coupon,
        OrderDetail
    },
    computed: {
        ...mapGetters([
            'userId',
            'orderList',
            'hotelList',
            'addHotelModalVisible',
            'addRoomModalVisible',
            'activeHotelId',
            'couponVisible',
            // 'currentHotelInfo'
        ]),
    },
    async mounted() {
        // this.set_currentHotelId(Number(this.$route.params.hotelId))
        // this.getHotelById()
        this.set_activeManagerId(this.userId)
        await this.getHotels()
        await this.getAllOrders()
    },
    // beforeRouteUpdate(to, from, next) {
    //     this.set_currentHotelId(Number(to.params.hotelId))
    //     this.getHotelById()
    //     next()
    // },
    methods: {
        ...mapMutations([
            'set_addHotelModalVisible',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_activeHotelId',
            'set_activeManagerId',
            'set_currentOrderId',
            'set_orderDetailVisibleOfUser',
            // 'set_currentHotelId'
        ]),
        ...mapActions([
            'getHotels',
            'getAllOrders',
            'getHotelCoupon',
            'deleteHotel',
            'deleteOrder',
            'getOrderById',
            // 'getHotelById',
        ]),
        addHotel() {
            this.set_addHotelModalVisible(true)
        },
        addRoom(record) {
            this.set_activeHotelId(record.id)
            this.set_addRoomModalVisible(true)
        },
        showCoupon(record) {
            this.set_activeHotelId(record.id)
            this.set_couponVisible(true)
            this.getHotelCoupon()
        },
        confirmDeleteHotel(hotelId){
            this.deleteHotel(hotelId)
        },
        confirmDeleteOrder(orderId){
            this.deleteOrder(orderId)
        },
        checkOrderDetail(orderId){
            this.set_currentOrderId(Number(orderId))
            this.getOrderById()
            this.set_orderDetailVisibleOfUser(true)
        }
    }
}
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
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
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>