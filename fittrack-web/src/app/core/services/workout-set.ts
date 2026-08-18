import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

export interface WorkoutSet{
  
  id: string;
  setNumber: number;
  reps: number;
  weight: number;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})

export class WorkoutSetService{

  private apiUrl = 'http://localhost:8080/api/workout-exercises';

  constructor(private http: HttpClient){}

  getSets(workoutExerciseId: string): Observable<WorkoutSet[]> {
    return this.http.get<WorkoutSet[]>(
      `${this.apiUrl}/${workoutExerciseId}/sets`
    );
  }

  addSet(workoutExerciseId: string, setNumber: number, reps:number, weight:number): Observable<WorkoutSet>{
    return this.http.post<WorkoutSet>(`${this.apiUrl}/${workoutExerciseId}/sets`,
    {
      setNumber,
      reps,
      weight
    }
    );
}

updateSet(
    workoutExerciseId: string,
    setId: string,
    setNumber: number,
    reps: number,
    weight: number
  ): Observable<WorkoutSet> {
    return this.http.put<WorkoutSet>(
      `${this.apiUrl}/${workoutExerciseId}/sets/${setId}`,
      {
        setNumber,
        reps,
        weight
      }
    );
  }

  deleteSet(
    workoutExerciseId: string,
    setId: string
  ): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/${workoutExerciseId}/sets/${setId}`
    );
  }
}