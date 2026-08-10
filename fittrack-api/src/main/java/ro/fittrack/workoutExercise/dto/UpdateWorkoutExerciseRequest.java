package ro.fittrack.workoutExercise.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateWorkoutExerciseRequest(

        @NotNull(message = "Sets are required")
        @Positive(message = "Sets must be greater than 0")
        Integer sets,

        @NotNull(message = "Reps are required")
        @Positive(message = "Reps must be greater than 0")
        Integer reps,

        @NotNull(message = "Weight is required")
        @Positive(message = "Weight must be greater than 0")
        Double weight
) {
}