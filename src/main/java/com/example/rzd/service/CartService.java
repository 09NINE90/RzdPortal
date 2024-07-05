package com.example.rzd.service;

import com.example.rzd.entity.Cart;
import com.example.rzd.entity.Product;
import com.example.rzd.entity.User;

import java.util.List;

public interface CartService {
    void saveProductCart(Cart cart);

    public List<Cart> getProductsInCartForUser(User user);

    Cart findCartByUserAndProduct(User user, Product product);
}
