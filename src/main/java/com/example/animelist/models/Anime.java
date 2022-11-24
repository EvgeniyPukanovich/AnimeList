package com.example.animelist.models;

import com.example.animelist.DateUtils;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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

    @ElementCollection
    @CollectionTable(name = "anime_genres", joinColumns = @JoinColumn(name = "anime_id"))
    private Set<String> genres = new HashSet<>();

    public Anime(){
    }

    public Anime(String name,  String status, Date startDate, Date endDate, String imageUrl,
                 Integer numberOfEpisodes, Set<String> genres) {
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

    public Set<String> getGenres() {return  genres;}
}
