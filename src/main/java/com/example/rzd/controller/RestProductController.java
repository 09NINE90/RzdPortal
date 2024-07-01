package com.example.rzd.controller;

import com.example.rzd.entity.Product;
import com.example.rzd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestProductController {
    private final ProductService productService;

    public RestProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/searchProducts")
    public List<Product> search(@RequestParam String query) {
        return productService.findByColumnNameContaining(query);
    }
}
