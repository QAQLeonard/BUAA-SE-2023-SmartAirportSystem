package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.format.annotation.DateTimeFormat;
import top.leonardsaikou.backend.entity.Ticket;
import top.leonardsaikou.backend.mapper.TicketMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class TicketController
{
    private final TicketMapper ticketMapper;

    public TicketController(TicketMapper ticketMapper)
    {
        this.ticketMapper = ticketMapper;
    }

    @ApiOperation("获取全部机票信息")
    @GetMapping("/ticket")
    public String getTickets() throws JsonProcessingException
    {
        List<Ticket> ticketsList = ticketMapper.selectList(null);
        //System.out.println(ticketsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(ticketsList);
        return "{" + "\"TotalNumber\":" + ticketsList.size() + ",\"ticketData\":" + json + "}";
    }

    @ApiOperation("根据id获取单个机票信息")
    @GetMapping("/ticket/{id}")
    public String getTicketById(@PathVariable String id) throws JsonProcessingException
    {
        Ticket ticket = ticketMapper.selectById(id);
        //System.out.println(ticket);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(ticket);
        return "{" + "\"TotalNumber\":" + 1 + ",\"ticketData\":" + json + "}";
    }

    @ApiOperation("插入机票信息")
    @PostMapping("/ticket")
    public String addTicket(@RequestBody Ticket ticket)
    {
        int i = ticketMapper.insert(ticket);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新机票信息")
    @PutMapping("/ticket")
    public String updateTicket(@RequestBody Ticket ticket)
    {
        int i = ticketMapper.updateById(ticket);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除机票信息")
    @DeleteMapping("/ticket/{id}")
    public String deleteTicketById(@PathVariable String id)
    {
        int i = ticketMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("获取创建新一次飞行的新id")
    @GetMapping("/ticket-id")
    public String generateNewId()
    {
        // 查询当前最大的ticket ID
        int maxIdNum = ticketMapper.selectMaxId();
        if (maxIdNum == 0)
        {
            // 如果还没有ticket，那么返回 "T0001"
            return "T0001";
        }
        else
        {
            // 否则，增加当前最大的ticket ID
            return "T" + String.format("%04d", maxIdNum + 1);
        }
    }

    @ApiOperation("获取某一天的所有票价之和")
    @GetMapping("/totalTicketPrice/{date}")
    public Double getTotalTicketPrice(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
    {
        return ticketMapper.selectTotalPriceByDate(date);
    }

    @ApiOperation("获取某一乘客的所有机票信息")
    @GetMapping("/ticketByPassengerId/{passengerId}")
    public String getTicketByPassengerId(@PathVariable String passengerId) throws JsonProcessingException
    {
        List<Ticket> ticketsList = ticketMapper.selectByPassengerId(passengerId);
        //System.out.println(ticketsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(ticketsList);
        return "{" + "\"TotalNumber\":" + ticketsList.size() + ",\"ticketData\":" + json + "}";
    }

    @ApiOperation("获取某一周的所有票价")
    @GetMapping("/weekTicketPrice/{date}")
    public String getweekTicketPrice(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"TotalNumber\":7,\"ticketData\":[");
        for(int i = 0; i < 7; i++)
        {
            stringBuilder.append("{");
            stringBuilder.append(date.plusDays(i));
            stringBuilder.append(":");
            stringBuilder.append(ticketMapper.selectTotalPriceByDate(date.plusDays(i)));
            stringBuilder.append("}");
            if(i != 6)
            {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]}");

        return stringBuilder.toString();
    }

}
