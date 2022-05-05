package cn.ecnu.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * http://127.0.0.1:8088/swagger-ui/index.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){
 /*       Profiles profiles = Profiles.of("dev", "test");
        // 判断是否处于自己设置的环境中
        boolean flag = environment.acceptsProfiles(profiles);*/

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 根据环境是否开启swagger
                .enable(true) //暂时写死
                .groupName("Web攻坚")
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage() 扫描指定路径下的包
                //any() 扫描所有
                //none() 都不扫描
                //withClassAnnotation()扫描指定的注解
                // GetMapping()扫描方法上的注解
//                .apis(RequestHandlerSelectors.basePackage("com.lili.controller"))
//                过滤掉一些路径扫描
//                .paths(PathSelectors.ant("/lili/**"))
                .build();
    }

    /**
     * 配置swagger信息apiInfo，作者信息
     */
    public ApiInfo apiInfo(){
        Contact contact = new Contact("农产品溯源系统", "https://www.baidu.com/", "666@ecnu.cn");
        return new ApiInfo("ECNU Web高级数据库 课程设计",
                "Web攻坚，一起加油",
                "1.0",
                "www.baidu.com",
                contact,
                "Apache 2.0",
                "http://localhost:8080/tmall/admin",
                new ArrayList());
    }

}

