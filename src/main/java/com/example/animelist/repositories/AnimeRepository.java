package com.example.animelist.repositories;

import com.example.animelist.models.Anime;
import org.springframework.data.repository.CrudRepository;

public interface AnimeRepository extends CrudRepository<Anime, Long> {

}
