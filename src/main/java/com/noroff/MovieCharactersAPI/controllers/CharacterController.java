package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/character")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

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
}
