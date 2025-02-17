package com.frameworksbackend.trabajofinal.service;
import com.frameworksbackend.trabajofinal.model.Movie;
import  java.util.List;

public interface IMovieService {

    List<Movie> getAllMovies();

    Movie saveMovie(Movie movie);

    Movie getMovieById(Long id);

    void deleteMovie(Long id);

    Movie addActorToMovie(Long movieId, Long actorId);

    Movie updateMovie(Long id, Movie movieDetails);

    List<Movie> searchMovies(String query);
}
