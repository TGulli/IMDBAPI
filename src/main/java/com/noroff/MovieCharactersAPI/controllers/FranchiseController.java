package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.franchiesRepository.FranchiseRepository;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/franchies")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepo;

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public List<Franchise> getAll(){
            return franchiseRepo.findAll();
    }

    @PostMapping
    public void setFranchise(@RequestBody Franchise franchise){
       franchiseRepo.save(franchise);
    }


    @PutMapping("/franchise/{franchiseId}/add-character/{characterId}")
    public ResponseEntity<Franchise> addCharacterToFranchise(@PathVariable("franchiseId") long franchiseId, @PathVariable("characterId") long characterId) throws NoItemFoundException{
        ActorCharacter actorCharacter = this.characterRepository.findById(characterId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
        Franchise franchise = this.franchiseRepo.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));

        franchise.getCharacters().add(actorCharacter);

        return ResponseEntity.ok().body(this.franchiseRepo.save(franchise));
    }


}






