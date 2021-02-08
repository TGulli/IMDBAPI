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

import java.util.ArrayList;
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

    @Autowired
    FranchiseRepository franchiseRepository;

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


    @PutMapping("/update/{movieid}")
    public HttpStatus update(@RequestBody Movie movie, @PathVariable("movieid") long movieid) throws NoItemFoundException {
        Movie oldMovie = movieRepository.findById(movieid).orElseThrow(()-> new NoItemFoundException("No movie by id " + movieid));


        oldMovie.setTitle(movie.getTitle());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setYear(movie.getYear());
        oldMovie.setDirector(movie.getDirector());
        oldMovie.setPicture(movie.getPicture());
        oldMovie.setTrailer(movie.getTrailer());


        oldMovie.setCharacters(movie.getCharacters());

        movieRepository.save(oldMovie);
        return HttpStatus.ACCEPTED;
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No movie by id " + id));
        List<ActorCharacter> characters = movie.getCharacters();
        for (ActorCharacter character: characters) {
            character.getMovies().remove(character);
        }
        movieRepository.delete(movie);
        return ResponseEntity.ok().body(movie);
    }


    /**
     * SPECIAL QUERY 1:
     */

    @GetMapping("/franchise/{franchiseid}")
    public ResponseEntity<List<Movie>> getMovieByFranchise(@PathVariable(value = "franchiseid") long franchiseid) throws NoItemFoundException {
        Franchise franchise = franchiseRepository.findById(franchiseid).orElseThrow(() -> new NoItemFoundException("AHHHHHH"));
        List<Movie> movieList = movieRepository.findMovieByFranchise(franchise);
        return ResponseEntity.ok().body(movieList);
    }

    /**
     * SPECIAL QUERY 2:
     */

    @GetMapping("/all-characters/{movieid}")
    public ResponseEntity<Set<ActorCharacter>> extractCharactersFromMovie(@PathVariable("movieid") long movieid) throws NoItemFoundException {
        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("AHHHHHH"));
        Set<ActorCharacter> output = characterRepository.findActorCharacterById(movie.getMovie_id());
        return ResponseEntity.ok().body(output);

    }

    /**
     * SPECIAL QUERY 3:
     */

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





    /**
     * PROVING MANY TO MANY
     */

    @PutMapping("/playingaround/{charid}/m/{movid}")
    public HttpStatus testingOutRestFunctionality(@PathVariable("charid") long characterId, @PathVariable("movid") long movieid) throws NoItemFoundException{
        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        ActorCharacter actorCharacter = this.characterRepository.findById(characterId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));

        List<Movie> a = new ArrayList<>();
        a.add(movie);

        List<ActorCharacter> b = new ArrayList<>();
        b.add(actorCharacter);

        actorCharacter.setMovies(a);
        movie.setCharacters(b);


        characterRepository.save(actorCharacter);
        movieRepository.save(movie);

        return HttpStatus.ACCEPTED;
    }
}