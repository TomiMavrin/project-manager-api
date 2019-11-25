CREATE TABLE TICKETS (id UUID NOT NULL PRIMARY KEY, title VARCHAR(45) NOT NULL, description VARCHAR(255), priority INT);

CREATE TABLE USERS (
    id UUID NOT NULL,
    email VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    name VARCHAR(50),
    enabled boolean,
    PRIMARY KEY (id, email)
);

CREATE TABLE AUTHORITIES (
    email VARCHAR(20) NOT NULL,
    authority VARCHAR(20)
);