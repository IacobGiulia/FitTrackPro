import { WorkoutSet } from "./workout-set";

export interface WorkoutExercise {
  id: string;
  exerciseId: string;
  exerciseName: string;
  createdAt: string;
  sets: WorkoutSet[];
}