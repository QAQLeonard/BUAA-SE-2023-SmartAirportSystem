package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.ParkingLotApplication;
import top.leonardsaikou.backend.entity.User;
import java.util.List;

@Mapper
public interface ParkingLotApplicationMapper extends BaseMapper<ParkingLotApplication>{

    @Select("SELECT * FROM parkinglot_applications WHERE status = '0'")
    List<ParkingLotApplication> getPendingApplications();

    @Select("SELECT * FROM parkinglot_applications WHERE status = '1'")
    List<ParkingLotApplication> getApprovedApplications();

}
