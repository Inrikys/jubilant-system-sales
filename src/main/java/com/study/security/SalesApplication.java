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
//        System.out.println("Saving customers");
//
//        Customer customer = new Customer("Fulano");
//        customer.setCpf("01624343082");
//        customerRepository.save(customer);


//        System.out.println(this.jumps(5, 2));

    }
//
//    public int jumps(int alturaBandeira, int bigJump) {
//
//        int result = 0;
//        int escalada = alturaBandeira;
//
//        while (escalada >= bigJump) {
//            result++;
//            escalada -= bigJump;
//        }
//
//        while (escalada > 0) {
//            result++;
//            escalada--;
//        }
//
//        return result;
//
//    }

}
