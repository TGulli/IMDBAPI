package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise,Long> {

}
