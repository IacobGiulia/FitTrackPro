import { Component, OnInit, signal } from '@angular/core';
import { Exercise, ExerciseService } from '../../core/services/exercise';

interface MuscleGroupStyle {
  emoji: string,
  gradient: string
}

const MUSCLE_GROUP_STYLES: Record<string, MuscleGroupStyle> = {
  chest: { emoji: '🏋️', gradient: 'linear-gradient(135deg, #ff9a8b, #ff6a88)' },
  back: { emoji: '🔙', gradient: 'linear-gradient(135deg, #667eea, #764ba2)' },
  legs: { emoji: '🦵', gradient: 'linear-gradient(135deg, #43e97b, #38f9d7)' },
  shoulders: { emoji: '🤸', gradient: 'linear-gradient(135deg, #fa709a, #fee140)' },
  arms: { emoji: '💪', gradient: 'linear-gradient(135deg, #f093fb, #f5576c)' },
  biceps: { emoji: '💪', gradient: 'linear-gradient(135deg, #f093fb, #f5576c)' },
  triceps: { emoji: '💪', gradient: 'linear-gradient(135deg, #ff758c, #ff7eb3)' },
  core: { emoji: '🧘', gradient: 'linear-gradient(135deg, #30cfd0, #330867)' },
  abs: { emoji: '🧘', gradient: 'linear-gradient(135deg, #30cfd0, #330867)' },
  cardio: { emoji: '🏃', gradient: 'linear-gradient(135deg, #ff9966, #ff5e62)' },
  fullbody: { emoji: '🔥', gradient: 'linear-gradient(135deg, #f7971e, #ffd200)' }
};

const DEFAULT_STYLE: MuscleGroupStyle = {
  emoji: '🏆',
  gradient: 'linear-gradient(135deg, #a8a8a8, #6b6b6b)'
}

@Component({
  selector: 'app-exercises',
  imports: [],
  templateUrl: './exercises.html',
  styleUrl: './exercises.scss',
})

export class Exercises implements OnInit{

  exercises = signal<Exercise[]>([]);
  selectedExercise = signal<Exercise | null>(null);
  loading = signal (true);

  constructor(private exerciseService: ExerciseService){}

  ngOnInit(): void {
    this.exerciseService.getAllExercises().subscribe({
      next: (exercises) => {
        this.exercises.set(exercises);
        this.loading.set(false);
      },
      error: (error) => {
        console.error('EROARE EXERCISE:', error);
        this.loading.set(false);
      }
    });
  }

  openExercise(exercise: Exercise): void {
    this.selectedExercise.set(exercise);
  }

  closeExercise(): void {
    this.selectedExercise.set(null);
  }

  getStyle(muscleGroup: string): MuscleGroupStyle {
    const key = (muscleGroup || '').toLowerCase().replace(/\s+/g, '');
    return MUSCLE_GROUP_STYLES[key] ?? DEFAULT_STYLE;
  }
}
