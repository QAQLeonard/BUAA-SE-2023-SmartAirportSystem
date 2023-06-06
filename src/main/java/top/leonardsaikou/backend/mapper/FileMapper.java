package top.leonardsaikou.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface FileMapper extends BaseMapper<MultipartFile> {

    //把头像路径存入数据库
    @Update("update users set avatar=#{path} where username=#{username}")
    void updateAvatar(String path, String username);


}
