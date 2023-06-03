<template>
    <div>
        <el-scrollbar height="400px">
        <el-space fill>
            <el-row>
                <el-col :span="8" v-for="item in this.lists.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)" :key="o" :offset="index > 0 ? 2 : 0">
                  <el-card :body-style="{ padding: '0px' }">
                    <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
                    <div style="padding: 14px;">
                      <span>{{item.name}}</span><br>
                      <el-row><el-col offset="8">{{item.price}}¥</el-col></el-row>
                      <div class="bottom clearfix">
                        <time class="time">{{ currentDate }}</time>
                        <el-button type="text" class="button" @click="add(item)">添加商品</el-button>
                        <el-button type="text" class="button" @click="sub(item)">删除商品</el-button>
                      </div>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
        <el-divider></el-divider>
        <el-row>
            <el-col :offset="7"><i class="fa fa-cart-arrow-down"></i></el-col>
            <el-col :offset="7">总价: {{this.total()}} ¥</el-col>
        </el-row>
        <el-button type="success">结算</el-button>
        </el-space>
        </el-scrollbar>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
          :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>

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
            pageSize:10,
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
        computed: {
        comData() {
          return this.lists.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
        }
        }
    
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