<template>
    <div>
        <el-tabs type="border-card" >
            <el-tab-pane label="旅客注册" >
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="usename">
                    <el-input v-model="ruleForm.usename"></el-input>
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
                    <el-button id="sendCodeBtn" type="info" round>
                    发送验证码
                    </el-button>
                    </el-form-item>
                    <el-form-item label="验证码" prop="account">
                    <el-input v-model="ruleForm.Checkcode"></el-input>
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
import { InsertUser } from '@/api/api';
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
        ruleForm: {
            pass: '',
            checkPass: '',
            phoneNumber:'',
            usename:'',
            Gender:'',
            Checkcode:'',
        },

        rules: {
            pass: [
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
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                alert('submit!');
                InsertUser(this.ruleForm).then(res=>{
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
        }
        
    }
}
</script>