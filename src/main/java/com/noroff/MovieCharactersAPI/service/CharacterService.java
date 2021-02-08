package com.noroff.MovieCharactersAPI.service;

import com.noroff.MovieCharactersAPI.exceptions.NoItemFoundException;
import com.noroff.MovieCharactersAPI.models.ActorCharacter;
import com.noroff.MovieCharactersAPI.models.Franchise;
import com.noroff.MovieCharactersAPI.models.Movie;
import com.noroff.MovieCharactersAPI.repositories.CharacterRepository;
import com.noroff.MovieCharactersAPI.repositories.FranchiseRepository;
import com.noroff.MovieCharactersAPI.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    //Get all
    public List<ActorCharacter> getAllCharacters(){
        return characterRepository.findAll();
    }

    //Get by ID
    public ResponseEntity<ActorCharacter> getCharacterById(Long id) throws NoItemFoundException {
        ActorCharacter c = characterRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No character by id " + id));
        return ResponseEntity.ok().body(c);
    }

    //Post new character
    public ResponseEntity<ActorCharacter> createNewCharacter(ActorCharacter character){
        ActorCharacter c = characterRepository.save(character);
        return ResponseEntity.ok().body(c);
    }

    public ResponseEntity<ActorCharacter> deleteCharacter(Long id) throws NoItemFoundException{
        ActorCharacter actorCharacter = characterRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No character by id " + id));
        List<Movie> movies = actorCharacter.getMovies();
        for (Movie m: movies) {
            m.getCharacters().remove(actorCharacter);
        }
        characterRepository.delete(actorCharacter);

        //check if found

        return ResponseEntity.ok().body(actorCharacter);

    }

    //Update character
    public ResponseEntity<ActorCharacter> updateCharacter(ActorCharacter character, Long id) throws NoItemFoundException{
        //ActorCharacter oldCharacter = characterRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No character by id " + id));

        ActorCharacter updatedCharacter = character;
        updatedCharacter.setId(id);
        characterRepository.save(updatedCharacter);
        return ResponseEntity.ok().body(updatedCharacter);
    }

    //Special query 2: Extract characters from movie
    public ResponseEntity<List<ActorCharacter>> extractCharactersFromMovie(long movieid) throws NoItemFoundException {
        Movie movie = movieRepository.findById(movieid).orElseThrow(() -> new NoItemFoundException("AHHHHHH"));
        List<ActorCharacter> output = characterRepository.findActorCharacterById(movie.getMovie_id());
        return ResponseEntity.ok().body(output);
    }

    //special query 3: Extract characters from franchise
    public ResponseEntity<List<ActorCharacter>> extractCharactersFromFranchise(long franchiseId) throws NoItemFoundException {

        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new NoItemFoundException("NOOOOO"));
        List<ActorCharacter> b = new ArrayList<>();

        for (Movie m : movieRepository.findMovieByFranchise(franchise)) {
            for (ActorCharacter a : characterRepository.findActorCharacterById(m.getMovie_id())) {
                b.add(a);
            }
        }

        return ResponseEntity.ok().body(b);
    }
}
