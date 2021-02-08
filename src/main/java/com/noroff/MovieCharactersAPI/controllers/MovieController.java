package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {
    /*
    The MovieController class is responsible for making endpoints for movies,
    handling user interactions and deciding what to do with it.
     */

    @Autowired
    MovieService movieService;


    @GetMapping("/getAll")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.createNewMovie(movie);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Movie> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        /*
        A method to return a specific movie by its id.
         */

    @GetMapping("/get/{id}")
    public ResponseEntity<Movie> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok().body(movie);
    }

      /*
        A method to update a specific movie and all its relationships in the database.
         */


    @PutMapping("/update/{movie_id}")
    public ResponseEntity<Movie> update(@RequestBody Movie movie, @PathVariable("movie_id") long movie_id) throws NoItemFoundException {
        return movieService.updateMovie(movie, movie_id);
    }



    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        return movieService.deleteMovie(id);
    }


    @GetMapping("/franchise/{franchiseid}")
    public ResponseEntity<List<Movie>> getMovieByFranchise(@PathVariable(value = "franchiseid") long franchiseid) throws NoItemFoundException {
        return movieService.getAllMoviesFromFranchise(franchiseid);
    }




}