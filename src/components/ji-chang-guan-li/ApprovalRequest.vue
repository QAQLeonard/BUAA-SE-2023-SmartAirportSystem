<template>
    <el-tabs type="border-card">
      <el-tab-pane label="报修申请">
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="id" label="报修单号" align="center"></el-table-column>
            <el-table-column prop="creater_id" label="请求者单号" align="center"></el-table-column>
            <el-table-column prop="position" label="位置" align="center"></el-table-column>
            <el-table-column prop="time" label="发生时间" align="center"></el-table-column>
            <el-table-column prop="cost" label="报销价格" align="center"></el-table-column>
            <el-table-column prop="status" label="状态" align="center"></el-table-column>
            <el-table-column prop="description" label="描述" align="center"></el-table-column>
            <el-table-column label="操作" align="center" width="180px">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" align="left">
        </el-pagination>
      </el-tab-pane>
      <el-dialog title="是否确定通过报修请求？" :visible.sync="baoxiu_dialogFormVisible" width="600px">
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click="check('form')" align="center">确 定</el-button>
                <el-button @click="baoxiu_dialogFormVisible = false" align="center">取 消</el-button>
            </div>
      </el-dialog>
      <el-tab-pane label="商家申请">
        <el-table :data="comData2" border style="width: 100%">
            <el-table-column prop="username" label="商家用户名" align="center"></el-table-column>
            <el-table-column prop="name" label="店名" align="center"></el-table-column>
            <el-table-column prop="contactPerson" label="联系人" align="center"></el-table-column>
            <el-table-column prop="contactPhone" label="联系电话" align="center"></el-table-column>
            <el-table-column prop="storeLocation" label="商家位置" align="center"></el-table-column>
            <el-table-column label="操作" align="center" width="180px">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="是否确定通过商家请求？" :visible.sync="shangjia_dialogFormVisible" width="600px">
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click="check('form')" align="center">确 定</el-button>
                <el-button @click="shangjia_dialogFormVisible = false" align="center">取 消</el-button>
            </div>
      </el-dialog>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" align="left">
        </el-pagination>
      </el-tab-pane>
    </el-tabs>
  </template>
  
  <script>
  export default {
      data() {
          return {
            baoxiu_dialogFormVisible: false,
            shangjia_dialogFormVisible: false,
              form: {
                  id: "",
                  creater_id: "",
                  position: "",
                  time: '',
                  cost: '',
                  status: '',
              },
              rules: {
                  id: [{required:true,message:'请输入航班号'}],
                  start: [{required:true,message:'请输入起点'}],
                  destination: [{required:true,message:'请输入终点'}],
                  starttime: [{required:true}],
                  endtime: [{required:true}],
                  price:[{required:true,message:'请输入航班价格'}],
                  seat: [{required:true,message:'请输入航班座位数量'}],
              },
              tableData: [{
                  id: "0001",
                  creater_id: "1",
                  position: "1号机场",
                  time: '05月13日12:00',
                  cost: '100',
                  description:"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",
                  status: 2
  
  
              }, {
                  id: "0002",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
  
              }, {
                  id: "0003",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
  
              }, {
                  id: "0004",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
              }, {
                  id: "0005",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
              }, {
                  id: "0006",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
              }, {
                  id: "0007",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
              }, {
                  id: "0008",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
              }, {
                  id: "0009",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
              }, {
                  id: "0010",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
              }, {
                  id: "0011",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
  
              }, {
                  id: "0012",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
              }, {
                  id: "0013",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
              }, {
                  id: "0014",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
              }, {
                  id: "0015",
                  location: "1号机场",
                  dlevel: "一般",
                  time: '05月13日12:00',
                  usetime: '10天',
                  consume: '100',
                  state: 2
              },],
              tableData2: [{
                username:"why",
                name:"kaixin",
                contactPerson:"why",
                contactPhone:"13662319185",
                storeLocation:"1"
              },],
              currentPage: 1,//当前页数
              pageSize: 10,//每页显示条数
              total: 15,
              formInline: {
                  id: ''
              },
              form:{
                  name:"zhangsan "
              }
          }
      },
      methods: {
          getData(){
              //查询数据
          },
          edit(row) {
              this.baoxiu_dialogFormVisible = true
              this.form = {...row}
          },
          find() {
  
          },
          check(form){
              console.log(form,this.form)
              this.$refs[form].validate(valid=>{
                  if(valid){
                      //如果通过，执行对应操作
                      this.dialogFormVisible = false
                      this.$message({ message: '修改数据成功', type: 'success' })
                  }
              })
          },
          reset() {
              this.formInline = {}
          },
          handleSizeChange(val) {
              this.pageSize = val
              this.currentPage = 1
              console.log(`每页 ${val} 条`);
          },
          handleCurrentChange(val) {
              this.currentPage = val
              console.log(`当前页: ${val}`);
          },
          changeData() {
              this.tableData.forEach(item => {
                  item.state === 1 ? (item.statetext = '已审批') :  (item.statetext = '未审批')
              });
          },
          del(row) {
              console.log(row)
              this.$message({ message: '删除数据成功', type: 'success' })
          }
      },
      computed: {
          comData() {
              return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
          },
          comData2() {
            return this.tableData2.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
          }
      }, created() {
          this.changeData()
      },
  }
  </script>
  <style lang="scss">
  .flightrelease {
  
      .demo-form-inline,
      .el-form-item {
          text-align: left;
      }
  
      .demo-form-inline {
          .el-form-item {
              .el-input {
                  width: 120px;
              }
              
          }
      }
  
      .el-pagination {
          text-align: left;
          margin-top: 20px;
      }
  }</style>
  