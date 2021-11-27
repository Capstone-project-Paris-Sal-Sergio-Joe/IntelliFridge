CREATE DATABASE IF NOT EXISTS intellifridge_db;

CREATE USER intellifridge_user@localhost IDENTIFIED BY 'codeup';
GRANT ALL ON intellifridge_db.* TO intellifridge_user@localhost;

use intellifridge_db;

DROP TABLE if exists food_group;
DROP TABLE if exists food_shelf_life;

create table if not exists food_group
(
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) not null,
    PRIMARY KEY (id)
);





create table if not exists food_shelf_life
(
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) not null,
    shelf_life_fridge_days int not null,
    shelf_life_freezer_days int not null,
    img_url VARCHAR(200) not null,
    food_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (food_id) REFERENCES food_group(id)
);

INSERT INTO food_group(name) VALUES
('grains'),
('fruits and vegitables'),
('dairy'),
('meats and poultry'),
('desserts');


INSERT INTO food_shelf_life(name, shelf_life_fridge_days, shelf_life_freezer_days, food_group_id) VALUES
('bread',7,180, 1),

('carrot',21, 365,  2),

('milk', 7, 180,3 ),

('turkey', 5, 365,  4),

('apple pie', 4, 120,  5),

('bagels', 7, 120, 1),

('blueberries', 10, 300, 2),

('butter', 60, 240,  3),

('steak', 5, 120, 4),

('ice cream', 21, 120,  5),

('oatmeal', 6, 180,  1),
('grapes', 14,300,  2),

('yogurt' ,7, 90,  3),
('raw chicken', 2, 270,  4),

('doughnuts', 7, 90,  5);


insert into users(id, email, password, phone_number, username) VALUES
(1,'email@email.com', 'yo','123','bob');

insert into fridges(id, name) VALUE (1,'home');

insert into fridge_user(user_id, fridge_id) VALUES (1,1);

INSERT INTO foods(name, date_added,expiration_date, is_in_freezer, fridge_id,food_shelf_life_id) VALUES
                                                                                                     ('milk', Now(),NOW(),false, 1,3),
                                                                                                     ('eggs', Now(),NOW(),false, 1,3),
                                                                                                     ('bread', Now(),NOW(),false, 1,3),
                                                                                                     ('apples', Now(),NOW(),false, 1,3),
                                                                                                     ('lettuce', Now(),NOW(),false, 1,3)
