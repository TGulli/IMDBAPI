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


    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /*
     A method to create a new Movie object and add it to the database.
     */

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.createNewMovie(movie);
    }


     /*
        A method to return a specific movie by its id.
         */

    @GetMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        return movieService.deleteMovie(id);
    }


    /**
     * SPECIAL QUERY 1:
     *  A method that returns all the movies for a specific franchise.
     */

    @GetMapping("/franchise/{franchiseid}")
    public ResponseEntity<List<Movie>> getMovieByFranchise(@PathVariable(value = "franchiseid") long franchiseid) throws NoItemFoundException {
        return movieService.getAllMoviesFromFranchise(franchiseid);
    }



    /**
     * SPECIAL QUERY 2:
     * A method that returns all the characters in a specific movie
     */
    /*
    @GetMapping("/all-characters/{movieid}")
    public ResponseEntity<Set<ActorCharacter>> extractCharactersFromMovie(@PathVariable("movieid") long movieid) throws NoItemFoundException {

        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("AHHHHHH"));
        Set<ActorCharacter> output = characterRepository.findActorCharacterById(movie.getMovie_id());
        return ResponseEntity.ok().body(output);

    }
    */
    /**
     * SPECIAL QUERY 3:
     * A method that returns all characters in a given franchise
     */
    /*
    @GetMapping("/get-characters-franchise/{id}")
    public ResponseEntity<Set<ActorCharacter>> extractCharactersFromFranchise(@PathVariable("id") long franchiseId) throws NoItemFoundException {

        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("NOOOOO"));
        Set<ActorCharacter> b = new HashSet<>();

        for (Movie m : movieRepository.findMovieByFranchise(franchise)){
            for (ActorCharacter a : characterRepository.findActorCharacterById(m.getMovie_id())) {
                b.add(a);
            }
        }

        return ResponseEntity.ok().body(b);

    }
    */

}