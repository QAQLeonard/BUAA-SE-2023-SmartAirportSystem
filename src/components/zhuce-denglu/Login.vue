<template>
    <div class="body">
        <div class="container">
            <div class="form-box" :class="{ 'transformR': (role0 === false), 'transformL': (role0 === true) }">
                <!-- 注册 -->
                <div class="register-box" :model="form"
                    :class="{ 'hidden': (role0 === true)/*,'transformR':(role0===false)*/ }">
                    <h1>login</h1>
                    <input v-model="form.username" type="text" placeholder="用户名">
                    <input v-model="form.code" type="password" placeholder="验证码">
                    <button @click="send()">发送验证码</button>
                    <button @click="codelogin()">登录</button>
                </div>
                <!-- 登录 -->
                <div class="login-box" :model="form"
                    :class="{ 'hidden': (role0 === false)/*,'transformL':(role0 === true)*/ }">
                    <h1>login</h1>
                    <input v-model="form.username" type="text" placeholder="用户名">
                    <input v-model="form.password" type="password" placeholder="密码">
                    <button @click="login()">登录</button>
                </div>
            </div>
            <div class="con-box left">
                <h2><span>天航</span>智慧机场</h2>
                <p><span>便捷</span>你的出行</p>
                <img src="https://img2.baidu.com/it/u=1091118846,3966823999&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                    alt="图片加载失败">
                <p>没有账号？</p>
                <button id="login" @click="changeToRegi()">去注册</button>
                <button id="login1" @click="change()">密码登录</button>
            </div>
            <div class="con-box right">
                <h2><span>天航</span>智慧机场</h2>
                <p><span>便捷</span>你的出行</p>
                <img src="https://photo.16pic.com/00/92/09/16pic_9209376_b.jpg" alt="图片加载失败">
                <p>密码登录太麻烦？</p>
                <button id="register" @click="change()">验证码登录</button>
            </div>
        </div>
    </div>
</template>
<script>
import { getUserData } from '@/api/api';
import { sendCode } from '@/api/api';
// // 要操作到的元素
// let login = document.getElementById("login");
// let register = document.getElementById("register");
// let form_box = document.getElementsByClassName("form-box")[0];
// let register_box = document.getElementsByClassName("register-box")[0];
// let login_box = document.getElementsByClassName("login-box")[0];
// // 注册点击事件
// register.addEventListener('click', () => {
//     form_box.style.transform = 'translateX(80%)';
//     login_box.classList.add('hidden');
//     register_box.classList.remove('hidden');
// })
// // 登录点击事件
// login.addEventListener('click', () => {
//     form_box.style.transform = 'translateX(0%)';
//     login_box.classList.remove('hidden');
//     register_box.classList.add('hidden');
// })
export default {
    data() {
        return {
            role0: true,
            form: {
                username: '',
                password: '',
                code: ''
            },
            password: '',
            total: '',
            code: '-1',
            Email: {
                toEmail: "1626450436@qq.com"
            },
        }
    },
    methods: {
        login() {
            console.log(this.form.username)
            getUserData(this.form.username).then(res => {
                console.log(this.form.username)
                if (this.form.username === '') {
                    this.$message({ message: '请输入用户名', type: 'error' })
                } else {
                    console.log(res)
                    if (res.status === 200) {
                        this.total = res.data.TotalNumber
                        if (res.data.userData === null) {
                            this.$message({ message: '用户名不存在', type: 'error' })
                        } else {
                            this.password = res.data.userData.password
                            console.log(this.password)
                            if (this.password === this.form.password) {
                                this.$message({ message: '登录成功', type: 'success' })
                                localStorage.setItem('username', this.form.username)
                                localStorage.setItem('userid', res.data.userData.id)
                                localStorage.setItem('role',res.data.userData.role)
                                console.log(localStorage.getItem('username'))
                                this.$router.replace('/home')
                            } else if (this.form.password === '') {
                                this.$message({ message: '请输入密码', type: 'error' })
                            } else {
                                this.$message({ message: '密码错误', type: 'error' })
                            }
                        }

                    }
                }
            })
        },
        codelogin() {
            console.log(this.form.username)
            getUserData(this.form.username).then(res => {
                if (this.form.username === '') {
                    this.$message({ message: '请输入用户名', type: 'error' })
                } else {
                    console.log(res)
                    if (res.status === 200) {
                        this.total = res.data.TotalNumber
                        if (res.data.userData === null) {
                            this.$message({ message: '用户名不存在', type: 'error' })
                        } else {
                            console.log(this.code)
                            console.log(this.form.code)
                            // this.password = res.data.userData.password
                            // console.log(this.password)
                            if (this.code == this.form.code) {
                                this.$message({ message: '登录成功', type: 'success' })
                                localStorage.setItem('username', this.form.username)
                                localStorage.setItem('userid', res.data.userData.id)
                                localStorage.setItem('role',res.data.userData.role)
                                console.log(localStorage.getItem('username'))
                                this.$router.push('/home')
                            } else if (this.form.code === '') {
                                this.$message({ message: '请输入验证码', type: 'error' })
                            } else {
                                this.$message({ message: '验证码错误', type: 'error' })
                            }
                        }

                    }
                }
            })
        },
        change() {
            this.role0 = !this.role0
        },
        send() {
            getUserData(this.form.username).then(res => {
                if (this.form.username === '') {
                    this.$message({ message: '请输入用户名', type: 'error' })
                } else {
                    if (res.status === 200) {
                        this.total = res.data.TotalNumber
                        if (res.data.userData === null) {
                            this.$message({ message: '用户名不存在', type: 'error' })
                        } else {
                            this.Email.toEmail = res.data.userData.email
                            sendCode(this.Email).then(res => {
                                console.log(res)
                                if(res.status === 200)
                                {
                                    this.$message({ message: '验证码发送成功', type: 'success' })
                                    this.code = res.data.toString()
                                    console.log(this.code)

                                }
                            })
                        }
                    }
                }
            })

        },
        changeToRegi() {
            this.$router.replace('/register')
        }
    }
}
</script >
<style lang="scss" scoped>
* {
    /* 初始化 */
    margin: 0;
    padding: 0;
}

.body {
    /* 100%窗口高度 */
    height: 100vh;
    /* 弹性布局 水平+垂直居中 */
    display: flex;
    justify-content: center;
    align-items: center;
    /* 渐变背景 */
    background: linear-gradient(200deg, #f3e7e9, #e3eeff);
}


.container {
    background-color: #fff;
    width: 650px;
    height: 415px;
    border-radius: 5px;
    /* 阴影 */
    box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1);
    /* 相对定位 */
    position: relative;
}

.form-box {
    position: absolute;
    top: -10%;
    left: 5%;
    background-color: #69c0ff;
    width: 320px;
    height: 500px;
    border-radius: 5px;
    box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2;
    /* 动画过渡 加速后减速 */
    transition: 0.5s ease-in-out;
}

.register-box,
.login-box {
    /* 弹性布局 垂直排列 */
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
}

.hidden {
    display: none;
    transition: 0.5s;
}

.transformR {
    transform: translateX(80%);
}

.transformL {
    transform: translateX(0%);
}

h1 {
    text-align: center;
    margin-bottom: 25px;
    /* 大写 */
    text-transform: uppercase;
    color: #fff;
    /* 字间距 */
    letter-spacing: 5px;
    font-size: 50px;
}

input {
    background-color: transparent;
    width: 70%;
    color: #fff;
    border: none;
    border-bottom: 1px solid rgba(255, 255, 255, 0.4);
    padding: 10px 0;
    text-indent: 10px;
    margin: 8px 0;
    font-size: 14px;
    letter-spacing: 2px;
}

input::placeholder {
    color: #fff;
}

input:focus {
    color: #3377fe;
    outline: none;
    border-bottom: 1px solid #a262ad80;
    transition: 0.5s;
}

input:focus::placeholder {
    opacity: 0;
}

.form-box button {
    width: 70%;
    margin-top: 35px;
    background-color: #f6f6f6;
    outline: none;
    border-radius: 8px;
    padding: 13px;
    color: #3377fe;
    letter-spacing: 2px;
    border: none;
    cursor: pointer;

}

.form-box button:hover {
    background-color: #3377fe;
    color: #f6f6f6;
    transition: background-color 0.5s ease;
}

.con-box {
    width: 50%;
    /* 弹性布局 垂直排列 居中 */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    /* 绝对定位 居中 */
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
}

.con-box.left {
    left: -2%;
}

.con-box.right {
    right: -2%;
}

.con-box h2 {
    color: #8e9aaf;
    font-size: 25px;
    font-weight: bold;
    letter-spacing: 3px;
    text-align: center;
    margin-bottom: 4px;
}

.con-box p {
    font-size: 12px;
    letter-spacing: 2px;
    color: #8e9aaf;
    text-align: center;
}

.con-box span {
    color: #69c0ff;
}

.con-box img {
    width: 150px;
    height: 150px;
    opacity: 0.9;
    margin: 40px 0;
}

.con-box button {
    margin-top: 3%;
    background-color: #fff;
    color: #068ff1;
    border: 1px solid #69c0ff;
    padding: 6px 10px;
    border-radius: 5px;
    letter-spacing: 1px;
    outline: none;
    cursor: pointer;
}

.con-box button:hover {
    background-color: #69c0ff;
    color: #fff;
}
</style>