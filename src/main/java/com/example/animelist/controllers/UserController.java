package com.example.animelist.controllers;

import com.example.animelist.models.Anime;
import com.example.animelist.models.MyUserPrincipal;
import com.example.animelist.models.User;
import com.example.animelist.services.AnimeService;
import com.example.animelist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final AnimeService animeService;

    @Autowired
    public UserController(UserService userService, AnimeService animeService){
        this.userService = userService;
        this.animeService = animeService;
    }

    @GetMapping("/user/{username}")
    public String getCurrent(Model model, @PathVariable String username){
        if (username.equals(""))
            return "redirect:/user";
        User user = userService.getUser(username);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("animelist", user.getAnimeList());
        return "user";
    }

    @GetMapping("/user")
    public String getCurrentUser(Principal principal, Model model){
        if (principal==null)
            return "redirect:/login";
        User user = userService.getUser(principal.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("animelist", user.getAnimeList().toArray());
        return "user";
    }

    @GetMapping("/add_anime")
    public String addAnime(@RequestParam Long id, Principal principal) throws Exception {
        Anime anime = animeService.getAnimeById(id);
        if (principal==null) return "redirect:/login";
        User user = userService.getUser(principal.getName());
        userService.addAnimeToUser(user, anime);
        return "redirect:/user";
    }
}
