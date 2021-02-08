package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
An interface for movies that extends JpaRepository which contains contains API for basic CRUD operations.
*/
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByFranchise(Franchise franchise);
}
