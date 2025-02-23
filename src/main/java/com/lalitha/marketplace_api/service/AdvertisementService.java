package com.lalitha.marketplace_api.service;

import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOForm;
import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOView;
import com.lalitha.marketplace_api.domain.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDTOView create(AdvertisementDTOForm form);
    AdvertisementDTOView findById(Long id);
    List<AdvertisementDTOView> findAll();
    List<AdvertisementDTOView> findAllBySeller(String sellerId);
    List<AdvertisementDTOView> findByFilterAndValue(String filter, String value);
    List<AdvertisementDTOView> findByPriceRange(Double min, Double max);
    AdvertisementDTOView update(AdvertisementDTOForm form);
    void delete(Long id);
}
