import { TestBed } from '@angular/core/testing';

import { WorkoutExercise } from './workout-exercise';

describe('WorkoutExercise', () => {
  let service: WorkoutExercise;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkoutExercise);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
