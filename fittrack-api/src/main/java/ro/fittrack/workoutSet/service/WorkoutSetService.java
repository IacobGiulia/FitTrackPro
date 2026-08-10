package ro.fittrack.workoutSet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fittrack.workoutSet.entity.WorkoutSet;
import ro.fittrack.workoutSet.repository.WorkoutSetRepository;
import ro.fittrack.workoutSet.dto.WorkoutSetResponse;
import ro.fittrack.workoutSet.dto.CreateWorkoutSetRequest;
import ro.fittrack.workoutExercise.entity.WorkoutExercise;
import ro.fittrack.workoutExercise.repository.WorkoutExerciseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutSetService {

    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutSetResponse createSet(UUID workoutExerciseId, CreateWorkoutSetRequest request) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(workoutExerciseId).orElseThrow(() -> new IllegalArgumentException("Workout exercise not found"));

        WorkoutSet workoutSet = WorkoutSet.builder()
                .workoutExercise(workoutExercise)
                .setNumber(request.setNumber())
                .reps(request.reps())
                .weight(request.weight())
                .build();

        WorkoutSet savedSet = workoutSetRepository.save(workoutSet);

        return toResponse(savedSet);

    }
    public List<WorkoutSetResponse> getSets(UUID workoutExerciseId) {

        return workoutSetRepository
                .findAllByWorkoutExerciseId(workoutExerciseId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public WorkoutSetResponse getSet(
            UUID workoutExerciseId,
            UUID setId
    ) {
        WorkoutSet workoutSet =
                workoutSetRepository
                        .findByIdAndWorkoutExerciseId(setId, workoutExerciseId)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Workout set not found"));

        return toResponse(workoutSet);
    }

    public WorkoutSetResponse updateSet(
            UUID workoutExerciseId,
            UUID setId,
            CreateWorkoutSetRequest request
    ) {
        WorkoutSet workoutSet =
                workoutSetRepository
                        .findByIdAndWorkoutExerciseId(setId, workoutExerciseId)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Workout set not found"));

        workoutSet.setSetNumber(request.setNumber());
        workoutSet.setReps(request.reps());
        workoutSet.setWeight(request.weight());

        WorkoutSet updatedSet = workoutSetRepository.save(workoutSet);

        return toResponse(updatedSet);
    }

    public void deleteSet(
            UUID workoutExerciseId,
            UUID setId
    ) {
        WorkoutSet workoutSet =
                workoutSetRepository
                        .findByIdAndWorkoutExerciseId(setId, workoutExerciseId)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Workout set not found"));

        workoutSetRepository.delete(workoutSet);
    }

    private WorkoutSetResponse toResponse(WorkoutSet workoutSet) {
        return new WorkoutSetResponse(
                workoutSet.getId(),
                workoutSet.getSetNumber(),
                workoutSet.getReps(),
                workoutSet.getWeight(),
                workoutSet.getCreatedAt()
        );
    }
}