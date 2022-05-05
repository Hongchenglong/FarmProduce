package cn.ecnu.system;

import cn.ecnu.common.handler.BaseExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @Author: cn.ecnu.trace
 * @Description:
 * @Date: Create in 20:17 2021/8/22
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, RabbitAutoConfiguration.class})
@EnableEurekaClient
@MapperScan("cn.ecnu.system.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    @Bean
    public BaseExceptionHandler baseExceptionHandler(){
        return new BaseExceptionHandler();
    }
}
