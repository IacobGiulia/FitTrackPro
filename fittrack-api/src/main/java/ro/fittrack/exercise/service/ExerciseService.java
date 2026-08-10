package ro.fittrack.exercise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fittrack.exercise.dto.CreateExerciseRequest;
import ro.fittrack.exercise.dto.ExerciseResponse;
import ro.fittrack.exercise.entity.Exercise;
import ro.fittrack.exercise.repository.ExerciseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseResponse createExercise(CreateExerciseRequest request) {

        Exercise exercise = Exercise.builder()
                .name(request.name())
                .muscleGroup(request.muscleGroup())
                .description(request.description())
                .build();

        Exercise savedExercise = exerciseRepository.save(exercise);

        return toResponse(savedExercise);
    }

    public List<ExerciseResponse> getAllExercises() {

        return exerciseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ExerciseResponse getExercise(UUID id) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Exercise not found"));

        return toResponse(exercise);
    }

    public ExerciseResponse updateExercise(
            UUID id,
            CreateExerciseRequest request
    ) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Exercise not found"));

        exercise.setName(request.name());
        exercise.setMuscleGroup(request.muscleGroup());
        exercise.setDescription(request.description());

        Exercise updatedExercise = exerciseRepository.save(exercise);

        return toResponse(updatedExercise);
    }

    public void deleteExercise(UUID id) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Exercise not found"));

        exerciseRepository.delete(exercise);
    }

    private ExerciseResponse toResponse(Exercise exercise) {

        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getMuscleGroup(),
                exercise.getDescription()
        );
    }
}