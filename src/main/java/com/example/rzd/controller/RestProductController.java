package com.example.rzd.controller;

import com.example.rzd.dto.CustomUserDetails;
import com.example.rzd.entity.*;
import com.example.rzd.service.CartService;
import com.example.rzd.service.OrderService;
import com.example.rzd.service.ProductService;
import com.example.rzd.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestProductController {
    final ProductService productService;
    final
    UserService userService;
    final
    CartService cartService;
    final OrderService orderService;

    public RestProductController(ProductService productService, CartService cartService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
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

    @GetMapping("/getOrders")
    public List<Order> getUserOrders(Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId()); // Получение управляемого экземпляра User
        return orderService.getUsersOrders(user);
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/searchProducts")
    public List<Product> search(@RequestParam String query) {
        return productService.findByColumnNameContaining(query);
    }

    @PostMapping("/addProduct")
    public void addProduct(Authentication authentication, @RequestBody ProductCart productCart) {
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
            cart.setPrice(productCart.getPrice());
            cart.setMonth(productCart.getMonth());
            cart.setQuarter(productCart.getQuarter());
            cart.setReason(productCart.getReason());

            try {
                cartService.saveProductCart(cart);
                System.out.println("Добавлено в корзину");
            } catch (Exception e) {
                System.out.println("Не могу " + e);
            }
        }
//        List<Cart> productsInCart = cartService.getProductsInCartForUser(user);
    }
    @PostMapping("/deleteProductCart")
    public void deleteProductCart(Authentication authentication, @RequestBody Cart cart){
        cartService.deleteById(cart.getId());
    }
    @PostMapping("/editProductCart")
    public void editProductCart(Authentication authentication, @RequestBody ProductCart productCart) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(userDetails.getId()); // Получение управляемого экземпляра User


        Product product = productService.getProductById(productCart.getProduct().getId()); // Получение управляемого экземпляра Product

        Cart existingCartEntry = cartService.findCartByUserAndProduct(user, product);
        if (existingCartEntry != null) {
            // Если продукт уже есть в корзине, увеличиваем количество товара
            existingCartEntry.setProductCount(productCart.getProductCount());
            existingCartEntry.setReason(productCart.getReason());
            existingCartEntry.setPrice(productCart.getPrice());
            existingCartEntry.setSum(productCart.getSum());
            existingCartEntry.setQuarter(productCart.getQuarter());
            existingCartEntry.setMonth(productCart.getMonth());
            cartService.saveProductCart(existingCartEntry);
            System.out.println("Обновлены параметры товара ID: " + existingCartEntry.getId());
        }

    }

    @PostMapping("/editOrderStatus")
    public void editOrderStatus(@RequestBody Order order) {
        orderService.changeOrderStatus(order.getId(), order.getStatus());
    }

    @PostMapping("/createComment")
    public void createComment(@RequestBody Order order) {
        orderService.createComment(order.getId(), order.getComment());
    }

    @PostMapping("/createOder")
    public void createOder(Authentication authentication,@RequestBody Cart cart){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (cartService.findEntityWithNonEmptyValues(cart.getId()) != null){
            User user = userService.getUserById(userDetails.getId()); // Получение управляемого экземпляра User

            Product product = productService.getProductById(cart.getProduct().getId()); // Получение управляемого экземпляра Product

            Cart existingCartEntry = cartService.findCartByUserAndProduct(user, product);
            Order order = new Order();
            orderService.saveOrder(order.getOrderFromCart(existingCartEntry));
            cartService.deleteById(existingCartEntry.getId());

        }
    }

    @GetMapping("/getOrdersByStatus")
    public List<Order> getOrdersByStatus(@RequestParam String query){
        return orderService.getOrdersByStatus(query);
    }

    @GetMapping("/getOrdersByQuarter")
    public List<Order> getOrdersByStatus(@RequestParam int query){
        return orderService.getOrdersByQuarter(query);
    }
}
