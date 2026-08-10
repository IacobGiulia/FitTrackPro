CREATE TABLE exercises (
    id UUID PRIMARY KEY ,
    name VARCHAR(255) NOT NULL UNIQUE,
    muscle_group VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL
);