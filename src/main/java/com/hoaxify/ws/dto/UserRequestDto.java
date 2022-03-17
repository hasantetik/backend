package com.hoaxify.ws.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRequestDto {

    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be null")
    @Size(min = 4,max = 255)
    private String username;

    @NotNull(message = "must not be null")
    @NotEmpty(message ="must not be null" )
    @Size(min = 4,max = 255)
    private String displayName;

    @NotNull(message = "must not be null")
    @NotEmpty(message ="must not be null" )
    private String password;

}
