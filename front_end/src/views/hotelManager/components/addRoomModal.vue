<!--录入客房表单组件
    可以给酒店录入新的客房-->
<template>
    <a-modal
        :visible="addRoomModalVisible"
        title="录入客房"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="房型" v-bind="formItemLayout">
                <a-select
                    v-decorator="[
                    'roomType', 
                    { rules: [{ required: true, message: '请选择房型' }] }]"
                >
                    <a-select-option value="BigBed">大床房</a-select-option>
                    <a-select-option value="DoubleBed">双床房</a-select-option>
                    <a-select-option value="Family">家庭房</a-select-option>
                    <a-select-option value="Business">商务房</a-select-option>
                    <a-select-option value="President">豪华总统套房</a-select-option>
                    <a-select-option value="Loft">阁楼房</a-select-option>
                    <a-select-option value="ESports">电竞房</a-select-option>
                    <a-select-option value="Hour">钟点房</a-select-option>
                    <a-select-option value="OceanView">海景房</a-select-option>
                    <a-select-option value="RiverView">江景房</a-select-option>
                    <a-select-option value="LakeView">湖景房</a-select-option>
                    <a-select-option value="Lovers">情侣房</a-select-option>
                </a-select>
            </a-form-item>
<!--            <a-form-item v-bind="formItemLayout">-->
<!--                <a-checkbox-group>-->
<!--                    <a-row>-->
<!--                        <a-col span="8">-->
<!--                            <a-checkbox value="WiFi" v-model="roomService">-->
<!--                                Wifi-->
<!--                            </a-checkbox>-->
<!--                        </a-col>-->
<!--                        <a-col span="8">-->
<!--                            <a-checkbox value="HotWater" v-model="roomService">-->
<!--                                热水-->
<!--                            </a-checkbox>-->
<!--                        </a-col>-->
<!--                        <a-col span="8">-->
<!--                            <a-checkbox value="WakeUp" v-model="roomService">-->
<!--                                叫醒服务-->
<!--                            </a-checkbox>-->
<!--                        </a-col>-->
<!--                    </a-row>-->
<!--                </a-checkbox-group>-->
<!--            </a-form-item>-->
            <a-form-item label="房间数量" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写房间数量"
                    v-decorator="['roomNum', { rules: [{ required: true, message: '请输入房间数量' }, { validator: this.handleNumberAndPrice }], validateTrigger: 'blur'}]"
                />
            </a-form-item>
            <a-form-item label="原始价格" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写原始价格"
                    v-decorator="['price', { rules: [{ required: true, message: '请输入原始价格' }, { validator: this.handleNumberAndPrice }], validateTrigger: 'blur'}]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addRoomModal',
    data() {
        return {
            // roomService:[],
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
            'addRoomModalVisible',
            'activeHotelId'
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addRoomModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addRoomModalVisible',
            'set_addRoomParams'
        ]),
        ...mapActions([
            'addRoom'
        ]),
        cancel() {
            this.set_addRoomModalVisible(false)
        },
        handleNumberAndPrice(rule, value, callback){
            if(isNaN(value)){
                callback(new Error('请输入数字'));
            } else if(Number(value)<=0||!Number.isInteger(Number(value))){
                callback(new Error('请输入正整数'));
            }else {
                callback();
            }
            callback()
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        roomType: this.form.getFieldValue('roomType'),
                        price: Number(this.form.getFieldValue('price')),
                        total: Number(this.form.getFieldValue('roomNum')),
                        curNum: Number(this.form.getFieldValue('roomNum')),
                        // roomService: this.roomService,
                        hotelId: this.activeHotelId
                    }
                    this.set_addRoomParams(data)
                    this.addRoom()
                }
            });
        },
    }
}
</script>