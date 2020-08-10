<!--
    订单详情组件
    显示订单的信息
-->
<template>
    <a-modal
            :visible="orderDetailVisibleOfUser"
            cancelText="取消"
            @cancel="cancel"
            okText="确定"
            @ok="cancel"
            >
        <a-card title="订单详情" :bordered="false" style="width: 400px">
            <p>酒店名称：{{currentOrderInfo.hotelName}}</p>
            <p>有无儿童：{{currentOrderInfo.haveChild==true?'有':'没有'}}</p>
            <p>入住时间：{{currentOrderInfo.checkInDate}}</p>
            <p>离开时间：{{currentOrderInfo.checkOutDate}}</p>
            <p>房间类型：{{currentOrderInfo.roomType}}</p>
            <p>房间数量：{{currentOrderInfo.roomNum}}</p>
            <p>入住人数：{{currentOrderInfo.peopleNum}}</p>
            <p>创建时间：{{currentOrderInfo.createDate}}</p>
            <p>订单总额：{{currentOrderInfo.price}}</p>
            <p>订单状态：{{currentOrderInfo.orderState}}</p>
            <!--如果已撤销就显示撤销时间-->
            <p v-if="currentOrderInfo.orderState=='已撤销'">撤销时间：{{currentOrderInfo.annulDate}}</p>
            <!--如果有评分和评论就显示-->
            <p v-if="currentOrderInfo.isRating">评分：{{currentOrderInfo.rate}}</p>
            <p v-if="currentOrderInfo.isRating">评论：{{currentOrderInfo.comment}}</p>
        </a-card>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: "orderDetail",
    data() {
        return{}
    },
    computed: {
        ...mapGetters([
            'currentOrderInfo',
            'orderDetailVisibleOfUser'
        ])
    },
    methods:{
        ...mapMutations([
            'set_orderDetailVisibleOfUser'
        ]),
        cancel(){
            this.set_orderDetailVisibleOfUser(false)
        }
    }
}
</script>

<style scoped>

</style>