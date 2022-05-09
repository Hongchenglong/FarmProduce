package cn.ecnu.oauth.controller;

import cn.ecnu.common.utils.BaseController;
import cn.ecnu.common.utils.R;
import cn.ecnu.common.utils.StatusCode;
import cn.ecnu.oauth.pojo.Enterprise;
import cn.ecnu.oauth.service.EnterpriseService;
import cn.ecnu.oauth.service.LoginService;
import cn.ecnu.oauth.service.OauthClientDetailsService;
import cn.ecnu.oauth.util.AuthToken;
import cn.ecnu.oauth.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述
 *
 * @author www.lqjai.com
 * @version 1.0
 * @package cn.ecnu.oauth.controller *
 * @since 1.0
 */
@RestController
@Slf4j
@RequestMapping("/oauth")
public class AuthController extends BaseController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private EnterpriseService userService;
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;


    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    private static final String GRAND_TYPE = "password";//授权模式 密码模式
    private static final String SMS_CODE = "sms_code"; //自定义验证码类型

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    //Cookie生命周期
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    /**
     * 密码模式  认证.
     *
     * @param enterprise
     * @return
     */
    @PostMapping("/login")
    public R<Map> login(@RequestBody Enterprise enterprise, HttpServletResponse response) {
        //登录 之后生成令牌的数据返回
        AuthToken authToken = loginService.login(enterprise.getAccount(), enterprise.getPassword(), clientId, clientSecret, GRAND_TYPE);

        // 将token存到客户端(cookie)
        Cookie cookie = new Cookie("Authorization", authToken.getAccessToken());
        response.addCookie(cookie);
        response.setHeader("Authorization","Bearer " + authToken.getAccessToken());
        log.info("\n###### 用户：{} 登录成功", enterprise.getAccount());
        return new R<>(true, StatusCode.OK,"令牌生成成功",authToken.getAccessToken());
    }

    private void saveCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addCookie(response,cookieDomain,"/","Authorization",token,cookieMaxAge,false);
    }

    //使用手机号和验证码登录
    @PostMapping("/vlogin")
    @Transactional
    public R vlogin(String telephone, String smscode) {
        //登录 之后生成令牌的数据返回
        AuthToken authToken = loginService.vlogin(telephone, smscode, clientId, clientSecret, SMS_CODE);
        Enterprise user = userService.findByAccount(telephone);
        //设置到cookie中
        saveCookie(authToken.getAccessToken());
        return new R<>(true, StatusCode.OK,"令牌生成成功",authToken);
    }

    //注册
    @PostMapping("/register")
    @Transactional
    public R register(@RequestBody Enterprise user) {
        return R.error("接口暂不开放");
    }

    public static void main(String[] args) {
        String str = "$$$$$$SGG$$HH";
        System.out.println(str.substring(str.length()-6,str.length()));
    }

}
