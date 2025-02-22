package com.lalitha.marketplace_api.repository;

import com.lalitha.marketplace_api.domain.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT a FROM Advertisement a WHERE a.expiryDate > CURRENT_DATE()")
    List<Advertisement> findAllAdv();

    @Query("SELECT a FROM Advertisement a,User u WHERE a.expiryDate > CURRENT_DATE() and a.seller.email = :email")
    List<Advertisement> findAllBySeller(@Param("email") String email);

}
