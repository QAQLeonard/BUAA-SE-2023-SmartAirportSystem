<template>
  <div class="ParkingSystem">
      <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
          <el-form-item label="查询">
              <el-input v-model="formInline.parkid" placeholder="请输入位置"></el-input>
              <el-input v-model="formInline.period" placeholder="时间段"></el-input>
              <!-- <el-input v-model="formInline.finishtime" placeholder="结束时间"></el-input> -->
              <!-- <el-input v-model="formInline.status" placeholder="车位状态"></el-input> -->
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="find">查询</el-button>
          </el-form-item>
          <el-form-item>
              <el-button type="danger" @click="reset">重置</el-button>
          </el-form-item>
      </el-form>
      <el-table :data="comData" border style="width: 100%">
          <el-table-column prop="id" label="车位id" align="center"></el-table-column>
          <el-table-column prop="location" label="车位地址" align="center"></el-table-column>
          <el-table-column prop="status" label="车位状态" align="center"></el-table-column>
          <el-table-column label="预定" align="center">
              <template>
                
                <input prop="formInline.id2" v-model="formInline.id2" placeholder="id" >
                <input prop="formInline.starttime" v-model="formInline.starttime" placeholder="start">
                <input prop="formInline.finishtime" v-model="formInline.finishtime" placeholder="finish">
                
                  <el-button type="primary" size="mini" icon="el-icon-s-goods" @click="open"></el-button>
                
              </template>
          </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
  </div>
</template>
<script>
import { getAllParking } from '@/api/api'
import { searchParkingTB,searchParkingTB1,updateParking } from '@/api/api'
export default {
  data() {
      return {
          tableData: [
              
          ],
          currentPage: 1,//当前页数
          pageSize: 10,//每页显示条数
          total: 15,
          formInline:{
              starttime:null,
              finishtime:null,
              parkid:'',
              period:'0',
              id2:''
          }
      }
  },
  methods: {
    open() {
      this.$alert('confirm', 'confirm', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
            updateParking(this.formInline.id2,this.formInline.starttime,this.formInline.finishtime).then(res=>{
              console.log(res);
            })
          }
        });
      },
      getData(params){
        getAllParking(params).then(res=>{
          console.log(res);
          this.tableData = res.data.parkingSpaceData;
          this.total = res.data.TotalNumber;
        })
      },
      searchData(id,period){
            searchParkingTB1(id,period).then(res=>{
              if(res==1)
              {
                return;
              }
              else{
              searchParkingTB(id).then(res=>{
              //console.log(res);  
              this.tableData=[];           
              this.tableData.push(res.data.parkingSpaceData);
              this.total = res.data.TotalNumber;
            })
              }
            })

            /*searchParkingTB(id).then(res=>{
              //console.log(res);  
              this.tableData=[];           
              this.tableData.push(res.data.parkingSpaceData);
              this.total = res.data.TotalNumber;
            })*/
        },
      find(){
        this.searchData(this.formInline.parkid,this.formInline.period);
        console.log(this.tableData)
        //console.log(this.tableData)
      },
      reset(){
          this.formInline={}
          this.getData()
      },
      handleSizeChange(val) {
          this.pageSize = val
          this.currentPage = 1
          console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
          this.currentPage = val
          console.log(`当前页: ${val}`);
      }
  },
  computed: {
      comData() {
        return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
        //return this.tableData.slice(0,2);
      }
  },
  created() {
        this.getData()
        //this.find();
    }
}
</script>
<style lang="scss">
.ParkingSystem {

  .demo-form-inline,
  .el-form-item {
      text-align: left;
  }

  .demo-form-inline {
      .el-form-item {
          .el-input {
              width: 100px;
          }
      }
  }

  .el-pagination {
      text-align: left;
      margin-top: 20px;
  }
}</style>