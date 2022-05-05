package cn.ecnu.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class EureakApplication {
    public static void main(String[] args) {
        SpringApplication.run(EureakApplication.class, args);
    }
}
