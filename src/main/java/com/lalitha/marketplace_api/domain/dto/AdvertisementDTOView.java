package com.lalitha.marketplace_api.domain.dto;

import com.lalitha.marketplace_api.domain.entity.User;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AdvertisementDTOView(
        Long id,
        String title,
        String description,
        String category,
        String brand,
        Double price,
        String currency,
        String item_condition,
        LocalDate createdDate,
        LocalDate expiryDate,
        Boolean sold
) {
}
