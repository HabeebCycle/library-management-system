package com.habeebcycle.lms.service.userservice.payload;

import org.springframework.http.HttpStatus;

public class APIResponse {

    private Boolean success;
    private String message;
    private HttpStatus statusCode;
    private String serviceUtil;

    public APIResponse(Boolean success, String message, HttpStatus statusCode, String serviceUtil) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
        this.serviceUtil = serviceUtil;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getServiceUtil() {
        return serviceUtil;
    }

    public void setServiceUtil(String serviceUtil) {
        this.serviceUtil = serviceUtil;
    }
}
