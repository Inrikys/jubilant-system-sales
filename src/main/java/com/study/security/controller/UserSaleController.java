package com.study.security.controller;

import com.study.security.exception.InvalidPasswordException;
import com.study.security.model.UserSale;
import com.study.security.request.CredentialsRequest;
import com.study.security.response.CredentialsResponse;
import com.study.security.security.JwtService;
import com.study.security.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserSaleController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserSale save(@Valid @RequestBody UserSale userSale) {
        String passwordEncrypted = passwordEncoder.encode(userSale.getPassword());
        userSale.setPassword(passwordEncrypted);
        return this.userService.save(userSale);
    }

    @PostMapping("/auth")
    public CredentialsResponse auth(@RequestBody CredentialsRequest credentials) {
        try {
            UserSale userSale = UserSale.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword())
                    .build();

            UserDetails userDetails = userService.auth(userSale);
            String token = jwtService.getToken(userSale);
            return new CredentialsResponse(userSale.getLogin(), token);

        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
