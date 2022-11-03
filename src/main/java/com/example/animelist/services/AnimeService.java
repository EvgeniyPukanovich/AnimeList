package com.example.animelist.services;

import com.example.animelist.models.Anime;
import com.example.animelist.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository){
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAnimes(){
        List<Anime> result = new ArrayList<>();
        animeRepository.findAll().forEach(result::add);
        return result;
    }

    public Anime getAnimeById(Long id){
        return animeRepository.findById(id).get();
    }

    public List<Anime> getAnimeByUrl(String url) {return animeRepository.findByUrl(url).get();}

    public void saveAnime(String name, String url, String status, Date airedOn, Date releasedOn, String imageUrl){
        Anime anime = new Anime(name, url, status, airedOn, releasedOn, imageUrl);
        animeRepository.save(anime);
    }
}
