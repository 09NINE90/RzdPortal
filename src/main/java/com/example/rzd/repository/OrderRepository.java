package com.example.rzd.repository;

import com.example.rzd.entity.Cart;
import com.example.rzd.entity.Order;
import com.example.rzd.entity.Product;
import com.example.rzd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User user);

    @Query(value = "SELECT * FROM products_order po ORDER BY po.id LIMIT 100", nativeQuery = true)
    List<Order> findFirst100();

    @Query(value = "SELECT * FROM products_order po WHERE status = :status LIMIT 100", nativeQuery = true)
    List<Order> findOrdersByStatus(@Param("status") String status);

    @Query(value = "SELECT * FROM products_order po WHERE quarter = :quarter LIMIT 100", nativeQuery = true)
    List<Order> findOrdersByQuarter(@Param("quarter") int quarter);

    @Modifying
    @Query(value = "UPDATE products_order SET status = :status  WHERE id = :order_id", nativeQuery = true)
    void updateStatusById(@Param("order_id") Long id, @Param("status") String status);

    @Modifying
    @Query(value = "UPDATE products_order SET comment = :comment  WHERE id = :order_id", nativeQuery = true)
    void updateCommentById(@Param("order_id") Long id, @Param("comment") String comment);
}
