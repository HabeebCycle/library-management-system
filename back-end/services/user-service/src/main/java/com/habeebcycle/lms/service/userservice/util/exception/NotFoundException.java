package com.habeebcycle.lms.service.userservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

//@ResponseStatus(HttpStatus.NOT_FOUND) // Status 404
public class NotFoundException  extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private ZonedDateTime timestamp;

    public NotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        timestamp = ZonedDateTime.now();
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
