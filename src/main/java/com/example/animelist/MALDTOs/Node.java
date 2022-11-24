package com.example.animelist.MALDTOs;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public class Node {
    public Integer id;

    public String title;
    public MainPicture main_picture;
    public String status;
    public Date start_date;
    public Date end_date;
    public List<Genre> genres;
    public Integer num_episodes;
}
