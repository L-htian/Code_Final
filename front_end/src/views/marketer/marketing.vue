<!--
    网站营销人员营销选项页面
    使用tab可以在网站优惠策略，信用充值，会员充值页面来回切换
    其中，信用充值和会员充值页面共用一个表单（参考login.vue）
-->
<template>
    <div class="marketing-wrapper">
        <a-form :form="form">
        <a-tabs
        :active-key="customActiveKey"
        @change="handleTabClick"
        >
            <a-tab-pane tab="网站优惠策略" key="1">
                <!--
                    可以通过表格查看网站目前的营销策略
                    可以添加网站营销策略
                -->
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addWebCoupon"><a-icon type="plus" />添加网站优惠策略</a-button>
                </div>
                <a-table bordered :dataSource="marketerCouponList" :columns="columns" >
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="信用充值ֵ" key="2">
                <!--
                    信用充值页面
                    输入目标邮箱和充值额度可对其进行信用充值
                -->
                <a-form-item label="用户邮箱">
                    <a-input
                            size="large"
                            placeholder="请输入用户邮箱"
                            v-decorator="[
                                   'userEmail',
                                   {rules: [{ required: true, message: '请输入用户邮箱' }, { validator: this.handleEmail }], validateTrigger: 'blur'}]">
                    </a-input>
                </a-form-item>
                <a-form-item label="充值额度">
                    <a-input
                            size="large"
                            placeholder="请输入充值额度"
                            v-decorator="[
                                   'deposit',
                                   {rules: [{ required: true, message: '请输入充值额度' }, { validator: this.handleNumber }], validateTrigger: 'blur'}]">
                    </a-input>
                </a-form-item>
                <a-form-item style="margin-top:24px">
                    <a-button
                            size="large"
                            type="primary"
                            class="deposit-button"
                            @click="handleDeposit()"
                    >确定充值</a-button>
                </a-form-item>
            </a-tab-pane>
            <a-tab-pane tab="会员充值" key="3">
                <!--
                    会员充值页面
                    输入目标邮箱和生日可对其进行会员充值
                    会员记录生日
                -->
                <a-form-item label="用户邮箱">
                    <a-input
                            size="large"
                            placeholder="请输入用户邮箱"
                            v-decorator="[
                               'vipEmail',
                               {rules: [{ required: true, message: '请输入用户邮箱' }, { validator: this.handleEmail }], validateTrigger: 'blur'}]">
                    </a-input>
                </a-form-item>
                <a-form-item label="生日">
                    <a-date-picker
                            format="YYYY-MM-DD"
                            size="large"
                            placeholder="请输入用户生日"
                            v-decorator="['birthday',
                               {rules: [{required: true,message:'请输入用户生日'}]}
                               ]"
                    />
                </a-form-item>
                <a-form-item style="margin-top:24px">
                    <a-button
                            size="large"
                            type="primary"
                            class="set-vip-button"
                            @click="handleSetVip()"
                    >确定充值会员</a-button>
                </a-form-item>
            </a-tab-pane>
        </a-tabs>
        </a-form>
        <AddWebCoupon></AddWebCoupon>
    </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddWebCoupon from './components/addWebCouponModal'
const moment = require('moment')
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
        title: '折扣',
        dataIndex: 'discountMoney'
    },
    {
        title: '优惠金额',
        dataIndex: 'discount'
    },
];
 export default {
     name: "marketing",
     components: {
         AddWebCoupon
     },
     data() {
         return {
             customActiveKey: '网站优惠策略',
             form: this.$form.createForm(this),
             columns
         }
     },
     computed: {
         ...mapGetters([
             'marketerCouponList'
         ])
     },
     async mounted() {
         await this.getMarketerCoupon()
     },
     methods: {
         ...mapMutations([
             'set_addWebStrategyModalVisible'
         ]),
         ...mapActions([
             'creditDeposit',
             'getMarketerCoupon',
             'setVip'
         ]),
         addWebCoupon() {
             this.set_addWebStrategyModalVisible(true)
         },
         // 对目标邮箱的检查
         handleEmail(rule, value, callback) {
             const re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
             if (re.test(value)) {
                 callback();
             } else {
                 callback(new Error('请输入有效邮箱'));
             }
             callback()
         },
         // 对充值额度的检查
         handleNumber(rule, value, callback) {
             if (isNaN(value)) {
                 callback(new Error('充值额度必须是正数！'));
             } else if (Number(value) <= 0) {
                 callback(new Error('充值额度必须是正数！'));
             } else {
                 callback();
             }
             callback()
         },
         handleSetVip(){
             this.form.validateFields((err,values) => {
                 if (!err) {
                     const data = {
                         userEmail: this.form.getFieldValue('vipEmail'),
                         birthday: moment(this.form.getFieldValue('birthday')).format('YYYY-MM-DD'),
                     }
                     this.setVip(data)
                 }
             });
         },
         handleDeposit() {
             this.form.validateFields((err, values) => {
                 if (!err) {
                     const data = {
                         userEmail: this.form.getFieldValue('userEmail'),
                         deposit: Number(this.form.getFieldValue('deposit'))
                     }
                     this.creditDeposit(data)
                 }
             });
         },
         handleTabClick (key) {
             this.customActiveKey = key
         },
     }
 }
</script>

<style scoped lang="less">
    .marketing-wrapper {
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
    .marketing-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>