<template>
    <div>
        <el-scrollbar height="400px">
        <el-space fill>
        <p v-for="item in comData()" :key="item.id" class="scrollbar-demo-item">
            <el-col>
            <el-image style="width: 80px; height: 80px" :src="item.src" :fit="fill" />
            </el-col>
            <el-col>
            <el-text class="mx-1"  size="Large" type="warning">{{item.name}}</el-text>
            </el-col>
            <el-col>
            <el-text class="mx-1" type="warning" size="Large" >单价：{{item.price}}</el-text>
            </el-col>
            <el-col>
                <button class="btn btn-primary btn-xs" @click="sub(item)">-</button>
                <input type="text" v-model="item.num" @blur="changeNum(item)">
                <button class="btn btn-primary btn-xs"  @click="add(item)">+</button>
            </el-col>
        </p>
        <el-divider></el-divider>
        <el-row>
            <el-col :offset="7"><i class="fa fa-cart-arrow-down"></i></el-col>
            <el-col :offset="7">总价: {{this.total()}} ¥</el-col>
        </el-row>
        <el-button type="success">结算</el-button>
        </el-space>
        </el-scrollbar>
        <el-pagination
        :page-size="pageSize"
        :pager-count="10"
        :current-page="currentPage"
        layout="total,sizes,prev, pager, next,jumper"
        background
        :total="Total"
        @size-change="handleSizeChange" @current-change="handleCurrentChange"/>

    </div>
</template>

<script>
export default{
    data(){
        return{
            lists:[
                {id:1,price:100,name:"aaa",num:0,url:"front-end/front-end/src/components/shang-jia-ru-zhu/photo/img1.jpeg"}
            ],
            currentPage:1,
            pageSize:20,
            Total:100,
        }
    },
    
    methods:{
        handleSizeChange(val) {
            this.pageSize = val
            this.currentPage = 1
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.currentPage = val
            console.log(`当前页: ${val}`);
        },
        sub(item){
            if(item.num>0)
            item.num--;
        },
        add(item){
            item.num++;
        },
        changeNum(item){

        },
        total(){
            var t=0;
            for (var key in this.lists){
                t+=this.lists[key].price*this.lists[key].num;
            }
            return t;
        },
        comData() {
            return this.lists.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);  
        },
    
    },
}





</script>
<style scoped>
.scrollbar-demo-item {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    height: 100px;
    margin: 20px;
    text-align: left;
    border-radius: 20px;
    background: rgb(176, 204, 218);
    
}
.el-pagination{
    margin-top: 40px;
}
</style>