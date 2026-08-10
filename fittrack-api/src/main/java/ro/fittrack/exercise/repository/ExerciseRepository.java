package ro.fittrack.exercise.repository;

import ro.fittrack.exercise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID>{

}
