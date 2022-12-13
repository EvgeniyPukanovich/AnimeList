package com.example.animelist.models;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="my_users")
public class User{
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String information;
    private boolean active;

    @ElementCollection
    @MapKeyColumn(name = "anime_id")
    @CollectionTable(name = "user_animes", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @Column(name = "episodes_watched")
    Map<Anime, Integer> animeList = new HashMap<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public User(){

    }

    public User(String username, String password, Role role){
        this.username = username;
        this.password = password;
        roles.add(role);
    }

    public void addAnime(Anime anime){
        this.animeList.putIfAbsent(anime, 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Map<Anime, Integer> getAnimeList() {
        return animeList;
    }

    public void setAnimeList(Map<Anime, Integer> animeList) {
        this.animeList = animeList;
    }
}
