package com.example.animelist.controllers;

import com.example.animelist.models.Anime;
import com.example.animelist.services.AnimeService;
import com.example.animelist.services.MALParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AnimeController {

    private final AnimeService animeService;
    private final MALParserService malParserService;

    @Autowired
    public AnimeController(AnimeService animeService, MALParserService malParserService) {
        this.animeService = animeService;
        this.malParserService = malParserService;
    }

    @GetMapping("/animes")
    public String getAllAnime(Model model) {
        model.addAttribute("animes", animeService.getAnimes());
        return "animes";
    }

    @GetMapping("/anime/{id}")
    public String getAllAnime(Model model, @PathVariable Long id) {
        model.addAttribute("anime", animeService.getAnimeById(id));
        return "anime";
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
