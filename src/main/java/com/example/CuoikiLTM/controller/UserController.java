package com.example.CuoikiLTM.controller;


import com.example.CuoikiLTM.model.User;
import com.example.CuoikiLTM.repository.UserRepository;
import com.example.CuoikiLTM.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/view")
    public String viewprofile(Principal principal, Model model) {
        String username = principal.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user=userOptional.get();
        model.addAttribute("user",user);
        return "profile";
    }
    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("user") User user) {
        User user1= userService.getUserByUserName(user.getUsername());
        user.setPassword(user1.getPassword());
        user.setRoles(user1.getRoles());
        userService.updateUser(user);
        return "redirect:/user/view";
    }
    @PostMapping("/editImgUser")
    public String editFood(Principal principal, @RequestParam("file") MultipartFile file) {
        User user = userService.getUserByUserName(principal.getName());
        try {
            if (!file.isEmpty()) {
                String fileName = "/static/image/" + file.getOriginalFilename();
                Path path = Paths.get("src/main/resources" + fileName);
                Files.write(path, file.getBytes());
                user.setImage(fileName);
            }
            userService.updateUser(user);
            return "redirect:/user/view";
        } catch (IOException e) {
            // Xử lý lỗi khi lưu tệp
            e.printStackTrace();
            return "errorPage";
        }
    }

}
