package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CharacterRepository extends JpaRepository<ActorCharacter, Long> {
    //List<ActorCharacter> findActorCharactersByMovies(Movie movie);
}
