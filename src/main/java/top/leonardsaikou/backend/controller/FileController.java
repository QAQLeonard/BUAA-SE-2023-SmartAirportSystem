package top.leonardsaikou.backend.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.leonardsaikou.backend.mapper.FileMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController
{

    private final FileMapper fileMapper;

    public FileController(FileMapper fileMapper)
    {
        this.fileMapper = fileMapper;
    }

    @ApiOperation("上传头像")
    @PostMapping("/upload/{username}")
    public String upload(@PathVariable String username, MultipartFile avater) throws IOException
    {

        String path = System.getProperty("user.dir") + "/images/";
        File folder = new File(path);
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        saveFile(avater, path);
        String avatarPath = "/images/" + avater.getOriginalFilename();
        fileMapper.updateAvatar(avatarPath, username);

        return "success";
    }

    public void saveFile(MultipartFile file, String path) throws IOException
    {
        File dest = new File(path + file.getOriginalFilename());
        file.transferTo(dest);
    }

}
