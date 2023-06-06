package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.Merchant;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant>
{
    @Select("SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) FROM merchants")
    int selectMaxId();
}
