package com.example.rzd.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int productCount;
    private String reason;
    private Double price;
    private Double sum;
    private byte month;
    private byte quarter;
    private String status;
    private String comment;

    public Order getOrderFromCart(Cart cart){
        Order order= new Order();
        order.setPrice(cart.getPrice());
        order.setQuarter(cart.getQuarter());
        order.setMonth(cart.getMonth());
        order.setReason(cart.getReason());
        order.setProduct(cart.getProduct());
        order.setUser(cart.getUser());
        order.setSum(cart.getSum());
        order.setProductCount(cart.getProductCount());
        order.setStatus("Отправлено");
        return order;
    }

}
