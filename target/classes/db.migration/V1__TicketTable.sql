
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
    owner_id UUID NOT NULL REFERENCES USERS(id),
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
    title VARCHAR(20) NOT NULL,
    subtitle VARCHAR(35),
    description VARCHAR(150),
    date_created TIMESTAMP DEFAULT current_timestamp,
    date_due TIMESTAMP,
    column_id UUID NOT NULL REFERENCES COLUMNS(id) ON DELETE CASCADE,
    created_by UUID NOT NULL REFERENCES USERS(id),
    assigned_to UUID REFERENCES USERS(id),
    color VARCHAR(7) NOT NULL,
    PRIMARY KEY (id)
);


INSERT INTO tickets (title,description,created_by) VALUES ('test', 'desc', '5f270bcc-62d1-419f-974d-b04ec15cc3be');

BEGIN;
CREATE TEMPORARY TABLE bid (id UUID) ON COMMIT DROP;
WITH rows AS (
    INSERT INTO boards (name, description) VALUES (?, ?) RETURNING id)
    INSERT INTO bid (id) SELECT id FROM rows;
    INSERT INTO users_boards (board_id, user_id) SELECT id, ? from bid;
    INSERT INTO columns (name, board_id) SELECT ?, id from bid;
    INSERT INTO columns (name, board_id) SELECT ?, id from bid;
    INSERT INTO columns (name, board_id) SELECT ?, id from bid;
COMMIT;

INSERT INTO TICKETS (column_id, title, description, created_by) VALUES('de559667-5ee8-484a-9179-0c12da97c3e4', 'test1', 'test1', '117df756-8a8f-4535-a4d1-0e69beda098c') RETURNING id,title,description,date_created,column_id,created_by;

UPDATE tickets SET column_id='72ace237-d5af-42b0-b749-6ffd9d64756e' WHERE id ='a2d10649-9152-472c-8c0b-34e8c3003822';

-----DOCKER RESET IMAGE AND RUN
--    cd Repositories/Java/project-manager-api/ && docker stop api && docker rm api && docker run -d -p 8080:8080 --name api api

