package com.customer.customerservice.service;

import com.customer.customerservice.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> fetchAllCustomers();
    Optional<Customer> fetchCustomerById(Long id);
    Customer createCustomer(Customer newCustomer);
    Customer updateCustomer(Customer newCustomer, Long id);
    String deleteCustomer(Long id);
}
