package ro.fittrack.workoutExercise.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CreateWorkoutExerciseRequest(

        @NotNull(message = "Exercise ID is required")
        UUID exerciseId
) {
}