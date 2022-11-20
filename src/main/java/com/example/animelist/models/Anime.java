package com.example.animelist.models;

import com.example.animelist.DateUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Anime {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private String status;
    private Date startDate;
    private Date endDate;
    private String imageUrl;

    public Anime(){
    }

    public Anime(String name, String url, String status, Date startDate, Date endDate, String imageUrl) {
        this.name = name;
        this.url = url;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
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
}
