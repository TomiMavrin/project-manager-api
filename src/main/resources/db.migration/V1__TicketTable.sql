CREATE TABLE TICKETS (id UUID NOT NULL PRIMARY KEY, title VARCHAR(45) NOT NULL, description VARCHAR(255), priority INT);
CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(256) NOT NULL,
    name VARCHAR(50),
    enabled boolean
);