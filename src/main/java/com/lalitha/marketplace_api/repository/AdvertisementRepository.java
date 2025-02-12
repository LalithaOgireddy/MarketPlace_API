package com.lalitha.marketplace_api.repository;

import com.lalitha.marketplace_api.domain.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
