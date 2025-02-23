package com.lalitha.marketplace_api.controller;

import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOForm;
import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOView;
import com.lalitha.marketplace_api.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
@Validated
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    //add an advertisement
    @PostMapping
    public ResponseEntity<AdvertisementDTOView> doCreateAd(@RequestBody @Validated AdvertisementDTOForm advertisementDTOForm) {
        AdvertisementDTOView adView = advertisementService.create(advertisementDTOForm);
        return new ResponseEntity<>(adView, HttpStatus.CREATED);
    }
    //findbyid
    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDTOView> getAdById(@PathVariable("id") Long id) {
        AdvertisementDTOView adView = advertisementService.findById(id);
        return new ResponseEntity<>(adView, HttpStatus.OK);
    }
    //findAll
    @GetMapping
    public ResponseEntity<List<AdvertisementDTOView>> getAllAds() {
        List<AdvertisementDTOView> adViews = advertisementService.findAll();
        return new ResponseEntity<>(adViews, HttpStatus.OK);
    }
    //findall by sellerid
    @GetMapping("/seller/{sellerEmail}")
    public ResponseEntity<List<AdvertisementDTOView>> getAdsBySeller(@PathVariable("sellerEmail") String sellerEmail) {
        List<AdvertisementDTOView> adViews = advertisementService.findAllBySeller(sellerEmail);
        return new ResponseEntity<>(adViews, HttpStatus.OK);
    }

    //findbyfilterandvalue
    @GetMapping("/filter")
    public ResponseEntity<List<AdvertisementDTOView>> getAdsByFilter(@RequestParam String filter, @RequestParam String value) {
        List<AdvertisementDTOView> adViews = advertisementService.findByFilterAndValue(filter, value);
        return new ResponseEntity<>(adViews, HttpStatus.OK);
    }

    //findbyprice range
    @GetMapping("/filter/price")
    public ResponseEntity<List<AdvertisementDTOView>> getAdsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<AdvertisementDTOView> adViews = advertisementService.findByPriceRange(min, max);
        return new ResponseEntity<>(adViews, HttpStatus.OK);
    }


}
