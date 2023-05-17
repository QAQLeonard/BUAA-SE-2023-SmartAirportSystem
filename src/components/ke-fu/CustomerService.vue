<template>
    <div>
        <el-header>
            <div class="bbb">
                <i class="fa fa-comment"></i>
                <el-text class="mx-1" tag="b" type="warning" size="large" >欢迎来到客服页面</el-text>
            </div> 
        </el-header>
        <el-main>
            <div class="aaa">
                点击右下角的图标即可进入客服对话页面
            </div>
        </el-main>
    </div>
</template>
<script>
(function(i,s,o,g,r,a,m){i["DaoVoiceObject"]=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,"script","http://widget.daovoice.io/widget/09d4d346.js","daovoice");
    daovoice('init', {
    app_id: "09d4d346",
    user_id: "NO_89757", // 必填: 该用户在您系统上的唯一ID
    email: "daovoice@example.com", // 选填:  该用户在您系统上的主邮箱
    name: "道客船长", // 选填: 用户名
    signed_up: 1449821660 // 选填: 用户的注册时间，用Unix时间戳表示
    });
    daovoice('update');
</script>
<style>
.aaa{
    font-size: large;
    font-weight:400;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

}
.bbb{
    font-style: oblique;
    font-variant-ligatures: historical-ligatures;
    font-weight: bolder;
    color: rgb(218, 197, 34);
}


</style>
