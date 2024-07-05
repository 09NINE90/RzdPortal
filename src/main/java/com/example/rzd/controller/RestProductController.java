package com.example.rzd.controller;

import com.example.rzd.dto.CustomUserDetails;
import com.example.rzd.dto.UserDTO;
import com.example.rzd.entity.Cart;
import com.example.rzd.entity.Product;
import com.example.rzd.entity.ProductCart;
import com.example.rzd.entity.User;
import com.example.rzd.service.CartService;
import com.example.rzd.service.ProductService;
import com.example.rzd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestProductController {
    private final ProductService productService;
    final
    UserService userService;
    final
    CartService cartService;

    public RestProductController(ProductService productService, CartService cartService, UserService userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/productsCart")
    public List<Cart> getUserProductsCart(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId()); // Получение управляемого экземпляра User
        return cartService.getProductsInCartForUser(user);
    }

    @GetMapping("/searchProducts")
    public List<Product> search( @RequestParam String query) {
        return productService.findByColumnNameContaining(query);
    }

    @PostMapping("/addProduct")
    public void addProduct(Authentication authentication, @RequestBody ProductCart productCart){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId()); // Получение управляемого экземпляра User

        Product product = productService.getProductById(productCart.getProduct().getId()); // Получение управляемого экземпляра Product

        Cart existingCartEntry = cartService.findCartByUserAndProduct(user, product);

        if (existingCartEntry != null) {
            // Если продукт уже есть в корзине, увеличиваем количество товара
            int newCount = existingCartEntry.getProductCount() + productCart.getProductCount();
            existingCartEntry.setProductCount(newCount);
            cartService.saveProductCart(existingCartEntry);
            System.out.println("Количество товара обновлено в корзине");
        } else {
            // Если продукта нет в корзине, добавляем его
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setProductCount(productCart.getProductCount());

            try {
                cartService.saveProductCart(cart);
                System.out.println("Добавлено в корзину");
            } catch (Exception e) {
                System.out.println("Не могу " + e);
            }
        }
//        List<Cart> productsInCart = cartService.getProductsInCartForUser(user);
    }
}
