package com.study.security.service.impl;

import com.study.security.exception.InvalidPasswordException;
import com.study.security.model.UserSale;
import com.study.security.repository.UserSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserSaleRepository userSaleRepository;

    public UserDetails auth(UserSale userSale) {
        UserDetails userDetails = loadUserByUsername(userSale.getLogin());
        // Senha atual, senha esperada
        boolean isValid = passwordEncoder.matches(userSale.getPassword(), userDetails.getPassword());

        if (!isValid) {
            throw new InvalidPasswordException("Senha inválida.");
        }

        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSale userSale = userSaleRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found in the database."));

        String[] roles = userSale.isAdmin() ? new String[]{"ADMIN", "USER"}
                : new String[]{"USER"};

        return User.builder()
                .username(userSale.getLogin())
                .password(userSale.getPassword())
                .roles(roles)
                .build();
    }

    @Transactional
    public UserSale save(UserSale userSale) {
        return userSaleRepository.save(userSale);
    }
}
