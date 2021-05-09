package com.aduner.servicebase.handler.ExceptionHandler;

import com.aduner.utils.ExceptionUtil;
import com.aduner.utils.Result;
import com.aduner.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author Aduner
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result error(HttpMessageNotReadableException e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return Result.error().message("json error");
    }

    //自定义异常
    @ExceptionHandler(AdunerException.class)
    @ResponseBody //为了返回数据
    public Result error(AdunerException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
