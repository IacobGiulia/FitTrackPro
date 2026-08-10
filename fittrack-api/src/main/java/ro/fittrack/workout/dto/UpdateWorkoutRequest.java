package ro.fittrack.workout.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateWorkoutRequest(

        @NotBlank(message="Workout name is required")
        String name
){}