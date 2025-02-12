package com.lalitha.marketplace_api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDetailsDTOForm(

        @NotBlank(message = "First name is mandatory")
        String firstName,

        @NotBlank(message = "Last name is mandatory")
        String lastName,

        @NotBlank(message = "Phone number is mandatory")
        String phoneNumber,

        @NotBlank(message = "City is mandatory")
        String city
) {
}
