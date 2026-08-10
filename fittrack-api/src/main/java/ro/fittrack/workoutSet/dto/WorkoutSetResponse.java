package ro.fittrack.workoutSet.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record WorkoutSetResponse (
    UUID id,
    Integer setNumber,
    Integer reps,
    Double weight,
    LocalDateTime createdAt

){}