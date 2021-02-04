package com.noroff.MovieCharactersAPI.controllers;


import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return this.movieRepository.save(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new NoItemFoundException("No movie by id " + id));
        return ResponseEntity.ok().body(movie);
    }

    /*
    @GetMapping("/franchise/{franchiseid}")
    public ResponseEntity<Set<Movie>> getMovieByFranchise(@PathVariable(value = "franchiseid") long franchiseid) throws NoItemFoundException {
        Set<Movie> movies = movieRepository.findAllById(movieRepository.findAll()).orElseThrow(() -> new NoItemFoundException("No franchise by id " + franchiseid));
    }*/
}