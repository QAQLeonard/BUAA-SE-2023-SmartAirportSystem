<template>
    <div class="scheduleview">
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="flightId" label="航班号" align="center"></el-table-column>
            <el-table-column prop="departureCity" label="起点" align="center"></el-table-column>
            <el-table-column prop="arrivalCity" label="终点" align="center"></el-table-column>
            <el-table-column prop="departureDateTime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="arrivalDateTime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="statetext" label="状态" align="center"></el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </div>
</template>
<script>
import { getFlightAble } from '@/api/api';

export default {
    data() {
        return {
            dialogFormVisible: false,
            tableData: [],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 15,
        }
    },
    methods: {
        getData(params) {
            //查询数据
            getFlightAble(params).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.flightData
                    this.total = res.data.TotalNumber
                    console.log(this.tableData)
                    this.changeData()
                }
            })
        },
        changeData() {
            this.tableData.forEach(item => {
                item.status === 1 ? (item.statetext = '未起飞') : item.status === 2 ? (item.statetext = '正在检票') : item.status === 3 ? (item.statetext = '飞行中') : item.status === 4 ? (item.statetext = '已到达') : item.status === 5 ? (item.statetext = '航班延迟') : item.status === 0 ? (item.statetext = '未发布') : (item.statetext = '回收站中')
            });
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
    }, created() {
        this.getData()
    }, computed: {
        comData() {
            return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
        }
    },
}
</script>
<style lang="scss" scoped>
.scheduleview {

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
}
</style>