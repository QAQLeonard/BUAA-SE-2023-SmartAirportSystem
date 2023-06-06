package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.MerchantApplication;

@Mapper
public interface MerchantApplicationMapper extends BaseMapper<MerchantApplication>
{
    @Select("SELECT MAX(CAST(SUBSTRING(id, 3) AS UNSIGNED)) FROM merchant_applications")
    int selectMaxId();
}
