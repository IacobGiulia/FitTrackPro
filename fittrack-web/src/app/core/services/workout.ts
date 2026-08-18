import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WorkoutExercise } from './workout-exercise';

export interface Workout {
  id: string;
  user_id: string;
  name: string;
  description?: string;
  started_at: string;
  finished_at?: string | null;
  created_at: string;
}

@Injectable({
  providedIn: 'root'
})

export class WorkoutService {

  private apiUrl='http://localhost:8080/api/workouts';

  constructor(private http: HttpClient) {}

  getWorkouts() : Observable<Workout[]> {
    return this.http.get<Workout[]>(this.apiUrl);
  }

  getWorkout(id: string): Observable<Workout> {
  return this.http.get<Workout>(`${this.apiUrl}/${id}`);
}

  getWorkoutExercises(id: string): Observable<WorkoutExercise[]> {
  return this.http.get<WorkoutExercise[]>(
    `${this.apiUrl}/${id}/exercises`
  );
}

  addExerciseToWorkout(workoutId: string, exerciseId: string): Observable<WorkoutExercise>{
    return this.http.post<WorkoutExercise>(`${this.apiUrl}/${workoutId}/exercises`, {exerciseId});
  }

}
