ALTER TABLE workout_exercises
    ADD COLUMN exercise_id UUID;

ALTER TABLE workout_exercises
    ADD CONSTRAINT fk_workout_exercises_exercise
        FOREIGN KEY (exercise_id)
            REFERENCES exercises(id)
            ON DELETE RESTRICT;

CREATE INDEX idx_workout_exercises_exercise_id
    ON workout_exercises(exercise_id);