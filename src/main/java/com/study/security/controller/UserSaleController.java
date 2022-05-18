package com.study.security.controller;

import com.study.security.model.UserSale;
import com.study.security.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserSaleController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserSale save(@Valid @RequestBody UserSale userSale) {
        String passwordEncrypted = passwordEncoder.encode(userSale.getPassword());
        userSale.setPassword(passwordEncrypted);
        return this.userService.save(userSale);
    }

}
