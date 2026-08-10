package ro.fittrack.workout.entity;

import jakarta.persistence.*;
import lombok.*;
import ro.fittrack.auth.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(name="started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name="finished_at")
    private LocalDateTime finishedAt;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }

}