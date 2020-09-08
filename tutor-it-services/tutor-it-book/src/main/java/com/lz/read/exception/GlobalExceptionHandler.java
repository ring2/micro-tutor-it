package com.lz.read.exception;

import com.lz.read.common.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author :     lz
 * @date :       2020/4/1 11:38
 * description:  全局异常处理器
 *
 **/
@RestControllerAdvice(annotations = {RestController.class})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RestResult handleException(Exception exception) {
        // 如果发生的异常为Service异常
        if (exception instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) exception;
            Integer statusCode = serviceException.getCommonStatus().getStatusCode();
            String message = serviceException.getCommonStatus().getMessage();
            log.error("service层发生了异常【{}】,错误状态码【{}】", message, statusCode);
            return RestResult.failure(serviceException.getCommonStatus());
        }
        // 其他未知异常
        String message = exception.getMessage();
        log.error("发生了异常【{}】", message);
        return RestResult.failure("系统正在闹脾气，请稍后再试~~ ");
    }
}
