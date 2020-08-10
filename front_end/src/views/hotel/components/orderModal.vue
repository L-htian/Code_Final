<!--预订酒店表单组件
    可以预订酒店-->
<template>
    <a-modal
        :visible="orderModalVisible"
        title="预定详情"
        cancelText="取消"
        okText="下单"
        @cancel="cancelOrder"
        @ok="handleSubmit"
    >
        <a-form :form="form">
            <a-form-item v-bind="formItemLayout" label="房型信息">
                <span>{{ currentOrderRoom.roomType }}</span>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="入住人姓名">
                <a-input
                    v-decorator="[
                        'clientName',
                        { rules: [{required: true, message: '请填写入住人姓名', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="手机号">
                <a-input
                    v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请填写入住人联系手机', }] }
                    ]"
                />
            </a-form-item>
            
            <a-form-item v-bind="formItemLayout" label="入住日期">
                <a-range-picker
                    format="YYYY-MM-DD"
                    @change="changeDate"
                    v-decorator="[
                        'date', 
                        {
                            rules: [{ required: true, message: '请选择入住时间' }]   
                        }
                    ]"
                    :placeholder="['入住日期','退房日期']"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="入住人数">
                <a-select
                    v-decorator="[
                        'peopleNum',
                        { rules: [{ required: true, message: '请选择入住人数' }] },
                    ]"
                    placeholder="请选择入住人数"
                    @change="changePeopleNum"
                >
                    <a-select-option :value="1">
                    1
                    </a-select-option>
                    <a-select-option :value="2">
                    2
                    </a-select-option>
                     <a-select-option :value="3">
                    3
                    </a-select-option>
                    <a-select-option :value="4">
                    4
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有无儿童">
                <a-radio-group
                    v-decorator="[
                        'haveChild',
                        { rules: [{required: true, message: '请选择有无儿童入住', }] }
                    ]"
                >
                    <a-radio :value="1">有</a-radio>
                    <a-radio :value="0">无</a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="房间数">
                <a-select
                    v-decorator="[
                        'roomNum',
                        { rules: [{ required: true, message: '请选择房间数' }] },
                    ]"
                    placeholder="请选择房间数"
                    @change="changeRoomNum"
                >
                    <a-select-option :value="1">
                    1
                    </a-select-option>
                    <a-select-option :value="2">
                    2
                    </a-select-option>
                     <a-select-option :value="3">
                    3
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="房间单价">
                <span>{{ currentOrderRoom.price }}</span>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="总价">
                <span>￥{{ totalPrice.toFixed(2) }}</span>
            </a-form-item>
            <a-divider></a-divider>
            <h2 v-if="orderMatchCouponList.length>0">优惠</h2>
            <a-checkbox-group v-model="checkedList" @change="onchange">
                <a-table
                    :columns="columns"
                    :dataSource="orderMatchCouponList"
                    :showHeader="false"
                    bordered
                    v-if="orderMatchCouponList.length>0"
                >
                    <a-checkbox
                        slot="id"
                        slot-scope="record"
                        :value="record"
                    >
                    </a-checkbox>
                </a-table>
            </a-checkbox-group>
             <a-form-item v-bind="formItemLayout" label="结算后总价" v-if="orderMatchCouponList.length>0">
                <span>￥{{ finalPrice.toFixed(2) }}</span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
const moment = require('moment')
const columns = [
    {  
        title: '勾选',
        dataIndex: 'id',
        scopedSlots: {customRender: 'id'}
    },
    {
        title: '优惠券名',
        dataIndex: 'couponName',
        scopedSlots: {customRender: 'couponName'}
    },
    {
        title: '优惠简介',
        dataIndex: 'description',
        
    },
    {
        title: '优惠金额',
        dataIndex: 'discountMoney',
    },
    {
        title: '折扣',
        dataIndex: 'discount',
    },
  ];
export default {
    name: 'orderModal',
    data() {
        return {
            formItemLayout: {
                labelCol: {
                    xs: { span: 12 },
                    sm: { span: 6 },
                },
                wrapperCol: {
                    xs: { span: 24 },
                    sm: { span: 16 },
                },
            },
            totalPrice: 0,
            columns,
            checkedList: [],
            finalPrice: 0
        }
    },
    computed: {
        ...mapGetters([
            'orderModalVisible',
            'currentOrderRoom',
            'currentHotelId',
            'currentHotelInfo',
            'userId',
            'orderMatchCouponList'
        ]),
        
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'orderModal' });
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible'
        ]),
        ...mapActions([
            'addOrder',
            'getOrderMatchCoupons'
        ]),
        cancelOrder() {  //取消下单之后的操作
            this.set_orderModalVisible(false)
        },
        confirmOrder() {

        },
        changeDate(v) {  //更改日期之后算价的操作
            if(this.totalPrice != 0){
                this.totalPrice = this.form.getFieldValue('roomNum') * moment(v[1]).diff(moment(v[0]), 'day') * Number(this.currentOrderRoom.price)
            }
            this.finalPrice = this.totalPrice
        },
        changePeopleNum(v){

        },
        changeRoomNum(v) { //更改房间数目之后算价的操作
            this.totalPrice = Number(v) * Number(this.currentOrderRoom.price) * moment(this.form.getFieldValue('date')[1]).diff(moment(this.form.getFieldValue('date')[0]),'day')
            this.finalPrice = this.totalPrice
        },
        onchange() {  //使用优惠券之后算价的操作
            this.finalPrice = this.totalPrice
            if(this.checkedList.length>0){
                this.orderMatchCouponList.filter(item => this.checkedList.indexOf(item.id)!=-1).forEach(item =>{
                    if(Number(item.couponType)==1||Number(item.couponType)==3){this.finalPrice=this.finalPrice-item.discountMoney}
                    else{this.finalPrice=this.finalPrice*item.discount}
                })
            }else{
                
            }
        },
        handleSubmit(e) {  //提交订单
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        hotelId: this.currentHotelId,
                        hotelName: this.currentHotelInfo.name,
                        userId: Number(this.userId),
                        checkInDate: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                        checkOutDate: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                        roomType: this.currentOrderRoom.roomType == '大床房' ? 'BigBed' : this.currentOrderRoom.roomType == '双床房' ? 'DoubleBed' : this.currentOrderRoom.roomType == '家庭房' ? 'Family' : this.currentOrderRoom.roomType == '商务房' ? 'Business' : this.currentOrderRoom.roomType == '豪华总统套房' ? 'President': this.currentOrderRoom.roomType == '阁楼房' ? 'Loft' : this.currentOrderRoom.roomType == '电竞房' ? 'ESports' : this.currentOrderRoom.roomType == '钟点房' ? 'Hour' : this.currentOrderRoom.roomType == '海景房' ? 'OceanView':this.currentOrderRoom.roomType == '江景房'? 'RiverView': this.currentOrderRoom.roomType == '湖景房' ? 'LakeView':'Lovers',
                        roomNum: this.form.getFieldValue('roomNum'),
                        peopleNum: this.form.getFieldValue('peopleNum'),
                        haveChild: this.form.getFieldValue('haveChild'),
                        createDate: '',
                        price: this.checkedList.length > 0 ? this.finalPrice: this.totalPrice
                    }
                    this.addOrder(data)
                }
            });
        },
    },
    watch:{
        totalPrice(val) {
            let data = {
                userId: this.userId,
                hotelId: this.currentHotelId,
                orderPrice: this.totalPrice,
                roomNum: this.form.getFieldValue('roomNum'),
                checkIn: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                checkOut: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
            }
            this.getOrderMatchCoupons(data)
            this.checkedList.forEach(item=>{item=-1})
        }
    }
}
</script>