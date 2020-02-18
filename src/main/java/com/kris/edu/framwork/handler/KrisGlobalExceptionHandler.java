package com.kris.edu.framwork.handler;


import com.kris.edu.framwork.BaseResult.BaseResult;
import com.kris.edu.framwork.Enum.EduErrorEnum;
import com.kris.edu.framwork.exception.EduException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class KrisGlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResult errorHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return BaseResult.operateFailed(EduErrorEnum.SYSTEM_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = EduException.class)
    public BaseResult EduErrorHandler(EduException ex) {
        return BaseResult.fail(ex);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResult methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String validResult = ex.getBindingResult().getAllErrors().stream().map(objectError -> ((DefaultMessageSourceResolvable)objectError.getArguments()[0]).getDefaultMessage() + objectError.getDefaultMessage()).collect(Collectors.joining(";"));
        return BaseResult.fail(EduErrorEnum.ILLEGAL_ARGUMENT.getCode(), validResult);
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public BaseResult illegalArgumentErrorHandler(IllegalArgumentException ex) {
        return BaseResult.fail(EduErrorEnum.ILLEGAL_ARGUMENT.getCode(), ex.getMessage());
    }



}
