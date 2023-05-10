<template>
    <div class="header">
        <el-header>
            <div class="title">天航智慧机场系统</div>
            <div>{{ name }}</div>
        </el-header>
    </div>
</template>
<script>
    export default {
        data(){
            return{
                name:'张三'
            }
        }
    }
</script>
<style lang="scss" scoped>
    .header{
        .el-header {
            background-color: #2578b5;
            color: #fff;
            line-height: 60px;
            display: flex;
            justify-content: space-between;
            .title {
                width: 200px;
                font-size: 24px;
            }
        }
    }
</style>