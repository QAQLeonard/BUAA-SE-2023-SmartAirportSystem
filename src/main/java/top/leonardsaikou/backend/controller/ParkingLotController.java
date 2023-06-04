package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.ParkingLot;
import top.leonardsaikou.backend.mapper.ParkingLotMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController
{
    private final ParkingLotMapper parkingLotMapper;

    public ParkingLotController(ParkingLotMapper parkingLotMapper)
    {
        this.parkingLotMapper = parkingLotMapper;
    }

    @ApiOperation("获取全部停车位信息")
    @GetMapping("/parkingLot")
    public String getParkingSpaces() throws JsonProcessingException
    {
        List<ParkingLot> parkingSpacesList = parkingLotMapper.selectList(null);
        //System.out.println(parkingSpacesList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(parkingSpacesList);
        return "{"+"\"TotalNumber\":" + parkingSpacesList.size() + ",\"parkingSpaceData\":"+ json + "}";
    }

    @ApiOperation("根据id获取单个停车位信息")
    @GetMapping("/parkingSpace/{id}")
    public String getParkingSpaceById(@PathVariable String id) throws JsonProcessingException
    {
        ParkingLot parkingLot = parkingLotMapper.selectById(id);
        //System.out.println(parkingSpace);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(parkingLot);
        return "{"+"\"TotalNumber\":" + 1 + ",\"parkingSpaceData\":"+ json + "}";
    }

    @ApiOperation("插入停车位信息")
    @PostMapping("/parkingSpace")
    public String addParkingSpace(@RequestBody ParkingLot parkingLot)
    {
        int i = parkingLotMapper.insert(parkingLot);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新停车位信息")
    @PutMapping("/parkingSpace")
    public String updateParkingSpace(@RequestBody ParkingLot parkingLot)
    {
        int i = parkingLotMapper.updateById(parkingLot);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除停车位信息")
    @DeleteMapping("/parkingSpace/{id}")
    public String deleteParkingSpaceById(@PathVariable String id)
    {
        int i = parkingLotMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新车位在某一时段的状态")
    @PutMapping("/parkingSpace/{id}/{starttime}/{finishtime}")
    public String updateParkingSpaceStatus(@PathVariable String id, @PathVariable String starttime, @PathVariable String finishtime)
    {
        ParkingLot parkingLot = parkingLotMapper.selectById(id);
        int status = parkingLot.getStatus();
        for(int i = Integer.parseInt(starttime); i < Integer.parseInt(finishtime); i++)
        {
            status |= (1<<i);
        }
        parkingLot.setStatus(status);
        int i = parkingLotMapper.updateById(parkingLot);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("获取车位在某一时段的状态")
    @GetMapping("/parkingSpace/{id}/{timeperiod}")
    public String getParkingSpaceStatus(@PathVariable String id, @PathVariable String timeperiod)
    {
        ParkingLot parkingLot = parkingLotMapper.selectById(id);
        int status = parkingLot.getStatus();
        int time = Integer.parseInt(timeperiod);
        int result = (status >> time) & 1;
        return "{"+"\"status\":" + result + "}";
    }
}
