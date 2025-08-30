package com.order.orderservice.service;

import com.order.orderservice.entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> getAllOrders();
    Orders createOrder(Orders newOrder);
}
