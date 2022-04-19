package cn.ecnu.trace.common.handler;

import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.common.utils.StatusCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Configuration
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return new R(false, StatusCode.ERROR, e.getMessage());
    }
}