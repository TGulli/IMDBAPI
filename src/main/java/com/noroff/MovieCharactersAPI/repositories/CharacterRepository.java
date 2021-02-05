package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CharacterRepository extends JpaRepository<ActorCharacter, Long> {
    @Query("SELECT m.characters FROM Movie m WHERE m.movie_id = :id")
    Set<ActorCharacter> findActorCharacterById(@Param("id") long id);
}
