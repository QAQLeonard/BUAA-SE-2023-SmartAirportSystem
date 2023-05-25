package top.leonardsaikou.backend.controller;

import top.leonardsaikou.backend.entity.MerchantApplication;
import top.leonardsaikou.backend.mapper.MerchantApplicationMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    public List<MerchantApplication> getMerchantApplications()
    {
        List<MerchantApplication> merchantApplicationsList = merchantApplicationMapper.selectList(null);
        System.out.println(merchantApplicationsList);
        return merchantApplicationsList;
    }

    @ApiOperation("根据id获取单个商户申请信息")
    @GetMapping("/merchantApplication/{id}")
    public MerchantApplication getMerchantApplicationById(@PathVariable String id)
    {
        MerchantApplication merchantApplication = merchantApplicationMapper.selectById(id);
        System.out.println(merchantApplication);
        return merchantApplication;
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
}
