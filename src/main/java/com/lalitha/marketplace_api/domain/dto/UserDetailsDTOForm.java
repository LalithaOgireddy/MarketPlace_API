package com.lalitha.marketplace_api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDetailsDTOForm(

        Long id,

        @NotBlank(message = "First name is mandatory")
        String firstName,

        @NotBlank(message = "Last name is mandatory")
        String lastName,

        @NotBlank(message = "Phone number is mandatory") // Todo validation to be added
        String phoneNumber,

        @NotBlank(message = "City is mandatory")
        String city
) {
}
