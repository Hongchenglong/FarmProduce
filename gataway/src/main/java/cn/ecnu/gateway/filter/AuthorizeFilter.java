package cn.ecnu.gateway.filter;

import cn.ecnu.common.exception.ErrorCode;
import cn.ecnu.common.exception.utils.ServiceExceptionUtil;
import cn.ecnu.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权过滤器 验证token
 */
@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求
        ServerHttpRequest request = exchange.getRequest();
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();

        log.info("\n###### request.getURI():{}, \n###### request.getPath():{}, \n###### request.getURI().getPath():{}"
                , request.getURI(),request.getPath(),request.getURI().getPath());

        //3. 判断接口是否放行
        String url = request.getURI().getPath();
        if (URLFilter.hasAuthorization(url)) {
            return chain.filter(exchange);
        }
        if("/api".equals(request.getURI().getPath()) ||
           "/api/".equals(request.getURI().getPath()) ||
           request.getURI().getPath().contains("/trace/")) {
                return chain.filter(exchange);
            }

        //4. 获取请求头token
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        // 如果请求头中不包含token就从cooKie中获取
        if (StringUtils.isEmpty(token)) {
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (cookie != null) {
                token = cookie.getValue();
                try {
                    //解析令牌，抛异常代表未授权
                    JwtUtil.parseJWT(token);

                    // 将token添加到请求头信息,有对应的微服务去解析
                    if(!token.startsWith("bearer ") && !token.startsWith("Bearer "))
                        token = "Bearer " + token;
                    request.mutate().header(AUTHORIZE_TOKEN, token);
                    log.info("\n###### 从cookie中获取token,存入请求头中");

                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("\n###### token非法！！！！！！！！！");
                    throw ServiceExceptionUtil.exception(new ErrorCode(401, HttpStatus.UNAUTHORIZED.getReasonPhrase()));
                }
            }else {
                throw ServiceExceptionUtil.exception(new ErrorCode(401, HttpStatus.UNAUTHORIZED.getReasonPhrase()));
            }
        }

        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
