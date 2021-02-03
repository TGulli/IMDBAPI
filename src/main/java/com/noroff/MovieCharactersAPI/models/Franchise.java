package com.noroff.MovieCharactersAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long franchiseId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL)
    private Set<ActorCharacter> characters; // TODO: Replace String with movie class

    public Franchise() {

    }

    public Franchise(String name, String description) {
        this.name = name;
        this.description = description;
    }



    public long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(long franchiseId) {
        this.franchiseId = franchiseId;
    }

    public List<String> getCharacterNames(){
        return characters.stream()
                .map(ActorCharacter::getName)
                .collect(Collectors.toList());
    }
    
    @JsonIgnore
    public void setCharacters(Set<ActorCharacter> characters) {
        this.characters = characters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
