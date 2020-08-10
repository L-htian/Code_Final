<!--这个页面是管理人员登陆之后看到的页面-->
<template>
    <div class="welcome-page">
        <span class="welcome-text">欢迎您使用本酒店管理系统</span>
    </div>
</template>
<script>
    export default {
        name: "welcome"
    }
</script>

<style scoped lang="less">
    .welcome-page{
        text-align: center;
        padding: 50px 0;
        .welcome-text{
            top: 150px;
            font-size: 33px;
            color: #aaaaaa;
            font-family: Avenir, 'Helvetica Neue', Arial, Helvetica, sans-serif;
            font-weight: 600;
            position: relative;
        }
    }
</style>