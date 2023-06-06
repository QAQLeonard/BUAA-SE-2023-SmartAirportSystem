package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.MerchantApplication;
import top.leonardsaikou.backend.mapper.MerchantApplicationMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MerchantApplicationController
{
    private final MerchantApplicationMapper merchantApplicationMapper;

    public MerchantApplicationController(MerchantApplicationMapper merchantApplicationMapper)
    {
        this.merchantApplicationMapper = merchantApplicationMapper;
    }

    @ApiOperation("获取全部商户申请信息")
    @GetMapping("/merchantApplication")
    public String getMerchantApplications() throws JsonProcessingException
    {
        List<MerchantApplication> merchantApplicationsList = merchantApplicationMapper.selectList(null);
        //System.out.println(merchantApplicationsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(merchantApplicationsList);
        return "{"+"\"TotalNumber\":" + merchantApplicationsList.size() + ",\"merchantApplicationData\":"+ json + "}";
    }

    @ApiOperation("根据id获取单个商户申请信息")
    @GetMapping("/merchantApplication/{id}")
    public String getMerchantApplicationById(@PathVariable String id) throws JsonProcessingException
    {
        MerchantApplication merchantApplication = merchantApplicationMapper.selectById(id);
        List<MerchantApplication> merchantApplicationsList = new ArrayList<>();
        merchantApplicationsList.add(merchantApplication);
        //System.out.println(merchantApplication);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(merchantApplicationsList);
        return "{"+"\"TotalNumber\":" + 1 + ",\"merchantApplicationData\":"+ json + "}";
    }

    @ApiOperation("插入商户申请信息")
    @PostMapping("/merchantApplication")
    public String addMerchantApplication(@RequestBody MerchantApplication merchantApplication)
    {
        int i = merchantApplicationMapper.insert(merchantApplication);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新商户申请信息")
    @PutMapping("/merchantApplication")
    public String updateMerchantApplication(@RequestBody MerchantApplication merchantApplication)
    {
        int i = merchantApplicationMapper.updateById(merchantApplication);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除商户申请信息")
    @DeleteMapping("/merchantApplication/{id}")
    public String deleteMerchantApplicationById(@PathVariable String id)
    {
        int i = merchantApplicationMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("获取创建新一次商家申请的新id")
    @GetMapping("/merchantApplication-id")
    public String generateNewId()
    {
        int maxIdNum = merchantApplicationMapper.selectMaxId();
        if (maxIdNum == 0)
        {
            return "MA001";
        }
        else
        {
            return "MA" + String.format("%03d", maxIdNum + 1);
        }
    }
}
