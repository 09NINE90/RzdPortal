package com.example.rzd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCart {
    private Product product;
    private int productCount;
    private String reason;
    private Double price;
    private Double sum;
    private byte month;
    private byte quarter;
//    private Cart cart;
}
