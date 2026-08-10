package ro.fittrack.workoutExercise.entity;

import jakarta.persistence.*;
import lombok.*;
import ro.fittrack.exercise.entity.Exercise;
import ro.fittrack.workout.entity.Workout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="workout_exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class WorkoutExercise {

    @Id
    private UUID Id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="workout_id", nullable = false)
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(nullable = false)
    private Integer sets;

    @Column(nullable = false)
    private Integer reps;

    @Column(nullable = false)
    private Double weight;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist(){
        Id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}