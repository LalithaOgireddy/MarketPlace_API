package com.lalitha.marketplace_api.service;

import com.lalitha.marketplace_api.domain.dto.AdvertisementDTOView;
import com.lalitha.marketplace_api.domain.dto.UserDTOForm;
import com.lalitha.marketplace_api.domain.dto.UserDTOView;

import java.util.List;

public interface UserService {

    UserDTOView register(UserDTOForm userDTOForm);

    boolean authorizeUser(String email, String password);

    UserDTOView getByEmail(String email);

    void disableByEmail(String email);

    void enableByEmail(String email);

    List<AdvertisementDTOView> authAndGetCatalog(UserDTOForm userDTOForm);
}
