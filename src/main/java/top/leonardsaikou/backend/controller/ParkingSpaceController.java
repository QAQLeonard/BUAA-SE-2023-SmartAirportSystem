package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.ParkingSpace;
import top.leonardsaikou.backend.mapper.ParkingSpaceMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingSpaceController
{
    private final ParkingSpaceMapper parkingSpaceMapper;

    public ParkingSpaceController(ParkingSpaceMapper parkingSpaceMapper)
    {
        this.parkingSpaceMapper = parkingSpaceMapper;
    }

    @ApiOperation("获取全部停车位信息")
    @GetMapping("/parkingSpace")
    public String getParkingSpaces() throws JsonProcessingException
    {
        List<ParkingSpace> parkingSpacesList = parkingSpaceMapper.selectList(null);
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
        ParkingSpace parkingSpace = parkingSpaceMapper.selectById(id);
        //System.out.println(parkingSpace);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(parkingSpace);
        return "{"+"\"TotalNumber\":" + 1 + ",\"parkingSpaceData\":"+ json + "}";
    }

    @ApiOperation("插入停车位信息")
    @PostMapping("/parkingSpace")
    public String addParkingSpace(@RequestBody ParkingSpace parkingSpace)
    {
        int i = parkingSpaceMapper.insert(parkingSpace);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新停车位信息")
    @PutMapping("/parkingSpace")
    public String updateParkingSpace(@RequestBody ParkingSpace parkingSpace)
    {
        int i = parkingSpaceMapper.updateById(parkingSpace);
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
        int i = parkingSpaceMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }
}
