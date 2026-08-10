package ro.fittrack.workout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fittrack.workout.entity.Workout;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {


    List<Workout> findAllByUserEmail(String email);

    Optional<Workout> findByIdAndUserEmail(UUID id, String email);
}