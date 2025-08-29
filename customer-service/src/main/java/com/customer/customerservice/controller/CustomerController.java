package com.customer.customerservice.controller;

import com.customer.customerservice.entity.Customer;
import com.customer.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> fetchAllCustomers() {
        return customerService.fetchAllCustomers();
    }

    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer fetchCustomerById(@PathVariable("id") Long id) {
        return customerService.fetchCustomerById(id).orElse(null);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerService.createCustomer(newCustomer);
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(updatedCustomer, id);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomer(@PathVariable("id") Long id) {
        return customerService.deleteCustomer(id);
    }

}
