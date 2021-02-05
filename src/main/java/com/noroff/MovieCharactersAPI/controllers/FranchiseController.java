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
import java.util.Set;

@RestController
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


    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        Franchise franchise = franchiseRepo.findById(id).orElseThrow(() -> new NoItemFoundException("No movie by id " + id));
        return ResponseEntity.ok().body(franchise);
    }


    @PostMapping
    public void setFranchise(@RequestBody Franchise franchise){
       franchiseRepo.save(franchise);
    }

    /**
     * PROVING 1toMANY
     */

    @PutMapping("/{franchiseId}/movie/{movieid}")
    public ResponseEntity<Movie> addCharacterToFranchise(@PathVariable("franchiseId") long franchiseId, @PathVariable("movieid") long movieid) throws NoItemFoundException{

        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        Franchise franchise = this.franchiseRepo.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));

        franchise.getMovies().add(movie);
        movie.setFranchise(franchise);

        movieRepository.save(movie);

        return ResponseEntity.ok().body(movie);
    }

    @DeleteMapping("/delete/{franchiseid}")
    public void deleteFranchise(@PathVariable("franchiseid") long franchiseid) throws NoItemFoundException{
        Franchise franchise = franchiseRepo.findById(franchiseid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        Set<Movie> movies = franchise.getMovies();
        for (Movie movie : movies){
            movie.setFranchise(null);
            movieRepository.save(movie);
        }
        franchiseRepo.deleteById(franchiseid);
    }


    /*@DeleteMapping("/delete/{franchiseid}")
    public void deleteFranchise(@PathVariable("franchiseid") long franchiseid) throws NoItemFoundException{
        Franchise franchise = franchiseRepo.findById(franchiseid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        List<Movie> movies = movieRepository.findMovieByFranchise(franchise);
        for (Movie movie : movies){
            movie.setFranchise(null);
            movieRepository.save(movie);
        }
        franchiseRepo.deleteById(franchiseid);
    }*/


}






