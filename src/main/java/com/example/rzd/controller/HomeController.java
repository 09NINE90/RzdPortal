package com.example.rzd.controller;

import com.example.rzd.dto.CustomUserDetails;
import com.example.rzd.dto.UserDTO;
import com.example.rzd.entity.User;
import com.example.rzd.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rzd/portal")
public class HomeController {
    @Autowired
    private UserService userService;



    @GetMapping("/home")
    public String getUserProfile(Authentication authentication, Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("post", userDetails.getPost());

        return "mainPage";
    }

    @GetMapping("/create")
    public String userCreate(Model model){
        model.addAttribute("user",new UserDTO());
        return "signup";
    }
    @PostMapping("/create")
    public String userSave(@ModelAttribute("user") User user, Model model){
        userService.createUser(user);
        List<User> usersList = userService.findAllUsers();
        model.addAttribute("usersList", usersList);
        return "mainPage";
    }

}