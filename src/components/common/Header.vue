<template>
    <div class="header">
        <el-header>
            <div class="title">天航智慧机场系统 </div>
            <div class="head"> <el-avatar shape="square" :size="size" :src="squareUrl"></el-avatar></div>
            <div>{{ name }}</div>
        </el-header>
    </div>
</template>
<script>
    export default {
        data(){
            return{
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                name:'张三'
            }
        },
        created(){
            this.name = localStorage.getItem('username')
            console.log(this.name)
        }
    }
</script>
<style lang="scss" scoped>
    .header{
        .head{
            margin-top: 10px;
            margin-left: 1200px;
        }
        .el-header {
            background-color: #00bfff;
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