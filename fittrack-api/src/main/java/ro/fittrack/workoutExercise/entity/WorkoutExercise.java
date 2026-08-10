package ro.fittrack.workoutExercise.entity;

import jakarta.persistence.*;
import lombok.*;
import ro.fittrack.exercise.entity.Exercise;
import ro.fittrack.workout.entity.Workout;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "workout_exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExercise {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}