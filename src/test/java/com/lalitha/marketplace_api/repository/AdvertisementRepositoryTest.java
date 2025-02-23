package com.lalitha.marketplace_api.repository;

import com.lalitha.marketplace_api.domain.entity.Advertisement;
import com.lalitha.marketplace_api.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AdvertisementRepositoryTest {

    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private UserRepository userRepository;

    private Advertisement ad1, ad2;
    private User seller;

    @BeforeEach
    void setUp() {
        seller = new User("test@lexicon.se","Password1!");
        userRepository.save(seller);

        ad1 = Advertisement.builder()
                .title("Laptop for sale")
                .description("Gaming laptop in good condition")
                .category("Electronics")
                .brand("Dell")
                .price(1000.00)
                .currency("SEK")
                .item_condition("Used")
                .createdDate(LocalDate.now())
                .expiryDate(LocalDate.now().plusDays(10))
                .sold(false)
                .seller(seller)
                .build();

        ad2 = Advertisement.builder()
                .title("Phone for sale")
                .description("Brand new phone")
                .category("Electronics")
                .brand("Samsung")
                .price(800.00)
                .currency("SEK")
                .item_condition("Like New")
                .createdDate(LocalDate.now())
                .expiryDate(LocalDate.now().plusDays(10))
                .sold(false)
                .seller(seller)
                .build();

        advertisementRepository.save(ad1);
        advertisementRepository.save(ad2);
    }

    @Test
    void testFindAllAdv() {
        List<Advertisement> ads = advertisementRepository.findAllAdv();
        assertEquals(2, ads.size());
    }

    @Test
    void testFindAllBySeller() {
        List<Advertisement> ads = advertisementRepository.findAllBySeller("test@lexicon.se");
        assertEquals(2, ads.size());
    }

    @Test
    void testFindByBrand() {
        List<Advertisement> ads = advertisementRepository.findByBrand("dell");
        assertEquals(1, ads.size());
        assertEquals("Dell", ads.get(0).getBrand());
    }

    @Test
    void testFindByTitleContains() {
        List<Advertisement> ads = advertisementRepository.findByTitleContains("Laptop");
        assertEquals(1, ads.size());
    }

    @Test
    void testFindByCategory() {
        List<Advertisement> ads = advertisementRepository.findByCategory("Electronics");
        assertEquals(2, ads.size());
    }

    @Test
    void testFindByPriceBetween() {
        List<Advertisement> ads = advertisementRepository.findByPriceBetween(500.0, 1200.0);
        assertEquals(2, ads.size());
    }
}
