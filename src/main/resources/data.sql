DELETE
FROM dish;
DELETE
FROM menu;
DELETE
FROM vote;
DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM restaurant;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('User2', 'user2@yandex.ru', '{noop}password2');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3);

INSERT INTO restaurant (name)
VALUES ('ALPHA'),
       ('BETA'),
       ('GAMMA');

INSERT INTO menu (name, date, restaurant_id)
VALUES ('AlphaMenu', CURRENT_DATE, 1),
       ('BetaMenu', CURRENT_DATE, 2),
       ('GammaMenu', CURRENT_DATE, 3);

INSERT INTO dish (name, price, restaurant_id,MENU_ID)
VALUES ('Albanian soup', 2.5, 1, 1),
       ('Albanian tea', 0.5, 1, 1),
       ('Albanian meat', 5, 1, null),
       ('Brazilian soup', 2, 2, null),
       ('Brazilian tea', 1, 2, 2),
       ('Brazilian meat', 6, 2, 2),
       ('Germans soup', 3, 3, 3),
       ('Germans tea', 1.5, 3, null),
       ('Germans meat', 7, 3, 3);

INSERT INTO vote (date, user_id, RESTAURANT_ID)
VALUES (DATEADD('DAY', -3, CURRENT_DATE), 1, 3),
       (DATEADD('DAY', -3, CURRENT_DATE), 2, 2),
       (DATEADD('DAY', -3, CURRENT_DATE), 3, 1),
       (DATEADD('DAY', -2, CURRENT_DATE), 1, 2),
       (DATEADD('DAY', -2, CURRENT_DATE), 2, 1),
       (DATEADD('DAY', -2, CURRENT_DATE), 3, 3),
       (DATEADD('DAY', -1, CURRENT_DATE), 1, 1),
       (DATEADD('DAY', -1, CURRENT_DATE), 2, 3),
       (DATEADD('DAY', -1, CURRENT_DATE), 3, 2),
       (CURRENT_DATE, 3, 1),
       (CURRENT_DATE, 1, 1);

