package com.example.java8.exAdvice;

import com.example.java8.exception.MemberException;
import com.example.java8.vo.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberException.class)
    public ErrorResult memberExceptionHandler(MemberException me) {
        log.error("[MemberExceptionHandler] ex", me);
        return new ErrorResult("memberException", me.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult exceptionHandler(Exception e) {
        log.error("[Exception] ex", e);
        return new ErrorResult("Exception", e.getMessage());
    }
}
