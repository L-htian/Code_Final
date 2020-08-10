<!--酒店订单表格组件
    主要显示酒店相关的订单信息-->
<template>
    <div class="hotel-orders">
        <div class="list">
            <a-table
                    :columns="columns"
                    :dataSource="hotelOrders"
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
            </a-table>
        </div>
    </div>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
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
            title: '状态',
            filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已执行', value: '已执行' }],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: { customRender: 'orderState' }
        },
    ];
    export default {
        name: "hotelOrders",
        props: {
            // orders: {
            //     type: Array
            // }
        },
        data(){
            return {
                columns,
            }
        },
        components: {

        },
        computed: {
            ...mapGetters([
                'hotelOrders'
            ])
        },
        mounted() {

        },
        methods: {

        }
    }
</script>

<style scoped>

</style>