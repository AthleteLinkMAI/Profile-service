CREATE TABLE user(
    id                UUID        PRIMARY KEY,
    nickname          VARCHAR(30) NOT NULL UNIQUE,
    name              VARCHAR(30),
    surname           VARCHAR(30),
    birthdate         DATE        NOT NULL,
    email             VARCHAR     NOT NULL UNIQUE,
    is_verified       BOOLEAN     NOT NULL DEFAULT FALSE,
    registration_date DATE        NOT NULL
);

CREATE TABLE ban(
    user_id UUID PRIMARY KEY REFERENCES user(id)
);

CREATE TABLE friend_request(
    user_id   UUID NOT NULL REFERENCES user(id),
    friend_id UUID NOT NULL REFERENCES user(id),
    PRIMARY KEY (user_id, friend_id),
    CHECK (user_id <> friend_id)
);

CREATE UNIQUE INDEX unique_friend_request_pair on friend_request(
    LEAST(user_id, friend_id),
    GREATEST(user_id, friend_id)
);

CREATE TABLE friend(
    user_id   UUID NOT NULL REFERENCES user(id),
    friend_id UUID NOT NULL REFERENCES user(id),
    PRIMARY KEY (user_id, friend_id),
    CHECK (user_id <> friend_id)
);

CREATE UNIQUE INDEX unique_friend_pair on friend(
    LEAST(user_id, friend_id),
    GREATEST(user_id, friend_id)
);