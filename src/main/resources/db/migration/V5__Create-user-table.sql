CREATE TABLE USERS (
    ID SERIAL PRIMARY KEY,
    NAME TEXT NOT NULL,
    EMAIL TEXT NOT NULL,
    PASSWORD TEXT NOT NULL,
    CREATED_AT DATE NOT NULL
);