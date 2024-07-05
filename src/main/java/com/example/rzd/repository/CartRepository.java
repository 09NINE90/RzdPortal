package com.example.rzd.repository;

import com.example.rzd.entity.Cart;
import com.example.rzd.entity.Product;
import com.example.rzd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
    Cart findByUserAndProduct(User user, Product product);
}
