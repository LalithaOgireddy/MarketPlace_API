package com.lalitha.marketplace_api.controller;

import com.lalitha.marketplace_api.domain.dto.UserDetailsDTOForm;
import com.lalitha.marketplace_api.domain.dto.UserDetailsDTOView;
import com.lalitha.marketplace_api.service.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userDetails")
@Validated
public class UserDetailsController {
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDTOView> createUserDetails(@Valid @RequestBody UserDetailsDTOForm userDetailsDTOForm) {
        UserDetailsDTOView userDetailsDTOView = userDetailsService.create(userDetailsDTOForm);
        return new ResponseEntity<>(userDetailsDTOView, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTOView> getUserDetails(@PathVariable Long id){
        UserDetailsDTOView userDetailsDTOView = userDetailsService.findById(id);
        if(userDetailsDTOView != null){
            return new ResponseEntity<>(userDetailsDTOView, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsDTOView>> getAllUserDetails(){
        List<UserDetailsDTOView> userDetailsDTOViews = userDetailsService.findAll();
        return ResponseEntity.ok(userDetailsDTOViews);
    }

    @PutMapping
    public ResponseEntity<UserDetailsDTOView> updateUserDetails(@Valid @RequestBody UserDetailsDTOForm userDetailsDTOForm) {
        UserDetailsDTOView updatedView = userDetailsService.update(userDetailsDTOForm);
        if(updatedView != null){
            return new ResponseEntity<>(updatedView, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDetailsDTOView> deleteUserDetails(@PathVariable Long id){
        userDetailsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
