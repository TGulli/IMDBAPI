package com.noroff.MovieCharactersAPI.service;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.FranchiseRepository;
import com.noroff.MovieCharactersAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepo;

    @Autowired
    private MovieRepository movieRepository;

    //get all
    public List<Franchise> getAllFranchises(){
        return franchiseRepo.findAll();
    }

    //get by id
    public ResponseEntity<Franchise> getFranchiseById(Long id) throws NoItemFoundException {
        Franchise franchise = franchiseRepo.findById(id).orElseThrow(() -> new NoItemFoundException("No movie by id " + id));
        return ResponseEntity.ok().body(franchise);
    }

    //create new franchise
    public ResponseEntity<Franchise> createNewFranchise(Franchise franchise){
        return ResponseEntity.ok().body(franchiseRepo.save(franchise));
    }

    //update
    public ResponseEntity<Franchise> updateFranchise(Long franchiseId, Franchise franchise) throws NoItemFoundException {
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

        Franchise returnFranchise = franchiseRepo.save(f);
        return ResponseEntity.ok().body(returnFranchise);
    }

    //deletes franchise
    public ResponseEntity<Franchise> deleteFranchise(Long franchiseId) throws NoItemFoundException {
        Franchise franchise = franchiseRepo.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        List<Movie> movies = franchise.getMovies();
        for (Movie movie : movies){
            movie.setFranchise(null);
            movieRepository.save(movie);
        }
        franchiseRepo.deleteById(franchiseId);

        //check if this exists in database

        return ResponseEntity.ok().body(franchise);
    }


}
