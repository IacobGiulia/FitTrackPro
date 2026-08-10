CREATE TABLE workouts(
    id UUID primary key,
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    started_at TIMESTAMP NOT NULL,
    finished_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_workouts_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE

);

CREATE INDEX idx_workouts_user_id
    ON workouts(user_id);