package com.trimix.backend.controllers;

import com.trimix.backend.Response.ResponseDefault;
import com.trimix.backend.exception.ResultNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);
    private static final java.lang.String ERROR = "ERROR";

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseDefault MethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                err -> map.put(err.getField(), err.getDefaultMessage())
        );
        ResponseDefault responseDefault = ResponseDefault.builder()
                .action("ERROR EN EL REQUEST")
                .messageType("A")
                .detalle(map)
                .build();
        LOGGER.error(responseDefault.toString());
        return responseDefault;
    }

    @ExceptionHandler(value = ResultNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseDefault notFoundException(ResultNotFound ex){
        LOGGER.error(ex.getResponseDefault().toString());
        return ex.getResponseDefault();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDefault InternalServerErrorException(Exception ex){
        LOGGER.error(ex.getMessage());
        return ResponseDefault.builder().message(ex.getMessage()).build();
    }

}
