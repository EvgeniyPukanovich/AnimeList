package com.example.animelist.models;

import com.example.animelist.DateUtils;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Anime {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String status;
    private Date startDate;
    private Date endDate;
    private String imageUrl;
    private Integer numberOfEpisodes;

    @ManyToMany
    private List<Genre> genres;

    public Anime(){
    }

    public Anime(String name,  String status, Date startDate, Date endDate, String imageUrl,
                 Integer numberOfEpisodes, List<Genre> genres) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.numberOfEpisodes = numberOfEpisodes;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return DateUtils.getShortDate(startDate);
    }

    public String getEndDate() {
        return DateUtils.getShortDate(endDate);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getNumberOfEpisodes() {return numberOfEpisodes;}

    public List<String> getGenres() {return  genres.stream().map(Genre::getName).toList();}
}
