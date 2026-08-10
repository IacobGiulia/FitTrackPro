package ro.fittrack.workoutExercise.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record WorkoutExerciseResponse(
        UUID id,
        String exerciseName,
        Integer sets,
        Integer reps,
        Double weight,
        LocalDateTime createdAt
) {}