package com.customer.customerservice.service;

import com.customer.customerservice.entity.Customer;
import com.customer.customerservice.exception.CustomerAlreadyExistsException;
import com.customer.customerservice.exception.CustomerNotFoundException;
import com.customer.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> fetchCustomerById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
        Customer customer = customerRepository.findCustomerByEmail(newCustomer.getEmail()).orElse(null);
        if (customer != null) {
            throw new CustomerAlreadyExistsException(newCustomer.getEmail());
        }

        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer newCustomer, Long id) throws RuntimeException {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(newCustomer.getName());
                    existingCustomer.setEmail(newCustomer.getEmail());
                    existingCustomer.setLocation(newCustomer.getLocation());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public String deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        } else {
            return "Customer with id " + id + " not found!";
        }

        return "Customer with id " + id + " deleted!";
    }

}
