<template>
    <div class="HandleAffair">
        <!-- 机场保修 -->
        <!-- 查询和重置 -->
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small" align="left">
            <el-form-item label="查询">
                <el-input v-model="formInline.id" placeholder="请输入报修单号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="find">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
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

        <el-dialog title="修改请求信息" :visible.sync="dialogFormVisible" width="600px">
            <el-form :model="form" :rules="rules" ref="form">
                <el-form-item label="位置" :label-width="formLabelWidth" prop="position">
                    <el-input v-model="form.position" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发生时间" :label-width="formLabelWidth" prop="requestDate" align="left">
                    <el-date-picker v-model="form.requestDate" format="MM 月 dd 日" value-format="MM-dd" type="date"
                        placeholder="选择维修日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="报销价格" :label-width="formLabelWidth" prop="cost">
                    <el-input v-model="form.cost" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="报修描述" :label-width="formLabelWidth" prop="description">
                    <el-input v-model="form.description" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="check('form')">确 定</el-button>
            </div>
        </el-dialog>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </div>
</template>
<script>
import { getrepairrequest } from '@/api/api'
import { searchrequest } from '@/api/api'
import { moverepairrequest } from '@/api/api'
import { editrequest } from '@/api/api'
export default {
    data() {
        return {
            formLabelWidth:'120px',
            dialogFormVisible: false,
            form: {
            },
            rules: {

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
        },
        searchData(id) {
            searchrequest(id).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.repairRequestData
                    this.total = res.data.TotalNumber
                    console.log(this.tableData)
                    this.changeData()
                }
            })
        },
        edit(row) {
            this.dialogFormVisible = true
            this.form = {...row}
            this.form.id = row.id
            this.form.reporterId = row.reporterId
            delete this.form.statetext
        },
        find() {
            console.log(this.formInline)
            this.searchData(this.formInline.id)
        },
        check(form) {
            console.log(form, this.form)
            this.$refs[form].validate(valid => {
                if (valid) {
                    //如果通过，执行对应操作
                    this.dialogFormVisible = false
                    editrequest(this.form).then(res => {
                        console.log(res)
                        if (res.status === 200) {
                            this.getData()
                            this.$message({ message: '报修请求信息修改成功', type: 'success' })
                        }
                    })
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
            this.tableData=Array.from(this.tableData)
            console.log(this.tableData)
            this.tableData.forEach(item => {
                item.status === 1 ? (item.statetext = '已发布') : item.status === 2 ? (item.statetext = '正在检票') :item.status === 3 ? (item.statetext = '飞行中') :item.status === 4 ? (item.statetext = '已到达') :item.status === 5 ? (item.statetext = '航班延迟') :item.status === 0 ? (item.statetext = '未发布') : (item.statetext = '回收站中')
            });
        },
        Delete(id) {
            moverepairrequest(id).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.$message({ message: '删除报修请求成功', type: 'success' })
                }
            })

        },
        del(row) {
            console.log(row)
            this.Delete(row.id)
            this.getData()
        },
    },
    computed: {
        comData() {
            return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
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

