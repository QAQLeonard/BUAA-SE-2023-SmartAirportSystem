package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.Merchant;
import top.leonardsaikou.backend.mapper.MerchantMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MerchantController
{
    private final MerchantMapper merchantMapper;

    public MerchantController(MerchantMapper merchantMapper)
    {
        this.merchantMapper = merchantMapper;
    }

    @ApiOperation("获取全部商家信息")
    @GetMapping("/merchant")
    public String getMerchants() throws JsonProcessingException
    {
        List<Merchant> merchantsList = merchantMapper.selectList(null);
        //System.out.println(merchantsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(merchantsList);
        return "{"+"\"TotalNumber\":" + merchantsList.size() + ",\"merchantData\":"+ json + "}";
    }

    @ApiOperation("根据id获取单个商家信息")
    @GetMapping("/merchant/{id}")
    public String getMerchantById(@PathVariable String id) throws JsonProcessingException
    {
        Merchant merchant = merchantMapper.selectById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(merchant);
        return "{"+"\"TotalNumber\":" + 1 + ",\"merchantData\":"+ json + "}";
    }

    @ApiOperation("插入商家信息")
    @PostMapping("/merchant")
    public String addMerchant(@RequestBody Merchant merchant)
    {
        int i = merchantMapper.insert(merchant);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新商家信息")
    @PutMapping("/merchant")
    public String updateMerchant(@RequestBody Merchant merchant)
    {
        int i = merchantMapper.updateById(merchant);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除商家信息")
    @DeleteMapping("/merchant/{id}")
    public String deleteMerchantById(@PathVariable String id)
    {
        int i = merchantMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("获取创建新一个新商家的新id")
    @GetMapping("/merchant-id")
    public String generateNewId()
    {
        int maxIdNum = merchantMapper.selectMaxId();
        if (maxIdNum == 0)
        {
            return "M001";
        }
        else
        {
            // 否则，增加当前最大的flight ID
            return "M" + String.format("%03d", maxIdNum + 1);
        }
    }
}
