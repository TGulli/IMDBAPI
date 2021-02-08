package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.FranchiseRepository;
import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.repositories.MovieRepository;
import com.noroff.MovieCharactersAPI.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
The FranchiseController class is responsible for making endpoints for franchises,
handling user interactions for franchise.
 */
@RestController
@RequestMapping(value = "api/v1/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepo;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FranchiseService franchiseService;

    /* Returns all the franchises in the database */
    @GetMapping
    public List<Franchise> getAll(){
        return franchiseService.getAllFranchises();
    }

    /*Returns a given Franchise based on the ID from PathVariable. If not found, the function throws a NoItemFoundException. */
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        return franchiseService.getFranchiseById(id);
    }

    /* Store a Franchise given as RequestBody */
    @PostMapping
    public ResponseEntity<Franchise> postFranchise(@RequestBody Franchise franchise){
       return franchiseService.createNewFranchise(franchise);
    }

    /**
     * PROVING 1toMANY
     */

    /* Binds a character to a movie, with franchiseId and movieIs as path variables.
     *  If the given movie id or franchise id is not found, the function throws a NoItemFoundException. */
    @PutMapping("/{franchiseId}/movie/{movieid}")
    public ResponseEntity<Movie> addCharacterToFranchise(@PathVariable("franchiseId") long franchiseId, @PathVariable("movieid") long movieid) throws NoItemFoundException{

        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        Franchise franchise = this.franchiseRepo.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));

        franchise.getMovies().add(movie);
        movie.setFranchise(franchise);

        movieRepository.save(movie);

        return ResponseEntity.ok().body(movie);
    }

    /* Updating a franchise with a given id as PathVariable to the given franchise from RequestBody.
     *  If the franchise with the given id is not found, the function throws a noItemFoundException*/
    @PutMapping("/update/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable("id") long franchiseId, @RequestBody Franchise franchise) throws NoItemFoundException{
        return franchiseService.updateFranchise(franchiseId, franchise);
    }


    /* Delete a franchise at a given id from pathvariabel.
     *  If not found, the function throws a NoItemFoundException. */
    @DeleteMapping("/delete/{franchiseid}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable("franchiseid") long franchiseid) throws NoItemFoundException{
        return franchiseService.deleteFranchise(franchiseid);
    }

}






