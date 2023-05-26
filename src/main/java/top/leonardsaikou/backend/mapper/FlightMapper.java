package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.leonardsaikou.backend.entity.Flight;

import java.util.List;

@Mapper
public interface FlightMapper extends BaseMapper<Flight> {

    @Select("select * from flights where departure_city = #{departureCity} or arrival_city = #{arrivalCity}")
    List<Flight> selectByCity(String departureCity, String arrivalCity);

    @Select("select * from flights where status = '回收站中'")
    List<Flight> selectRecycleBin();

    @Update("update flights set status = '未发布' where id = #{id} and status = '回收站中'")
    int recoverFlight(String id);

    //查询：前端提供航班号，查找对应航班号且状态为“回收站中”的航班。
    @Select("select * from flights where id = #{id} and status = '回收站中'")
    List<Flight> selectRecycleBinById(String id);


}
