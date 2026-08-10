package ro.fittrack.workoutSet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fittrack.workoutSet.entity.WorkoutSet;

import java.util.List;
import java.util.UUID;

public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, UUID> {

    List<WorkoutSet> findAllByWorkoutExerciseId(UUID workoutExerciseId);

    java.util.Optional<WorkoutSet> findByIdAndWorkoutExerciseId(UUID id, UUID workoutExerciseId);
}