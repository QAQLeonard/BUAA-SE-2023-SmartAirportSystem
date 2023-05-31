<template>
    <div class="flightrelease">
        <!-- 发布航班 -->
        <!-- 查询和重置 -->
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
            <el-form-item label="查询">
                <el-input v-model="formInline.id" placeholder="请输入航班号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="find">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="flightId" label="航班号" align="center"></el-table-column>
            <el-table-column prop="departureCity" label="起点" align="center"></el-table-column>
            <el-table-column prop="arrivalCity" label="终点" align="center"></el-table-column>
            <el-table-column prop="departureDateTime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="arrivalDateTime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="fare" label="价格" align="center"></el-table-column>
            <el-table-column prop="status" label="状态" align="center"></el-table-column>
            <el-table-column prop="remainingSeats" label="剩余座位" align="center"></el-table-column>
            <el-table-column label="操作" >
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-s-claim"></el-button>
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
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
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </div>
</template>
<script>
import { getFlightAble } from '@/api/api'
export default {
    data() {
        return {
            dialogFormVisible: false,
            form: {
                id: "",
                start: "",
                destination: "",
                starttime: '',
                endtime: '',
                price: '',
                seat: '',

            },
            rules: {
                id: [{ required: true, message: '请输入航班号' }],
                start: [{ required: true, message: '请输入起点' }],
                destination: [{ required: true, message: '请输入终点' }],
                starttime: [{ required: true }],
                endtime: [{ required: true }],
                price: [{ required: true, message: '请输入航班价格' }],
                seat: [{ required: true, message: '请输入航班座位数量' }],
            },
            tableData: [],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 2,
            formInline: {
                id: ''
            }
        }
    },
    methods: {
        getData(params) {
            //查询数据
            getFlightAble(params).then(res => {
                console.log(res)
                if(res.data.status === 200){
                    this.tableData = res.data.flightData
                    console.log(this.tableData)
                }
            })
        },
        edit(row) {
            this.dialogFormVisible = true
            this.form = { ...row }
        },
        check(form) {
            console.log(form, this.form)
            this.$refs[form].validate(valid => {
                if (valid) {
                    //如果通过，执行对应操作
                    this.dialogFormVisible = false
                    this.$message({ message: '修改数据成功', type: 'success' })
                }
            })
        },
        del(row) {
            console.log(row)
            this.$message({ message: '删除数据成功', type: 'success' })
        },
        find() {

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
                item.state === 1 ? (item.statetext = '已发布') : item.state === 2 ? (item.statetext = '未发布') : (item.statetext = '回收站中')
            });
        }
    },
    computed: {
        comData() {
            return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
        }
    }, created() {
        this.getData()
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
}
</style>