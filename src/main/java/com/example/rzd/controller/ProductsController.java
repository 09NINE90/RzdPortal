package com.example.rzd.controller;

import com.example.rzd.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rzd/portal")
public class ProductsController {
    final
    CartService cartService;

    public ProductsController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/productsCart")
    public String getCartPage(){
        return "productsCart";
    }
}
