package ro.fittrack.workoutExercise.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record WorkoutExerciseResponse(
        UUID id,
        UUID exerciseId,
        String exerciseName,
        Integer sets,
        Integer reps,
        Double weight,
        LocalDateTime createdAt
) {
}