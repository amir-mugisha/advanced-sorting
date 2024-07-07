package com.atlab.advanced.sorting.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Integer> {

        @Query("SELECT a FROM Actor a JOIN FETCH a.movies")
        List<Actor> findAllActorsWithMovies();
}
