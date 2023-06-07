import Vue from "vue"
import Router from "vue-router"
import Home from '../components/Home.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path:'/',
            name:'首页',
            redirect:'/login',
            //iconClass:'fa fa-plane',
        },
        {
            path:'/home',
            name:'机场主页',
            iconClass:'fa fa-plane',
            redirect:'/home/personalinformation',
            component:()=>import('@/components/Home'),
            // hidden:false,
            meta:{
                hidden:true
            },
            children:[
                {
                    path: '/home/ticketbook',
                    name:'机票预订',
                    iconClass:'fa fa-fighter-jet',
                    component:()=>import('@/components/ji-chang-zhu-ye/TicketBook')
                },
                {
                    path: '/home/myorder',
                    name:'我的订单',
                    iconClass:'fa fa-cny',
                    component:()=>import('@/components/ji-chang-zhu-ye/MyOrder')
                },
                {
                    path: '/home/parkingbook',
                    name:'停车位预订',
                    iconClass:'fa fa-car',
                    component:()=>import('@/components/ji-chang-zhu-ye/ParkingBook')
                },
                {
                    path: '/home/scheduleview',
                    name:'航班表查看',
                    iconClass:'fa fa-table',
                    component:()=>import('@/components/ji-chang-zhu-ye/ScheduleView')
                },
            ]
        },
        {
            path:'/home',
            name:'机场报修',
            iconClass:'fa fa-wrench',
            component:()=>import('@/components/Home'),
            meta:{
                hidden:true
            },
            children:[
                {
                    path: '/home/createrequest',
                    name:'创建请求',
                    iconClass:'fa fa-tasks',
                    component:()=>import('@/components/ji-chang-bao-xiu/CreateRequest')
                },
                {
                    path: '/home/handleaffair',
                    name:'处理事务',
                    iconClass:'fa fa-check',
                    component:()=>import('@/components/ji-chang-bao-xiu/HandleAffair')
                },
            ]
        },
        {
            path:'/home',
            name:'机场管理',
            iconClass:'fa fa-user-plus',
            component:()=>import('@/components/Home'),
            meta:{
                hidden:true
            },
            children:[
                {
                    path: '/home/approvalrequest',
                    name:'审批请求',
                    iconClass:'fa fa-bookmark',
                    component:()=>import('@/components/ji-chang-guan-li/ApprovalRequest')
                },
                {
                    path: '/home/dataviewing',
                    name:'数据查看',
                    iconClass:'fa fa-line-chart',
                    component:()=>import('@/components/ji-chang-guan-li/DataViewing')
                },
                {
                    path: '/home/importflights',
                    name:'导入航班',
                    iconClass:'fa fa-cloud-upload',
                    component:()=>import('@/components/ji-chang-guan-li/ImportFlights')
                },
            ]
        },
        {
            path:'/home',
            name:'商家入驻',
            iconClass:'fa fa-handshake-o',
            component:()=>import('@/components/Home'),
            meta:{
                hidden:true
            },
            children:[
                {
                    path: '/home/myshop',
                    name:'我的店铺',
                    iconClass:'fa fa-shopping-bag',
                    component:()=>import('@/components/shang-jia-ru-zhu/MyShop')
                },
                {
                    path: '/home/residencyapplication',
                    name:'入驻申请',
                    iconClass:'fa fa-sign-in',
                    component:()=>import('@/components/shang-jia-ru-zhu/ResidencyApplication')
                },
            ]
        },
        {
            path:'/home',
            name:'航司',
            iconClass:'fa fa-vcard',
            component:()=>import('@/components/Home'),
            meta:{
                hidden:true
            },
            children:[
                {
                    path: '/home/bin',
                    name:'回收站',
                    iconClass:'fa fa-trash',
                    component:()=>import('@/components/hang-si/Bin')
                },
                {
                    path: '/home/flightrelease',
                    name:'航班发布',
                    iconClass:'fa fa-sitemap',
                    component:()=>import('@/components/hang-si/FlightRelease')
                },
                {
                    path: '/home/publishedflights',
                    name:'已发布',
                    iconClass:'fa fa-anchor',
                    component:()=>import('@/components/hang-si/PublishedFlights')
                },
            ]
        },
        {
            path:'/home',
            name:'个人中心',
            iconClass:'fa fa-users',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/personalinformation',
                    name:'个人信息',
                    iconClass:'fa fa-user',
                    component:()=>import('@/components/ge-ren-zhong-xin/PersonalInformation')
                },
                // {
                //     path: '/home/myalerts',
                //     name:'我的通知',
                //     iconClass:'fa fa-bell',
                //     component:()=>import('@/components/ge-ren-zhong-xin/MyAlerts')
                // },
            ]
        },
        {
            path:'/login',
            name:'登陆',
            iconClass:'fa fa-sign-in',
            component:()=>import('@/components/zhuce-denglu/Login'),
            children:[
                {
                    path: '/home/login',
                    name:'登录界面',
                    iconClass:'fa fa-registered ',
                    component:()=>import('@/components/zhuce-denglu/Login')
                },
                
            ]
        },
        {
            path:'/register',
            name:'注册',
            iconClass:'fa fa-sign-in',
            component:()=>import('@/components/zhuce-denglu/RegisterTo'),
            children:[
                {
                    path: '/home/register',
                    name:'注册界面',
                    iconClass:'fa fa-registered ',
                    component:()=>import('@/components/zhuce-denglu/RegisterTo')
                },
                
            ]
        }
    ],
    mode:'history'
})