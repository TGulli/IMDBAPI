package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import com.noroff.MovieCharactersAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/character")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<ActorCharacter> getAllCharacters(){
        return this.characterRepository.findAll();
    }

    @PostMapping
    public ActorCharacter addCharacter(@RequestBody ActorCharacter character){
        return this.characterRepository.save(character);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorCharacter> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        ActorCharacter actorCharacter = characterRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No character by id " + id));
        return ResponseEntity.ok().body(actorCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorCharacter> deleteById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        ActorCharacter actorCharacter = characterRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No character by id " + id));
        List<Movie> movies = actorCharacter.getMovies();
        for (Movie m: movies) {
            m.getCharacters().remove(actorCharacter);
        }
        characterRepository.delete(actorCharacter);
        return ResponseEntity.ok().body(actorCharacter);
    }

    @PutMapping("/{charid}")
    public ResponseEntity<ActorCharacter> update(@RequestBody ActorCharacter character, @PathVariable("charid") long charid) throws NoItemFoundException {
        ActorCharacter oldCharacter = characterRepository.findById(charid).orElseThrow(()-> new NoItemFoundException("No character by id " + character.getId()));

        //TODO: GRIS DETTA TE

        oldCharacter.setMovies(character.getMovies());
        oldCharacter.setName(character.getName());
        oldCharacter.setAlias(character.getAlias());
        oldCharacter.setGender(character.getGender());
        oldCharacter.setPicture(character.getPicture());

        characterRepository.save(oldCharacter);
        return ResponseEntity.ok().body(oldCharacter);
    }
}
