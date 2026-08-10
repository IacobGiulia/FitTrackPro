package ro.fittrack.workoutSet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateWorkoutSetRequest(

        @NotNull(message = "Set number is required")
        @Positive(message = "Set number must be greater than 0")
        Integer setNumber,

        @NotNull(message = "Reps are required")
        @Positive(message = "Reps must be greater than 0")
        Integer reps,

        @NotNull(message = "Weight is required")
        @Positive(message = "Weight must be greater than 0")
        Double weight

){}