package com.atlab.advanced.sorting.Actor;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActors() {
        return actorRepository.findAllActorsWithMovies();
    }

    public void addActor(Actor actor){
        actorRepository.save(actor);
    }

    public void deleteActor(Integer actorId) throws IllegalAccessException {
        boolean exists = actorRepository.existsById(actorId);
        if(!exists){
            throw new IllegalAccessException("Actor with id " + actorId + " does not exist");
        }
       actorRepository.deleteById(actorId);
    }

    @Transactional
    public void updateActor(int actorId, String name) throws IllegalStateException {
        Actor actor = actorRepository.findById(actorId).orElseThrow(
                () -> new IllegalStateException("Actor with id " + actorId + " does not exist")
        );

        if(name != null && !name.isEmpty() && !Objects.equals(actor.getName(), name)){
            actor.setName(name);
        }

    }
}
