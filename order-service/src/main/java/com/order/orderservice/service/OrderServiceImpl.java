package com.order.orderservice.service;

import com.order.orderservice.entity.Orders;
import com.order.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Orders createOrder(Orders newOrder) {
        return orderRepository.save(newOrder);
    }
}
