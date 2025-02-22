package com.lalitha.marketplace_api.service;

import com.lalitha.marketplace_api.domain.dto.UserDetailsDTOForm;
import com.lalitha.marketplace_api.domain.dto.UserDetailsDTOView;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDetailsService {

    UserDetailsDTOView create(UserDetailsDTOForm userDetailsDTOForm);
    UserDetailsDTOView findById(Long id);
    List<UserDetailsDTOView> findAll();
    UserDetailsDTOView update(UserDetailsDTOForm userDetailsDTOForm);
    void deleteById(Long id);
}
