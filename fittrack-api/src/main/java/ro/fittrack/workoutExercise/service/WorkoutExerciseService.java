package ro.fittrack.workoutExercise.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fittrack.workout.entity.Workout;
import ro.fittrack.exercise.entity.Exercise;
import ro.fittrack.exercise.repository.ExerciseRepository;
import ro.fittrack.workout.repository.WorkoutRepository;
import ro.fittrack.workoutExercise.dto.CreateWorkoutExerciseRequest;
import ro.fittrack.workoutExercise.dto.UpdateWorkoutExerciseRequest;
import ro.fittrack.workoutExercise.dto.WorkoutExerciseResponse;
import ro.fittrack.workoutExercise.entity.WorkoutExercise;
import ro.fittrack.workoutExercise.repository.WorkoutExerciseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class WorkoutExerciseService {
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutExerciseResponse createExercise(UUID workoutId, CreateWorkoutExerciseRequest request)
    {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(() -> new IllegalArgumentException("Workout not found"));
        Exercise exercise = exerciseRepository.findById(request.exerciseId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Exercise not found"));
        WorkoutExercise workoutExercise = WorkoutExercise.builder()
                .workout(workout)
                .exercise(exercise)
                .build();

        WorkoutExercise savedExercise = workoutExerciseRepository.save(workoutExercise);

        return toResponse(savedExercise);
    }

    public List<WorkoutExerciseResponse> getExercises(UUID workoutId) {

        return workoutExerciseRepository.findAllByWorkoutId(workoutId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public WorkoutExerciseResponse getExercise(
            UUID workoutId,
            UUID exerciseId
    ) {
        WorkoutExercise exercise = workoutExerciseRepository
                .findByIdAndWorkoutId(exerciseId, workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        return toResponse(exercise);
    }

    public WorkoutExerciseResponse updateExercise(
            UUID workoutId,
            UUID exerciseId,
            UpdateWorkoutExerciseRequest request
    ) {
        WorkoutExercise workoutExercise =
                workoutExerciseRepository
                        .findByIdAndWorkoutId(exerciseId, workoutId)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Exercise not found"));

        WorkoutExercise updated =
                workoutExerciseRepository.save(workoutExercise);

        return toResponse(updated);
    }

    public void deleteExercise(
            UUID workoutId,
            UUID exerciseId
    ) {
        WorkoutExercise exercise = workoutExerciseRepository
                .findByIdAndWorkoutId(exerciseId, workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        workoutExerciseRepository.delete(exercise);
    }

    private WorkoutExerciseResponse toResponse(WorkoutExercise workoutExercise) {
        return new WorkoutExerciseResponse(
                workoutExercise.getId(),
                workoutExercise.getExercise().getId(),
                workoutExercise.getExercise().getName(),
                workoutExercise.getCreatedAt()
        );
    }
}