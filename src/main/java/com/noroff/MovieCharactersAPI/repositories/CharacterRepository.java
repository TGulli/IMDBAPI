package com.noroff.MovieCharactersAPI.repositories;

import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<ActorCharacter, Long> {
}
