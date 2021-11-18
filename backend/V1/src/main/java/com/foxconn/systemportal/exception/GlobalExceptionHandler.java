package com.foxconn.systemportal.exception;

import com.foxconn.systemportal.model.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("【系统异常:】", e);

        if (e instanceof MethodArgumentNotValidException) {
            // 返回参数验证异常信息提示
            return ResultDTO.of("500", ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage());
        }

        return ResultDTO.of("500", e.getMessage() != null ? e.getMessage() : "服务器发生错误");
    }
}
