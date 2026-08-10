CREATE TABLE workout_sets (
                              id UUID PRIMARY KEY,
                              workout_exercise_id UUID NOT NULL,
                              set_number INTEGER NOT NULL,
                              reps INTEGER NOT NULL,
                              weight DOUBLE PRECISION NOT NULL,
                              created_at TIMESTAMP NOT NULL,

                              CONSTRAINT fk_workout_sets_workout_exercise
                                  FOREIGN KEY (workout_exercise_id)
                                      REFERENCES workout_exercises(id)
                                      ON DELETE CASCADE
);

CREATE INDEX idx_workout_sets_workout_exercise_id
    ON workout_sets(workout_exercise_id);