package ro.fittrack.workoutSet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.fittrack.workoutSet.dto.CreateWorkoutSetRequest;
import ro.fittrack.workoutSet.dto.WorkoutSetResponse;
import ro.fittrack.workoutSet.service.WorkoutSetService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workout-exercises/{workoutExerciseId}/sets")
@RequiredArgsConstructor
public class WorkoutSetController {
    private final WorkoutSetService workoutSetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutSetResponse createSet(@PathVariable UUID workoutExerciseId, @RequestBody @Valid CreateWorkoutSetRequest request) {
        return workoutSetService.createSet(workoutExerciseId, request);
    }

    @GetMapping
    public List<WorkoutSetResponse> getAllSets(@PathVariable UUID workoutExerciseId) {
        return workoutSetService.getSets(workoutExerciseId);
    }

    @GetMapping("/{setId}")
    public WorkoutSetResponse getSet(@PathVariable UUID workoutExerciseId, @PathVariable UUID setId) {
        return workoutSetService.getSet(workoutExerciseId, setId);
    }

    @PutMapping("/{setId}")
    public WorkoutSetResponse updateSet(
            @PathVariable UUID workoutExerciseId,
            @PathVariable UUID setId,
            @RequestBody @Valid CreateWorkoutSetRequest request
    ) {
        return workoutSetService.updateSet(
                workoutExerciseId,
                setId,
                request
        );
    }

    @DeleteMapping("/{setId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSet(
            @PathVariable UUID workoutExerciseId,
            @PathVariable UUID setId
    ) {
        workoutSetService.deleteSet(workoutExerciseId, setId);
    }
}