<template>
    <div class="CreateRequest">
        <el-form :inline="false" class="demo-form-inline" size="big" label-position="top" label-width="100px">
            <el-form-item label="位置" align="left">
                <el-select> 
                    <el-option label="1号机场" value="1"></el-option>   
                    <el-option label="2号机场" value="2"></el-option> 
                    <el-option label="3号机场" value="3"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="发生时间" :label-width="formLabelWidth" prop="starttime" align="left">
                    <el-date-picker format="MM 月 dd 日" value-format="MM-dd" type="date"
                        placeholder="日期">
                    </el-date-picker>
                    <el-time-picker :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" placeholder="时间">
                    </el-time-picker>
                </el-form-item>
            <el-form-item label="预计维修时间" align="left">
                <el-input> </el-input>
            </el-form-item>
            <el-form-item label="报销价格" align="left">
                <el-input> </el-input>
            </el-form-item>
            <el-form-item label="问题详细描述" align="left">
                <el-input> </el-input>
            </el-form-item>
            <el-form-item align="left">
                <el-button type="primary" @click="onSubmit">创建请求</el-button>
                <el-button @click="cancle">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
        export default {
        data(){
            return{

            }
        },
        methods: {
            onSubmit(){
                //add请求
                this.$message({ message: '创建请求成功', type: 'success' })
            },
            cancle(){
                //清空数据
                this.$message({ message: '取消成功' })
            }
        }
    }
</script>
<style lang="scss">

</style>