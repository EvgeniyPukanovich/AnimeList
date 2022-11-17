package com.example.animelist.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@ResponseBody
public class UserController {
    @GetMapping("/user")
    public String getCurrentUser(Principal principal){
        if (principal != null)
            return principal.getName();
        else
            return "redirect:/login";
    }
}
