package com.example.rzd.repository;

import com.example.rzd.entity.Cart;
import com.example.rzd.entity.Product;
import com.example.rzd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
    Cart findByUserAndProduct(User user, Product product);
    @Query(value = "SELECT * FROM products_cart pc WHERE pc.id =:id AND pc.reason IS NOT NULL AND pc.reason <> '' AND pc.sum IS NOT NULL AND  pc.price IS NOT NULL", nativeQuery = true)
    Cart findEntityWithNonEmptyValues(@Param("id") Long id);
}
