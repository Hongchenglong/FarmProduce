package cn.ecnu.system.controller;

import cn.ecnu.common.utils.R;
import cn.ecnu.common.utils.TokenDecode;
import cn.ecnu.system.convert.UserConvert;
import cn.ecnu.system.model.vo.EnterpriseVO;
import cn.ecnu.system.pojo.Enterprise;
import cn.ecnu.system.service.EnterpriseService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hongchenglong
 * @since 2022-04-23
 * @update 2022-05-05 by Kili
 */
@Api(value = "登录和注册", tags = "登录和注册")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EnterpriseService enterpriseService;

    /***
     * 此接口已废弃，登录接口网关路由到oauth微服务
     */
    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public R login(@RequestBody Enterprise enterprise, HttpServletResponse response){
        log.info("\n###### 用户登录信息：{}", JSON.toJSONString(enterprise));
        String jwt = enterpriseService.login(enterprise);

        // 将token存到客户端(cookie)
        Cookie cookie = new Cookie("Authorization", jwt);
        response.addCookie(cookie);
        response.setHeader("Authorization","Bearer " + jwt);
        log.info("\n###### 用户：{} 登录成功", enterprise.getAccount());
        return R.ok(jwt);
    }

    /***
     * 根据ID查询User数据
     * @return
     */
    @ApiOperation(value = "User根据username查询")
    @GetMapping("/info")
    public R<EnterpriseVO> findUserInfo(){
        String username = null;
        try {
            username = TokenDecode.getUserInfo().get("username");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用UserService实现根据主键查询User
        Enterprise user = enterpriseService.getOne(Wrappers.<Enterprise>lambdaQuery().eq(Enterprise::getAccount, username));
        return R.ok(UserConvert.INSTANCE.convert(user));
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R<String> register(@RequestBody Enterprise enterprise) {
        return enterpriseService.register(enterprise);
    }
}
