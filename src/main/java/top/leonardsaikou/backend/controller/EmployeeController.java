package top.leonardsaikou.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import top.leonardsaikou.backend.entity.Employee;
import top.leonardsaikou.backend.mapper.EmployeeMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @ApiOperation("获取全部员工信息")
    @GetMapping("/employee")
    public String getEmployee() throws JsonProcessingException {
        List<Employee> employeesList = employeeMapper.selectList(null);
        //System.out.println(employeesList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(employeesList);
        return "{"+"\"TotalNumber\":" + employeesList.size() + ",\"employeeData\":"+ json + "}";
    }

    @ApiOperation("根据id获取单个员工信息")
    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable String id) throws JsonProcessingException{
        Employee employee = employeeMapper.selectById(id);
        //System.out.println(employee);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = objectMapper.writeValueAsString(employee);
        return "{"+"\"TotalNumber\":" + "1" + ",\"employeeData\":"+ json + "}";
    }

    @ApiOperation("插入员工信息")
    @PostMapping("/employee")
    public String addEmployee(Employee employee) {
        int i = employeeMapper.insert(employee);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("更新员工信息")
    @PutMapping("/employee")
    public String updateEmployee(Employee employee) {
        int i = employeeMapper.updateById(employee);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }

    @ApiOperation("删除员工信息")
    @DeleteMapping("/employee/{id}")
    public String deleteEmployeeById(@PathVariable String id) {
        int i = employeeMapper.deleteById(id);
        if (i > 0) {
            return "success";
        }
        return "fail";
    }
}
