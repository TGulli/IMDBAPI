package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Set<Movie> findMovieByFranchise(long franchiseId);

}
