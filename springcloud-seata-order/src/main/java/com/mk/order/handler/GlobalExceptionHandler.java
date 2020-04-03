package com.mk.order.handler;

import com.mk.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) throws Exception {
        log.error(e.getMessage(), e);

        return Result.builder()
                .success(false)
                .message(e.getMessage())
                .build();
    }


}
