package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    /*
    An interface for movies that extends JpaRepository which contains contains API for basic CRUD operations.
     */
    List<Movie> findMovieByFranchise(Franchise franchise);
}
