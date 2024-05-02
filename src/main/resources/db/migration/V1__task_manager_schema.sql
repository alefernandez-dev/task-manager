CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS tasks (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    creation_date DATE,
    due_date DATE,
    completion_date DATE,
    task_status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS users_tasks (
    user_id UUID,
    task_id UUID,
    PRIMARY KEY (user_id, task_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE
);
