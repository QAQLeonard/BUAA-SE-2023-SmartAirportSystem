<template>
    <div class="CreateRequest">
        <el-form :model="form" :rules="rules" ref="form" size="big" label-position="top">
                <el-form-item label="地点" :label-width="formLabelWidth" prop="position" align="left">
                    <el-input v-model="form.position" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发生时间" :label-width="formLabelWidth" prop="requestDate" align="left">
                    <el-date-picker v-model="form.requestDate" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"
                        type="date" placeholder="选择发生日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="报修费用" :label-width="formLabelWidth" prop="cost" align="left">
                    <el-input v-model="form.cost" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="情况描述" :label-width="formLabelWidth" prop="description" align="left">
                    <el-input v-model="form.description" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item align="center">
                    <el-button type="primary" @click="onSubmit(form)" align="center">创建请求</el-button>
                </el-form-item>
            </el-form>
    </div>
</template>
<script>
import { createrepairrequest } from '@/api/api'
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
            onSubmit(form){
                createrepairrequest(form).then(res => {
                console.log(res)
                })
                //add请求
                this.$message({ message: '创建请求成功', type: 'success' })
            }
        }
    }
</script>
<style lang="scss">

</style>