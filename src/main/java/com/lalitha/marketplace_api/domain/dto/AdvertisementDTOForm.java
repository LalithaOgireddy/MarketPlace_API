package com.lalitha.marketplace_api.domain.dto;

import com.lalitha.marketplace_api.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

        @NotNull(message = "Price is mandatory")
        Double price,
        String currency,
        String item_condition,
        LocalDate createdDate,

        @NotNull(message = "Expiry date is mandatory")
        LocalDate expiryDate,
        Boolean sold,

        @NotNull(message = "Seller is mandatory")
        UserDTOForm seller
){
}
