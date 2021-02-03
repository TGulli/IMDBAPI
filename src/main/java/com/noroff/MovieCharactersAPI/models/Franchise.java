package com.noroff.MovieCharactersAPI.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long franchiseId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "franchise")
    private List<ActorCharacter> characters; // TODO: Replace String with movie class

    public Franchise() {

    }

    public Franchise(String name, String description) {
        this.name = name;
        this.description = description;
        this.characters = characters;
    }
    public Franchise(long franchiseId, String name, String description) {
        this.franchiseId = franchiseId;
        this.name = name;
        this.description = description;
    }

    public long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(long franchiseId) {
        this.franchiseId = franchiseId;
    }

    public List<ActorCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<ActorCharacter> characters) {
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
