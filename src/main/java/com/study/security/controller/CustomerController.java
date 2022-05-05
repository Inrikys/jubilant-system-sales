package com.study.security.controller;

import com.study.security.model.Customer;
import com.study.security.repository.CustomerRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> findAllByExample(Customer filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);

        List<Customer> customers = this.customerRepository.findAll(example);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        Customer customerResult = customerRepository.save(customer);
        return ResponseEntity.ok(customerResult);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Customer> customerResult = customerRepository.findById(id);
        if (customerResult.isPresent()) {
            customerRepository.delete(customerResult.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        return customerRepository.findById(id)
                .map(c -> {
                    customer.setId(c.getId());
                    customerRepository.save(customer);
                    return ResponseEntity.ok().body(customer);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
