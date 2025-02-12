package com.lalitha.marketplace_api.repository;

import com.lalitha.marketplace_api.domain.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
