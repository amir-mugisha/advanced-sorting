package com.atlab.advanced.sorting.Movie;

import com.atlab.advanced.sorting.Actor.Actor;
import com.atlab.advanced.sorting.Actor.ActorService;
import com.atlab.advanced.sorting.utils.ApiResponse;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ApiResponse<List<Movie>> getMovies() {
        List<Movie> movies = movieService.getMovies();
        Map<String, String> paths = Map.of(
                "addMovie", "=/movies",
                "deleteMovie", "/movies/{movieId}",
                "updateMovie", "/movies/{movieId}",
                "addActorToMovie", "/movies/{movieId}/actor/{actorId}",
                "removeActorFromMovie", "/movies/{movieId}/actor/{actorId}"
        );
        movies.forEach(movie -> Hibernate.initialize(movie.getActors()));
        return new ApiResponse<>(movies, "Movies retrieved successfully", 200, paths);
    }

    @PostMapping
    public ApiResponse<Movie> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        Map<String,String> paths= Map.of(
                "deleteMovie", "/movies/{movieId}",
                "updateMovie", "=/movies/{movieId}",
                "addActorToMovie", "=/movies/{movieId}/actor/{actorId}",
                "removeActorFromMovie", "/movies/{movieId}/actor/{actorId}"
        );
        return new ApiResponse<>(movie, "Movie added successfully", 201, paths);
    }

    @DeleteMapping(path="{movieId}")
    public ApiResponse<Integer> deleteMovie(@PathVariable("movieId") Integer movieId) throws IllegalAccessException {
        movieService.deleteMovie(movieId);
        Map<String, String> paths = Map.of(
                "addMovie", "/movies");
        return new ApiResponse<>(movieId, "Movie deleted successfully", 200, paths);
    }

    @PutMapping(path = "{movieId}")
    public ApiResponse<Integer> updateMovie(
            @PathVariable int movieId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer year){
        movieService.updateMovie(movieId, name, year);
        Map<String, String> paths = Map.of(
                "addActorToMovie", "=/movies/{movieId}/actor/{actorId}",
                "removeActorFromMovie", "/movies/{movieId}/actor/{actorId}",
                "deleteMovie", "/movies/{movieId}"
        );
        return new ApiResponse<>(movieId, "Movie updated successfully", 200, paths);

    }

    @PostMapping(path = "{movieId}/actors/{actorId}")
    public ApiResponse<Integer> addActor(@PathVariable Integer movieId, @PathVariable Integer actorId){
        movieService.addActor(movieId, actorId);
        Map<String, String> paths = Map.of(
                "removeActorFromMovie", "/movies/{movieId}/actor/{actorId}",
                "deleteMovie", "/movies/{movieId}"
        );
        return new ApiResponse<>(actorId, "Actor added successfully", 201, paths);
    }

    @DeleteMapping(path = "{movieId}/actors/{actorId}")
    public ApiResponse<Integer> removeActor(@PathVariable Integer movieId, @PathVariable Integer actorId){
        movieService.removeActor(movieId, actorId);
        Map<String, String> paths = Map.of(
                "addActorToMovie", "=/movies/{movieId}/actor/{actorId}",
                "deleteMovie", "/movies/{movieId}"
        );
        return new ApiResponse<>(actorId, "Actor removed successfully", 200, paths);
    }

}
