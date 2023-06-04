package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.ParkingLotApplication;
import top.leonardsaikou.backend.mapper.ParkingLotApplicationMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotApplicationController
{

    private final ParkingLotApplicationMapper parkingLotApplicationMapper;

    public ParkingLotApplicationController(ParkingLotApplicationMapper parkingLotApplicationMapper)
    {
        this.parkingLotApplicationMapper = parkingLotApplicationMapper;
    }

    @ApiOperation("获取全部停车位申请信息")
    @GetMapping("/parkingLotApplication")
    public String getParkingLotApplications() throws JsonProcessingException
    {
        List<ParkingLotApplication> parkingLotApplicationsList = parkingLotApplicationMapper.selectList(null);
        //System.out.println(parkingLotApplicationsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(parkingLotApplicationsList);
        return "{" + "\"TotalNumber\":" + parkingLotApplicationsList.size() + ",\"parkingLotApplicationData\":" + json + "}";
    }

    @ApiOperation("根据id获取单个停车位申请信息")
    @GetMapping("/parkingLotApplication/{id}")
    public String getParkingLotApplicationById(@PathVariable String id) throws JsonProcessingException
    {
        ParkingLotApplication parkingLotApplication = parkingLotApplicationMapper.selectById(id);
        //System.out.println(parkingLotApplication);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(parkingLotApplication);
        return "{" + "\"TotalNumber\":" + 1 + ",\"parkingLotApplicationData\":" + json + "}";
    }

    @ApiOperation("插入停车位申请信息")
    @PostMapping("/parkingLotApplication")
    public String addParkingLotApplication(@RequestBody ParkingLotApplication parkingLotApplication)
    {
        int i = parkingLotApplicationMapper.insert(parkingLotApplication);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新停车位申请信息")
    @PutMapping("/parkingLotApplication")
    public String updateParkingLotApplication(@RequestBody ParkingLotApplication parkingLotApplication)
    {
        int i = parkingLotApplicationMapper.updateById(parkingLotApplication);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

}
