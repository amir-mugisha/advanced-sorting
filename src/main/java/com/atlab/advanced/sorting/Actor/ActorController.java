package com.atlab.advanced.sorting.Actor;

import com.atlab.advanced.sorting.utils.ApiResponse;
import com.atlab.advanced.sorting.utils.QuickSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ApiResponse<List<Actor>> getActors() {
        Map<String,String> paths= Map.of(
                "addActor", "=/actors",
                "deleteActor", "/actors/{actorId}",
                "updateActor", "=/actors/{actorId}"
        );
        List<Actor> actors = actorService.getActors();


        return new ApiResponse<>(actors, "Actors retrieved successfully", 200, paths);
    }

    @PostMapping
    public ApiResponse<Actor> addActor(@RequestBody Actor actor){
        actorService.addActor(actor);
        Map<String,String> paths = Map.of(
                "getActors","/actors",
                "deleteActor", "/actors/{actorId}",
                "updateActor", "=/actors/{actorId}"
        );
        return new ApiResponse<>(actor, "Actor added successfully", 201, paths);

    }

    @DeleteMapping(path="{actorId}")
    public ApiResponse<Integer> deleteActor(@PathVariable("actorId") Integer actorId) throws IllegalAccessException {
        actorService.deleteActor(actorId);
        Map<String,String> paths = Map.of(
                "getActors","/actors"
        );
        return new ApiResponse<>(actorId, "Actor deleted successfully", 200, paths);

    }

    @PutMapping(path = "{actorId}")
    public ApiResponse<Integer> updateActor(@PathVariable int actorId, @RequestParam(required = false) String name){
        actorService.updateActor(actorId, name);
        Map<String,String> paths = Map.of(
                "getActors","/actors",
                "deleteActor", "=/actors/{actorId}"
        );
        return new ApiResponse<>(actorId, "Actor updated successfully", 200, paths);
    }

}
