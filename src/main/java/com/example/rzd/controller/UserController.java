package com.example.rzd.controller;

import com.example.rzd.dto.CustomUserDetails;
import com.example.rzd.dto.UserDTO;
import com.example.rzd.entity.*;
import com.example.rzd.service.ExcelService;
import com.example.rzd.service.MailService;
import com.example.rzd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rzd/portal/user")
public class UserController {
    private UserService userService;
    private ExcelService excelService;
    MailService mailService;

    @Autowired
    public UserController(ExcelService excelService, MailService mailService, UserService userService) {
        this.excelService = excelService;
        this.mailService = mailService;
        this.userService = userService;
    }

    @GetMapping("/logout")
    public String logout() {

        return "login";
    }
    @GetMapping("/{userId}")
    public String userCreate(@PathVariable("userId") Long id, Model model){
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();

        return "userPage";
    }
    @GetMapping("/delete/{userId}")
    public String userDelete(@PathVariable("userId") Long id){
        userService.deleteUser(id);
        return "redirect:/rzd/portal/home";
    }
    @GetMapping("/create")
    public String userCreate(Model model){
        excelService.read();
        model.addAttribute("user",new UserDTO());
        return "signup";
    }
    @PostMapping("/create")
    public String userSave(Authentication authentication, @ModelAttribute("user") User user, Model model){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (user.getRoles().equals("initiator")){
            user.setCreatorId(userDetails.getId());
            user.setRoles("ROLE_INITIATOR");
        }else if (user.getRoles().equals("receptionist")){
            user.setCreatorId(userDetails.getId());
            user.setRoles("ROLE_RECEPTIONIST");
        }

//        user.setRoles("ROLE_ADMIN");
//        mailService.sendMail(user, MailType.REGISTRATION, new Properties());
        userService.createUser(user);
        return "redirect:/rzd/portal/home";
    }

}

