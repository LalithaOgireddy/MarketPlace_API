package com.lalitha.marketplace_api.domain.dto;

import lombok.Builder;

@Builder
public record UserDetailsDTOView (
        String firstName,
        String lastName,
        String phoneNumber,
        String city
){
}
