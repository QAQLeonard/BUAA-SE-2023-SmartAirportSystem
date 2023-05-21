<template>
    <div class="personalInfo">
        <el-avatar shape="square" :size="150" :src="squareUrl"></el-avatar>
        <el-form ref="form" :model="form" label-width="80px" :disabled.sync="disabled">
            <el-form-item label="用户名" class="userName">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" class="genderCheck userName" >
                <el-radio label="男" v-model="form.gender">男</el-radio>
                <el-radio label="女" v-model="form.gender">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号">
                <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="个性签名">
                <el-input type="textarea" v-model="form.note"></el-input>
            </el-form-item>
        </el-form>
        <el-form class="workCheck">
            <el-form-item label="工号认证" :model="form" label-width="80px">
                <el-input type="password" v-model="form.name"></el-input>
                <el-button type="primary" @click="check()"> 点击认证</el-button>
            </el-form-item>
        </el-form>
        <template>
            <div class="operate">
                <el-button type="primary" @click="edit()"> 编辑个人信息</el-button>
                <el-button type="primary" @click="submit()"> 保存</el-button>
            </div>
        </template>
        
    </div>
</template>
<script>
export default {
    data() {
        return {
            form: {
                name: 'test',
                gender: '女',
                phone: '13211223344',
                note: 'Hello',
                worknum: 'SXC114514'
            },
            disabled: true,
            checked: false,
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
        }
    },
    methods: {
        onSubmit() {
            console.log('submit!');
        },
        check() {
            if (this.checked === false) {
                this.$message({ message: '认证成功', type: 'success' })
                this.checked = true
            } else {
                this.$message({ message: '不能重复认证！', type: 'error' })
            }
        },
        edit() {
            this.disabled = false
        },
        submit() {
            this.disabled = true
        }
    }
}
</script>
<style lang="scss" scoped>
.personalInfo {
    margin-top: 40px;
    .el-avatar{
        float: right;
        margin-right: 300px;
    }
    .operate{
        margin-top: 100px;
    }
    .userName{
        height: 40px;
    }
    .el-form-item {
        margin-left: 150px;
    }
    .workCheck {
        width: 50%;

        .el-form-item {
            // background-color: blue;
            .el-input {
                margin-left: 0px;
                width: 60%;
                // background-color: aquamarine;
            }

            .el-button {
                display: inline-block;
                float: right;
                // width: 60px;
                // background-color: aqua;
            }
        }
    }

    .el-form {
        .el-form-item {
            width: 50%;
        }

        .genderCheck {
            width: 15%;
        }
    }
}
</style>