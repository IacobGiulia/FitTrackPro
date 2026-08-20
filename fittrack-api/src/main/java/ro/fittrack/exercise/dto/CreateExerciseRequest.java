package ro.fittrack.exercise.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateExerciseRequest(

        @NotBlank(message="Exercise name is required")
        String name,

        @NotBlank(message="Muscle group is required")
        String muscleGroup,

        String description,

        String imageUrl
){}