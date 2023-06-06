<template>
    <div class="myorder">
        <!-- 查询和重置 -->
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
            <el-form-item label="查询">
                <el-input v-model="formInline.start" placeholder="请输入订单号"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="find">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="id" label="订单号" align="center"></el-table-column>
            <el-table-column prop="flightId" label="航班号" align="center"></el-table-column>
            <el-table-column prop="departureCity" label="起点" align="center"></el-table-column>
            <el-table-column prop="arrivalCity" label="终点" align="center"></el-table-column>
            <el-table-column prop="departureDateTime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="arrivalDateTime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="fare" label="价格" align="center"></el-table-column>
            <el-table-column prop="statetext" label="状态" align="center"></el-table-column>
            <el-table-column label="购买" align="center">
                <template>
                    <el-button type="primary" size="mini" icon="el-icon-s-goods" @click="purchase"></el-button>
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
export default {
    data() {
        return {
            tableData: [],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 15,
            formInline: {

            }
        }
    }, computed: {
        comData() {
            return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
        }
    },
    methods: {
        changeData() {
            console.log(1)
            this.tableData.forEach(item => {
                console.log(item)
                item.status === 1 ? (item.statetext = '已发布') : item.status === 2 ? (item.statetext = '正在检票') : item.status === 3 ? (item.statetext = '飞行中') : item.status === 4 ? (item.statetext = '已到达') : item.status === 5 ? (item.statetext = '航班延迟') : item.status === 0 ? (item.statetext = '未发布') : (item.statetext = '回收站中')
            });
        },
        find()
        {

        },
        reset()
        {
            
        }
    }
}
</script>
<style lang="scss" scoped>
.myorder {

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