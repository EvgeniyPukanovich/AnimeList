package com.example.animelist.controllers;

import com.example.animelist.models.User;
import com.example.animelist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Home page");
        return "index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact information");
        model.addAttribute("message", "AnimeList");
        return "contact";
    }

    @GetMapping("/registration")
    public String registration(Model model)
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model)
    {
        try{
            userService.saveUser(user);
            return "redirect:/login";
        }
        catch (Exception e){
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}
