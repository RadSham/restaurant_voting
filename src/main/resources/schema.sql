DROP TABLE IF EXISTS DISH;
DROP TABLE IF EXISTS MENU;
DROP TABLE IF EXISTS VOTE;
DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS RESTAURANT;

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

CREATE TABLE USER_ROLES
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE RESTAURANT
(
    id    INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name  VARCHAR(255) NOT NULL
);


CREATE TABLE MENU
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR(255)            NOT NULL,
    date          TIMESTAMP DEFAULT now() NOT NULL,
    restaurant_id INTEGER                 NOT NULL,
    CONSTRAINT menu_idx UNIQUE (id, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE DISH
(
    id      INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    price   DOUBLE       NOT NULL,
    menu_id INTEGER      NOT NULL,
    CONSTRAINT dish_idx UNIQUE (id, menu_id),
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE TABLE VOTE
(
    id            INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id       INTEGER                 NOT NULL,
    date          TIMESTAMP DEFAULT now() NOT NULL,
    restaurant_id INTEGER                 NOT NULL,
    CONSTRAINT vote_date_idx UNIQUE (id, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);