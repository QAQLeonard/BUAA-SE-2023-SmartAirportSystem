package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) FROM products")
    int selectMaxId();
}
