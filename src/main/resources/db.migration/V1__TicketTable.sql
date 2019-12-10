
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

SELECT id,name,description FROM boards INNER JOIN users_boards ON (users_boards.board_id = boards.id) WHERE users_boards.user_id=?;

CREATE TABLE COLUMNS (
    id UUID DEFAULT uuid_generate_v4 (),
    name VARCHAR(25),
    color VARCHAR(20),
    board_id UUID NOT NULL REFERENCES BOARDS(id),
    PRIMARY KEY (id)
);




CREATE TABLE TICKETS (
    id UUID DEFAULT uuid_generate_v4 (),
    title VARCHAR(50) NOT NULL,
    description VARCHAR(150),
    date_created TIMESTAMP DEFAULT current_timestamp,
    column_id UUID NOT NULL REFERENCES COLUMNS(id),
    created_by UUID NOT NULL REFERENCES USERS(id),
    PRIMARY KEY (id)
);


INSERT INTO tickets (title,description,created_by) VALUES ('test', 'desc', '5f270bcc-62d1-419f-974d-b04ec15cc3be');
