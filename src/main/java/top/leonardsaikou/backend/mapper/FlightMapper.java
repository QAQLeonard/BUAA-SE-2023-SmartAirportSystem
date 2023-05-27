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

    @Select("select * from flights where status = '-1'")
    List<Flight> selectRecycleBin();

    @Update("update flights set status = '0' where id = #{id} and status = '-1'")
    int recoverFlight(String id);

    @Select("select * from flights where id = #{id} and status = '-1'")
    List<Flight> selectRecycleBinById(String id);

    @Select("select * from flights where status = '0'")
    List<Flight> selectUnpublished();

    @Update("update flights set status = '1' where id = #{id} and status = '0'")
    int publishFlight(String id);

    @Update("update flights set status = '-1' where id = #{id} and status = '0'")
    int flightUnpublishedToDelete(String id);

    @Select("select * from flights where status = '1'")
    List<Flight> selectPublished();

    @Update("update flights set status = '-1' where id = #{id} and status = '1'")
    int flightPublishedToDelete(String id);

}
