package com.example.animelist.repositories;

import com.example.animelist.models.Anime;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends CrudRepository<Anime, Long> {
    List<Anime> findByName(String name);

    List<Anime> findAll(Specification<Anime> spec);

    boolean existsAnimeByName(String name);
}
