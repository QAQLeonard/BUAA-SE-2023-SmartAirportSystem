package top.leonardsaikou.backend.controller;

import top.leonardsaikou.backend.entity.Passenger;
import top.leonardsaikou.backend.mapper.PassengerMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PassengerController
{
    private final PassengerMapper passengerMapper;

    public PassengerController(PassengerMapper passengerMapper)
    {
        this.passengerMapper = passengerMapper;
    }

    @ApiOperation("获取全部乘客信息")
    @GetMapping("/passenger")
    public List<Passenger> getPassengers()
    {
        List<Passenger> passengersList = passengerMapper.selectList(null);
        System.out.println(passengersList);
        return passengersList;
    }

    @ApiOperation("根据id获取单个乘客信息")
    @GetMapping("/passenger/{id}")
    public Passenger getPassengerById(@PathVariable String id)
    {
        Passenger passenger = passengerMapper.selectById(id);
        System.out.println(passenger);
        return passenger;
    }

    @ApiOperation("插入乘客信息")
    @PostMapping("/passenger")
    public String addPassenger(@RequestBody Passenger passenger)
    {
        int i = passengerMapper.insert(passenger);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新乘客信息")
    @PutMapping("/passenger")
    public String updatePassenger(@RequestBody Passenger passenger)
    {
        int i = passengerMapper.updateById(passenger);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除乘客信息")
    @DeleteMapping("/passenger/{id}")
    public String deletePassengerById(@PathVariable String id)
    {
        int i = passengerMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }
}