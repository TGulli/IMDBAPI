package com.noroff.MovieCharactersAPI.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long franchise_id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

    public Franchise() {

    }

    public Franchise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getFranchise_id() {
        return franchise_id;
    }

    public void setFranchise_id(long franchiseId) {
        this.franchise_id = franchiseId;
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
