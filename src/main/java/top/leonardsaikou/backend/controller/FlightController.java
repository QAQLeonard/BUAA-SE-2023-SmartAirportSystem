package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.Flight;
import top.leonardsaikou.backend.mapper.FlightMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController
{

    private final FlightMapper flightMapper;

    public FlightController(FlightMapper flightMapper)
    {
        this.flightMapper = flightMapper;
    }

    @ApiOperation("获取全部航班信息,返回值为所有航班信息的数量")
    @GetMapping("/flight")
    public String getFlight() throws JsonProcessingException
    {
        List<Flight> flightList = flightMapper.selectList(null);
        //System.out.println(flightList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(flightList);
        return "{" + "\"TotalNumber\":" + flightList.size() + ",\"flightData\":" + json + "}";
    }

    @ApiOperation("根据id获取单个航班信息")
    @GetMapping("/flight/{id}")
    public String getFlightById(@PathVariable String id) throws JsonProcessingException
    {
        Flight flight = flightMapper.selectById(id);
        //System.out.println(flight);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(flight);
        return "{" + "\"TotalNumber\":" + 1 + ",\"flightData\":" + json + "}";
    }

    @ApiOperation("插入航班信息")
    @PostMapping("/flight")
    public String addFlight(Flight flight)
    {
        int i = flightMapper.insert(flight);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新航班信息")
    @PutMapping("/flight")
    public String updateFlight(Flight flight)
    {
        int i = flightMapper.updateById(flight);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除航班信息")
    @DeleteMapping("/flight/{id}")
    public String deleteFlightById(@PathVariable String id)
    {
        int i = flightMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("查询特定航班,提供起点，终点")
    @GetMapping("/flight/{departureCity}/{arrivalCity}")
    public String getFlightByCity(@PathVariable String departureCity, @PathVariable String arrivalCity) throws JsonProcessingException
    {
        List<Flight> flightList = flightMapper.selectByCity(departureCity, arrivalCity);
        //System.out.println(flightList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(flightList);
        return "{" + "\"TotalNumber\":" + flightList.size() + ",\"flightData\":" + json + "}";
    }

    @ApiOperation("查询回收站中的航班,返回值为回收站中航班的数量")
    @GetMapping("/flight-deleted")
    public String getRecycleBin() throws JsonProcessingException
    {
        List<Flight> flightList = flightMapper.selectRecycleBin();
        //System.out.println(flightList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(flightList);
        return "{" + "\"TotalNumber\":" + flightList.size() + ",\"flightData\":" + json + "}";
    }

    @ApiOperation("查询：前端提供航班id，查找对应航班号且状态为“回收站中”的航班。")
    @GetMapping("/flight-deleted/{id}")
    public String getRecycleBinById(@PathVariable String id) throws JsonProcessingException
    {
        List<Flight> flightList = flightMapper.selectRecycleBinById(id);
        //System.out.println(flightList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(flightList);
        return "{" + "\"TotalNumber\":" + flightList.size() + ",\"flightData\":" + json + "}";
    }


    @ApiOperation("前端提供航班id，从“回收站中”改为“未发布”。")
    @PutMapping("/flight-recover/deletedToUnpublished/{id}")
    public String recoverFlight(@PathVariable String id)
    {
        int i = flightMapper.recoverFlight(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("前端提供航班id，将状态从“未发布”改为“回收站中”。")
    @PutMapping("/flight-recover/unpublishedToDeleted/{id}")
    public String deleteFlight(@PathVariable String id)
    {
        int i = flightMapper.flightUnpublishedToDelete(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("前端提供航班id，将状态从“已发布”改为“回收站中”")
    @PutMapping("/flight-recover/publishedToDeleted/{id}")
    public String deletePublishedFlight(@PathVariable String id)
    {
        int i = flightMapper.flightPublishedToDelete(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("查询未发布的航班,返回值为未发布航班的数量")
    @GetMapping("/flight-unpublished")
    public String getUnpublished() throws JsonProcessingException
    {
        List<Flight> flightList = flightMapper.selectUnpublished();
        //System.out.println(flightList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(flightList);
        return "{" + "\"TotalNumber\":" + flightList.size() + ",\"flightData\":" + json + "}";
    }

    @ApiOperation("发布航班：前端提供航班号，修改对应航班号的状态为“已发布”。")
    @PutMapping("/flight-publish/{id}")
    public String publishFlight(@PathVariable String id)
    {
        int i = flightMapper.publishFlight(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("查询已发布的航班,返回值为已发布航班的数量")
    @GetMapping("/flight-published")
    public String getPublished() throws JsonProcessingException
    {
        List<Flight> flightList = flightMapper.selectPublished();
        //System.out.println(flightList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(flightList);
        return "{" + "\"TotalNumber\":" + flightList.size() + ",\"flightData\":" + json + "}";
    }

    

}
