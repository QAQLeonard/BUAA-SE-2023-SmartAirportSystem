<template>
    <div class="scheduleview">
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="flightId" label="航班号" align="center"></el-table-column>
            <el-table-column prop="departureCity" label="起点" align="center"></el-table-column>
            <el-table-column prop="arrivalCity" label="终点" align="center"></el-table-column>
            <el-table-column prop="departureDateTime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="arrivalDateTime" label="到达时间" align="center"></el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </div>
</template>
<script>
export default {
    data() {
        return {
            dialogFormVisible: false,
            tableData: [],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 15,
        }
    }
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