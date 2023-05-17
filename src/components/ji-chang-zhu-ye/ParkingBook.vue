<template>
  <div class="ParkingSystem">
      <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
          <el-form-item label="查询">
              <el-input v-model="formInline.address" placeholder="请输入位置"></el-input>
              <el-input v-model="formInline.status" placeholder="车位状态"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="find">查询</el-button>
          </el-form-item>
          <el-form-item>
              <el-button type="danger" @click="reset">重置</el-button>
          </el-form-item>
      </el-form>
      <el-table :data="comData" border style="width: 100%">
          <el-table-column prop="id" label="航班号" align="center"></el-table-column>
          <el-table-column prop="address" label="车位地址" align="center"></el-table-column>
          <el-table-column prop="status" label="车位状态" align="center"></el-table-column>
          <el-table-column label="预定" align="center">
              <template>
                  <el-button type="primary" size="mini" icon="el-icon-s-goods"></el-button>
              </template>
          </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
          :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
  </div>
</template>
<script>
export default {
  data() {
      return {
          tableData: [{
              id: "P101",
              address:'A3',
              status:1,

          }, {
            id: "P102",
              address:'A1',
              status:1,

          }, {
            id: "P103",
              address:'A5',
              status:0,

          }, {
            id: "P202",
              address:'B3',
              status:1,

          }, {
            id: "P201",
              address:'B4',
              status:0,

          }, {
            id: "P107",
              address:'A9',
              status:0,

          }, {
            id: "P110",
              address:'C1',
              status:0,

          }, {
            id: "P301",
              address:'A3',
              status:1,

          }, {
            id: "P401",
              address:'A9',
              status:1,

          }, {
            id: "P303",
              address:'A5',
              status:0,

          }, {
            id: "P121",
              address:'C1',
              status:0,
          }, {
            id: "P141",
              address:'A3',
              status:0,

          }, {
            id: "P212",
              address:'A3',
              status:1,
          }, {
            id: "P331",
              address:'B5',
              status:1,

          }, {
            id: "P101",
              address:'C3',
              status:0,
          },],
          currentPage: 1,//当前页数
          pageSize: 10,//每页显示条数
          total: 15,
          formInline:{
              address:'',
              status:0,
          }
      }
  },
  methods: {
      find(){

      },
      reset(){
          this.formInline={}
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
      }
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