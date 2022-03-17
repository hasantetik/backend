package com.hoaxify.ws.controller;

import com.hoaxify.ws.dto.UserRequestDto;
import com.hoaxify.ws.dto.UserResponseDto;
import com.hoaxify.ws.entity.User;
import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/1.0/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
         return ResponseEntity.ok(userService.save(userRequestDto));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException exception, WebRequest webRequest){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),webRequest.getDescription(false),HttpStatus.BAD_REQUEST);
        Map<String,String> validationErrors = new HashMap<>();
        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        error.setValidationErrors(validationErrors);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
