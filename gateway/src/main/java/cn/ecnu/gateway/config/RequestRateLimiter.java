//package cn.ecnu.gateway.config;
//
//import com.alibaba.fastjson.JSON;
//import cn.ecnu.common.utils.R;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
//import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
//import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
///**
// * 自定义限流提示
// * @author: lqj
// * @date: 2021/8/10
// */
//@Slf4j
//@Component
//public class RequestRateLimiter extends RequestRateLimiterGatewayFilterFactory {
//
//    private final RateLimiter defaultRateLimiter;
//
//    private final KeyResolver defaultKeyResolver;
//
//    public RequestRateLimiter(RateLimiter defaultRateLimiter, KeyResolver defaultKeyResolver) {
//        super(defaultRateLimiter, defaultKeyResolver);
//        this.defaultRateLimiter = defaultRateLimiter;
//        this.defaultKeyResolver = defaultKeyResolver;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        KeyResolver resolver = getOrDefault(config.getKeyResolver(), defaultKeyResolver);
//        RateLimiter<Object> limiter = getOrDefault(config.getRateLimiter(), defaultRateLimiter);
//        return (exchange, chain) -> resolver.resolve(exchange).flatMap(key -> {
//            String routeId = config.getRouteId();
//            log.info("\n###### routeId:{}", routeId);
//            if (routeId == null) {
//                Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
//                routeId = route.getId();
//            }
//
//            String finalRouteId = routeId;
//            return limiter.isAllowed(routeId, key).flatMap(response -> {
//
//                for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
//                    exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
//                }
//
//                if (response.isAllowed()) {
//                    return chain.filter(exchange);
//                }
//
//                log.info("\n#######################已限流: {}", finalRouteId);
//                ServerHttpResponse httpResponse = exchange.getResponse();
//                httpResponse.setStatusCode(config.getStatusCode());
//                if (!httpResponse.getHeaders().containsKey("Content-Type")) {
//                    httpResponse.getHeaders().add("Content-Type", "application/json");
//                }
//                DataBuffer buffer = httpResponse.bufferFactory().wrap((JSON.toJSONString(R.error("Too Many Requests"))).getBytes(StandardCharsets.UTF_8));
//                return httpResponse.writeWith(Mono.just(buffer));
//
//                // return exchange.getResponse().setComplete();
//            });
//        });
//    }
//
//    private <T> T getOrDefault(T configValue, T defaultValue) {
//        return (configValue != null) ? configValue : defaultValue;
//    }
//}