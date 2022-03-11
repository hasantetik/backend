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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto){
        ApiError apiError = new ApiError(400,"Validation error","/api/1.0/users");
        Map<String,String> validationErrors = new HashMap<>();
        String username = userRequestDto.getUsername();
        String displayName = userRequestDto.getDisplayName();
        if(username == null || username.isEmpty()){
            validationErrors.put("username","username cannot be null");
        }
        if(displayName == null || displayName.isEmpty()){
            validationErrors.put("displayName","Cannot be null");
        }
        if(validationErrors.size()>0){
            apiError.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        }
         return ResponseEntity.ok(userService.save(userRequestDto));
    }
}
