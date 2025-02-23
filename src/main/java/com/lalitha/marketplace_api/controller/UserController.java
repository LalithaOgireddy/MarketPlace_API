package com.lalitha.marketplace_api.controller;

import com.lalitha.marketplace_api.domain.dto.UserDTOForm;
import com.lalitha.marketplace_api.domain.dto.UserDTOView;
import com.lalitha.marketplace_api.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/users")
@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<UserDTOView> doGetUserByEmail(
            @RequestParam
            @NotEmpty
            @NotNull
            @Email(message = "Invalid Email format")
            String email) {
        UserDTOView responseBody =userService.getByEmail(email);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<UserDTOView> doRegisterUser(@Valid @RequestBody UserDTOForm userDTOForm) {
        UserDTOView userDTOView =userService.register(userDTOForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTOView);
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> doDisableUser(@RequestParam("email") String email) {
        userService.disableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> doEnableUser(@RequestParam("email") String email) {
        userService.enableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
