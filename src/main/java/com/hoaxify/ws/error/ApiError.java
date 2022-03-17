package com.hoaxify.ws.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

@Data
public class ApiError {

    private int status;

    private String message;

    private String path;

    private long timestamp = new Date().getTime();

    private HttpStatus httpStatus;

    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String path, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.httpStatus = httpStatus;
    }
}
