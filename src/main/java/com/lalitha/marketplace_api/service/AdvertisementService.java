package com.lalitha.marketplace_api.service;

import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOForm;
import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOView;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDTOView create(AdvertisementDTOForm form);
    AdvertisementDTOView findById(Long id);
    List<AdvertisementDTOView> findAll();
    List<AdvertisementDTOView> findAllBySeller(String sellerId);
    /*
    List<AdvertisementDTOView> findAllByCategory(String category);
    List<AdvertisementDTOView> findAllByCategoryAndKeyword(String category, String keyword);*/
    AdvertisementDTOView update(AdvertisementDTOForm form);
    void delete(Long id);
}
