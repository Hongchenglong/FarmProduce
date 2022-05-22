package cn.ecnu.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
 
/**
 * @author OZH
 * @Description:
 * @date 2022/1/30 0:37
 */
@Configuration //将当前类变为xxx.xml配置类
public class CorsConfig {
    //@Bean 创建一个bean对象交给Spring容器管理
    // <bean class ="org.springframework.web.cors.reactive.CorsWebFilter"/>相当于
    @Bean
    public CorsWebFilter corsWebFilter() {
        //需要CorsConfigurationSource这个对象
//        CorsConfigurationSource  这个对象是一个接口,所以我们需要当前接口的实现类UrlBasedCorsConfigurationSource
 
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");//请求头* 代表任意随便  任意请求头
        corsConfiguration.addAllowedOrigin("*");//请求域* 代表任意,这个可以写*也可以写域名  任意域名
        corsConfiguration.addAllowedMethod("*");//GET,POST,PUT  *表示任意
        corsConfiguration.setAllowCredentials(true);//允许携带cookie
        //第一个参数表示路径,第二个参数表示设置跨域的方式等信息 CorsConfiguration  ,下面这个/**表示当前路径下所有
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
 
        //  返回当前对象
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }
 
}