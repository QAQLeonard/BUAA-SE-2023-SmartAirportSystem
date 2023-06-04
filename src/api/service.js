import axios from "axios";
import { Message } from "element-ui";

const service = axios.create({
    baseURL: '/api', //baseURL自动加在请求地址上
    timeout: 3000
});

service.interceptors.request.use((config) => {
    return config
}, (error) => {
    return Promise.reject(error)
})

service.interceptors.response.use((response) => {
    let status = response.status
    let message = response.statusText
    console.log(response)
    if (status !== 200) {
        Message({ message: message || 'error', type: 'warning' })
    }
    return response
}, (error) => {
    return Promise.reject(error)
})



export default service;