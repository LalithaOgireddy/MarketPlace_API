package com.lalitha.marketplace_api.service.impl;

import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOForm;
import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOView;
import com.lalitha.marketplace_api.domain.entity.Advertisement;
import com.lalitha.marketplace_api.domain.entity.User;
import com.lalitha.marketplace_api.exception.DataNotFoundException;
import com.lalitha.marketplace_api.repository.AdvertisementRepository;
import com.lalitha.marketplace_api.repository.UserRepository;
import com.lalitha.marketplace_api.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository,UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdvertisementDTOView create(AdvertisementDTOForm form) {
        //check null value in form
        if(form == null) throw new IllegalArgumentException("form is null");
        User seller = userRepository.getUserByEmail(form.seller().email());
        //convert form to entity
        Advertisement advertisement = Advertisement.builder()
                .title(form.title())
                .description(form.description())
                .category(form.category())
                .brand(form.brand())
                .price(form.price())
                .currency(form.currency())
                .createdDate(form.createdDate())
                .item_condition(form.item_condition())
                .expiryDate(form.expiryDate())
                .seller(seller)
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

    @Override
    public List<AdvertisementDTOView> findByFilterAndValue(String filter, String value){
        if(filter == null && filter.isEmpty()) return findAll();
        if(filter != null && value.isEmpty()) throw new IllegalArgumentException("Filter value is null");
        if(filter == null && !value.isEmpty()) throw new IllegalArgumentException("Filter is null");
        List<Advertisement> ads = new ArrayList<>();
        if(filter.toLowerCase().equals("brand")){
            ads = advertisementRepository.findByBrand(value.toLowerCase());
        }
        if(filter.toLowerCase().equals("brand")){
            ads = advertisementRepository.findByBrand(value.toLowerCase());
        }
        if(filter.toLowerCase().equals("title")){
            ads = advertisementRepository.findByTitleContains(value.toLowerCase());
        }
        if(filter.toLowerCase().equals("category")){
            ads = advertisementRepository.findByCategory(value.toLowerCase());
        }
        if(ads.isEmpty()) throw new DataNotFoundException("Advertisements with given filter not found");
        return ads.stream()
                .map(this::fromAdvEntityToView)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdvertisementDTOView> findByPriceRange(Double min, Double max){
        List<Advertisement> ads = new ArrayList<>();
        ads = advertisementRepository.findByPriceBetween(min, max);
        return ads.stream()
                .map(this::fromAdvEntityToView)
                .collect(Collectors.toList());
    }

    @Override
    public AdvertisementDTOView update(AdvertisementDTOForm form) {
        //check if input frm is null, thow exception if null
        if(form == null) throw new IllegalArgumentException("Advertisement input form is null");

        //get entity if ad exists by id, if not throw exception
        Advertisement advertisement = advertisementRepository.findById(form.id()).orElseThrow(()->new DataNotFoundException("Advertisement not found"));
        //update entityby setters
        advertisement.setTitle(form.title());
        advertisement.setDescription(form.description());
        advertisement.setCategory(form.category());
        advertisement.setBrand(form.brand());
        advertisement.setPrice(form.price());
        advertisement.setCurrency(form.currency());
        advertisement.setItem_condition(form.item_condition());
        advertisement.setExpiryDate(form.expiryDate());
        advertisement.setSold(form.sold());

        //save entity to db
        advertisementRepository.save(advertisement);

        //convert entity to view and return
        return fromAdvEntityToView(advertisement);
    }

    @Override
    public void delete(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(()->new DataNotFoundException("Advertisement not found"));
        advertisementRepository.delete(advertisement);
    }

    private AdvertisementDTOView fromAdvEntityToView(Advertisement advertisement) {
        AdvertisementDTOView.AdvertisementDTOViewBuilder builder = AdvertisementDTOView.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .description(advertisement.getDescription())
                .category(advertisement.getCategory())
                .price(advertisement.getPrice())
                .currency(advertisement.getCurrency())
                .createdDate(advertisement.getCreatedDate())
                .expiryDate(advertisement.getExpiryDate());

        if (advertisement.getBrand() != null) {
            builder.brand(advertisement.getBrand());
        }
        if (advertisement.getItem_condition() != null) {
            builder.item_condition(advertisement.getItem_condition());
        }
        return builder.build();
    }
}
