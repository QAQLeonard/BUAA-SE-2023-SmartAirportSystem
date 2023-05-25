package top.leonardsaikou.backend.controller;

import top.leonardsaikou.backend.entity.Ticket;
import top.leonardsaikou.backend.mapper.TicketMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    public List<Ticket> getTickets()
    {
        List<Ticket> ticketsList = ticketMapper.selectList(null);
        System.out.println(ticketsList);
        return ticketsList;
    }

    @ApiOperation("根据id获取单个机票信息")
    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable String id)
    {
        Ticket ticket = ticketMapper.selectById(id);
        System.out.println(ticket);
        return ticket;
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
}
