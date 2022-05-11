package com.study.security.repository;

import com.study.security.model.Customer;
import com.study.security.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

    @Query("select o from Order o left join fetch o.orderItems where o.id = :id")
    Optional<Order> findByIdFetchOrderItems(Long id);

}
