package com.habeebcycle.lms.service.userservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException{

    public InternalServerException(){}

    public InternalServerException(String message){
        super(message);
    }

    public InternalServerException(String message, Throwable cause){
        super(message, cause);
    }

    public InternalServerException(Throwable cause){
        super(cause);
    }
}
