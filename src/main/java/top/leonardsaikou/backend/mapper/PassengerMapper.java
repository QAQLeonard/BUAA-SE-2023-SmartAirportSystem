package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.leonardsaikou.backend.entity.Passenger;

@Mapper
public interface PassengerMapper extends BaseMapper<Passenger> {
}