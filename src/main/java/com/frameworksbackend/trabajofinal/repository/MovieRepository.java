package com.frameworksbackend.trabajofinal.repository;

import com.frameworksbackend.trabajofinal.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContaining(String title);
}
