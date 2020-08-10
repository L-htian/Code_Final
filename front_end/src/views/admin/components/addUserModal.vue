<!--添加用户表单组件
    可以添加用户-->
<template>
    <a-modal
            :visible="addUserModalVisible"
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
                        { rules: [{required: true, message: '请输入用户邮箱'}, { validator: this.handleEmail }], validateTrigger: 'blur' }
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
            <a-form-item v-bind="formItemLayout" label="用户名">
                <a-input
                        v-decorator="[
                        'username',
                        { rules: [{required: true, message: '请输入用户名'}] }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="手机号">
                <a-input
                        v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请输入手机号'}] }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="信用值">
                <a-input
                        v-decorator="[
                        'credit',
                        { rules: [{required: true, message: '请输入信用值'}, { validator: this.handleCredit }], validateTrigger: 'blur'}
                    ]"
                />
            </a-form-item >
        </a-Form>
    </a-modal>
</template><script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addUserModal',
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
            'addUserModalVisible',
            'userList'
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addUserModal' });
    },
    mounted() {

    },
       methods: {
           ...mapMutations([
               'set_addUserModalVisible',
               'set_addUserParams'
           ]),
           ...mapActions([
               'getUserList',
               'addUser'
           ]),
           cancel() { //取消操作
               this.set_addUserModalVisible(false)
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
           handleCredit(rule, value, callback) {  //信用值格式判断
               if (isNaN(value)){
                   callback(new Error('信用值必须是数字！'));
               }
               callback()
           },
           handleSubmit(e) {  //提交操作
               e.preventDefault();
               this.form.validateFieldsAndScroll((err, values) => {
                   if (!err) {
                       const data = {
                           email: this.form.getFieldValue('email'),
                           password: this.form.getFieldValue('password'),
                           userName:this.form.getFieldValue('username'),
                           phoneNumber:this.form.getFieldValue('phoneNumber'),
                           credit:Number(this.form.getFieldValue('credit')),
                       }
                       this.set_addUserParams(data)
                       this.addUser()
                   }
                });
            },
        }
    }
</script>