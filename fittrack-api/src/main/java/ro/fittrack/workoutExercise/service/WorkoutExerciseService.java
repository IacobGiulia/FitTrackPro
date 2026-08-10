package ro.fittrack.workoutExercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fittrack.workout.entity.Workout;
import ro.fittrack.workout.repository.WorkoutRepository;
import ro.fittrack.workoutExercise.dto.CreateWorkoutExerciseRequest;
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

    public WorkoutExerciseResponse createExercise(UUID workoutId, CreateWorkoutExerciseRequest request)
    {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        WorkoutExercise exercise = WorkoutExercise.builder()
                .workout(workout)
                .exerciseName(request.exerciseName())
                .sets(request.sets())
                .reps(request.reps())
                .weight(request.weight())
                .build();

        WorkoutExercise savedExercise = workoutExerciseRepository.save(exercise);

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
            CreateWorkoutExerciseRequest request
    ) {
        WorkoutExercise exercise = workoutExerciseRepository
                .findByIdAndWorkoutId(exerciseId, workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        exercise.setExerciseName(request.exerciseName());
        exercise.setSets(request.sets());
        exercise.setReps(request.reps());
        exercise.setWeight(request.weight());

        WorkoutExercise updated = workoutExerciseRepository.save(exercise);

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

    private WorkoutExerciseResponse toResponse(WorkoutExercise exercise) {
        return new WorkoutExerciseResponse(
                exercise.getId(),
                exercise.getExerciseName(),
                exercise.getSets(),
                exercise.getReps(),
                exercise.getWeight(),
                exercise.getCreatedAt()
        );
    }
}