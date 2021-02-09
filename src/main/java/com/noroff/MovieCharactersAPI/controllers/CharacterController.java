package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
The CharacterController class is responsible for making endpoints for characters,
handling user interactions and deciding what to do with it.
*/

@RestController
@RequestMapping("api/v1/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /* A method to returns all the characters in the database as ActorCharacter objects. */
    @GetMapping("/getAll")
    public List<ActorCharacter> getAllCharacters(){
        return characterService.getAllCharacters();
    }

    /* A method to create a new ActorCharacter object and add it to the database. */
    @PostMapping("/add")
    public ResponseEntity<ActorCharacter> addCharacter(@RequestBody ActorCharacter character){
        return characterService.createNewCharacter(character);
    }

    /* A method to return a specific character by its id.  */
    @GetMapping("/get/{id}")
    public ResponseEntity<ActorCharacter> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        return characterService.getCharacterById(id);
    }

    /* A method to delete a specific character and all its relationships in the database. */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ActorCharacter> deleteById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        return characterService.deleteCharacter(id);
    }

    /* A method to update a specific character in the database. */
    @PutMapping("/update/{id}")
    public ResponseEntity<ActorCharacter> update(@RequestBody ActorCharacter character, @PathVariable("id") long id) throws NoItemFoundException {
        return characterService.updateCharacter(character, id);
    }

    /* A method that returns all characters in a movie */
    @GetMapping("/all-from-movie/{movieid}")
    public ResponseEntity<List<ActorCharacter>> movieCharacters(@PathVariable("movieid") long movieid) throws NoItemFoundException {
        return characterService.extractCharactersFromMovie(movieid);
    }

    /* A method that returns all franchises in a franchise */
    @GetMapping("/all-from-franchise/{franchiseid}")
    public ResponseEntity<List<ActorCharacter>> franchiseCharacters(@PathVariable("franchiseid") long franchiseid) throws NoItemFoundException{
        return characterService.extractCharactersFromFranchise(franchiseid);
    }
}