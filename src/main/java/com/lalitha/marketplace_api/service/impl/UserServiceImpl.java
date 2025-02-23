package com.lalitha.marketplace_api.service.impl;

import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOView;
import com.lalitha.marketplace_api.domain.dto.UserDTOForm;
import com.lalitha.marketplace_api.domain.dto.UserDTOView;
import com.lalitha.marketplace_api.domain.entity.Advertisement;
import com.lalitha.marketplace_api.domain.entity.User;
import com.lalitha.marketplace_api.exception.DataNotFoundException;
import com.lalitha.marketplace_api.exception.InvalidUserException;
import com.lalitha.marketplace_api.repository.AdvertisementRepository;
import com.lalitha.marketplace_api.repository.UserRepository;
import com.lalitha.marketplace_api.service.AdvertisementService;
import com.lalitha.marketplace_api.service.UserService;
import com.lalitha.marketplace_api.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder passwordEncoder;
    private final AdvertisementService advertisementService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomPasswordEncoder passwordEncoder, AdvertisementService advertisementService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.advertisementService = advertisementService;
    }

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {
        //check if input form is null. If null throw an exception
        if(userDTOForm == null) throw new IllegalArgumentException("User form cannot be null");

        //check if input is already existing. If so throw an exception
        boolean emailExists = userRepository.existsByEmail(userDTOForm.email());
        if(emailExists) throw new DuplicateKeyException("Email already exists");

        //convert form to entity
        User user = User.builder()
                .email(userDTOForm.email())
                .password(passwordEncoder.encode(userDTOForm.password()))
                .build();

        //save to db
        userRepository.save(user);

        // convert saved user to view
        UserDTOView userView = UserDTOView.builder()
                .email(user.getEmail())
                .build();

        //return view
        return userView;
    }

    @Override
    public boolean authorizeUser(String email, String password) {
        //get user entity object based on email
        User user = userRepository.getUserByEmail(email);

        //if user not found, return false
        if(user == null) return false;

        //if user is found, compare passwords, if not same  return false else return true
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserDTOView getByEmail(String email) {
        //get user by email
        User user = userRepository.getUserByEmail(email);
        UserDTOView userView;

        //if no user is returned, throw exception else convert entity to view
        if (user == null) throw new DataNotFoundException("User not found");
        else {
            userView = UserDTOView.builder()
                    .email(user.getEmail())
                    .build();
        }
        return userView;
    }

    @Override
    public void disableByEmail(String email) {
        //if user doesn't exists, throw exception
        if(!userRepository.existsByEmail(email)) throw new DataNotFoundException("User not found");

        //else update
        userRepository.updateExpiredByEmail(email,false);
    }

    @Override
    public void enableByEmail(String email) {
        //if user doesn't exists, throw exception
        if(!userRepository.existsByEmail(email)) throw new DataNotFoundException("User not found");

        //else update
        userRepository.updateExpiredByEmail(email,true);
    }

    @Override
    public List<AdvertisementDTOView> authAndGetCatalog(UserDTOForm userDTOForm) {
        List<AdvertisementDTOView> ads;
        boolean authUser = authorizeUser(userDTOForm.email(), userDTOForm.password());
        if(!authUser) { throw new InvalidUserException("User not found"); }
        else {
            ads = advertisementService.findAllBySeller(userDTOForm.email());
        }
        if(ads.isEmpty()) throw new DataNotFoundException("No advertisements found");
        return ads;

    }
}
