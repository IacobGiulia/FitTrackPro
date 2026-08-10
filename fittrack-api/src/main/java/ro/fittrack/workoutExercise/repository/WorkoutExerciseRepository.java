package ro.fittrack.workoutExercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fittrack.workoutExercise.entity.WorkoutExercise;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, UUID> {

    List<WorkoutExercise> findAllByWorkoutId(UUID workoutId);

    Optional<WorkoutExercise> findByIdAndWorkoutId(UUID id, UUID workoutId);


}