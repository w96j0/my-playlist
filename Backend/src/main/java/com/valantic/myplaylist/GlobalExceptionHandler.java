package com.valantic.myplaylist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail illegalArgumentExceptionResponse(IllegalArgumentException exception) {
        ProblemDetail response = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidExceptionResponse(MethodArgumentNotValidException exception) {
        ProblemDetail response = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return response;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail noSuchElementExceptionResponse(NoSuchElementException exception) {
        ProblemDetail response = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public  ProblemDetail generalExceptionResponse(Exception exception) {
        ProblemDetail response = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return response;
    }
}
