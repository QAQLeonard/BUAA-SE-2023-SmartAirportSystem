package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import top.leonardsaikou.backend.entity.Notification;
import top.leonardsaikou.backend.mapper.NotificationMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationController
{
    private final NotificationMapper notificationMapper;

    public NotificationController(NotificationMapper notificationMapper)
    {
        this.notificationMapper = notificationMapper;
    }

    @ApiOperation("Get all notifications")
    @GetMapping("/notification")
    public String getAllNotifications()  throws JsonProcessingException
    {
        List<Notification> notificationList = notificationMapper.selectList(null);
        //System.out.println(parkingLotApplicationsList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(notificationList);
        return "{" + "\"TotalNumber\":" + notificationList.size() + ",\"parkingLotApplicationData\":" + json + "}";
    }

    @ApiOperation("Get notification by id")
    @GetMapping("/notification/{id}")
    public String getNotificationById(@PathVariable String id)  throws JsonProcessingException
    {
        Notification notification = notificationMapper.selectById(id);
        //System.out.println(parkingLotApplication);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(notification);
        return "{" + "\"TotalNumber\":" + 1 + ",\"parkingLotApplicationData\":" + json + "}";
    }

    @ApiOperation("Add notification")
    @PostMapping("/notification")
    public String addNotification(Notification notification)
    {
        int i = notificationMapper.insert(notification);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("Update notification")
    @PutMapping("/notification")
    public String updateNotification(Notification notification)
    {
        int i = notificationMapper.updateById(notification);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

}
