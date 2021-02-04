package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.franchiesRepository.FranchiseRepository;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

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


    @PutMapping("/franchise/{franchiseId}/character/{charid}")
    public HttpStatus addCharacterToFranchise(@PathVariable("franchiseId") long franchiseId, @PathVariable("charid") long charid) throws NoItemFoundException{
        ActorCharacter actorCharacter = this.characterRepository.findById(charid).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));

        //Ferdig objekt ikkje populert
//        Franchise franchise = this.franchiseRepo.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("Something is terribly wrong"));
//        franchise.getCharacters().add(actorCharacter);
//        actorCharacter.setFranchise(franchise);
//        characterRepository.save(actorCharacter);

        return HttpStatus.ACCEPTED;
    }


}






