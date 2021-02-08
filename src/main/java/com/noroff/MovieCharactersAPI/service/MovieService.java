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

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    FranchiseRepository franchiseRepository;

    //Get all
    public List<Movie> getAllMovies(){
        return this.movieRepository.findAll();
    }


    //Get by id
    public Movie getMovieById(Long id) throws NoItemFoundException{
        return movieRepository.findById(id).orElseThrow(() -> new NoItemFoundException("No movie by id " + id));
    }


    //Post
    public Movie createNewMovie(Movie movie){
        return movieRepository.save(movie);
    }

    //Update
    public ResponseEntity<Movie> updateMovie(Movie movie, Long movie_id) throws NoItemFoundException{
        Movie oldMovie = movieRepository.findById(movie_id).orElseThrow(()-> new NoItemFoundException("No movie by id " + movie_id));

        oldMovie.setTitle(movie.getTitle());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setYear(movie.getYear());
        oldMovie.setDirector(movie.getDirector());
        oldMovie.setPicture(movie.getPicture());
        oldMovie.setTrailer(movie.getTrailer());

        oldMovie.setCharacters(movie.getCharacters());

        Movie returnMovie = movieRepository.save(oldMovie);

        if(returnMovie == oldMovie){
            return ResponseEntity.ok().body(returnMovie);
        }
        else {
            return ResponseEntity.unprocessableEntity().body(returnMovie);
        }
    }

    //Delete
    public ResponseEntity<Movie> deleteMovie(Long id) throws NoItemFoundException{
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new NoItemFoundException("No movie by id " + id));
        List<ActorCharacter> characters = movie.getCharacters();
        for (ActorCharacter character: characters) {
            character.getMovies().remove(character);
        }
        movieRepository.delete(movie);

        Movie returnMovie = movieRepository.findById(id).orElse(null);

        if (returnMovie == null) {
            return ResponseEntity.ok().body(movie);
        }
        else {
            return ResponseEntity.badRequest().body(movie);
        }
    }

    //Special Query #2
    public ResponseEntity<List<Movie>> getAllMoviesFromFranchise(Long franchiseid) throws NoItemFoundException{
        Franchise franchise = franchiseRepository.findById(franchiseid).orElseThrow(() -> new NoItemFoundException("AHHHHHH"));
        List<Movie> movieList = movieRepository.findMovieByFranchise(franchise);
        return ResponseEntity.ok().body(movieList);
    }




}
