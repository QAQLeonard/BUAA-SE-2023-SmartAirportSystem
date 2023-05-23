package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.leonardsaikou.backend.entity.Merchant;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {
}
