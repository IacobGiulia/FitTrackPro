import { Routes } from '@angular/router';
import { Workouts } from './features/workouts/workouts';
import { WorkoutDetails } from './features/workouts/workout-details/workout-details';
import { Login } from './auth/login/login/login';
import { authGuard } from './core/guards/auth-guard';
import { Exercises } from './features/exercises/exercises';

export const routes: Routes = [
  {
    path: 'login',
    component: Login  
  },
  {
    path: 'workouts',
    component: Workouts,
    canActivate: [authGuard]
  },

  {
    path: 'workouts/:id',
    component: WorkoutDetails,
    canActivate: [authGuard]
  },

  {
    path:'exercises',
    component: Exercises,
    canActivate: [authGuard]
  },

  {
    path: '',
    redirectTo: 'workouts',
    pathMatch: 'full'
  }

];