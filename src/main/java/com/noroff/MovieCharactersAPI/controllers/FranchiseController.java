package com.noroff.MovieCharactersAPI.controllers;

import com.noroff.MovieCharactersAPI.franchiesRepository.FranchiseRepository;
import com.noroff.MovieCharactersAPI.models.Franchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/franchies")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepo;

    @GetMapping
    public List<Franchise> getAll(){
            return franchiseRepo.findAll();
    }

    @PostMapping
    public void setFranchise(@RequestBody Franchise franchise){
       franchiseRepo.save(franchise);
    }
}






