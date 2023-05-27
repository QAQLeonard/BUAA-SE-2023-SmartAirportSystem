package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    public String getTicketPurchases() throws JsonProcessingException
    {
        List<TicketPurchase> ticketPurchaseList = ticketPurchaseMapper.selectList(null);
        //System.out.println(ticketPurchaseList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(ticketPurchaseList);
        return "{"+"\"TotalNumber\":" + ticketPurchaseList.size() + ",\"ticketPurchaseData\":"+ json + "}";
    }

    @ApiOperation("根据id获取单个票据购买信息")
    @GetMapping("/ticketPurchase/{id}")
    public String getTicketPurchaseById(@PathVariable String id) throws JsonProcessingException
    {
        TicketPurchase ticketPurchase = ticketPurchaseMapper.selectById(id);
        //System.out.println(ticketPurchase);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(ticketPurchase);
        return "{"+"\"TotalNumber\":" + 1 + ",\"ticketPurchaseData\":"+ json + "}";
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
