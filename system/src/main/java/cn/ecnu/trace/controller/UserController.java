package cn.ecnu.trace.controller;

import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.pojo.Enterprise;
import cn.ecnu.trace.service.EnterpriseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hongchenglong
 * @since 2022-04-23
 */
@Api(value = "用户登录", tags = "用户登录")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping("/login")
    public R<String> login(@RequestBody Enterprise enterprise) {
        return enterpriseService.login(enterprise);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody Enterprise enterprise) {
        return enterpriseService.register(enterprise);
    }
}
