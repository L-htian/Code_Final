<!--酒店评论表格组件
    显示酒店相关的用户评论-->
<template>
    <div class="hotel-comments">
        <a-table
                :columns="columns"
                :dataSource="hotelComments"
                bordered
        >
            <span slot="comment" slot-scope="text">
                <span>{{ text }}</span>
            </span>
            <span slot="rate" slot-scope="text">
                <span>{{ text }}</span>
            </span>
        </a-table>
<!--        <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="comments">-->
<!--            <div slot="footer"><b>尊敬的用户</b> 欢迎您的到来</div>-->
<!--            <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">-->
<!--                <template v-for="{ type, text } in actions" slot="actions">-->
<!--        <span :key="type">-->
<!--          <a-icon :type="type" style="margin-right: 8px" />-->
<!--          {{ text }}-->
<!--        </span>-->
<!--                </template>-->
<!--                <a-list-item-meta :description="item.description">-->
<!--                    <a slot="title" :href="item.href">{{ item.title }}</a>-->
<!--                    <a-avatar slot="avatar" :src="item.avatar" />-->
<!--                </a-list-item-meta>-->
<!--                {{ item.content }}-->
<!--            </a-list-item>-->
<!--        </a-list>-->
    </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
const columns = [
    {
        title: '评论',
        dataIndex: 'comment'
    },
    {
        title: '评分',
        dataIndex: 'rate'
    }
];
export default {
    name: "hotelComments",
    data(){
        return{
            columns,
            pagination: {
                onChange: page => {
                    console.log(page);
                },
                pageSize: 4,
            },
            actions: [
                { type: 'star-o', text: '156' },
                { type: 'like-o', text: '156' },
                { type: 'message', text: '2' },
            ],
        }
    },
    components: {

    },
    computed: {
        ...mapGetters([
            'hotelComments'
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