package cn.ecnu.common.handler;

import cn.ecnu.common.exception.ServiceException;
import cn.ecnu.common.utils.R;
import cn.ecnu.common.utils.StatusCode;
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

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public R error(ServiceException e) {
        e.printStackTrace();
        return new R(false,e.getCode(), e.getMessage());
    }
}