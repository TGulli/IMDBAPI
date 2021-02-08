package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/character")
public class CharacterController {
    /*
    The CharacterController class is responsible for making endpoints for characters,
    handling user interactions and deciding what to do with it.
     */

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public List<ActorCharacter> getAllCharacters(){
        /*
        A method to returns all the characters in the database
        as ActorCharacter objects.
         */
        return this.characterRepository.findAll();
    }

    @PostMapping
    public ActorCharacter addCharacter(@RequestBody ActorCharacter character){
        /*
        A method to create a new ActorCharacter object and add it to the database.
         */
        return this.characterRepository.save(character);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorCharacter> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        /*
        A method to return a specific character by its id.
         */
        ActorCharacter actorCharacter = characterRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No character by id " + id));
        return ResponseEntity.ok().body(actorCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorCharacter> deleteById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        /*
        A method to delete a specific character and all its relationships in the database.
         */
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
        /*
        A method to update a specific character and all its relationships in the database.
         */
        ActorCharacter oldCharacter = characterRepository.findById(charid).orElseThrow(()-> new NoItemFoundException("No character by id " + character.getId()));

        oldCharacter.setMovies(character.getMovies());
        oldCharacter.setName(character.getName());
        oldCharacter.setAlias(character.getAlias());
        oldCharacter.setGender(character.getGender());
        oldCharacter.setPicture(character.getPicture());

        characterRepository.save(oldCharacter);
        return ResponseEntity.ok().body(oldCharacter);
    }
}
