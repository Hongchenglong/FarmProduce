package cn.ecnu.gateway.filter;

import cn.ecnu.common.exception.ErrorCode;
import cn.ecnu.common.exception.utils.ServiceExceptionUtil;
import cn.ecnu.common.utils.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权过滤器 验证token
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZATION = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求
        ServerHttpRequest request = exchange.getRequest();
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();
        //3. 如果是登录请求则放行
        if (request.getURI().getPath().contains("/user/login")) {
            return chain.filter(exchange);
        }
        //4. 获取请求头
        HttpHeaders headers = request.getHeaders();
        //5. 请求头中获取令牌
        String token = headers.getFirst(AUTHORIZATION);
        //6. 判断请求头中是否有令牌
        if (StringUtils.isEmpty(token)) {
/*        //7. 响应中放入返回的状态吗, 没有权限访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //8. 返回
            return response.setComplete();*/
            throw ServiceExceptionUtil.exception(new ErrorCode(401, "UNAUTHORIZED"));
        }
        //9. 如果请求头中有令牌则解析令牌
        try {
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
//            e.printStackTrace();
//            //10. 解析jwt令牌出错, 说明令牌过期或者伪造等不合法情况出现
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            //11. 返回
//            return response.setComplete();

            throw ServiceExceptionUtil.exception(new ErrorCode(401, "UNAUTHORIZED"));
        }
        //12. 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
