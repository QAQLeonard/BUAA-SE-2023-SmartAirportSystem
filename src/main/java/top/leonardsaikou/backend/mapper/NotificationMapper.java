package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.leonardsaikou.backend.entity.Merchant;
import top.leonardsaikou.backend.entity.Notification;

@Mapper
public interface NotificationMapper extends BaseMapper<Notification>
{
    @Select("SELECT MAX(CAST(SUBSTRING(id, 2) AS UNSIGNED)) FROM notifications")
    int selectMaxId();
}
