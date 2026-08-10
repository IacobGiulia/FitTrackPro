package ro.fittrack.exercise.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise{

    @Id
    private UUID Id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name="muscle_group", nullable = false)
    private String muscleGroup;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist(){
        Id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}