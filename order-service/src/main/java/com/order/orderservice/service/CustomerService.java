package com.order.orderservice.service;

import com.order.orderservice.client.CustomerClient;
import com.order.orderservice.model.Customer;
import com.order.orderservice.model.CustomerResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;

    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "fallBackGetCustomer")
    public CustomerResponse getCustomer(Long id) {
        CustomerResponse customerResponse = new CustomerResponse();

        Customer customer = customerClient.getCustomerById(id);
        if (customer != null) {
            customerResponse.setCustomer(customer);
            customerResponse.setIsError(false);
        } else {
            customerResponse.setIsError(true);
            customerResponse.setErrorMessage("Customer Not Found");
        }

        return customerResponse;
    }

    public CustomerResponse fallBackGetCustomer(Long id, Throwable t) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setIsError(true);
        customerResponse.setErrorMessage("Customer Service Temporary Unavailable. Please try again later.");

        return customerResponse;
    }

}
