package cn.ecnu;

import cn.ecnu.common.handler.BaseExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@MapperScan("cn.ecnu.oauth.mapper")
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BaseExceptionHandler baseExceptionHandler(){
        return new BaseExceptionHandler();
    }

}