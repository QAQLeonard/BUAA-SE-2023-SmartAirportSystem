import service from "./service";
//查询可购买机票接口
export function getFlightAble(params) {
    return service({
        method: 'get',
        url: '/flight',
        params
    })
}
//航司航班发布界面获取所有航班信息接口
export function getFlightUnpublished(params) {
    return service({
        method: 'get',
        url: '/flight-unpublished',
        params
    })
}
//机票预订界面搜索接口
export function searchFlightTB(start, dest) {
    return service({
        method: 'get',
        url: `/flight/${start}/${dest}`,
        start,
        dest
    })
}
//航司三个界面搜索航班的接口
export function searchFlightHS(id) {
    return service({
        method: 'get',
        url: `/flight/${id}`,
        id
    })
}

//航司已发布界面获取所有航班信息接口
export function getFlightPublished(params) {
    return service({
        method: 'get',
        url: '/flight-published',
        params
    })
}
//航司回收站界面获取所有界面接口
export function getFlightDeleted(params) {
    return service({
        method: 'get',
        url: '/flight-deleted',
        params
    })
}

//航司回收站界面搜索航班接口
export function searchFlightDeleted(id) {
    return service({
        method: 'get',
        url: `/flight-deleted/${id}`,
        id
    })
}
//航司回收站界面恢复回收站中的航班
export function recoverDeleted(id) {
    return service({
        method: 'put',
        url: `/flight-recover/deletedToUnpublished/${id}`,
        id
    })
}

//航司回收站界面彻底删除航班
export function deleteFlight(id) {
    return service({
        method: 'delete',
        url: `/flight/${id}`,
        id
    })
}

//航班发布界面移动航班到回收站
export function moveUnpublishedToBin(id) {
    return service({
        method: 'put',
        url: `/flight-recover/unpublishedToDeleted/${id}`,
        id
    })
}

//航司已发布界面移动航班到回收站
export function movePublishedToBin(id) {
    return service({
        method:'put',
        url:`/flight-recover/publishedToDeleted/${id}`,
        id
    })
}

//航司航班发布界面发布航班接口
export function publishFlight(id){
    return service({
        method:'put',
        url:`/flight-publish/${id}`,
        id
    })
}
//编辑航班信息
export function editFlight(data)
{
    data = JSON.stringify(data)
    console.log(data)
    return service({
        method:'put',
        url:'/flight',
        headers: {'Content-Type': 'application/json'},
        data
    })
}

export function getUserData(id){
    return service({
        method:'get',
        url:`/user/${id}`,
        id
    })
}

export function SubmitMerchantApp(data){
    data=JSON.stringify(data);
    console.log(data);
    return service({
        method:'post',
        url:'/merchant',
        headers: {'Content-Type': 'application/json'},
        data:data
    })
}

export function InsertUser(data){
    data=JSON.stringify(data);
    console.log(data);
    return service({
        method:'post',
        url:'/user',
        headers: {'Content-Type': 'application/json'},
        data
    })
}
