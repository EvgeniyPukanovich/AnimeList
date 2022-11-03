package com.example.animelist.controllers;

import com.example.animelist.models.Anime;
import com.example.animelist.services.AnimeService;
import com.example.animelist.services.MALParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AnimeController {

    private final AnimeService animeService;
    private final MALParserService malParserService;

    @Autowired
    public AnimeController(AnimeService animeService, MALParserService malParserService){
        this.animeService = animeService;
        this.malParserService = malParserService;
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

    @GetMapping("/anime")
    @ResponseBody
    public List<Anime> getAllAnime(@RequestParam(value="name") String url){
        return animeService.getAnimeByUrl(url);
    }

    @GetMapping("/parse")
    public void parse(){malParserService.sendRequest();}

}
