<template>
    <div class="bin">
        <!-- 回收站 -->
        <!-- 查询和重置 -->
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
            <el-form-item label="查询">
                <el-input v-model="formInline.id" placeholder="请输入航班ID"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="find">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="id" label="航班ID" align="center"></el-table-column>
            <el-table-column prop="flightId" label="航班号" align="center"></el-table-column>
            <el-table-column prop="departureCity" label="起点" align="center"></el-table-column>
            <el-table-column prop="arrivalCity" label="终点" align="center"></el-table-column>
            <el-table-column prop="departureDateTime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="arrivalDateTime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="fare" label="价格" align="center"></el-table-column>
            <el-table-column prop="statetext" label="状态" align="center"></el-table-column>
            <el-table-column prop="remainingSeats" label="剩余座位" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-refresh-left"
                        @click="recover(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
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
import { getFlightDeleted } from '@/api/api'
import { searchFlightDeleted } from '@/api/api'
import { recoverDeleted } from '@/api/api'
import { deleteFlight } from '@/api/api'

export default {
    data() {
        return {
            tableData: [
                {
                    statetext: ''
                }
            ],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 15,
            formInline: {
                id: ''
            }
        }
    },
    methods: {
        getData(params) {
            //查询数据
            getFlightDeleted(params).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.flightData
                    this.total = res.data.TotalNumber
                    console.log(this.tableData)
                    this.changeData()
                }
            })
        },
        searchData(id) {
            searchFlightDeleted(id).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.flightData
                    this.total = res.data.TotalNumber
                }
            })
        },
        recoverFlight(id) {
            recoverDeleted(id).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.$message({ message: '恢复航班成功', type: 'success' })
                    this.getData()
                }
            })
        },
        Delete(id) {
            deleteFlight(id).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.$message({ message: '删除航班成功', type: 'success' })
                }
            })

        },
        find() {
            console.log(this.formInline)
            this.searchData(this.formInline.id)
        },
        reset() {
            this.formInline = {}
            this.getData()
        },
        recover(row) {
            console.log(row)
            this.recoverFlight(row.id)
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
            console.log(1)
            this.tableData.forEach(item => {
                console.log(item)
                item.status === 1 ? (item.statetext = '已发布') : item.status === 2 ? (item.statetext = '正在检票') : item.status === 3 ? (item.statetext = '飞行中') : item.status === 4 ? (item.statetext = '已到达') : item.status === 5 ? (item.statetext = '航班延迟') : item.status === 0 ? (item.statetext = '未发布') : (item.statetext = '回收站中')
            });
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
.bin {

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