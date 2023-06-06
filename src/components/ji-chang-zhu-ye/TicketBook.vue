<template>
    <div class="ticketbook">
        <!-- 机票预订 -->
        <!-- 查询和重置 -->
        <el-form :inline="true" :model="formInline" class="demo-form-inline" size="small">
            <el-form-item label="查询">
                <el-input v-model="formInline.start" placeholder="请输入起点"></el-input>
                <el-input v-model="formInline.destination" placeholder="请输入终点"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="find">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="flightId" label="航班号" align="center"></el-table-column>
            <el-table-column prop="departureCity" label="起点" align="center"></el-table-column>
            <el-table-column prop="arrivalCity" label="终点" align="center"></el-table-column>
            <el-table-column prop="departureDateTime" label="出发时间" align="center"></el-table-column>
            <el-table-column prop="arrivalDateTime" label="到达时间" align="center"></el-table-column>
            <el-table-column prop="fare" label="价格" align="center"></el-table-column>
            <el-table-column prop="remainingSeats" label="剩余座位" align="center"></el-table-column>
            <el-table-column label="购买" align="center">
                <template>
                    <el-button type="primary" size="mini" icon="el-icon-s-goods" @click="purchase"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="购买机票" :visible.sync="dialogFormVisible" width="600px">
            <img :src="require('../img/img1.jpg')" alt="">
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="check()">确 定</el-button>
            </div>
        </el-dialog>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20, 30, , 40, 50]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </div>
</template>
<script>

import { getFlightAble } from '@/api/api'
import { searchFlightTB } from '@/api/api'
export default {
    data() {
        return {
            dialogFormVisible: false,
            tableData: [],
            currentPage: 1,//当前页数
            pageSize: 10,//每页显示条数
            total: 15,
            formInline: {
                start: '',
                destination: ''
            },
            imgSrc: 'src\assets\img\img1.jpg'
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
                }
            })
        },
        searchData(departureCity, arrivalCity) {
            searchFlightTB(departureCity, arrivalCity).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.tableData = res.data.flightData
                    this.total = res.data.TotalNumber
                    console.log(this.tableData)
                }
            })
        },
        find() {
            console.log(this.formInline)
            this.searchData(this.formInline.start,this.formInline.destination)
            console.log(this.tableData)
        },
        reset() {
            this.formInline = {}
            this.getData()
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
        purchase() {
            this.dialogFormVisible = true
        },
        check() {
            this.$message({ message: '购买成功', type: 'success' })
            this.dialogFormVisible = false

        }
    },
    computed: {
        comData() {
            return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
        }
    },
    created() {
        this.getData()
    }
}
</script>
<style lang="scss">
img {
    width: 200px;
    height: 300px;
}

.ticketbook {

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

    .el-dialog {}

    .el-pagination {
        text-align: left;
        margin-top: 20px;
    }
}
</style>