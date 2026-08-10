package ro.fittrack.workoutExercise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.fittrack.workoutExercise.dto.WorkoutExerciseResponse;
import ro.fittrack.workoutExercise.service.WorkoutExerciseService;
import ro.fittrack.workoutExercise.dto.CreateWorkoutExerciseRequest;
import ro.fittrack.workoutExercise.dto.UpdateWorkoutExerciseRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts/{workoutId}/exercises")
@RequiredArgsConstructor
public class WorkoutExerciseController {
    private final WorkoutExerciseService workoutExerciseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutExerciseResponse createExercise(
            @PathVariable UUID workoutId,
            @RequestBody @Valid CreateWorkoutExerciseRequest request
    ){
        return workoutExerciseService.createExercise(workoutId, request);
    }

    @GetMapping
    public List<WorkoutExerciseResponse> getAllExercises(@PathVariable UUID workoutId) {
        return workoutExerciseService.getExercises(workoutId);
    }

    @GetMapping("/{exerciseId}")
    public WorkoutExerciseResponse getExercise(@PathVariable UUID workoutId, @PathVariable UUID exerciseId) {
        return workoutExerciseService.getExercise(workoutId, exerciseId);
    }

    @PutMapping("/{exerciseId}")
    public WorkoutExerciseResponse updateExercise(
            @PathVariable UUID workoutId,
            @PathVariable UUID exerciseId,
            @RequestBody @Valid UpdateWorkoutExerciseRequest request
    ) {
        return workoutExerciseService.updateExercise(
                workoutId,
                exerciseId,
                request
        );
    }

    @DeleteMapping("/{exerciseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(
            @PathVariable UUID workoutId,
            @PathVariable UUID exerciseId
    ) {
        workoutExerciseService.deleteExercise(workoutId, exerciseId);
    }
}