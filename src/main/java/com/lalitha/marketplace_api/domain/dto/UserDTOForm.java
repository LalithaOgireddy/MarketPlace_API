package com.lalitha.marketplace_api.domain.dto;

import jakarta.validation.constraints.*;

public record UserDTOForm (

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    String email,

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$",
        message="Password must contain at least one uppercase letter, one lower case letter, one number and one special character")
    String password
){
}
