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

    @Query("SELECT a FROM Advertisement a WHERE a.expiryDate > CURRENT_DATE() and lower(a.brand) = lower(:brand)")
    List<Advertisement> findByBrand(String brand);

    @Query("SELECT a FROM Advertisement a WHERE a.expiryDate > CURRENT_DATE() and lower(a.title) like concat('%',lower(:title),'%')")
    List<Advertisement> findByTitleContains(@Param("title") String title);

    @Query("SELECT a FROM Advertisement a WHERE a.expiryDate > CURRENT_DATE() and lower(a.category) = lower(:category)")
    List<Advertisement> findByCategory(@Param("category") String category);

    @Query("SELECT a FROM Advertisement a WHERE a.expiryDate > CURRENT_DATE() and a.price between :min and :max")
    List<Advertisement> findByPriceBetween(@Param("min") Double min, @Param("max") Double max);



}
