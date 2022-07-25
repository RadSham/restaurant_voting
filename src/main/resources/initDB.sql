DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS vote;

CREATE TABLE USERS
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOL      DEFAULT TRUE  NOT NULL,
    CONSTRAINT users_email_idx UNIQUE (email)
);

CREATE TABLE user_roles
(

);

CREATE TABLE dish
(

);

CREATE TABLE menu
(

);

CREATE TABLE restaurant
(

);

CREATE TABLE vote
(

);