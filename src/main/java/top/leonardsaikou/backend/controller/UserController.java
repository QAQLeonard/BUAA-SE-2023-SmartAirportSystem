package top.leonardsaikou.backend.controller;

import top.leonardsaikou.backend.entity.User;
import top.leonardsaikou.backend.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper)
    {
        this.userMapper = userMapper;
    }

    @ApiOperation("获取全部用户信息")
    @GetMapping("/user")
    public String getUser()
    {
        List<User> usersList = userMapper.selectList(null);
        System.out.println(usersList);
        return "user";
    }

    @ApiOperation("根据id获取单个用户信息")
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable String id)
    {
        User user = userMapper.selectById(id);
        System.out.println(user);
        return "user";
    }

    @ApiOperation("插入用户信息")
    @PostMapping("/user")
    public String addUser(User user)
    {
        int i = userMapper.insert(user);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/user")
    public String updateUser(User user)
    {
        int i = userMapper.updateById(user);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable String id)
    {
        int i = userMapper.deleteById(id);
        if (i > 0)
        {
            return "success";
        }
        return "fail";
    }


}
