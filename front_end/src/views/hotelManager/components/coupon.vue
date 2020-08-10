<!--
    查看酒店优惠策略表格组件
    可以添加优惠策略
-->
<template>
   <div>
    <a-modal
        :visible="couponVisible"
        title="优惠策略"
        width="1200px"
        :footer="null"
        @cancel="cancel"
    >
        <div style="width: 100%; text-align: right; margin:20px 0">
            <a-button type="primary" @click="addCoupon">
                添加优惠策略
            </a-button>
        </div>
        <a-table bordered :dataSource="couponList" :columns="columns">
        </a-table>
    </a-modal>
       <AddCoupon></AddCoupon>
   </div>
</template>
<script>

import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddCoupon from './addCoupon'

const columns = [
    {
        title:'优惠类型',
        dataIndex:'couponType',
    },
    {
        title:'优惠券名',
        dataIndex:'couponName',
    },
    {
        title: '优惠简介',
        dataIndex: 'description'
    },
    {
        title: '优惠间数',
        dataIndex: 'roomNum'
    },
    {
        title: '达标金额',
        dataIndex: 'targetMoney'
    },
    {
        title: '折扣',
        dataIndex: 'discount'
    },
    {
        title: '优惠金额',
        dataIndex: 'discountMoney'
    },
    {
        title: '开始时间',
        dataIndex: 'startTime'
    },
    {
        title: '结束时间',
        dataIndex: 'endTime'
    }
  ];

export default {
    name: 'coupon',
    data() {
        return {
            columns
        }
    },
    components: {
        AddCoupon,
    },
    computed: {
        ...mapGetters([
            'couponVisible',
            'couponList'
        ])
    },
    methods: {
        ...mapMutations([
            'set_addCouponVisible',
            'set_couponVisible'
        ]),
        ...mapActions([
            'getHotelCoupon'
        ]),
        cancel() {
            this.set_couponVisible(false)
        },
        addCoupon() {
            this.set_addCouponVisible(true),
            this.set_couponVisible(false)
        },
    }
}
</script>
<style scoped>

</style>