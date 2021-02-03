package com.noroff.MovieCharactersAPI.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Franchie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long franchie_id;

    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name = "franchie_id")
    private Set<Movie> movies; // TODO: Replace String with movie class

    public Franchie() {

    }

    public Franchie(String name, String description) {
        this.name = name;
        this.description = description;
        this.movies = movies;
    }
    public Franchie(long franchie_id, String name, String description) {
        this.franchie_id=franchie_id;
        this.name = name;
        this.description = description;
    }

    public long getFranchie_id() {
        return franchie_id;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
