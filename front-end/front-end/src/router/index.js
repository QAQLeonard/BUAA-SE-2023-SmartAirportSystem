import Vue from "vue"
import Router from "vue-router"
import Home from '../components/Home.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path:'/home',
            name:'机场主页',
            iconClass:'',
            redirect:'/home/ticketbook',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/ticketbook',
                    name:'机票预订',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-zhu-ye/TicketBook')
                },
                {
                    path: '/home/myorder',
                    name:'我的订单',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-zhu-ye/MyOrder')
                },
                {
                    path: '/home/parkingbook',
                    name:'停车位预订',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-zhu-ye/ParkingBook')
                },
                {
                    path: '/home/baggagetracking',
                    name:'行李跟踪',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-zhu-ye/BaggageTracking')
                },
                {
                    path: '/home/scheduleview',
                    name:'航班表查看',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-zhu-ye/ScheduleView')
                },
            ]
        },
        {
            path:'/home',
            name:'机场报修',
            iconClass:'',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/createrequest',
                    name:'创建请求',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-bao-xiu/CreateRequest')
                },
                {
                    path: '/home/handleaffair',
                    name:'处理事务',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-bao-xiu/HandleAffair')
                },
            ]
        },
        {
            path:'/home',
            name:'机场管理',
            iconClass:'',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/approvalrequest',
                    name:'审批请求',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-guan-li/ApprovalRequest')
                },
                {
                    path: '/home/dataviewing',
                    name:'数据查看',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-guan-li/DataViewing')
                },
                {
                    path: '/home/importflights',
                    name:'导入航班',
                    iconClass:'',
                    component:()=>import('@/components/ji-chang-guan-li/ImportFlights')
                },
            ]
        },
        {
            path:'/home',
            name:'商家入驻',
            iconClass:'',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/myshop',
                    name:'我的店铺',
                    iconClass:'',
                    component:()=>import('@/components/shang-jia-ru-zhu/MyShop')
                },
                {
                    path: '/home/residencyapplication',
                    name:'入驻申请',
                    iconClass:'',
                    component:()=>import('@/components/shang-jia-ru-zhu/ResidencyApplication')
                },
            ]
        },
        {
            path:'/home',
            name:'航司',
            iconClass:'',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/bin',
                    name:'回收站',
                    iconClass:'',
                    component:()=>import('@/components/hang-si/Bin')
                },
                {
                    path: '/home/flightrelease',
                    name:'航班发布',
                    iconClass:'',
                    component:()=>import('@/components/hang-si/FlightRelease')
                },
                {
                    path: '/home/publishedflights',
                    name:'已发布',
                    iconClass:'',
                    component:()=>import('@/components/hang-si/PublishedFlights')
                },
            ]
        },
        {
            path:'/home',
            name:'个人中心',
            iconClass:'',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/personalinformation',
                    name:'个人信息',
                    iconClass:'',
                    component:()=>import('@/components/ge-ren-zhong-xin/PersonalInformation')
                },
                {
                    path: '/home/myalerts',
                    name:'我的通知',
                    iconClass:'',
                    component:()=>import('@/components/ge-ren-zhong-xin/MyAlerts')
                },
            ]
        },
        {
            path:'/home',
            name:'客服',
            iconClass:'',
            component:()=>import('@/components/Home'),
            children:[
                {
                    path: '/home/customerservice',
                    name:'客服界面',
                    iconClass:'',
                    component:()=>import('@/components/ke-fu/CustomerService')
                },
            ]
        },
    ],
    mode:'history'
})