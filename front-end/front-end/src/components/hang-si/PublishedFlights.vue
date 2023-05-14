<template>
    <div class="published">
        <!-- 机票预订 -->
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="id" label="航班号" align="center"></el-table-column>
            <el-table-column prop="start" label="起点" align="center"></el-table-column>
            <el-table-column prop="destination" label="终点" align="center"></el-table-column>
            <el-table-column prop="starttime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="endtime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="price" label="价格" align="center"></el-table-column>
            <el-table-column prop="statetext" label="状态" align="center"></el-table-column>
            <el-table-column prop="seat" label="剩余座位" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
                <template>
                    <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete"></el-button>
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
                id: "F101",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1
                

            }, {
                id: "F102",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1
                

            }, {
                id: "F103",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1
                

            }, {
                id: "F104",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F105",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F106",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F107",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1
            }, {
                id: "F108",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F109",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1
            }, {
                id: "F110",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F111",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F112",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1
                

            }, {
                id: "F113",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F114",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1

            }, {
                id: "F115",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190',
                state: 1
            },],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 15
        }
    },
    methods: {
        
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
                item.state===1 ? (item.statetext='已发布'):item.state === 2?(item.statetext = '未发布'):(item.statetext = '回收站中')
            });
        }
    },
    computed: {
        comData() {
            return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
        }
    }, created(){
        this.changeData()
    },
}
</script>
<style lang="scss">
.published {
    .el-pagination {
        text-align: left;
        margin-top: 20px;
    }
}
</style>