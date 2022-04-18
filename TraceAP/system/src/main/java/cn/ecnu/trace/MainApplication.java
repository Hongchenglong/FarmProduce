package cn.ecnu.trace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: cn.ecnu.trace
 * @Description:
 * @Date: Create in 20:17 2021/8/22
 */
@SpringBootApplication
@MapperScan("cn.ecnu.trace.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

}
