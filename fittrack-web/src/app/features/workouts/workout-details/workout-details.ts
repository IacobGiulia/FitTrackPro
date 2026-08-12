import { Component, OnInit, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { WorkoutService } from '../../../core/services/workout';

@Component({
  selector: 'app-workout-details',
  imports: [DatePipe, RouterLink],
  templateUrl: './workout-details.html',
  styleUrl: './workout-details.scss'
})
export class WorkoutDetails implements OnInit {

  private route: ActivatedRoute;
  private workoutService: WorkoutService;

  workout = signal<any>(null);
  exercises = signal<any[]>([]);

  constructor(
    route: ActivatedRoute,
    workoutService: WorkoutService
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

    console.log('WORKOUT ID:', workoutId);

    this.workoutService.getWorkout(workoutId).subscribe({
      next: (workout) => {
        console.log('WORKOUT:', workout);
        this.workout.set(workout);
      },
      error: (error) => {
        console.error('EROARE WORKOUT:', error);
      }
    });

    this.workoutService.getWorkoutExercises(workoutId).subscribe({
      next: (exercises) => {
        console.log('EXERCISES:', exercises);
        this.exercises.set(exercises);
      },
      error: (error) => {
        console.error('EROARE EXERCISES:', error);
      }
    });
  }
}