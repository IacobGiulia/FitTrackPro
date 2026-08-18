import { Injectable } from "@angular/core";
import { HttpClient} from "@angular/common/http";
import { Observable} from "rxjs";

export interface Exercise{
  id: string;
  name: string;
  muscleGroup: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})

export class ExerciseService{

  private apiUrl='http://localhost:8080/api/exercises';

  constructor(private http: HttpClient){}

  getExercises(): Observable<Exercise[]>{
    return this.http.get<Exercise[]>(this.apiUrl);
  }
}