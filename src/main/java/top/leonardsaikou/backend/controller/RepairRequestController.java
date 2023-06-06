package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.RepairRequest;
import top.leonardsaikou.backend.mapper.RepairRequestMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepairRequestController
{
    private final RepairRequestMapper repairRequestMapper;

    public RepairRequestController(RepairRequestMapper repairRequestMapper)
    {
        this.repairRequestMapper = repairRequestMapper;
    }

    @ApiOperation("获取全部维修请求信息")
    @GetMapping("/repairRequest")
    public String getRepairRequests() throws JsonProcessingException
    {
        List<RepairRequest> repairRequestList = repairRequestMapper.selectList(null);
        //System.out.println(repairRequestList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(repairRequestList);
        return "{"+"\"TotalNumber\":" + repairRequestList.size() + ",\"repairRequestData\":"+ json + "}";
    }

    @ApiOperation("根据id获取单个维修请求信息")
    @GetMapping("/repairRequest/{id}")
    public String getRepairRequestById(@PathVariable String id) throws JsonProcessingException
    {
        RepairRequest repairRequest = repairRequestMapper.selectById(id);
        //System.out.println(repairRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<RepairRequest> repairRequestList = new ArrayList<>();
        repairRequestList.add(repairRequest);
        String json = objectMapper.writeValueAsString(repairRequestList);
        return "{"+"\"TotalNumber\":" + 1 + ",\"repairRequestData\":"+ json + "}";
    }

    @ApiOperation("插入维修请求信息")
    @PostMapping("/repairRequest")
    public String addRepairRequest(@RequestBody RepairRequest repairRequest)
    {
        int i = repairRequestMapper.insert(repairRequest);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新维修请求信息")
    @PutMapping("/repairRequest")
    public String updateRepairRequest(@RequestBody RepairRequest repairRequest)
    {
        int i = repairRequestMapper.updateById(repairRequest);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除维修请求信息")
    @DeleteMapping("/repairRequest/{id}")
    public String deleteRepairRequestById(@PathVariable String id)
    {
        int i = repairRequestMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }
}
