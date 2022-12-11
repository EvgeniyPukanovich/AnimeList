package com.example.animelist.services;

import com.example.animelist.models.Anime;
import com.example.animelist.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        return animeRepository.findById(id).orElse(null);
    }

    public List<Anime> getAnimeByName(String name) {return animeRepository.findByName(name);}

    public void saveAnime(String name, String status, Date airedOn, Date releasedOn, String imageUrl,
                          Integer numberOfEpisodes, Set<String> genres){
        Anime anime = new Anime(name, status, airedOn, releasedOn, imageUrl, numberOfEpisodes, genres);
        animeRepository.save(anime);
    }

    public void saveAnime(Anime anime){
        animeRepository.save(anime);
    }

    public List<Anime>  findAnimesBySpec(Specification<Anime> animeSpec){
        return animeRepository.findAll(animeSpec);
    }
}
