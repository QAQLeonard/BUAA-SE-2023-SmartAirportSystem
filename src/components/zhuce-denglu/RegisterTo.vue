<template>
    <div>
        <el-tabs type="border-card" >
            <el-tab-pane label="旅客注册" >
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="头像" prop="avatar">
                        <el-upload
                        class="avatar-uploader"
                        action="https://jsonplaceholder.typicode.com/posts/"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                    </el-upload>
                    </el-form-item>
                    <el-form-item>
                    <el-select v-model="value" placeholder="选择身份">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                        >
                        </el-option>
                    </el-select>
                    </el-form-item>
                    <el-form-item label="工号" prop="motto">
                        <el-input  v-model="employeeid"  placeholder="仅限employee填写" :disabled='false'></el-input>
                    </el-form-item>
                    <el-form-item label="用户名" prop="username">
                    <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="Gender">
                    <el-input v-model="ruleForm.Gender"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off" placeholder="8-16位由大小写字母、数字组成的密码"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="确认密码"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="phoneNumber">
                    <el-input type="tel" v-model="ruleForm.phoneNumber" id="phone" name="phone">
                    </el-input>
                    <!-- <el-button id="sendCodeBtn" type="info" round> -->
                    <!-- 发送验证码 -->
                    <!-- </el-button> -->
                    </el-form-item>
                    <!-- <el-form-item label="验证码" prop="account"> -->
                    <!-- <el-input v-model="ruleForm.Checkcode"></el-input> -->
                    <!-- </el-form-item> -->
                    <el-form-item label="motto" prop="motto">
                        <el-input  v-model="ruleForm.motoo"  placeholder="motoo"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            
        </el-tabs>
    </div>
</template>
<script>
import { InsertUser, MerchantID } from '@/api/api';
export default {
    data() {
        
        var validatePass = (rule, value, callback) => {
        var patrn=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]+$/;
        if (value === '') {
            callback(new Error('请输入密码'));
        } 
        else if(value.length<8||value.length>16){
            callback(new Error('密码要在8-16位'));
        }
        else if(!patrn.test(value)){
            callback(new Error('密码只能由大小写字母和数字组成'));
        }
        else {
            if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
            }
            callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
            callback(new Error('两次输入密码不一致!'));
        } else {
            callback();
        }
        };
        var generateVerificationCode= ()=>{
            return Math.floor(Math.random() * 900000 + 100000);
        };
        var sendVerificationCode= ()=>{
            var phoneInput = document.getElementById("phone");
            var sendCodeBtn = document.getElementById("sendCodeBtn");
            sendCodeBtn.disabled = true;
            setTimeout(function() {
            var code = this.generateVerificationCode();
            sessionStorage.setItem("verificationCode", code);
            alert("验证码已发送到您的手机，请注意查收！");
            sendCodeBtn.disabled = false;
            } , 2000);
        };
        var verifyCode=(rule, value, callback)=>{
        var storedCode = sessionStorage.getItem("verificationCode");
        if (value === storedCode) {
            alert("验证码正确！");
        } else {
        callback(new Error('验证码错误'));
        }
        }
        return {
        options: [{
            value: '选项1',
            label: 'passenger'
        }, {
            value: '选项2',
            label: 'employee'
        }, {
            value: '选项3',
            label: 'merchant'
        }, ],
        value: '',
        employeeid:'',
        flag:'false',
        ruleForm: {
            pass: 'Ss111111',
            checkPass: 'Ss111111',
            phoneNumber:'111',
            username:'user30',
            Gender:'male',
            Checkcode:'111',
            motto:''
        },
        RegForm:{
            username:'',
            password:'',
            id:'',
            avatar:'avatar11',
            role:'',
            email:'',
            motto:'',
            gender:''
        },
        imageUrl: '',
        rules: {
            password: [
                { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
                { validator: validatePass2, trigger: 'blur' }
            ],
            Checkcode:[
                {validator:verifyCode,trigger:'blur'}
            ]
        }
        };
    },
    methods: {
        fuck(){
            if(this.value=='employee'){
                this.flag='false';
            }
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                alert('submit!');
                this.RegForm.gender=this.ruleForm.Gender;
                this.RegForm.password=this.ruleForm.pass;
                this.RegForm.username=this.ruleForm.username;
                this.RegForm.avatar=this.imageUrl;
                this.RegForm.email=this.ruleForm.phoneNumber;
                this.RegForm.motto=this.ruleForm.motto;
                this.RegForm.role=this.value;
                if(this.value=='passenger')
                {
                    PassengerID().then(res=>{
                        this.RegForm.id=res;
                    })
                }else if(this.value='merchant'){
                    MerchantID().then(res=>{
                        this.RegForm.id=res;
                    })
                }else{
                    this.RegForm.id=this.employeeid;
                }
                InsertUser(this.RegForm).then(res=>{
                    console.log(res);
                })
                this.$router.replace('/login');
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
            his.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
        }
        
    },
    
}
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>