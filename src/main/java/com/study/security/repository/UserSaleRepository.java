package com.study.security.repository;

import com.study.security.model.UserSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSaleRepository extends JpaRepository<UserSale, Long> {

    Optional<UserSale> findByLogin(String login);

}
