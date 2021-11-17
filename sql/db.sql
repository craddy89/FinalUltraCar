create table car
(
    id          int auto_increment
        primary key,
    brand       varchar(20)          not null,
    name        varchar(20)          not null,
    car_class   varchar(1)           not null,
    color       varchar(20)          not null,
    price       int                  not null,
    photo       varchar(100)         not null,
    description text                 not null,
    active      tinyint(1) default 1 not null,
    constraint car_name_uindex
        unique (name)
);

create table user
(
    id       int auto_increment
        primary key,
    name     varchar(10)                           not null,
    surname  varchar(20)                           not null,
    phone    varchar(9)                            not null,
    password varchar(100)                          not null,
    active   tinyint(1)  default 1                 not null,
    reg_date datetime    default CURRENT_TIMESTAMP null,
    role     varchar(10) default 'User'            not null,
    money    int         default 0                 not null,
    constraint user_phone_uindex
        unique (phone)
);

create table history
(
    id              int auto_increment
        primary key,
    userID          int                              not null,
    carID           int                              not null,
    driver          tinyint(1)                       not null,
    days            int                              not null,
    price           int                              not null,
    rent_status     varchar(20) default 'Moderation' not null,
    date_start      datetime                         null,
    date_finish     datetime                         null,
    ban_description text                             null,
    constraint orders_car_id_fk
        foreign key (carID) references car (id),
    constraint orders_user_id_fk
        foreign key (userID) references user (id)
);

create table fine
(
    id          int auto_increment
        primary key,
    historyID   int         not null,
    userID      int         not null,
    carID       int         not null,
    damage      varchar(20) not null,
    damage_desc text        not null,
    sum         int         not null,
    constraint fine_history_id_fk
        foreign key (historyID) references history (id),
    constraint fines_car_id_fk
        foreign key (carID) references car (id),
    constraint fines_user_id_fk
        foreign key (userID) references user (id)
);

create table passport
(
    id         int auto_increment
        primary key,
    userID     int         not null,
    series     varchar(2)  not null,
    num        int         not null,
    who_made   varchar(20) not null,
    createDate varchar(20) not null,
    constraint passport_user_id_fk
        foreign key (userID) references user (id)
);

/*----------------==========INSERT DATA=========--------------------*/

/* -------------------------- CARS -------------------------- */

INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (2, 'BMW', '7 Series', 'E', 'black', 80, 'bmw2.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (3, 'Ford', 'Mustang B', 'S', 'blue', 180, 'mustang1.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (4, 'Ford', 'Mustang GT', 'S', 'red', 200, 'mustang2.png', 'New', 0);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (5, 'Ford', 'Mustang R', 'S', 'red', 180, 'mustang3.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (6, 'Nissan', 'GTR', 'S', 'orange', 200, 'nissan1.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (7, 'Porsche', '718 Cayman Y', 'S', 'yellow', 215, 'porsche1.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (8, 'Porsche', '718 Cayman R', 'S', 'red', 215, 'porsche2.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (9, 'Tesla', 'Model S', 'F', 'white', 170, 'tesla1.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (10, 'Mercedes', 'Gelenvagen', 'G', 'black', 190, 'mercedes1.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (11, 'Chevrolet', 'Camaro', 'S', 'red', 200, 'chevrolet1.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (12, 'Chevrolet', 'Camaro ZL1', 'S', 'red', 215, 'chevrolet2.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (13, 'VAZ', '2103', 'C', 'red', 15, 'vaz1.png', 'New', 1);
INSERT INTO BestCar.car (id, brand, name, car_class, color, price, photo, description, active) VALUES (14, 'Ferrari', '812 Superfast', 'S', 'grey', 300, 'ferrari1.png', 'New', 1);

/* -------------------------- USERS -------------------------- */

INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (26, 'Vlad', 'Yanitskyi', '990099969', 'BF19F64654543FB67E3B5C3214F7E054848F75B41C36115ECF264670C98551A7', 1, '2021-01-27 19:15:12', 'Admin', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (27, 'User', 'Test', '123456789', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 0, '2021-01-27 19:15:55', 'User', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (28, 'Max', 'Maximovich', '987654321', '8D23CF6C86E834A7AA6EDED54C26CE2BB2E74903538C61BDD5D2197997AB2F72', 1, '2021-01-27 21:25:04', 'User', 10284);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (29, 'Test', 'Moderator', '777777777', '8D23CF6C86E834A7AA6EDED54C26CE2BB2E74903538C61BDD5D2197997AB2F72', 1, '2021-01-28 17:04:26', 'Moderator', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (30, '123', '123', '000000000', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 1, '2021-01-28 17:54:38', 'User', 500);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (31, 'Name', 'Surname', '999999999', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 1, '2021-02-03 15:59:30', 'User', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (32, 'Moder', 'Moder', '444444444', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 1, '2021-02-03 17:29:05', 'Moderator', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (33, 'HashTest', 'Test', '121212121', '4C0D1D972C5BB0124CE2DC4FF2F168EF7C824E4115D8199BD89B305410BC3B87', 1, '2021-02-15 13:55:50', 'User', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (34, 'test', 'test', '123123123', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 1, '2021-02-16 19:15:29', 'User', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (35, 'Hash', 'Test', '090909090', 'EF92B778BAFE771E89245B89ECBC08A44A4E166C06659911881F383D4473E94F', 1, '2021-02-19 23:20:34', 'User', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (37, '123', '123', '123321123', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 0, '2021-02-20 01:07:18', 'Moderator', 0);
INSERT INTO BestCar.user (id, name, surname, phone, password, active, reg_date, role, money) VALUES (38, '123', '123', '456456456', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 1, '2021-02-20 01:18:10', 'User', 0);

/* -------------------------- PASSPORT -------------------------- */

INSERT INTO BestCar.passport (id, userID, series, num, who_made, createDate) VALUES (4, 30, 'PL', 777777, 'KH', '2017');
INSERT INTO BestCar.passport (id, userID, series, num, who_made, createDate) VALUES (6, 28, 'TT', 121212, 'Kiev', '2021-02-02');


/* -------------------------- HISTORY -------------------------- */

INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (1, 28, 3, 1, 2, 500, 'Ban', null, null, 'BANNED TEST');
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (2, 28, 8, 0, 3, 645, 'Returned', '2021-02-19 00:00:00', '2021-02-22 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (3, 28, 9, 1, 1, 240, 'Returned', '2021-02-12 00:00:00', '2021-02-13 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (4, 28, 8, 1, 2, 570, 'Returned', '2021-02-13 00:00:00', '2021-02-15 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (5, 28, 9, 1, 4, 960, 'Returned', '2021-02-19 00:00:00', '2021-02-23 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (6, 28, 11, 1, 2, 540, 'Ban', null, null, 'sorry');
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (7, 28, 11, 1, 3, 810, 'Returned', '2021-02-12 00:00:00', '2021-02-15 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (10, 28, 3, 1, 3, 750, 'Returned', '2021-02-12 00:00:00', '2021-02-15 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (11, 28, 5, 1, 3, 750, 'Returned', '2021-02-13 00:00:00', '2021-02-16 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (12, 30, 5, 1, 2, 500, 'Returned', '2021-02-13 00:00:00', '2021-02-15 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (14, 28, 3, 1, 2, 180, 'Returned', '2021-02-13 00:00:00', '2021-02-15 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (15, 28, 3, 1, 3, 180, 'Ban', null, null, 'isn''t active');
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (16, 28, 3, 0, 2, 360, 'Ban', null, null, '1');
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (19, 28, 4, 1, 2, 540, 'Returned', '2021-02-19 00:00:00', '2021-02-21 00:00:00', null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (20, 28, 3, 0, 1, 180, 'Ban', null, null, 'ыы');
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (21, 28, 2, 0, 2, 160, 'Moderation', null, null, null);
INSERT INTO BestCar.history (id, userID, carID, driver, days, price, rent_status, date_start, date_finish, ban_description) VALUES (22, 28, 3, 0, 2, 360, 'Moderation', null, null, null);