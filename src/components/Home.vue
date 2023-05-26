<template>
  <div class="home">
    <Header />
    <el-container class="content">
      <Menu />
      <el-container>
        <el-main>
          <Bread />
          <div class="cont">
            <router-view></router-view>
          </div>
        </el-main>
        <el-footer><Footer /></el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Header from './common/Header.vue'
import Footer from './common/Footer.vue'
import Menu from './common/Menu.vue'
import Bread from './common/Breadcrumb.vue'

(function(i,s,o,g,r,a,m){i["DaoVoiceObject"]=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,"script","http://widget.daovoice.io/widget/09d4d346.js","daovoice");
    daovoice('init', {
    app_id: "09d4d346",
    user_id: "NO_89757", // 必填: 该用户在您系统上的唯一ID
    email: "daovoice@example.com", // 选填:  该用户在您系统上的主邮箱
    name: "道客船长", // 选填: 用户名
    signed_up: 1449821660 // 选填: 用户的注册时间，用Unix时间戳表示
    });
    daovoice('update');

export default {
  components: {
    Header,
    Footer,
    Menu,
    Bread
  },
  data() {
    return {}
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
.home {
  width: 100%;
  height: 100%;
  .content {
    position: absolute;
    width: 100%;
    top: 60px;
    bottom: 0;
    .cont {
      margin:20px 0;
    }
  }
}
</style>
