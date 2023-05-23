package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.leonardsaikou.backend.entity.Employee;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
