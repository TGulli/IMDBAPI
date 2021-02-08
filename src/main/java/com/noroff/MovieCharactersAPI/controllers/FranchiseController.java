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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The FranchiseController class is responsible for making endpoints, handling user interactions for franchise.
 */
@RestController
@RequestMapping(value = "api/v1/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepo;

    @Autowired
    private MovieRepository movieRepository;

    /* Returns all the franchises in the list*/
    @GetMapping
    public List<Franchise> getAll(){
        return franchiseRepo.findAll();
    }


    /*Returns a given Franchise based on the ID from pathvariabel. If not found, the function throws an NoItemFoundException. */
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        Franchise franchise = franchiseRepo.findById(id).orElseThrow(() -> new NoItemFoundException("No movie by id " + id));
        return ResponseEntity.ok().body(franchise);
    }

    /* Store a Franchise given as requestbody */
    @PostMapping
    public void setFranchise(@RequestBody Franchise franchise){
        franchiseRepo.save(franchise);
    }

    /**
     * PROVING 1toMANY
     */

    /* Binds a character to a movie, with franchiseId and movieIs as path variabels.
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

    /* Updating a franchise with a given id as path variabel to the given franchise from requestbody.
     *  If the franchise with the given id is not found, the function throws a noItemFoundException*/
    @PutMapping("/update/{id}")
    public HttpStatus updateFranchise(@PathVariable("id") long franchiseId, @RequestBody Franchise franchise) throws NoItemFoundException{

        Franchise f = franchiseRepo.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));

        //detach
        for (Movie q : movieRepository.findMovieByFranchise(f)){
            q.setFranchise(null);
            movieRepository.save(q);
        }
        //get and update
        if(franchise.getMovies() != null){
            List<Movie> x = new ArrayList<>();
            for (Movie m : franchise.getMovies()){
                Movie n = movieRepository.findById(m.getMovie_id()).orElse(null);
                if (n != null){
                    x.add(n);
                    n.setFranchise(f);
                    movieRepository.save(n);
                }
            }
            f.setMovies(x);
        }

        f.setName(franchise.getName());
        f.setDescription(franchise.getDescription());

        franchiseRepo.save(f);

        return HttpStatus.ACCEPTED;
    }


    /* Delete a franchise at a given id from pathvariabel.
     *  If not found, the function throws a NoItemFoundException. */
    @DeleteMapping("/delete/{franchiseid}")
    public void deleteFranchise(@PathVariable("franchiseid") long franchiseid) throws NoItemFoundException{
        Franchise franchise = franchiseRepo.findById(franchiseid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        List<Movie> movies = franchise.getMovies();
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






