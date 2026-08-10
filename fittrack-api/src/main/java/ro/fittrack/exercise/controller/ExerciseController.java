package ro.fittrack.exercise.controller;

import ro.fittrack.exercise.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.fittrack.exercise.dto.ExerciseResponse;
import ro.fittrack.exercise.dto.CreateExerciseRequest;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse createExercise(@RequestBody @Valid CreateExerciseRequest request) {
        return exerciseService.createExercise(request);
    }

    @GetMapping
    public List<ExerciseResponse> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public ExerciseResponse getExercise(@PathVariable UUID id) {
        return exerciseService.getExercise(id);
    }

    @PutMapping("/{id}")
    public ExerciseResponse updateExercise(
            @PathVariable UUID id,
            @RequestBody @Valid CreateExerciseRequest request
    ) {
        return exerciseService.updateExercise(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(
            @PathVariable UUID id
    ) {
        exerciseService.deleteExercise(id);
    }
}