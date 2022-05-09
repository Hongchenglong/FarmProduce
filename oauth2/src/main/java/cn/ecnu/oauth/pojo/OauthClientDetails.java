package cn.ecnu.oauth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 * @TableName oauth_client_details
 */
@TableName(value ="oauth_client_details")
@Data
@Accessors(chain = true)
public class OauthClientDetails implements Serializable {
    /**
     * 客户端ID，主要用于标识对应的应用
     */
    @TableId
    private String clientId;

    /**
     * 
     */
    private String resourceIds;

    /**
     * 客户端秘钥，BCryptPasswordEncoder加密
     */
    private String clientSecret;

    /**
     * 对应的范围
     */
    private String scope;

    /**
     * 认证模式
     */
    private String authorizedGrantTypes;

    /**
     * 认证后重定向地址
     */
    private String webServerRedirectUri;

    /**
     * 
     */
    private String authorities;

    /**
     * 令牌有效期
     */
    private Integer accessTokenValidity;

    /**
     * 令牌刷新周期
     */
    private Integer refreshTokenValidity;

    /**
     * 
     */
    private String additionalInformation;

    /**
     * 
     */
    private String autoapprove;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}