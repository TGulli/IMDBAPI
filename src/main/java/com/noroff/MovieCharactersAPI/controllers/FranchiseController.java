package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.FranchiseRepository;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import com.noroff.MovieCharactersAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/v1/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepo;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Franchise> getAll(){
        return franchiseRepo.findAll();
    }

    @PostMapping
    public void setFranchise(@RequestBody Franchise franchise){
       franchiseRepo.save(franchise);
    }

    /**
     * PROVING 1toMANY
     */

    @PutMapping("/franchise/{franchiseId}/movie/{movieid}")
    public ResponseEntity<Movie> addCharacterToFranchise(@PathVariable("franchiseId") long franchiseId, @PathVariable("movieid") long movieid) throws NoItemFoundException{

        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        Franchise franchise = this.franchiseRepo.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));


        franchise.getMovies().add(movie);
        movie.setFranchise(franchise);

        movieRepository.save(movie);



        //Ferdig objekt ikkje populert
//
//        franchise.getCharacters().add(actorCharacter);
//        actorCharacter.setFranchise(franchise);
//        characterRepository.save(actorCharacter);

        return ResponseEntity.ok().body(movie);
    }


}






