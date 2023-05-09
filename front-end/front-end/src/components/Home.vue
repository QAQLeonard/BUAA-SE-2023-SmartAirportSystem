<template>
  <div class="hello">
    <h1>Hello</h1>
    <el-button>Hello</el-button>
    <el-button type="primary">Hello</el-button>
    <el-button type="danger">Hello</el-button>
    <el-button type="info">Hello</el-button>
    <el-button type="success">Hello</el-button>
    <el-tag>hahaha</el-tag>
    <i class="fa fa-user"></i>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
  .hello {
    background: yellow;
    .el-button {
    color: red;
  }
  }
  
</style>
