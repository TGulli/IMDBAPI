package com.noroff.MovieCharactersAPI.models;

import javax.persistence.*;
import java.util.List;

/*
A class for storing franchise information in Franchise objects.
The class is represented as a table with its variable names as columns in the database.
*/

@Entity
@Table(name = "Franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long franchise_id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL)
    private List<Movie> movies;

    public Franchise() {

    }

    public Franchise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getFranchise_id() {
        return franchise_id;
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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
