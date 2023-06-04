<template>
    <el-tabs type="border-card">
      <el-tab-pane label="报修申请">
        <el-table :data="comData" border style="width: 100%">
            <el-table-column prop="id" label="报修单号" align="center"></el-table-column>
            <el-table-column prop="location" label="位置" align="center"></el-table-column>
            <el-table-column prop="time" label="发生时间" align="center"></el-table-column>
            <el-table-column prop="usetime" label="预计维修时间" align="center"></el-table-column>
            <el-table-column prop="consume" label="报销价格" align="center"></el-table-column>
            <el-table-column prop="statetext" label="状态" align="center"></el-table-column>
            <el-table-column label="操作" align="center" width="180px">
                <template slot-scope="scope">
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="edit(scope.row)"></el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)"></el-button>
                </template>
            </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="商家申请">
        
      </el-tab-pane>
    </el-tabs>
  </template>
  
<script>
        export default {
        data(){
            return{

            }
        },
        methods: {

        }
    }
</script>
<style lang="scss">

</style>