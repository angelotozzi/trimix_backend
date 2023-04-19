package com.trimix.backend.exception;

import com.trimix.backend.Response.ResponseDefault;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResultNotFound  extends RuntimeException{

    private final ResponseDefault responseDefault;

    public ResultNotFound(ResponseDefault responseDefault){
        super(responseDefault.toString());
        this.responseDefault = responseDefault;
    }

}
