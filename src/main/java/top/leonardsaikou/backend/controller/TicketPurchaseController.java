package top.leonardsaikou.backend.controller;

import top.leonardsaikou.backend.entity.TicketPurchase;
import top.leonardsaikou.backend.mapper.TicketPurchaseMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketPurchaseController
{
    private final TicketPurchaseMapper ticketPurchaseMapper;

    public TicketPurchaseController(TicketPurchaseMapper ticketPurchaseMapper)
    {
        this.ticketPurchaseMapper = ticketPurchaseMapper;
    }

    @ApiOperation("获取全部票据购买信息")
    @GetMapping("/ticketPurchase")
    public List<TicketPurchase> getTicketPurchases()
    {
        List<TicketPurchase> ticketPurchaseList = ticketPurchaseMapper.selectList(null);
        System.out.println(ticketPurchaseList);
        return ticketPurchaseList;
    }

    @ApiOperation("根据id获取单个票据购买信息")
    @GetMapping("/ticketPurchase/{id}")
    public TicketPurchase getTicketPurchaseById(@PathVariable String id)
    {
        TicketPurchase ticketPurchase = ticketPurchaseMapper.selectById(id);
        System.out.println(ticketPurchase);
        return ticketPurchase;
    }

    @ApiOperation("插入票据购买信息")
    @PostMapping("/ticketPurchase")
    public String addTicketPurchase(@RequestBody TicketPurchase ticketPurchase)
    {
        int i = ticketPurchaseMapper.insert(ticketPurchase);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新票据购买信息")
    @PutMapping("/ticketPurchase")
    public String updateTicketPurchase(@RequestBody TicketPurchase ticketPurchase)
    {
        int i = ticketPurchaseMapper.updateById(ticketPurchase);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除票据购买信息")
    @DeleteMapping("/ticketPurchase/{id}")
    public String deleteTicketPurchaseById(@PathVariable String id)
    {
        int i = ticketPurchaseMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }
}
