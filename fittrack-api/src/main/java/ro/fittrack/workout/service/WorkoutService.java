package ro.fittrack.workout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fittrack.auth.entity.User;
import ro.fittrack.auth.repository.UserRepository;
import ro.fittrack.workout.dto.CreateWorkoutRequest;
import ro.fittrack.workout.dto.UpdateWorkoutRequest;
import ro.fittrack.workout.dto.WorkoutResponse;
import ro.fittrack.workout.entity.Workout;
import ro.fittrack.workout.repository.WorkoutRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutResponse createWorkout(
            CreateWorkoutRequest request,
            String email
    ) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Workout workout = Workout.builder()
                .user(user)
                .name(request.name())
                .startedAt(java.time.LocalDateTime.now())
                .build();

        Workout savedWorkout = workoutRepository.save(workout);

        return toResponse(savedWorkout);
    }

    public List<WorkoutResponse> getMyWorkouts(String email) {

        return workoutRepository.findAllByUserEmail(email)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public WorkoutResponse getWorkout(
            UUID id,
            String email
    ) {

        Workout workout = workoutRepository
                .findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        return toResponse(workout);
    }

    public WorkoutResponse updateWorkout(
            UUID id,
            UpdateWorkoutRequest request,
            String email
    ) {

        Workout workout = workoutRepository
                .findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        workout.setName(request.name());

        Workout updatedWorkout = workoutRepository.save(workout);

        return toResponse(updatedWorkout);
    }

    public void deleteWorkout(
            UUID id,
            String email
    ) {

        Workout workout = workoutRepository
                .findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        workoutRepository.delete(workout);
    }

    private WorkoutResponse toResponse(Workout workout) {

        return new WorkoutResponse(
                workout.getId(),
                workout.getUser().getId(),
                workout.getName(),
                workout.getStartedAt(),
                workout.getFinishedAt(),
                workout.getCreatedAt()
        );
    }
}