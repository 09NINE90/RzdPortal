package com.example.rzd.service;

import com.example.rzd.entity.Order;
import com.example.rzd.entity.User;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    List<Order> getUsersOrders(User user);
}
