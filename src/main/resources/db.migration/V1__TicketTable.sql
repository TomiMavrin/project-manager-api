
CREATE TABLE USERS (
    id uuid DEFAULT uuid_generate_v4 (),
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    name VARCHAR(50),
    enabled boolean,
    PRIMARY KEY (id)
);

CREATE TABLE AUTHORITIES (
    email VARCHAR(50) NOT NULL,
    authority VARCHAR(20)
);

CREATE TABLE BOARDS (
    id uuid DEFAULT uuid_generate_v4 (),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(256),
    PRIMARY KEY (id)
);

CREATE TABLE USERS_BOARDS (
    user_id UUID NOT NULL REFERENCES USERS(id),
    board_id UUID NOT NULL REFERENCES BOARDS(id),
    PRIMARY KEY (user_id, board_id)
);
