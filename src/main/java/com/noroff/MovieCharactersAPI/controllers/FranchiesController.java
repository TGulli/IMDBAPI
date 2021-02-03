package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.franchiesRepository.FranchiesRepository;
import com.noroff.MovieCharactersAPI.models.Franchie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/franchies")
public class FranchiesController {

    @Autowired
    private FranchiesRepository franchiseRepo;

    @GetMapping
    public List<Franchie> getAll(){
            return franchiseRepo.findAll();
    }

    @PostMapping
    public void setFranchise(@RequestBody Franchie franchie){
       franchiseRepo.save(franchie);
    }
}






