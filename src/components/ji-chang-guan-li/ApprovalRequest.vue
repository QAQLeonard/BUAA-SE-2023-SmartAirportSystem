<template>
    <el-tabs type="border-card">
      <el-tab-pane label="报修申请">
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="id" label="报修单号" align="center"></el-table-column>
            <el-table-column prop="reporterId" label="报修员工号" align="center"></el-table-column>
            <el-table-column prop="position" label="位置" align="center"></el-table-column>
            <el-table-column prop="requestDate" label="发生时间" align="center"></el-table-column>
            <el-table-column prop="cost" label="报销价格" align="center"></el-table-column>
            <el-table-column prop="description" label="描述" align="center"></el-table-column>
            <el-table-column prop="statetext" label="状态" align="center"></el-table-column>
            <el-table-column label="操作" align="center" width="180px">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" align="left">
        </el-pagination>
      </el-tab-pane>
      <el-dialog title="是否通过报修请求？" :visible.sync="baoxiu_dialogFormVisible" width="600px">
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click="requestycheck(form)" align="center">通 过</el-button>
                <el-button type="primary" @click="requestncheck(form)" align="center">拒 绝</el-button>
                <el-button @click="baoxiu_dialogFormVisible = false" align="center">取 消</el-button>
            </div>
      </el-dialog>

      <el-tab-pane label="商家申请">
        <el-table :data="comData2" border style="width: 100%">
            <el-table-column prop="id" label="商家申请号" align="center"></el-table-column>
            <el-table-column prop="merchantId" label="商家工号" align="center"></el-table-column>
            <el-table-column prop="storeAddress" label="商家位置" align="center"></el-table-column>
            <el-table-column prop="statetext" label="审批状态" align="center"></el-table-column>
            <el-table-column label="操作" align="center" width="180px">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit2(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="是否通过商家请求？" :visible.sync="shangjia_dialogFormVisible" width="600px">
            <div slot="footer" class="dialog-footer" align="center">
                <el-button type="primary" @click="merchantycheck(form)" align="center">通 过</el-button>
                <el-button type="primary" @click="merchantncheck(form)" align="center">拒 绝</el-button>
                <el-button @click="shangjia_dialogFormVisible = false" align="center">取 消</el-button>
            </div>
      </el-dialog>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total2" align="left">
        </el-pagination>
      </el-tab-pane>
    </el-tabs>
  </template>
  
  <script>
import { getAllMAC } from '@/api/api'
import { getrepairrequest } from '@/api/api'
import { editrequest } from '@/api/api'
import { editMAC } from '@/api/api'
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
                  description:"",
                  status: 2
  
  
              }, ],
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
        getData() {
            //查询数据
            getrepairrequest().then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.repairRequestData
                    this.total = res.data.TotalNumber
                    console.log(this.tableData)
                    this.changeData()
                }
            })
            getAllMAC().then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData2 = res.data.merchantApplicationData
                    this.total2 = res.data.TotalNumber
                    console.log(this.tableData2)
                    this.changeData2()
                }
            })
        },
          edit(row) {
              this.baoxiu_dialogFormVisible = true
              this.form = {...row}
          },
          edit2(row) {
              this.shangjia_dialogFormVisible = true
              this.form = {...row}
          },
          find() {
  
          },
        //   requestycheck(form) {
        //     console.log(form, this.form)
        //     this.baoxiu_dialogFormVisible = false
        //     this.$refs[form].validate(valid => {
        //         if (valid) {
        //             //如果通过，执行对应操作
        //             this.form.status=1
        //             this.baoxiu_dialogFormVisible = false
                    // editrequest(this.form).then(res => {
                    //     console.log(res)
                    //     if (res.status === 200) {
                    //         this.getData()
                    //         this.$message({ message: '航班信息修改成功', type: 'success' })
                    //     }
                    // })
        //         }
        //     })
        // },
          requestycheck(form){
                console.log(form,this.form)
                this.form.status=1
                editrequest(this.form).then(res => {
                        console.log(res)
                    })
                this.$message({ message: '报修请求通过', type: 'success' })
                this.baoxiu_dialogFormVisible = false
                this.getData()
          },
          requestncheck(form){
                console.log(form,this.form)
                this.form.status=2
                editrequest(this.form).then(res => {
                        console.log(res)
                    })
                this.$message({ message: '报修请求拒绝', type: 'danger' })
                this.baoxiu_dialogFormVisible = false
                this.getData()
          },
          merchantycheck(form){
                console.log(form,this.form)
                this.form.status=1
                editMAC(this.form).then(res => {
                        console.log(res)
                    })
                this.$message({ message: '商家请求通过', type: 'success' })
                this.shangjia_dialogFormVisible = false
                this.getData()
          },
          merchantncheck(form){
                console.log(form,this.form)
                this.form.status=2
                editMAC(this.form).then(res => {
                        console.log(res)
                    })
                this.$message({ message: '商家请求拒绝', type: 'danger' })
                this.shangjia_dialogFormVisible = false
                this.getData()
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
                  item.status === 0 ? (item.statetext = '未审批') :  (item.status === 1 ? (item.statetext = '已通过'):(item.statetext = '被拒绝'))
              });
          },
          changeData2() {
              this.tableData2.forEach(item => {
                item.status === 0 ? (item.statetext = '未审批') :  (item.status === 1 ? (item.statetext = '已通过'):(item.statetext = '被拒绝'))
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
          this.getData()
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
  