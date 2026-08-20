import { Component, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { forkJoin, of, switchMap, map } from 'rxjs';
import { WorkoutService } from '../../../core/services/workout';
import { WorkoutExercise } from '../../../core/services/workout-exercise';
import { WorkoutSet, WorkoutSetService } from '../../../core/services/workout-set';
import { Exercise, ExerciseService } from '../../../core/services/exercise';

@Component({
  selector: 'app-workout-details',
  imports: [DatePipe, RouterLink, FormsModule],
  templateUrl: './workout-details.html',
  styleUrl: './workout-details.scss'
})
export class WorkoutDetails implements OnInit {

  private route: ActivatedRoute;
  private workoutService: WorkoutService;
  private workoutId = '';

  workout = signal<any>(null);
  exercises = signal<WorkoutExercise[]>([]);

  availableExercises = signal<Exercise[]>([]);
  addingExercise = signal(false);
  selectedExerciseId = '';

  addingSetExerciseId = signal<string | null>(null);
  newSet = { setNumber: 1, reps: 0, weight: 0 };

  constructor(
    route: ActivatedRoute,
    workoutService: WorkoutService,
    private workoutSetService: WorkoutSetService,
    private exerciseService: ExerciseService
  ) {
    this.route = route;
    this.workoutService = workoutService;
  }

  ngOnInit(): void {

    const workoutId = this.route.snapshot.paramMap.get('id');

    if (!workoutId) {
      console.error('Workout ID not found');
      return;
    }

    this.workoutId = workoutId;

    this.workoutService.getWorkout(workoutId).subscribe({
      next: (workout) => this.workout.set(workout),
      error: (error) => console.error('EROARE WORKOUT:', error)
    });

    this.loadExercises();

    this.exerciseService.getAllExercises().subscribe({
      next: (exercises) => this.availableExercises.set(exercises),
      error: (error) => console.error('EROARE EXERCISE CATALOG:', error)
    });
  }

  private loadExercises(): void {
    this.workoutService.getWorkoutExercises(this.workoutId).pipe(
      switchMap((exercises) => {
        if (exercises.length === 0) {
          return of([]);
        }

        const exercisesWithSets$ = exercises.map((exercise) =>
          this.workoutSetService.getSets(exercise.id).pipe(
            map((sets) => ({ ...exercise, sets }))
          )
        );

        return forkJoin(exercisesWithSets$);
      })
    ).subscribe({
      next: (exercisesWithSets) => this.exercises.set(exercisesWithSets),
      error: (error) => console.error('EROARE EXERCISES:', error)
    });
  }

  toggleAddExercise(): void {
    this.addingExercise.update((current) => !current);
    this.selectedExerciseId = '';
  }

  submitNewExercise(): void {
    if (!this.selectedExerciseId) {
      return;
    }

    this.workoutService.addExercise(this.workoutId, this.selectedExerciseId).subscribe({
      next: (createdExercise) => {
        this.exercises.update((current) => [
          ...current,
          { ...createdExercise, sets: [] }
        ]);
        this.addingExercise.set(false);
        this.selectedExerciseId = '';
      },
      error: (error) => console.error('EROARE ADD EXERCISE:', error)
    });
  }

  toggleAddSet(exercise: WorkoutExercise): void {
    if (this.addingSetExerciseId() === exercise.id) {
      this.addingSetExerciseId.set(null);
      return;
    }

    this.newSet = {
      setNumber: exercise.sets.length + 1,
      reps: 0,
      weight: 0
    };
    this.addingSetExerciseId.set(exercise.id);
  }

  cancelAddSet(): void {
    this.addingSetExerciseId.set(null);
  }

  submitNewSet(exercise: WorkoutExercise): void {
    const { setNumber, reps, weight } = this.newSet;

    if (!setNumber || !reps || !weight) {
      return;
    }

    this.workoutSetService.addSet(exercise.id, setNumber, reps, weight).subscribe({
      next: (createdSet) => {
        this.exercises.update((current) =>
          current.map((ex) =>
            ex.id === exercise.id
              ? { ...ex, sets: [...ex.sets, createdSet] }
              : ex
          )
        );
        this.addingSetExerciseId.set(null);
      },
      error: (error) => console.error('EROARE ADD SET:', error)
    });
  }

  deleteSet(exercise: WorkoutExercise, set: WorkoutSet): void {
    this.workoutSetService.deleteSet(exercise.id, set.id).subscribe({
      next: () => {
        this.exercises.update((current) =>
          current.map((ex) =>
            ex.id === exercise.id
              ? { ...ex, sets: ex.sets.filter((s) => s.id !== set.id) }
              : ex
          )
        );
      },
      error: (error) => console.error('EROARE DELETE SET:', error)
    });
  }
}