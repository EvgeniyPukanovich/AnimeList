package com.example.animelist.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Anime {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String russian;
    private String url;
    private String status;
    private Date airedOn;
    private Date releasedOn;
    private String imageUrl;

    public Anime(){

    }

    public Anime(String name, String russian, String url, String status, Date airedOn, Date releasedOn, String imageUrl) {
        this.name = name;
        this.russian = russian;
        this.url = url;
        this.status = status;
        this.airedOn = airedOn;
        this.releasedOn = releasedOn;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRussian() {
        return russian;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }

    public Date getAiredOn() {
        return airedOn;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
