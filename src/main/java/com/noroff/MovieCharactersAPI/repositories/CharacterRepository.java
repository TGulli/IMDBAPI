package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
An interface for character that extends JpaRepository which contains contains API for basic CRUD operations.
It has an extra method to find characters by a specific id.
*/

public interface CharacterRepository extends JpaRepository<ActorCharacter, Long> {
    @Query("SELECT m.characters FROM Movie m WHERE m.movie_id = :id")
    List<ActorCharacter> findActorCharacterById(@Param("id") long id);
}
