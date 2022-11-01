package com.example.animelist.controllers;

import com.example.animelist.models.Anime;
import com.example.animelist.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService){
        this.animeService = animeService;
    }

    @GetMapping("/animes")
    @ResponseBody
    public List<Anime> getAllAnime(){
        return animeService.getAnimes();
    }

    @GetMapping("/anime/{id}")
    @ResponseBody
    public Anime getAllAnime(@PathVariable Long id){
        return animeService.getAnimeById(id);
    }
}
