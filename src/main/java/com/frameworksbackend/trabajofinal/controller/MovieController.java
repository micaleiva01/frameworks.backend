package com.frameworksbackend.trabajofinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frameworksbackend.trabajofinal.model.Movie;
import com.frameworksbackend.trabajofinal.service.IMovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final IMovieService movieService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // 🔥 Handles JSON Parsing

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>>getMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createMovie(@RequestBody String movieJson, @RequestHeader HttpHeaders headers) {
        logger.info("📢 Received Headers: {}", headers);
        logger.info("📢 Raw JSON Payload: {}", movieJson);

        try {
            // ✅ Parse JSON into a Movie object
            Movie movie = objectMapper.readValue(movieJson, Movie.class);
            logger.info("✅ Parsed Movie Object: {}", movie);

            if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Title is required.");
            }

            Movie savedMovie = movieService.saveMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);

        } catch (Exception e) {
            logger.error("❌ Error processing request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Invalid request format.");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String query) {
        List<Movie> filteredMovies = movieService.searchMovies(query);
        return ResponseEntity.ok(filteredMovies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        try {
            Movie updatedMovie = movieService.updateMovie(id, movieDetails);
            return ResponseEntity.ok(updatedMovie);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully!");
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    public ResponseEntity<Movie> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) {
        try {
            Movie updatedMovie = movieService.addActorToMovie(movieId, actorId);
            return ResponseEntity.ok(updatedMovie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
