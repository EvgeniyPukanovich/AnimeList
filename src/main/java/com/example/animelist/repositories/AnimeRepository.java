package com.example.animelist.repositories;

import com.example.animelist.models.Anime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRepository extends CrudRepository<Anime, Long> {
    List<Anime> findByName(String name);

    boolean existsAnimeByName(String name);
}
