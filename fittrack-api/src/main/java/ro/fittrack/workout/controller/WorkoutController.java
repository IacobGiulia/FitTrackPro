package ro.fittrack.workout.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.fittrack.workout.dto.CreateWorkoutRequest;
import ro.fittrack.workout.dto.UpdateWorkoutRequest;
import ro.fittrack.workout.dto.WorkoutResponse;
import ro.fittrack.workout.service.WorkoutService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse createWorkout(
            @RequestBody @Valid CreateWorkoutRequest request,
            Authentication authentication
    ) {
        return workoutService.createWorkout(
                request,
                authentication.getName()
        );
    }

    @GetMapping
    public List<WorkoutResponse> getMyWorkouts(
            Authentication authentication
    ) {
        return workoutService.getMyWorkouts(
                authentication.getName()
        );
    }

    @GetMapping("/{id}")
    public WorkoutResponse getWorkout(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        return workoutService.getWorkout(
                id,
                authentication.getName()
        );
    }

    @PutMapping("/{id}")
    public WorkoutResponse updateWorkout(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateWorkoutRequest request,
            Authentication authentication
    ) {
        return workoutService.updateWorkout(
                id,
                request,
                authentication.getName()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        workoutService.deleteWorkout(
                id,
                authentication.getName()
        );
    }
}