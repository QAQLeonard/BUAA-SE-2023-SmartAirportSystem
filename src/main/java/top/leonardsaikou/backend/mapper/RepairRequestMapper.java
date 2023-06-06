package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.RepairRequest;

@Mapper
public interface RepairRequestMapper extends BaseMapper<RepairRequest> {

    @Select("SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) FROM repair_requests")
    int selectMaxId();

}
