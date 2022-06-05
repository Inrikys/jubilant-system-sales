package com.study.security.controller;

import com.study.security.model.Customer;
import com.study.security.repository.CustomerRepository;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Api("Clientes")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get customers details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Content found."),
            @ApiResponse(code = 404, message = "Customer not found.")
    })
    public List<Customer> findAllByExample(@ApiParam(name = "Customer info") Customer filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);

        List<Customer> customers = this.customerRepository.findAll(example);
        return customers;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get customer details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Content found."),
            @ApiResponse(code = 404, message = "Customer not found.")
    })
    public Customer findCustomerById(@ApiParam(name = "Customer id", example = "2") @PathVariable Long id) {
        return this.customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Save new customer")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer has been saved."),
            @ApiResponse(code = 400, message = "Validation error.")
    })
    public Customer save(@ApiParam(name = "Customer info") @Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Save new customer")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Customer has been deleted."),
            @ApiResponse(code = 404, message = "Customer not found.")
    })
    public void delete(@ApiParam(name = "Customer id", example = "2") @PathVariable Long id) {
        customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return customer;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
    }

    @PutMapping("{id}")
    @ApiOperation("Save new customer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer has been updated."),
            @ApiResponse(code = 404, message = "Customer not found.")
    })
    public ResponseEntity<Customer> update(@ApiParam(name = "Customer info", example = "2") @PathVariable Long id, @Valid @RequestBody Customer customer) {
        return customerRepository.findById(id)
                .map(c -> {
                    customer.setId(c.getId());
                    customerRepository.save(customer);
                    return ResponseEntity.ok().body(customer);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found."));
    }
}
