package com.example.animelist.services;

import com.example.animelist.models.Anime;
import com.example.animelist.models.Genre;
import com.example.animelist.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre getOrCreate(String name){
        if(genreRepository.existsGenreByName(name))
            return genreRepository.findByName(name);
        Genre genre = new Genre(name);
        genreRepository.save(genre);
        return genre;
    }

    public List<Genre> getGenres(){
        List<Genre> result = new ArrayList<>();
        genreRepository.findAll().forEach(result::add);
        return result;
    }
}
