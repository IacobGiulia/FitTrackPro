import { Component, OnInit, PLATFORM_ID, Inject, signal } from '@angular/core';
import { isPlatformBrowser, DatePipe } from '@angular/common';
import { Workout, WorkoutService } from '../../core/services/workout';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-workouts',
  imports: [DatePipe, RouterLink],
  templateUrl: './workouts.html',
  styleUrl: './workouts.scss'
})
export class Workouts implements OnInit {

  workouts = signal<Workout[]>([]);

  constructor(
    private workoutService: WorkoutService,
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
}