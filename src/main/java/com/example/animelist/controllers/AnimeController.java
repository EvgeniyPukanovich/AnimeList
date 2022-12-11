package com.example.animelist.controllers;

import com.example.animelist.models.Anime;
import com.example.animelist.models.Comment;
import com.example.animelist.models.User;
import com.example.animelist.services.AnimeService;
import com.example.animelist.services.CommentService;
import com.example.animelist.services.MALParserService;
import com.example.animelist.services.UserService;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.LessThan;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class AnimeController {

    private final AnimeService animeService;
    private final MALParserService malParserService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public AnimeController(AnimeService animeService, MALParserService malParserService,
                           CommentService commentService, UserService userService) {
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
    public String saveNewComment(@RequestParam String username, @RequestParam String message, @RequestParam Long animeId) {
        User user = userService.getUser(username);
        Comment comment = new Comment(user, animeId, message);
        commentService.saveComment(comment);
        return "redirect:/anime/" + animeId.toString();
    }

    @RequestMapping(path = {"/search"})
    public String search(Model model, String keyword) {
        if (keyword != null) {
            List<Anime> list = animeService.getAnimeByName(keyword);
            model.addAttribute("animes", list);
        }
        return "animes";
    }

    @Secured("ADMIN")
    @GetMapping("/parse")
    public String parse(Model model) {
        return "parse";
    }

    @Secured("ADMIN")
    @PostMapping("/parse")
    public String parse(Model model, @RequestParam Integer count) {
        model.addAttribute("response", malParserService.sendRequest(count));
        return "parse";
    }

    @PostMapping("/animes")
    public String filter(
            @And({
                    @Spec(path = "startDate", spec = GreaterThan.class),
                    @Spec(path = "endDate", spec = LessThan.class),
                    @Spec(path = "numberOfEpisodes",
                            params = {"episodesFrom", "episodesTo"}, spec = Between.class)
            }) Specification<Anime> animeSpec,
            Model model) {
        var animes = animeService.findAnimesBySpec(animeSpec);
        model.addAttribute("animes", animes);
        return "animes";
    }

    @Secured("ADMIN")
    @PostMapping("/addAnime")
    public String addAnime(Model model,
                           @RequestParam String name,
                           @RequestParam String status,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                           @RequestParam String imageUrl,
                           @RequestParam Integer numberOfEpisodes,
                           @RequestParam String genres) {

        animeService.saveAnime(name, status, startDate, endDate, imageUrl, numberOfEpisodes,
                new HashSet<>(Arrays.stream(genres.split(",")).toList()));
        return "parse";
    }
}
