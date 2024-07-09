package com.example.rzd.repository;

import com.example.rzd.entity.Cart;
import com.example.rzd.entity.Order;
import com.example.rzd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User user);

}
