package com.example.rzd.service.impl;

import com.example.rzd.entity.Order;
import com.example.rzd.entity.User;
import com.example.rzd.repository.OrderRepository;
import com.example.rzd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    final
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getUsersOrders(User user) {
        return orderRepository.findAllByUser(user);
    }
}
