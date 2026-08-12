import { Routes } from '@angular/router';
import { Workouts } from './features/workouts/workouts';
import { WorkoutDetails } from './features/workouts/workout-details/workout-details';

export const routes: Routes = [
  {
    path: 'workouts',
    component: Workouts
  },

  {
    path: 'workouts/:id',
    component: WorkoutDetails
  }

];