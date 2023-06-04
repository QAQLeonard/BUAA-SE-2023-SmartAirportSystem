import service from "./service";

//查询可购买机票接口
export function getFlightAble(params){
    return service({
        method:'get',
        url:'/flight',
        params
    })
}

export function getFlightUnpublished(params){
    return service({
        method:'get',
        url:'/flight-unpublished',
        params
    })
}
export function searchFlightTB(start,dest){
    return service({
        method:'get',
        url:`/flight/${start}/${dest}`,
        start,
        dest
    })
}

