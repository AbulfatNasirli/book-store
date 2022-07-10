package com.company.controller;

import com.company.dto.response.ErrorResponse;
import com.company.enums.ErrorCodeEnum;
import com.company.exception.CustomRestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CustomRestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomException(CustomRestException ex) {
        return ErrorResponse.builder().
                code(ex.getCode()).
                message(ex.getMessage()).
                build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputParam(MethodArgumentTypeMismatchException ex) {

        String paramName = ex.getParameter().getParameter().getName();
        return ErrorResponse.builder().
                code(ErrorCodeEnum.VALIDATION_ERROR.getCode()).
                message(paramName + ErrorCodeEnum.VALIDATION_ERROR.getMessage()).
                build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        String fieldname = ex.getBindingResult().getFieldError().getField();
        return ErrorResponse.builder().
                code(ErrorCodeEnum.VALIDATION_ERROR.getCode()).
                message(fieldname + ErrorCodeEnum.VALIDATION_ERROR.getMessage()).
                build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse unknownError(Exception ex) {
        return ErrorResponse.builder().
                code(ErrorCodeEnum.UNKNOWN_ERROR.getCode()).
                message(ErrorCodeEnum.UNKNOWN_ERROR.getMessage()).
                build();
    }
}
