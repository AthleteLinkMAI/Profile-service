CREATE TYPE user_role AS ENUM('ADMIN', 'MODERATOR', 'USER');

CREATE TABLE users(
    id                UUID        PRIMARY KEY,
    role              user_role   NOT NULL,
    nickname          VARCHAR(30) NOT NULL UNIQUE,
    name              VARCHAR(30),
    surname           VARCHAR(30),
    birthdate         DATE        NOT NULL,
    email             VARCHAR     NOT NULL UNIQUE,
    is_verified       BOOLEAN     NOT NULL DEFAULT FALSE,
    registration_date DATE        NOT NULL
);

CREATE TABLE ban(
    user_id UUID PRIMARY KEY REFERENCES users(id)
);

CREATE TABLE friend_request(
    user_id   UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    friend_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, friend_id),
    CHECK (user_id <> friend_id)
);

CREATE UNIQUE INDEX unique_friend_request_pair on friend_request(
    LEAST(user_id, friend_id),
    GREATEST(user_id, friend_id)
);

CREATE INDEX friend_request_friend_id_idx ON friend_request(friend_id);

CREATE TABLE friend(
    user_id   UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    friend_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, friend_id),
    CHECK (user_id <> friend_id)
);

CREATE UNIQUE INDEX unique_friend_pair on friend(
    LEAST(user_id, friend_id),
    GREATEST(user_id, friend_id)
);

CREATE INDEX friend_friend_id_idx ON friend(friend_id);