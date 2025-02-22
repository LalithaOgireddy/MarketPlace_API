package com.lalitha.marketplace_api.domain.dto;

import lombok.Builder;

@Builder
public record UserDetailsDTOView (
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String city
){
}
