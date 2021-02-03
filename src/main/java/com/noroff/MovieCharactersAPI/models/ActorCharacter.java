package com.noroff.MovieCharactersAPI.models;

import javax.persistence.*;

@Entity
@Table(name = "Character")
public class ActorCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FullName")
    private String name;

    @Column(name = "Alias")
    private String alias;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Picture")
    private String picture;

    /*@ManyToMany(mappedBy = characters)
    private Set<Movie> movies;*/

    public ActorCharacter() {}

    public ActorCharacter(String name, String alias, String gender, String picture) {
        this.name = name;
        this.alias = alias;
        this.gender = gender;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
