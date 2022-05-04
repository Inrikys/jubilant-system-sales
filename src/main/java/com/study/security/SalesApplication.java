package com.study.security;

import com.study.security.model.Customer;
import com.study.security.model.Order;
import com.study.security.repository.CustomerRepository;
import com.study.security.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class SalesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Saving customers");

        Customer customer = new Customer("Fulano");
        customerRepository.save(customer);

        Order o = new Order();
        o.setCustomer(customer);
        o.setOrderDate(LocalDate.now());
        o.setTotal(BigDecimal.valueOf(100));

        orderRepository.save(o);

//        customer = customerRepository.findCustomerFetchOrders(customer.getId());
//        System.out.println(customer);
//        System.out.println(customer.getOrders());

        orderRepository.findByCustomer(customer).forEach(System.out::println);
    }
}
