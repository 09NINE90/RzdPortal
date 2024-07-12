package com.example.rzd.service;

import com.example.rzd.entity.Product;

import java.util.List;

public interface ProductService {
    public void saveProduct(Product product);

    List<Product> getAllProducts();

    List<Product> findByColumnNameContaining(String category);

    boolean existsByProductId(Long productId);

    Product getProductById(long id);
}
