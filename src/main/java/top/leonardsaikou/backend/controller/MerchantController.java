package top.leonardsaikou.backend.controller;

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
    public List<Merchant> getMerchants()
    {
        List<Merchant> merchantsList = merchantMapper.selectList(null);
        System.out.println(merchantsList);
        return merchantsList;
    }

    @ApiOperation("根据id获取单个商家信息")
    @GetMapping("/merchant/{id}")
    public Merchant getMerchantById(@PathVariable String id)
    {
        Merchant merchant = merchantMapper.selectById(id);
        System.out.println(merchant);
        return merchant;
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
}
