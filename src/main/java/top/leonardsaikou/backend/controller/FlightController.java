package top.leonardsaikou.backend.controller;

import top.leonardsaikou.backend.entity.Flight;
import top.leonardsaikou.backend.mapper.FlightMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    private final FlightMapper flightMapper;

    public FlightController(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }

    @ApiOperation("获取全部航班信息,返回值为所有航班信息的数量")
    @GetMapping("/flight")
    public String getFlight() {
        List<Flight> flightList = flightMapper.selectList(null);
        System.out.println(flightList);
        return flightList.size() + "\n" + flightList.toString();
    }

    @ApiOperation("根据id获取单个航班信息")
    @GetMapping("/flight/{id}")
    public String getFlightById(@PathVariable String id) {
        Flight flight = flightMapper.selectById(id);
        System.out.println(flight);
        return "flight";
    }

    @ApiOperation("插入航班信息")
    @PostMapping("/flight")
    public String addFlight(Flight flight) {
        int i = flightMapper.insert(flight);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新航班信息")
    @PutMapping("/flight")
    public String updateFlight(Flight flight) {
        int i = flightMapper.updateById(flight);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除航班信息")
    @DeleteMapping("/flight/{id}")
    public String deleteFlightById(@PathVariable String id) {
        int i = flightMapper.deleteById(id);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("查询特定航班,提供起点，终点")
    @GetMapping("/flight/{departureCity}/{arrivalCity}")
    public String getFlightByCity(@PathVariable String departureCity,@PathVariable String arrivalCity) {
        List<Flight> flightList = flightMapper.selectByCity(departureCity,arrivalCity);
        System.out.println(flightList);
        return "flight";
    }

    @ApiOperation("查询回收站中的航班,返回值为回收站中航班的数量")
    @GetMapping("/flight/recycle")
    public int getRecycleBin() {
        List<Flight> flightList = flightMapper.selectRecycleBin();
        System.out.println(flightList);
        return flightList.size();
    }

    @ApiOperation("恢复按钮：修改特定航班的状态，前端提供航班号，从“回收站中”改为“未发布”。")
    @PutMapping("/flight/recover/{id}")
    public String recoverFlight(@PathVariable String id) {
        int i = flightMapper.recoverFlight(id);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("查询：前端提供航班号，查找对应航班号且状态为“回收站中”的航班。")
    @GetMapping("/flight/recycle/{id}")
    public String getRecycleBinById(@PathVariable String id) {
        List<Flight> flightList = flightMapper.selectRecycleBinById(id);
        System.out.println(flightList);
        return "success\n" + flightList.toString();
    }

    @ApiOperation("查询未发布的航班,返回值为未发布航班的数量")
    @GetMapping("/flight/unpublished")
    public String getUnpublished() {
        List<Flight> flightList = flightMapper.selectUnpublished();
        System.out.println(flightList);
        return flightList.size() + "\n" + flightList;
    }


}
