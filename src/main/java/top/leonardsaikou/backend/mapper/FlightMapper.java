package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.Flight;

import java.util.List;

@Mapper
public interface FlightMapper extends BaseMapper<Flight> {

    @Select("select * from flights where departure_city = #{departureCity} or arrival_city = #{arrivalCity}")
    List<Flight> selectByCity(String departureCity, String arrivalCity);


}
