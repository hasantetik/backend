package com.hoaxify.ws.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRequestDto {

    //@NotBlank can be used for both
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
    @Size(min = 8,max = 255)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = "büyük harf, küçük harf ve sayı içermelidir.")
    private String password;

}
