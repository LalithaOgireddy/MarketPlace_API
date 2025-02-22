package com.lalitha.marketplace_api.service.impl;

import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOForm;
import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOView;
import com.lalitha.marketplace_api.domain.entity.Advertisement;
import com.lalitha.marketplace_api.exception.DataNotFoundException;
import com.lalitha.marketplace_api.repository.AdvertisementRepository;
import com.lalitha.marketplace_api.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public AdvertisementDTOView create(AdvertisementDTOForm form) {
        //check null value in form
        if(form == null) throw new IllegalArgumentException("form is null");

        //convert form to entity
        Advertisement advertisement = Advertisement.builder()
                .title(form.title())
                .description(form.description())
                .category(form.category())
                .brand(form.brand())
                .price(form.price())
                .currency(form.currency())
                .createdDate(form.createdDate())
                .condition(form.condition())
                .expiryDate(form.expiryDate())
                .seller(form.seller())
                .build();

        //save entity to db
        advertisementRepository.save(advertisement);
        //convert saved entity to view and return
        return fromAdvEntityToView(advertisement);
    }

    @Override
    public AdvertisementDTOView findById(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(()->new DataNotFoundException("Advertisement not found"));
        return fromAdvEntityToView(advertisement);
    }

    @Override
    public List<AdvertisementDTOView> findAll() {
        List<Advertisement> advertisements = advertisementRepository.findAllAdv();

        return advertisements.stream()
                .map(this::fromAdvEntityToView)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdvertisementDTOView> findAllBySeller(String sellerId) {
        List<Advertisement> advertisements = advertisementRepository.findAllBySeller(sellerId);
        return advertisements.stream()
                .map(this::fromAdvEntityToView)
                .collect(Collectors.toList());
    }
    /*
    @Override
    public List<AdvertisementDTOView> findAllByCategory(String category) {
        return List.of();
    }

    @Override
    public List<AdvertisementDTOView> findAllByCategoryAndKeyword(String category, String keyword) {
        return List.of();
    }

    @Override
    public AdvertisementDTOView update(Long id, AdvertisementDTOForm form) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }*/

    private AdvertisementDTOView fromAdvEntityToView(Advertisement advertisement) {
        AdvertisementDTOView.AdvertisementDTOViewBuilder builder = AdvertisementDTOView.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .description(advertisement.getDescription())
                .category(advertisement.getCategory())
                .price(advertisement.getPrice())
                .currency(advertisement.getCurrency())
                .createdDate(advertisement.getCreatedDate())
                .expiryDate(advertisement.getExpiryDate())
                .seller(advertisement.getSeller());

        if (advertisement.getBrand() != null) {
            builder.brand(advertisement.getBrand());
        }
        if (advertisement.getCondition() != null) {
            builder.condition(advertisement.getCondition());
        }
        return builder.build();
    }
}
