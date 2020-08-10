<!--添加营销人员表单组件
    可以添加营销人员-->
<template>
    <a-modal
            :visible="addMarketerModalVisible"
            title="添加用户"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="用户邮箱">
                <a-input
                        v-decorator="[
                        'email',
                        { rules: [{required: true, message: '请输入用户邮箱'}, { validator: this.handleEmail }], validateTrigger: 'blur'}
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                        v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码'}] }
                    ]"
                />
            </a-form-item >
        </a-Form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addMarketerModal',
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
            }
        },
        computed: {
            ...mapGetters([
                'addMarketerModalVisible',
                'marketerList',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addMarketerModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addMarketerModalVisible',
                'set_addMarketerParams'
            ]),
            ...mapActions([
                'getMarketerList',
                'addMarketer'
            ]),
            cancel() { //取消操作
                this.set_addMarketerModalVisible(false)
            },
            handleEmail(rule, value, callback) { //邮箱格式判断
                const re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
                if (re.test(value)) {
                    callback();
                } else {
                    callback(new Error('请输入有效邮箱'));
                }
                callback()
            },
            handleSubmit(e) {  //提交操作
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            email: this.form.getFieldValue('email'),
                            password: this.form.getFieldValue('password')
                        }
                        this.set_addMarketerParams(data)
                        this.addMarketer()
                    }
                });
            },
        }
    }
</script>