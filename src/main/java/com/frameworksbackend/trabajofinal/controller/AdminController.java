package com.frameworksbackend.trabajofinal.controller;

import com.frameworksbackend.trabajofinal.model.Movie;
import com.frameworksbackend.trabajofinal.model.Actor;
import com.frameworksbackend.trabajofinal.service.IMovieService;
import com.frameworksbackend.trabajofinal.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IActorService actorService;

    // Get all movies
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // Add a new movie
    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.saveMovie(movie)); // Use saveMovie instead of createMovie
    }

    // Get a movie by ID
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    // Update a movie
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        return ResponseEntity.ok(movieService.updateMovie(id, movieDetails));
    }

    // Delete a movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }

    // Add an actor to a movie
    @PostMapping("/movies/{movieId}/actors/{actorId}")
    public ResponseEntity<Movie> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        return ResponseEntity.ok(movieService.addActorToMovie(movieId, actorId));
    }

    // Search for movies
    @GetMapping("/movies/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String query) {
        return ResponseEntity.ok(movieService.searchMovies(query));
    }

    // Get all actors
    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok(actorService.getAllActors());
    }

    // Add a new actor
    @PostMapping("/actors")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.saveActor(actor)); // Use saveActor instead of createActor
    }

    // Get an actor by ID
    @GetMapping("/actors/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    // Delete an actor
    @DeleteMapping("/actors/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok("Actor deleted successfully");
    }
}
