CREATE TABLE workout_exercises(
    id UUID PRIMARY KEY,
    workout_id UUID NOT NULL,
    exercise_name VARCHAR(255) NOT NULL,
    sets INTEGER NOT NULL,
    reps INTEGER NOT NULL,
    weight DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_workout_exercises_workout
         FOREIGN KEY (workout_id)
         REFERENCES workouts(id)
         ON DELETE CASCADE
);

CREATE INDEX idx_workout_exercises_workout_id
    ON workout_exercises(workout_id);