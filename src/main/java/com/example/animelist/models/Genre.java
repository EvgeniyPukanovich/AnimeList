package com.example.animelist.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    private List<Anime> anime;

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
