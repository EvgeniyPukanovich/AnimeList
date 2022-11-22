package com.example.animelist.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User user;
    private long animeId;
    private String message;

    public Comment(){

    }

    public Comment(User user, long animeId, String message) {
        this.user = user;
        this.animeId = animeId;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(long animeId) {
        this.animeId = animeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
