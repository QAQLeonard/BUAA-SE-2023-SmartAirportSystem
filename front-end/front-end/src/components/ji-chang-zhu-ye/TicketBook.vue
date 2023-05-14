<template>
    <div class="ticketbook">
        <!-- 机票预订 -->
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="id" label="航班号" align="center"></el-table-column>
            <el-table-column prop="start" label="起点" align="center"></el-table-column>
            <el-table-column prop="destination" label="终点" align="center"></el-table-column>
            <el-table-column prop="starttime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="endtime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="price" label="价格" align="center"></el-table-column>
            <el-table-column prop="seat" label="剩余座位" align="center"></el-table-column>
            <el-table-column label="购买" align="center">
                <template>
                    <el-button type="primary" size="mini" icon="el-icon-s-goods"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
            :page-sizes="[5, 10, 20, 30,,40,50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
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
                seat: '190'

            }, {
                id: "F102",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F103",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F104",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F105",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F106",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F107",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F108",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F109",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F110",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F111",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F112",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F113",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F114",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'

            }, {
                id: "F115",
                start: "北京",
                destination: "上海",
                starttime: '05月13日12:00',
                endtime: '05月13日15:00',
                price: '666',
                seat: '190'
            },],
            currentPage:1,//当前页数
            pageSize:10,//每页显示条数
            total:15
        }
    },
    methods: {
      handleSizeChange(val) {
        this.pageSize = val
        this.currentPage = 1
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage=val
        console.log(`当前页: ${val}`);
      }
    },
    computed:{
        comData(){
            return this.tableData.slice((this.currentPage-1)* this.pageSize,this.currentPage*this.pageSize)
        }
    }
}
</script>
<style lang="scss">
    .ticketbook{
        .el-pagination{
            text-align: left;
            margin-top: 20px;
        }
    }
</style>