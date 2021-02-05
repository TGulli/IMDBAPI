package com.noroff.MovieCharactersAPI.controllers;


import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import com.noroff.MovieCharactersAPI.repositories.FranchiseRepository;
import com.noroff.MovieCharactersAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CharacterRepository characterRepository;

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


    @GetMapping("/franchise/{franchiseid}")
    public ResponseEntity<Set<Movie>> getMovieByFranchise(@PathVariable(value = "franchiseid") long franchiseid) throws NoItemFoundException {
        Set<Movie> movies = movieRepository.findMovieByFranchise(franchiseid);
        return ResponseEntity.ok().body(movies);
    }

    /**
     * PROVING MANY TO MANY
     */

    @PutMapping("/playingaround/{charid}/m/{movid}")
    public HttpStatus testingOutRestFunctionality(@PathVariable("charid") long characterId, @PathVariable("movid") long movieid) throws NoItemFoundException{
        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        ActorCharacter actorCharacter = this.characterRepository.findById(characterId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));

        Set<Movie> a = new HashSet<>();
        a.add(movie);

        Set<ActorCharacter> b = new HashSet<>();
        b.add(actorCharacter);

        actorCharacter.setMovies(a);
        movie.setCharacters(b);


        characterRepository.save(actorCharacter);
        movieRepository.save(movie);

        return HttpStatus.ACCEPTED;
    }
}