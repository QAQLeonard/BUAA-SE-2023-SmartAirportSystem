import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
import axios from 'axios'
import router from './router'
import api from './api/api.js'
import service from './api/service'

Vue.use(ElementUI)
// Vue.prototype.axios = axios //挂载到原型，可以在全局使用
Vue.config.productionTip = false
Vue.prototype.service = service //将api挂载到原型实例，不用再在每一个组件中引用接口文件

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
