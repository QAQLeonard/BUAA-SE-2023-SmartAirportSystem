package top.leonardsaikou.backend.mapper;
import java.time.LocalDate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import top.leonardsaikou.backend.entity.Ticket;

@Mapper
public interface TicketMapper extends BaseMapper<Ticket>{
    @Select("SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) FROM tickets")
    int selectMaxId();

    @Select("SELECT SUM(ticket_price) FROM tickets WHERE DATE(purchase_datetime) = #{date}")
    double selectTotalPriceByDate(LocalDate date);
}
