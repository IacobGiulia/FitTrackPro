package ro.fittrack.workout.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record WorkoutResponse(
        UUID id,
        UUID user_id,
        String name,
        LocalDateTime started_at,
        LocalDateTime finished_at,
        LocalDateTime created_at
){}