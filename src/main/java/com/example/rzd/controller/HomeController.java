package com.example.rzd.controller;

import com.example.rzd.dto.CustomUserDetails;
import com.example.rzd.dto.UserDTO;
import com.example.rzd.entity.Product;
import com.example.rzd.entity.User;
import com.example.rzd.service.ExcelService;
import com.example.rzd.service.ProductService;
import com.example.rzd.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rzd/portal")
public class HomeController {
    private final UserService userService;
    private final ExcelService excelService;
    private final ProductService productService;

    public HomeController(UserService userService, ExcelService excelService, ProductService productService) {
        this.userService = userService;
        this.excelService = excelService;
        this.productService = productService;
    }


    @GetMapping("/home")
    public String getUserProfile(Authentication authentication, Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        List<Product> productList = productService.getAllProducts();
        Long userId = userDetails.getId();
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("post", userDetails.getPost());
//        model.addAttribute("productList",productList);
//        Thread thread = new Thread(() -> excelService.read("src/main/resources/excel/SKMTR.xlsx"));
//        thread.start();
        return "mainPage";
    }

    @GetMapping("/create")
    public String userCreate(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @PostMapping("/create")
    public String userSave(@ModelAttribute("user") User user, Model model) {
        userService.createUser(user);
        List<User> usersList = userService.findAllUsers();
        model.addAttribute("usersList", usersList);
        return "mainPage";
    }



}