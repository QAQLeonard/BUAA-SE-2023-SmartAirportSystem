package top.leonardsaikou.backend.mapper;
import java.time.LocalDate;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import top.leonardsaikou.backend.entity.Ticket;

@Mapper
public interface TicketMapper extends BaseMapper<Ticket>{
    @Select("SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) FROM tickets")
    int selectMaxId();

    @Select("SELECT COALESCE(SUM(ticket_price), 0) FROM tickets WHERE DATE(purchase_date_time) = #{date}")
    double selectTotalPriceByDate(LocalDate date);

    @Select("SELECT * FROM tickets WHERE passenger_id = #{passengerId}")
    List<Ticket> selectByPassengerId(String passengerId);

}
