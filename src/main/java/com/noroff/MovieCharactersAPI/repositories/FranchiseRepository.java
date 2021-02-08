package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
An interface for franchise that extends JpaRepository which contains contains API for basic CRUD operations.
*/

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise,Long> {
}
