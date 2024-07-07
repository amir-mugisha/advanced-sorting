package com.atlab.advanced.sorting.Movie;

import com.atlab.advanced.sorting.Actor.Actor;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actor_sequence"
    )
    private Integer id;
    private String name;
    private Integer year;

    @ManyToMany(mappedBy = "movies",fetch = FetchType.EAGER)
    private Set<Actor> actors = new HashSet<>();

    public Movie(){}

    public Movie(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public Movie(Integer id, String name, Integer year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
