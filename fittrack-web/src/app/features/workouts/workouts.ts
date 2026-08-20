import { Component, OnInit, PLATFORM_ID, Inject, signal } from '@angular/core';
import { isPlatformBrowser, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Workout, WorkoutService } from '../../core/services/workout';
import { RouterLink, Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-workouts',
  imports: [DatePipe, RouterLink, FormsModule],
  templateUrl: './workouts.html',
  styleUrl: './workouts.scss'
})
export class Workouts implements OnInit {

  workouts = signal<Workout[]>([]);

  addingWorkout = signal(false);
  newWorkoutName = '';

  constructor(
    private workoutService: WorkoutService,
    private authService: AuthService,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.workoutService.getWorkouts().subscribe({
        next: (workouts) => {
          console.log('WORKOUTS PRIMIT:', workouts);
          this.workouts.set(workouts);
        },
        error: (error) => {
          console.error('EROARE WORKOUTS:', error);
        }
      });
    }
  }

  toggleAddWorkout(): void {
    this.addingWorkout.update((current) => !current);
    this.newWorkoutName = '';
  }

  submitNewWorkout(): void {
    if(!this.newWorkoutName.trim())
      return;

    this.workoutService.createWorkout(this.newWorkoutName.trim()).subscribe({
      next: (createdWorkout) => {
        this.workouts.update((current) => [createdWorkout, ...current]);
        this.addingWorkout.set(false);
        this.newWorkoutName = '';
      },
      error: (error) => {
        console.error('EROARE CREATE WORKOUT:', error);
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}