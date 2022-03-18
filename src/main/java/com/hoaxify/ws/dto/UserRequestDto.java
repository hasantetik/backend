package com.hoaxify.ws.dto;

import com.hoaxify.ws.annotation.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRequestDto {

    @NotBlank(message = "{hoaxify.constraints.username.NotNull.message}")
    @UniqueUsername
    @Size(min = 4,max = 255)
    private String username;

    @NotBlank
    @Size(min = 4,max = 255)
    private String displayName;

    @NotBlank
    @Size(min = 8,max = 255)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.constraints.password.NotNull.message}")
    private String password;

}
