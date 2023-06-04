<template>
    <div>
        <el-table :data="comData" border style="width: 40%">
            <el-table-column lable="xuhao" type="index" :index="computetableindex" width="60"></el-table-column>>
            <el-table-column label="序号" width="100" align="center">
	            <template slot-scope="scope">
		            {{searchData.pageSize * (searchData.pageNo - 1) + (scope.$index + 1)}}
	            </template>
            </el-table-column>
            <el-table-column prop="id" label="航班号" align="center"></el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" align="left">
        </el-pagination>
    </div>
</template>
<script>
export default {
    data() {
        return {
            tableData: [{
                id:"F101"
            }, {
                id:"F102"
            }, {
                id:"F103"
            },],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 3,
        }
    },
    methods: {
        find(){

        },
        reset(){
            this.formInline={}
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
        },
        computetableindex(index) {
            return (this.page - 1 ) * this.pageSize + index + 1
        },
    }, created() {
        this.changeData()
    },
}
</script>