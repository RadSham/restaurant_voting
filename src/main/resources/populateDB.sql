DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM restaurant;
DELETE FROM vote;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO dish (name, price, restauratn_id)
VALUES ('Grilled Chicken Sandwich', 2.5, 1),
       ('Spaghetti', 5, 2),
       ('French Fries', 4, 3),
       ('Tossed Salad', 1.75, 1),
       ('Apple Pie', 1.5, 2),
       ('House Wine', 3, 3),
       ('New York Steak', 6, 1),
       ('Garlic Bread', 0.5, 2),
       ('Pepperoni Pizza', 3.5, 3),
       ('Fish and Chips', 4, 1);

INSERT INTO menu (name, date, restaurant_id)
VALUES ('AlphaMenu1', CURRENT_DATE, 1),
       ('AlphaMenu2', CURRENT_DATE, 1),
       ('BetaMenu1', CURRENT_DATE, 2),
       ('BetaMenu2', CURRENT_DATE, 2),
       ('GammaMenu1', CURRENT_DATE, 3),
       ('GammaMenu2', CURRENT_DATE, 3);

INSERT INTO restaurant (name)
VALUES ('ALPHA'),
       ('BETA'),
       ('GAMMA');

INSERT INTO vote (date, user_id, restaurant_id)
VALUES (CURRENT_DATE, 1, 1),
       (CURRENT_DATE, 2, 1),
       (DATEADD('DAY', 3, CURRENT_DATE), 1, 2),
       (DATEADD('DAY', 2, CURRENT_DATE), 2, 2),
       (CURRENT_DATE, 2, 3),
       (DATEADD('DAY', 2, CURRENT_DATE), 1, 3);

