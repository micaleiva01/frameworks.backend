package com.frameworksbackend.trabajofinal.service;

import com.frameworksbackend.trabajofinal.model.Actor;

import java.util.List;

public interface IActorService {
    List<Actor> getAllActors();

    Actor saveActor(Actor actor);

    Actor getActorById(Long id);

    void deleteActor(Long id);
}

