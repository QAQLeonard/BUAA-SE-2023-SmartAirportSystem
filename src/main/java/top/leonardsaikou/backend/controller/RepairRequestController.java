package top.leonardsaikou.backend.controller;

import top.leonardsaikou.backend.entity.RepairRequest;
import top.leonardsaikou.backend.mapper.RepairRequestMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    public List<RepairRequest> getRepairRequests()
    {
        List<RepairRequest> repairRequestList = repairRequestMapper.selectList(null);
        System.out.println(repairRequestList);
        return repairRequestList;
    }

    @ApiOperation("根据id获取单个维修请求信息")
    @GetMapping("/repairRequest/{id}")
    public RepairRequest getRepairRequestById(@PathVariable String id)
    {
        RepairRequest repairRequest = repairRequestMapper.selectById(id);
        System.out.println(repairRequest);
        return repairRequest;
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
