<template>
    <div class="importFlights">
        <!-- 导入航班 -->
        <!-- 查询和重置 -->
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small" align="left">
            <el-form-item label="查询">
                <el-input v-model="formInline.id" placeholder="请输入航班号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="find">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="reset">重置</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addListener" align="right">添加航班</el-button>

            </el-form-item>
        </el-form>
        <el-table :data="comData" border style="width: 100%">
            <!-- 这里，注意你的prop要和你获取的数据的key一样，不然无法一一对应上 -->
            <el-table-column prop="id" label="航班ID" align="center"></el-table-column>
            <el-table-column prop="flightId" label="航班号" align="center"></el-table-column>
            <el-table-column prop="departureCity" label="起点" align="center"></el-table-column>
            <el-table-column prop="arrivalCity" label="终点" align="center"></el-table-column>
            <el-table-column prop="departureDateTime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="arrivalDateTime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="fare" label="价格" align="center"></el-table-column>
            <el-table-column prop="statetext" label="状态" align="center"></el-table-column>
            <el-table-column prop="remainingSeats" label="剩余座位" align="center"></el-table-column>
            <el-table-column label="操作" align="center" width="180px">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        
        <el-dialog title="创建航班信息" :visible.sync="c_dialogFormVisible" width="600px">
            <el-form :model="form" :rules="rules" ref="form">
                <el-form-item label="航班号" :label-width="formLabelWidth" prop="id">
                    <el-input v-model="form.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="起点" :label-width="formLabelWidth" prop="start">
                    <el-input v-model="form.start" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="终点" :label-width="formLabelWidth" prop="destination">
                    <el-input v-model="form.destination" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出发时间" :label-width="formLabelWidth" prop="starttime">
                    <el-date-picker v-model="form.starttime" format="MM 月 dd 日" value-format="MM-dd" type="date"
                        placeholder="选择出发日期">
                    </el-date-picker>
                    <el-time-picker v-model="form.starttime" :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" placeholder="选择出发时间点">
                    </el-time-picker>
                </el-form-item>
                <el-form-item label="到达时间" :label-width="formLabelWidth" prop="endtime">
                    <el-date-picker v-model="form.endtime" format="MM 月 dd 日" value-format="MM-dd" type="date"
                        placeholder="选择到达日期">
                    </el-date-picker>
                    <el-time-picker v-model="form.endtime" :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" placeholder="选择到达时间点">
                    </el-time-picker>
                </el-form-item>
                <el-form-item label="价格" :label-width="formLabelWidth" prop="price">
                    <el-input v-model="form.price" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="剩余座位" :label-width="formLabelWidth" prop="seat">
                    <el-input v-model="form.seat" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="c_dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="c_check('form')">确 定</el-button>
            </div>
        </el-dialog>
<!-- 这个弹窗也要把model该成对应的字段名，我在航司界面都改好了，你自己看就行 -->
        <el-dialog title="修改航班信息" :visible.sync="dialogFormVisible" width="600px">
            <el-form :model="form" :rules="rules" ref="form">
                <el-form-item label="航班号" :label-width="formLabelWidth" prop="id">
                    <el-input v-model="form.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="起点" :label-width="formLabelWidth" prop="start">
                    <el-input v-model="form.start" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="终点" :label-width="formLabelWidth" prop="destination">
                    <el-input v-model="form.destination" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出发时间" :label-width="formLabelWidth" prop="starttime">
                    <el-date-picker v-model="form.starttime" format="MM 月 dd 日" value-format="MM-dd" type="date"
                        placeholder="选择出发日期">
                    </el-date-picker>
                    <el-time-picker v-model="form.starttime" :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" placeholder="选择出发时间点">
                    </el-time-picker>
                </el-form-item>
                <el-form-item label="到达时间" :label-width="formLabelWidth" prop="endtime">
                    <el-date-picker v-model="form.endtime" format="MM 月 dd 日" value-format="MM-dd" type="date"
                        placeholder="选择到达日期">
                    </el-date-picker>
                    <el-time-picker v-model="form.endtime" :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" placeholder="选择到达时间点">
                    </el-time-picker>
                </el-form-item>
                <el-form-item label="价格" :label-width="formLabelWidth" prop="price">
                    <el-input v-model="form.price" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="剩余座位" :label-width="formLabelWidth" prop="seat">
                    <el-input v-model="form.seat" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="check('form')">确 定</el-button>
            </div>
        </el-dialog>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" align="left">
        </el-pagination>
    </div>
</template>
<script>
//导入接口的方法：import语句
import { getAllFlight } from '@/api/api'
export default {
    data() {
        return {
            dialogFormVisible: false,
            c_dialogFormVisible: false,
            form: {

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
            tableData: [],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 15,
            formInline: {
                id: ''
            }
        }
    },
    methods: {
        getData(){
            //查询数据
            getAllFlight().then(res=>{
                console.log(res)//在控制台打印返回值，debug用的
                //res类似一个返回结构体，里面有返回的状态码，数据（在data字段）
                if(res.status === 200)//返回值200代表成功
                {
                    //把data字段里面的数据赋值给咱们的要渲染的数组
                    this.tableData = res.data.flightData
                    this.total = res.data.TotalNumber
                    this.changeData()
                }
            })
        },
        addListener(){
            this.c_dialogFormVisible = true
            this.form={}
            //add航班
        },
        edit(row) {
            this.dialogFormVisible = true
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
        c_check(form){
            //add航班
            this.$message({ message: '添加航班成功', type: 'success' })
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
                item.status === 1 ? (item.statetext = '已发布') : item.status === 2 ? (item.statetext = '正在检票') : item.status === 3 ? (item.statetext = '飞行中') : item.status === 4 ? (item.statetext = '已到达') : item.status === 5 ? (item.statetext = '航班延迟') : item.status === 0 ? (item.statetext = '未发布') : (item.statetext = '回收站中')
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
        }
    }, created() {//created就是在这个界面的生命周期被创建的时候调用
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