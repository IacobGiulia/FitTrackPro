package ro.fittrack.workoutSet.entity;

import jakarta.persistence.*;
import lombok.*;
import ro.fittrack.workoutExercise.entity.WorkoutExercise;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="workout_sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class WorkoutSet {

    @Id
    private UUID Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExercise workoutExercise;

    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Column(nullable = false)
    private Integer reps;

    @Column(nullable = false)
    private Double weight;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        Id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}