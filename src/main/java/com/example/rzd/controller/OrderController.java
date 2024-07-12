package com.example.rzd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rzd/portal")
public class OrderController {
    @GetMapping("/orders")
    public String getOrders(){
        return "ordersPage";
    }

    @GetMapping("/allOrders")
    public String getAllOrders(){return "allOrdersPage";}
}
