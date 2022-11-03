package com.example.animelist.repositories;

import com.example.animelist.models.Anime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnimeRepository extends CrudRepository<Anime, Long> {
    Optional<List<Anime>> findByUrl (String url);
}
