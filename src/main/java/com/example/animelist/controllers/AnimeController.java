package com.example.animelist.controllers;

import com.example.animelist.models.Anime;
import com.example.animelist.models.Comment;
import com.example.animelist.models.User;
import com.example.animelist.repositories.UserRepository;
import com.example.animelist.services.AnimeService;
import com.example.animelist.services.CommentService;
import com.example.animelist.services.MALParserService;
import com.example.animelist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AnimeController {

    private final AnimeService animeService;
    private final MALParserService malParserService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public AnimeController(AnimeService animeService, MALParserService malParserService,
                           CommentService commentService, UserService userService){
        this.animeService = animeService;
        this.malParserService = malParserService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/animes")
    public String getAllAnime(Model model) {
        model.addAttribute("animes", animeService.getAnimes());
        return "animes";
    }

    @GetMapping("/anime/{id}")
    public String getAnime(Model model, @PathVariable Long id) {
        model.addAttribute("anime", animeService.getAnimeById(id));
        model.addAttribute("comments", commentService.getCommentsForAnime(id));
        return "anime";
    }

    @PostMapping("/new_comment")
    public String saveNewComment(@RequestParam String username, @RequestParam String message, @RequestParam Long animeId){
        User user = userService.getUser(username);
        Comment comment = new Comment(user, animeId, message);
        commentService.saveComment(comment);
        return "redirect:/anime/" + animeId.toString();
    }

    @RequestMapping(path = {"/search"})
    public String search(Model model, String keyword) {
        if (keyword != null) {
            List<Anime> list = animeService.getAnimeByUrl(keyword);
            model.addAttribute("animes", list);
        }
        return "animes";
    }

    @GetMapping("/parse")
    public void parse() {
        malParserService.sendRequest();
    }

}
