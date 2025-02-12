package com.lalitha.marketplace_api.repository;

import com.lalitha.marketplace_api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    //Check if user exists by email
    boolean existsByEmail(String email);

    //Update expiry status by Email
    @Modifying
    @Query("UPDATE User u set u.expired = :status where u.email = :email")
    void updateExpiredByEmail(@Param("email")String email, @Param("status") boolean status);

    // Update password by Email
    @Modifying
    @Query("UPDATE User u set u.password = :password where u.email = :email")
    void updatePasswordByEmail(String email, String password);


}
