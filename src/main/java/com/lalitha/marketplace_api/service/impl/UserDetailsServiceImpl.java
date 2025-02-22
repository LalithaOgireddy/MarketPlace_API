package com.lalitha.marketplace_api.service.impl;

import com.lalitha.marketplace_api.domain.dto.UserDetailsDTOForm;
import com.lalitha.marketplace_api.domain.dto.UserDetailsDTOView;
import com.lalitha.marketplace_api.domain.entity.UserDetails;
import com.lalitha.marketplace_api.exception.DataNotFoundException;
import com.lalitha.marketplace_api.repository.UserDetailsRepository;
import com.lalitha.marketplace_api.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetailsDTOView create(UserDetailsDTOForm userDetailsDTOForm) {
        //check if Form is null, throw exception if null
        if(userDetailsDTOForm == null) throw new IllegalArgumentException("UserDetails Form cannot be null");

        //create entity from form
        UserDetails userDetails = UserDetails.builder()
                .firstName(userDetailsDTOForm.firstName())
                .lastName(userDetailsDTOForm.lastName())
                .phoneNumber(userDetailsDTOForm.phoneNumber())
                .city(userDetailsDTOForm.city())
                .build();

        //save entity in db
        userDetails = userDetailsRepository.save(userDetails);

        //create view from saved entity and return
        return UserDetailsDTOView.builder()
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .phoneNumber(userDetails.getPhoneNumber())
                .city(userDetails.getCity())
                .build();
    }

    @Override
    public UserDetailsDTOView findById(Long id) {
        UserDetails userDetails = userDetailsRepository.findById(id).orElseThrow(() -> new DataNotFoundException("User id is not found"));
        return UserDetailsDTOView.builder()
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .phoneNumber(userDetails.getPhoneNumber())
                .city(userDetails.getCity())
                .build();

    }

    @Override
    public List<UserDetailsDTOView> findAll() {
        return userDetailsRepository.findAll().stream()
                .map(userDetails -> UserDetailsDTOView.builder()
                        .id(userDetails.getId())
                        .firstName(userDetails.getFirstName())
                        .lastName(userDetails.getLastName())
                        .phoneNumber(userDetails.getPhoneNumber())
                        .city(userDetails.getCity())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDTOView update(UserDetailsDTOForm userDetailsDTOForm) {
        //validate input, if null return exception
        if(userDetailsDTOForm == null) throw new IllegalArgumentException("UserDetails Form cannot be null");

        //get entity for input form based on id, throw exception if not found
        UserDetails userdetails = userDetailsRepository.findById(userDetailsDTOForm.id()).orElseThrow(()->new DataNotFoundException("User id is not found"));

        //update or set all details
        userdetails.setFirstName(userDetailsDTOForm.firstName());
        userdetails.setLastName(userDetailsDTOForm.lastName());
        userdetails.setPhoneNumber(userDetailsDTOForm.phoneNumber());
        userdetails.setCity(userDetailsDTOForm.city());

        //save new entity to db
        userdetails = userDetailsRepository.save(userdetails);

        //change updated entity to view and return
        return UserDetailsDTOView.builder()
                .firstName(userdetails.getFirstName())
                .lastName(userdetails.getLastName())
                .phoneNumber(userdetails.getPhoneNumber())
                .city(userdetails.getCity())
                .build();
    }

    @Override
    public void deleteById(Long id) {
        //get entity for input id, throw exception if not found
        UserDetails userdetails = userDetailsRepository.findById(id).orElseThrow(()->new DataNotFoundException("User id is not found"));

        //delete the entity
        userDetailsRepository.deleteById(id);
    }
}
