package com.noroff.MovieCharactersAPI.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Character")
public class ActorCharacter {
    /*
    A class for storing character information in ActorCharacter objects.
    The class is represented as a table with its variable names as columns in the database.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long character_id;

    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "gender")
    private String gender;

    @Column(name = "picture")
    private String picture;

    @ManyToMany
    @JoinTable(
            name = "movie_characters",
            joinColumns = {@JoinColumn(name = "character_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    public List<Movie> movies;


    public ActorCharacter() {}

    public ActorCharacter(String name, String alias, String gender, String picture) {
        this.name = name;
        this.alias = alias;
        this.gender = gender;
        this.picture = picture;
    }


    // Getters and setters

    @JsonGetter("movies")
    public List<String> getMovieNames(){
        if(movies != null) {
            return movies.stream()
                    .map(Movie::getTitle)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public long getId() {
        return character_id;
    }

    public void setId(long character_id) {
        this.character_id = character_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
