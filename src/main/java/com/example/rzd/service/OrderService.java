package com.example.rzd.service;

import com.example.rzd.entity.Order;
import com.example.rzd.entity.User;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);
    List<Order> getUsersOrders(User user);

    List<Order> getAllOrders();

    List<Order> getOrdersByStatus(String status);
    List<Order> getOrdersByQuarter(int quarter);
    void changeOrderStatus(Long id, String status);


}
