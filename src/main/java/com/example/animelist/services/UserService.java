package com.example.animelist.services;

import com.example.animelist.models.Anime;
import com.example.animelist.models.Role;
import com.example.animelist.models.User;
import com.example.animelist.models.MyUserPrincipal;
import com.example.animelist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        //        mapRolesToAuthorities(user.getRoles()));
        return new MyUserPrincipal(user);
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) throws Exception{
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null)
        {
            throw new Exception("user exists");
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepository.save(user);
    }

    public void addAnimeToUser(User user, Anime anime){
        if (user.getAnimeList().contains(anime))
            return;
        user.addAnime(anime);
        userRepository.save(user);
    }
}
