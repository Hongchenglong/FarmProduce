package cn.ecnu.system.controller;

import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.Enterprise;
import cn.ecnu.system.service.EnterpriseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hongchenglong
 * @since 2022-04-23
 * @update 2022-05-05 by Kili
 */
@Api(value = "用户登录", tags = "用户登录")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EnterpriseService enterpriseService;

    /***
     * 用户登录
     */
    @RequestMapping(value = "/login")
    public R login(@RequestBody Enterprise enterprise, HttpServletResponse response){
        String jwt = enterpriseService.login(enterprise);

        // 将token存到客户端(cookie)
        Cookie cookie = new Cookie("Authorization", jwt);
        response.addCookie(cookie);
        response.setHeader("Authorization","Bearer " + jwt);
        return R.ok(jwt);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody Enterprise enterprise) {
        return enterpriseService.register(enterprise);
    }
}
