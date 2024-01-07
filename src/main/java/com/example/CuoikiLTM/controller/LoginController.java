package com.example.CuoikiLTM.controller;


import com.example.CuoikiLTM.model.User;
import com.example.CuoikiLTM.model.Verification;
import com.example.CuoikiLTM.service.EmailService;
import com.example.CuoikiLTM.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @GetMapping("/verification")
    @ResponseBody
    public String verification(@RequestParam String gmail) {
       Verification verification = new Verification();
       try {
           emailService.sendHtmlEmail(gmail,verification.getCode());
           return verification.getCode();
       }catch (Exception e){
           e.printStackTrace();
       }
        return verification.getCode();
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.register(user);
        return "redirect:/login";
    }


}