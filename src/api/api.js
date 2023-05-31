import service from "./service";

//查询可购买机票接口
export function getFlightAble(params){
    return service({
        method:'get',
        url:'/flight',
        params
    })
}
