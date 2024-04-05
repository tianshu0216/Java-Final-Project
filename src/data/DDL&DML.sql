DROP DATABASE IF EXISTS FWRP;

CREATE DATABASE FWRP;

USE FWRP;

create table user(
    id INT primary key auto_increment,
    name VARCHAR(45),
    email varchar(30) NOT NULL,
    password varchar(30) NOT NULL,
    isSubscribe boolean NOT NULL,
    userType ENUM('retailer', 'consumer', 'charitable organization') NOT NULL,
    location ENUM('Alberta', 'British Columbia', 'Manitoba', 'New Brunswick', 'Newfoundland and Labrador', 'Nova Scotia', 'Ontario', 'Prince Edward Island', 'Quebec', 'Saskatchewan') NOT NULL
);

INSERT INTO User (name, email, password, isSubscribe, userType, location)
VALUES ('Liu', 'Liu@example.com', '123456',TRUE, 'retailer', 'British Columbia'),
       ('Harvey','Harvey@gmail.com','22222',TRUE,'retailer','Nova Scotia'),
       ('Xie', 'Xie@example.com', '123456', TRUE,'consumer', 'Ontario'),
       ('Huang', 'Huang@example.com', '123456',FALSE, 'charitable organization', 'British Columbia'),
       ('Julia', 'julia.jones@example.com', 'juliasPass123',FALSE, 'consumer', 'Quebec'),
       ('Jack', 'jack.white@example.com', 'jacksPass123',FALSE, 'charitable organization', 'Ontario');

create table Food(
    id INT primary key auto_increment,
    name varchar(45),
    inventory int NOT NULL,
    price decimal(10,2),
    expirationDate DATE,
    demand int NOT NULL,
    isDonation boolean NOT NULL,
    retailer_id int,
    FOREIGN KEY (retailer_id) REFERENCES user(id)
);

insert into food(name, inventory, price, expirationDate,demand, isDonation,  retailer_id) value("cookie", 100, 2.5, '2024-10-30',50, false,  1);
insert into food(name, inventory, price, expirationDate,demand, isDonation,  retailer_id) value("potato", 500, 6.7, '2024-04-30',490, false,  1);
insert into food(name, inventory, price, expirationDate,demand, isDonation,  retailer_id) value("onion", 100, 3.25, '2024-4-10',50,false,  2);
insert into food(name, inventory, price, expirationDate,demand, isDonation,  retailer_id) value("ham", 300, 10.25,  '2024-03-20',200,true,  2);

create table transaction(
    id INT primary key auto_increment,
    order_id int,
    food_id int,
    quantity int,
    purchaser_id int,
    transaction_time datetime,
    FOREIGN KEY (food_id) REFERENCES food(id),
    FOREIGN KEY (purchaser_id) REFERENCES user(id)
);


insert into transaction(food_id, quantity, purchaser_id) value(1, 50, 2);
insert into transaction(food_id, quantity, purchaser_id) value(2, 20, 2);
insert into transaction(food_id, quantity, purchaser_id) value(3, 30, 3);
insert into transaction(food_id, quantity, purchaser_id) value(4, 50, 3);

select * from user;
select * from food;
select * from transaction;

insert into transaction(order_id, food_id, quantity, purchaser_id,`transaction_time`) value(12345,1, 50, 2, now());
insert into transaction(order_id,food_id, quantity, purchaser_id,`transaction_time`) value(12345,2, 20, 2, now());
insert into transaction(order_id,food_id, quantity, purchaser_id,`transaction_time`) value(12345,3, 30, 3, now());
insert into transaction(order_id,food_id, quantity, purchaser_id,`transaction_time`) value(12345,4, 50, 3, now());

