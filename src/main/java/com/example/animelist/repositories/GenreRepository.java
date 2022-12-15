package com.example.animelist.repositories;

import com.example.animelist.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByName(String name);

    boolean existsGenreByName(String name);
}
