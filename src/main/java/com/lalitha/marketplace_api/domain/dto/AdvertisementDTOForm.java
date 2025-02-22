package com.lalitha.marketplace_api.domain.dto;

import com.lalitha.marketplace_api.domain.entity.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AdvertisementDTOForm (
        Long id,

        @NotBlank(message = "Title is mandatory")
        String title,

        @NotBlank(message = "Description is mandatory")
        String description,

        @NotBlank(message = "Category is mandatory")
        String category,

        String brand,

        @NotBlank(message = "Price is mandatory")
        Double price,
        String currency,
        String condition,
        LocalDate createdDate,

        @NotBlank(message = "Expiry date is mandatory")
        LocalDate expiryDate,
        boolean sold,

        @NotBlank(message = "Seller is mandatory")
        User seller
){
}
