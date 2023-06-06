<template>
    <div class="personalInfo">
        <div class="upload">
            <!-- <el-upload class="avatar-uploader" action="" :show-file-list="false"
                :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" width="200px">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload> -->
            <!-- <span><input type="file" @click="upload()" v-on:change=""></span> -->
        </div>
        <el-form ref="form" :model="form" label-width="80px" :disabled.sync="disabled">
            <el-form-item label="用户名" class="userName">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="性别" class="genderCheck userName">
                <el-radio label="男" v-model="form.gender">男</el-radio>
                <el-radio label="女" v-model="form.gender">女</el-radio>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="个性签名">
                <el-input type="textarea" v-model="form.motto" maxlength="40" show-word-limit></el-input>
            </el-form-item>
        </el-form>
        <!-- <el-form class="workCheck">
            <el-form-item label="工号认证" :model="form" label-width="80px">
                <el-input type="password" v-model="form.name"></el-input>
                <el-button type="primary" @click="check()"> 点击认证</el-button>
            </el-form-item>
        </el-form> -->
        <template>
            <div class="operate">
                <el-button type="primary" @click="edit()"> 编辑个人信息</el-button>
                <el-button type="primary" @click="submit()"> 保存</el-button>
            </div>
        </template>

    </div>
</template>
<script>
import { getUserData } from '@/api/api';
export default {
    data() {
        return {
            form: {
                username: 'test',
                gender: '女',
                mail: '11223344@qq.com',
                note: 'Hello',
                worknum: 'SXC114514'
            },
            disabled: true,
            checked: false,
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            imageUrl: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png'
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
            if(this.disabled === false)
            {
                this.$message({ message: '保存成功', type: 'success' })
            }
            this.disabled = true
            
        },
        upload()
        {

        }
    },
    created(){
        getUserData(localStorage.getItem('username')).then(res=>{
            if(res.status === 200)
            {
                console.log(res)
                this.form = res.data.userData
            }
        })
    }
}
</script>
<style lang="scss" scoped>

.personalInfo {
    margin-top: 40px;

    //
    //


    //
    //
    .el-avatar {
        float: right;
        margin-right: 300px;
    }

    .operate {
        margin-top: 100px;
    }

    .userName {
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