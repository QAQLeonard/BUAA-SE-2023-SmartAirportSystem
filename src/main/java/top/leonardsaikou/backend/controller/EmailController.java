package top.leonardsaikou.backend.controller;

import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.leonardsaikou.backend.service.UserService;

import javax.servlet.http.HttpServletRequest;
@RestController
public class EmailController
{
    private final UserService userService;

    @Autowired
    public EmailController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 发送邮箱验证码
     * 传入邮箱即可如"toEmail":"123@qq.com"
     * @param json
     * @return
     */
    @ApiOperation("发送验证码")
    @PostMapping ("/send-email")
    public String sendEmail(@RequestBody String json)
    {
        JSONObject jsonObject = new JSONObject(json);
        String toEmail = jsonObject.getString("toEmail");
        String code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
        if(!userService.sendEmailCode(code, toEmail))
        {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "发送失败");
            return jsonObject.toString();
        }
        return code;
    }
}
