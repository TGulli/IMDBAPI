package com.noroff.MovieCharactersAPI.franchiesRepository;

import com.noroff.MovieCharactersAPI.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise,Long> {


}
