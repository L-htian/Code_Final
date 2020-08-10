<!--
    添加酒店优惠策略表单组件
    可以添加生日特惠、多间特惠、满减优惠、限时优惠
-->
<template>
    <a-modal
        :visible="addCouponVisible"
        title="添加优惠策略"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form" v-bind="formItemLayout">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'type',
                    { rules: [{ required: true}] }]"
                        @change="changeType"
                >
                    <a-select-option value="1">生日特惠</a-select-option>
                    <a-select-option value="2">多间特惠</a-select-option>
                    <a-select-option value="3">满减优惠</a-select-option>
                    <a-select-option value="4">限时优惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-if="modify">
                <a-input
                        placeholder="请填写优惠券名"
                        v-decorator="['name', { rules: [{ required: true, message: '请填写优惠券名' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout" v-if="modify">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写优惠简介"
                        v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介' }] }]"
                />
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout" v-if="check3">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['targetMoney', { rules: [{ required: true, message: '请填写达标金额' }, { validator: this.handleTargetMoney }], validateTrigger: 'blur'}]"
                />
            </a-form-item>
            <a-form-item label="优惠金额" v-bind="formItemLayout" v-if="checkDiscountMoney">
                <a-input
                        placeholder="请填写优惠金额"
                        v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }, { validator: this.handleDiscountMoney }], validateTrigger: 'blur'}]"
                />
            </a-form-item>
            <a-form-item label="折扣" v-bind="formItemLayout" v-if="checkDiscount">
                <a-input
                        placeholder="请填写折扣"
                        v-decorator="['discount', { rules: [{ required: true, message: '请填写折扣' }, { validator: this.handleDiscount }], validateTrigger: 'blur'}]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="开始/结束日期" v-if="check4">
                <a-range-picker
                        format="YYYY-MM-DD"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: true, message: '请选择开始/结束日期' }]
                        }
                    ]"
                        :placeholder="['开始日期','结束日期']"
                />
            </a-form-item>
            <a-form-item label="达标房间数" v-bind="formItemLayout" v-if="check2">
                <a-select
                        v-decorator="[
                        'roomNum',
                        { rules: [{ required: true, message: '请选择达标房间数' }] },
                    ]"
                        placeholder="请选择达标房间数"
                >
                    <a-select-option :value="1">1</a-select-option>
                    <a-select-option :value="2">2</a-select-option>
                    <a-select-option :value="3">3</a-select-option>
                </a-select>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import { message } from 'ant-design-vue'
const moment = require('moment')
export default {
    name: 'addCouponModal',
    data() {
        return {
            // 判断是否已经选择优惠券种类
            modify: false,
            // 判断优惠券种类是否为生日特惠
            check1: false,
            // 判断优惠券种类是否为多间特惠
            check2: false,
            // 判断优惠券种类是否为满减优惠
            check3: false,
            // 判断优惠券种类是否为限时优惠
            check4: false,
            // 判断优惠券是否包含折扣
            checkDiscount: false,
            // 判断优惠券是否包含优惠金额
            checkDiscountMoney: false,
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
        }
    },
    computed: {
        ...mapGetters([
            'activeHotelId',
            'addCouponVisible',
        ])
    },
    beforeCreate() {
        // 表单名默认为“form”
        this.form = this.$form.createForm(this, { name: 'addCouponModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addCouponVisible'
        ]),
        ...mapActions([
            'addHotelTargetMoneyCoupon',
            'addHotelMultiplyRoomsCoupon',
            'addHotelFestivalCoupon',
            'addHotelBirthdayCoupon'
        ]),
        cancel() {
            this.set_addCouponVisible(false)
        },
        changeType(v){
            if(v=='4'){
                this.modify=true;
                this.check1=false;
                this.check2=false;
                this.check3=false;
                this.check4=true;
                this.checkDiscount=true;
                this.checkDiscountMoney=false;
            }
            else if(v=='3'){
                this.modify=true;
                this.check1=false;
                this.check2=false;
                this.check3=true;
                this.check4=false;
                this.checkDiscount=false;
                this.checkDiscountMoney=true;
            }
            else if(v=='2'){
                this.modify=true;
                this.check1=false;
                this.check2=true;
                this.check3=false;
                this.check4=false;
                this.checkDiscount=true;
                this.checkDiscountMoney=false;
            }
            else if(v=='1'){
                this.modify=true;
                this.check1=true;
                this.check2=false;
                this.check3=false;
                this.check4=false;
                this.checkDiscount=false;
                this.checkDiscountMoney=true;
            }
            else{
                this.$message.warning('请实现')
            }
        },
        // 对达标金额的检查
        handleTargetMoney(rule, value, callback){
            if(isNaN(value)){
                callback(new Error('达标金额必须是正整数！'));
            } else if(Number(value)<=0||!Number.isInteger(Number(value))){
                callback(new Error('达标金额必须是正整数！'));
            }else {
                callback();
            }
            callback()
        },
        // 对折扣的检查
        handleDiscount(rule, value, callback){
            if(isNaN(value)){
                callback(new Error('折扣必须是在0和1之间的小数！'));
            } else if(Number(value)<=0||Number(value)>=1){
                callback(new Error('折扣必须是在0和1之间的小数！'));
            }else {
                callback();
            }
            callback()
        },
        // 对折扣金额的检查
        handleDiscountMoney(rule, value, callback){
            if(isNaN(value)){
                callback(new Error('折扣金额必须是正整数！'));
            } else if(Number(value)<=0||!Number.isInteger(Number(value))){
                callback(new Error('折扣金额必须是正整数！'));
            }else {
                callback();
            }
            callback()
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    if(Number(this.form.getFieldValue('type'))===3){
                        if(Number(this.form.getFieldValue('discountMoney'))>=Number(this.form.getFieldValue('targetMoney'))) {
                            message.error('优惠金额不能大于达标金额！')
                        }
                        else{
                            const data = {
                                type: Number(this.form.getFieldValue('type')),
                                name: this.form.getFieldValue('name'),
                                description: this.form.getFieldValue('description'),
                                targetMoney: Number(this.form.getFieldValue('targetMoney')),
                                discountMoney: Number(this.form.getFieldValue('discountMoney')),
                                hotelId: this.activeHotelId
                            }
                            this.addHotelTargetMoneyCoupon(data)
                        }
                    }
                    else if (Number(this.form.getFieldValue('type'))===2){
                        const data = {
                            type: Number(this.form.getFieldValue('type')),
                            name: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('description'),
                            roomNum: Number(this.form.getFieldValue('roomNum')),
                            discount: Number(this.form.getFieldValue('discount')),
                            hotelId: this.activeHotelId
                        }
                        this.addHotelMultiplyRoomsCoupon(data)
                    }
                    else if(Number(this.form.getFieldValue('type'))===4){
                        const data = {
                            type: Number(this.form.getFieldValue('type')),
                            name: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('description'),
                            startTime: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                            endTime: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                            discount: Number(this.form.getFieldValue('discount')),
                            hotelId: this.activeHotelId
                        }
                        this.addHotelFestivalCoupon(data)
                    }
                    else if(Number(this.form.getFieldValue('type'))===1){
                        if(Number(this.form.getFieldValue('discountMoney'))>=Number(this.form.getFieldValue('targetMoney'))) {
                            message.error('优惠金额不能大于达标金额！')
                        }
                        else{
                            const data = {
                                type: Number(this.form.getFieldValue('type')),
                                name: this.form.getFieldValue('name'),
                                description: this.form.getFieldValue('description'),
                                discountMoney: Number(this.form.getFieldValue('discountMoney')),
                                hotelId: this.activeHotelId
                            }
                            this.addHotelBirthdayCoupon(data)
                        }
                    }
                }
            });
        },
    }
}
</script>