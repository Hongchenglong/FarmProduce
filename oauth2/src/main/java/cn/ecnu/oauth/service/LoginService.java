package cn.ecnu.oauth.service;

import cn.ecnu.oauth.util.AuthToken;

/**
 * 描述
 *
 * @author www.lqjai.com
 * @version 1.0
 * @package cn.ecnu.oauth.service *
 * @since 1.0
 */
public interface LoginService {
    /**
     * 模拟用户的行为 发送请求 申请令牌 返回
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @param grandType
     * @return
     */
    AuthToken login(String username, String password, String clientId, String clientSecret, String grandType);

    AuthToken vlogin(String telephone, String validateCode, String clientId, String clientSecret, String grandType);
}
