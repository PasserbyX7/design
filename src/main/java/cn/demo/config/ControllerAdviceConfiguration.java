package cn.demo.config;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.demo.api.ErrorCode;
import cn.demo.api.R;
import cn.demo.exception.PhoneExistException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerAdviceConfiguration {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Map<String, String>> handleValidException(MethodArgumentNotValidException e) {
        // @formatter:off
        var map = e.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        StringBuilder sb = new StringBuilder();
        for (var entry : map.entrySet())
            sb.append("校验字段：")
                .append(entry.getKey())
                .append(" 错误信息：")
                .append(entry.getValue())
                .append("；");
        // @formatter:on
        log.error("数据校验异常：[{}]", sb.toString());
        return R.fail(ErrorCode.USER_REQUEST_PARAM_ERROR, map);
    }

    @ExceptionHandler(PhoneExistException.class)
    public R<Void> handleException(PhoneExistException E) {
        return R.fail(ErrorCode.USER_PHONE_EXIST_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public R<Void> handleException(Throwable throwable) {
        log.error("未知异常:{}", throwable.getClass().getName());
        log.error("信息异常:{}", throwable.getMessage());
        return R.fail();
    }

}