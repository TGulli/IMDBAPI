package com.noroff.MovieCharactersAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movie_id;

    @Column(name = "title")
    private String title;


    @Column(name = "genre")
    private String genre; //comma separated list

    @Column(name = "year")
    private String year;

    @Column(name = "director")
    private String director; //potential director object

    @Column(name = "picture")
    private String picture;

    @Column(name = "trailer")
    private String trailer;

    @ManyToMany(mappedBy = "movies")
    private Set<ActorCharacter> characters;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    public Movie() {
    }

    public Movie(String title, String genre, String year, String director, String picture, String trailer) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.picture = picture;
        this.trailer = trailer;
    }


    public List<String> getCharacterNames(){
        return characters.stream()
                .map(ActorCharacter::getName)
                .collect(Collectors.toList());
    }


    @JsonIgnore
    public Set<ActorCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<ActorCharacter> characters) {
        this.characters = characters;
    }

    @JsonIgnore
    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(long movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
