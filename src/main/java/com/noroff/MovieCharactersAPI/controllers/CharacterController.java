package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import com.noroff.MovieCharactersAPI.service.CharacterService;
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
    private CharacterService characterService;

    @GetMapping("/getAll")
    public List<ActorCharacter> getAllCharacters(){
        /*
        A method to returns all the characters in the database
        as ActorCharacter objects.
         */
        return characterService.getAllCharacters();
    }

    @PostMapping("/add")
    public ActorCharacter addCharacter(@RequestBody ActorCharacter character){
        /*
        A method to create a new ActorCharacter object and add it to the database.
         */
        return characterService.createNewCharacter(character);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ActorCharacter> getById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        /*
        A method to return a specific character by its id.
         */
        return characterService.getCharacterById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ActorCharacter> deleteById(@PathVariable(value = "id") long id) throws NoItemFoundException {
        /*
        A method to delete a specific character and all its relationships in the database.
         */

        return characterService.deleteCharacter(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ActorCharacter> update(@RequestBody ActorCharacter character, @PathVariable("id") long id) throws NoItemFoundException {
        /*
        A method to update a specific character in the database.
         */
        return characterService.updateCharacter(character, id);
    }

    @GetMapping("/all-from-movie/{movieid}")
    public ResponseEntity<List<ActorCharacter>> movieCharacters(@PathVariable("movieid") long movieid) throws NoItemFoundException {
        return characterService.extractCharactersFromMovie(movieid);
    }

    @GetMapping("/all-from-franchise/{franchiseid}")
    public ResponseEntity<List<ActorCharacter>> franchiseCharacters(@PathVariable("franchiseid") long franchiseid) throws NoItemFoundException{
        return characterService.extractCharactersFromFranchise(franchiseid);
    }
}
