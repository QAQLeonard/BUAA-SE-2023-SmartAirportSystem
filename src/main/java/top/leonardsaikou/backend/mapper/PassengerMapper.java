package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.Passenger;

@Mapper
public interface PassengerMapper extends BaseMapper<Passenger>
{
    @Select("SELECT MAX(CAST(SUBSTRING(id, 3) AS UNSIGNED)) FROM passengers")
    int selectMaxId();
}
