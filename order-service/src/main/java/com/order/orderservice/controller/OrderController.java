package com.order.orderservice.controller;

import com.order.orderservice.entity.Orders;
import com.order.orderservice.model.CustomerResponse;
import com.order.orderservice.service.CustomerService;
import com.order.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        CustomerResponse customerResponse = customerService.getCustomer(order.getCustomerId());
        if (customerResponse.getIsError() && customerResponse.getErrorMessage().contains("Customer Not Found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerResponse.getErrorMessage());
        } else if (customerResponse.getIsError() &&
                        customerResponse.getErrorMessage()
                        .contains("Customer Service Temporary Unavailable. Please try again later.")) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(customerResponse.getErrorMessage());
        }

        return ResponseEntity.ok("Order placed for customer: " + orderService.createOrder(order).getCustomerId());
    }

    @GetMapping("/order")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

}
