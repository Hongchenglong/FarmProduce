package cn.ecnu.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@EnableEurekaClient
@Slf4j
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

//    /***
//     * IP限流
//     * @return
//     */
//    @Bean(name="ipKeyResolver")
//    public KeyResolver userKeyResolver() {
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                //获取远程客户端IP
//                String ipAdrress = exchange.getRequest().getHeaders().getFirst("X-real-ip");
//                log.info("\n#####################【客户端请求地址：{}】#########################################################",ipAdrress);
//                return Mono.just(ipAdrress);
//            }
//        };
//    }
}