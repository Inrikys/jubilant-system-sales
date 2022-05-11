package com.study.security.advice;

import com.study.security.exception.BusinessException;
import com.study.security.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StandardError handleBusinessException(BusinessException ex) {
        String error = ex.getMessage();
        return new StandardError(error);
    }

}
