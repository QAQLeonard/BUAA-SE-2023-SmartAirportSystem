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

    @ApiOperation("获取全部航班信息")
    @GetMapping("/flight")
    public String getFlight() {
        List<Flight> flightList = flightMapper.selectList(null);
        System.out.println(flightList);
        return "flight";
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
}
