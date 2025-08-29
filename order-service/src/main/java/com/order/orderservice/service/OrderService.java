package com.order.orderservice.service;

import com.order.orderservice.entity.Order;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(Order newOrder);
}
