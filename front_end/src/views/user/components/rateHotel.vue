<!--
    评价酒店表单组件
    包含对酒店的评分和评论
-->
<template>
    <a-modal
        :visible="rateHotelVisibleOfUser"
        title="评价酒店"
        cancelText="取消"
        okText="确定"
        @cancel="cancelRate"
        @ok="handleSubmit"
    >
        <a-form :form="form">
            <a-form-item label="评分">
                <a-rate
                        v-decorator="[
                            'rate',
                            {initialValue: 0},
                            {rules: [{ required: true, message: '请填写评分'}]}
                         ]"
                        allow-half
                />
            </a-form-item>
            <a-form-item label="评价">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写评价"
                        v-decorator="['comment', { rules: [{ required: true, message: '请填写评价' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "rateHotel",
        data(){
            return{}
        },
        computed: {
            ...mapGetters([
                'rateOrderId',
                'rateHotelVisibleOfUser',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'rateHotel' });
        },
        methods:{
            ...mapMutations([
                'set_rateHotelVisibleOfUser'
            ]),
            ...mapActions([
                'rateHotel'
            ]),
            cancelRate(){
                this.set_rateHotelVisibleOfUser(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            rate: Number(this.form.getFieldValue('rate')),
                            comment: this.form.getFieldValue('comment'),
                            // 评分和评论与订单绑定
                            id: this.rateOrderId
                        }
                        this.rateHotel(data)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>