package com.example.rzd.repository;

import com.example.rzd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM products p ORDER BY p.id LIMIT 100", nativeQuery = true)
    List<Product> findFirst30();

//    @Query(value = "SELECT * FROM products p WHERE p.product_name LIKE %:query%", nativeQuery = true)
//    List<Product> findByProductNameContaining(@Param("query") String query);

//    @Query(value = "SELECT * FROM products p WHERE LOWER(p.product_name) LIKE LOWER(:query)%", nativeQuery = true)
//    List<Product> findByProductNameContaining(@Param("query") String query);

    @Query(value = "SELECT *  FROM products p WHERE LOWER(p.product_name) LIKE LOWER(CONCAT(:query, '%'))", nativeQuery = true)
    List<Product> findByProductNameContaining(@Param("query") String query);

    boolean existsByProductId(Long productId);


}
