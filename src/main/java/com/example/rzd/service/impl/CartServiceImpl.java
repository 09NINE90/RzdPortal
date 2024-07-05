package com.example.rzd.service.impl;

import com.example.rzd.entity.Cart;
import com.example.rzd.entity.Product;
import com.example.rzd.entity.User;
import com.example.rzd.repository.CartRepository;
import com.example.rzd.repository.ProductsRepository;
import com.example.rzd.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    final
    CartRepository cartRepository;
    final ProductsRepository productsRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductsRepository productsRepository) {
        this.cartRepository = cartRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public void saveProductCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getProductsInCartForUser(User user) {
        return cartRepository.findAllByUser(user);
    }

    @Override
    public Cart findCartByUserAndProduct(User user, Product product) {
        return cartRepository.findByUserAndProduct(user,product);
    }


}
