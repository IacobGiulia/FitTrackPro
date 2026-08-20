package ro.fittrack.exercise.dto;

import java.util.UUID;

public record ExerciseResponse(
        UUID id,
        String name,
        String muscleGroup,
        String description,
        String imageUrl
) {
}