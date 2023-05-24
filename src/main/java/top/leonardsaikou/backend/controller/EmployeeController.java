package top.leonardsaikou.backend.controller;

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
    public String getEmployee() {
        List<Employee> employeesList = employeeMapper.selectList(null);
        System.out.println(employeesList);
        return "employee";
    }

    @ApiOperation("根据id获取单个员工信息")
    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable String id) {
        Employee employee = employeeMapper.selectById(id);
        System.out.println(employee);
        return "employee";
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
