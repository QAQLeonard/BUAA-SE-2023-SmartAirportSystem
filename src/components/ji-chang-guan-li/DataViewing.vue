<template>
    <div>
        <el-form :inline="true" :model="form" align="left">
            <el-form-item label="查询后七日财务">
                <el-input v-model="form.sDate" placeholder="请输入日期"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="find">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="comData" border style="width: 100%">
            <el-table-column type="index" :index="computetableindex" width="60" align="center"></el-table-column>>
            <el-table-column prop="date" label="日期" align="center"></el-table-column>
            <el-table-column prop="money" label="金额" align="center"></el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage4"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total" align="left">
        </el-pagination>
    </div>
</template>
<script>
import { getfinance } from '@/api/api'
export default {
    data() {
        return {
            form: {

            },
            tableData: [],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 5,
        }
    },
    methods: {
        new_find(){
            getfinance(this.form.sDate).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.ticketData
                    this.total = res.data.TotalNumber
                    console.log(this.tableData)
                    //this.changeData()
                }
            })
        },
        find(form) {
            console.log(this.form.sDate)
            getfinance(this.form.sDate).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.ticketData
                    this.total = res.data.TotalNumber
                    console.log(this.tableData)
                    //this.changeData()
                }
            })
        },
        reset() {
            this.form = {}
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
    },
    computed: {
        comData() {
            return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
        },
        computetableindex(index) {
            return (this.page - 1) * this.pageSize + index + 1
        },
    }, created() {
        
    },
}
</script>