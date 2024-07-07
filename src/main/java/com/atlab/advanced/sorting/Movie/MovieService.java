package com.atlab.advanced.sorting.Movie;

import com.atlab.advanced.sorting.Actor.Actor;
import com.atlab.advanced.sorting.Actor.ActorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }

    public void deleteMovie(Integer movieId) throws IllegalAccessException {
        boolean exists = movieRepository.existsById(movieId);
        if(!exists){
            throw new IllegalAccessException("Movie with id " + movieId + " does not exist");
        }
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public void updateMovie(int movieId, String name, int year) throws IllegalStateException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalStateException("Movie with id " + movieId + " does not exist")
        );

        if(name != null && !name.isEmpty() && !Objects.equals(movie.getName(), name)){
            movie.setName(name);
        }

        if(year != 0 && !Objects.equals(movie.getYear(), year)){
            movie.setYear(year);
        }

    }

    @Transactional
    public void addActor(Integer movieId, Integer actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException("Movie not found"));
        Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new IllegalStateException("Actor not found"));
        movie.getActors().add(actor);
        movieRepository.save(movie);
    }

    @Transactional
    public void removeActor(Integer movieId, Integer actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException("Movie not found"));
        Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new IllegalStateException("Actor not found"));
        movie.getActors().remove(actor);
        movieRepository.save(movie);
    }
}
