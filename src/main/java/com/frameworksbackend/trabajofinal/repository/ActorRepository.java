package com.frameworksbackend.trabajofinal.repository;

import com.frameworksbackend.trabajofinal.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByNameContaining(String name);
}
