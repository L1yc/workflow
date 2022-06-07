package com.lyc.workflow.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyc.workflow.advice.ResAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author liyc
 */
@RestControllerAdvice
@Slf4j
public class ResponseHandler implements ResponseBodyAdvice {

    @ExceptionHandler(FlowException.class)
    public R handleRRException(FlowException e) {
        return R.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库中已存在该记录", e);
        return R.error("数据库中已存在该记录");
    }


    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error("系统异常", e);
        return R.error("系统异常");
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getContainingClass().getAnnotation(ResAdvice.class) != null || returnType.hasMethodAnnotation(ResAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (obj instanceof R) {
            return obj;
        } else if (obj instanceof String) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(R.success(obj));
            } catch (JsonProcessingException e) {
                log.error("");
                throw new RuntimeException(e);
            }
        }else {
            return R.success(obj);
        }
    }
}
