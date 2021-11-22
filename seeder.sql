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


INSERT INTO food_shelf_life(name, shelf_life_fridge_days, shelf_life_freezer_days, img_url, food_group_id) VALUES
('bread',7,180,'https://thumbs.dreamstime.com/z/cartoon-bread-vector-illustration-isolated-white-background-147370927.jpg', 1),

('carrot',21, 365, 'https://cdn3.vectorstock.com/i/1000x1000/77/52/carrot-icon-cartoon-style-on-white-background-vector-8177752.jpg', 2),

('milk', 7, 180,'https://www.meijer.com/content/dam/meijer/product/0004/12/5010/20/0004125010200_2_A1C1_1200.png',3 ),

('turkey', 5, 365, 'https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F9%2F2021%2F11%2F09%2FTurkey-Prices-FT-BLOG1121.jpg', 4),

('apple pie', 4, 120, 'https://520553-1656414-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/Slice-of-apple-pie.jpg', 5),

('bagels', 7, 120, 'bagel_image',1),

('blueberries', 10, 300, 'blueberry_url', 2),

('butter', 60, 240, 'butter_url', 3),

('steak', 5, 120, 'steak_url', 4),

('ice cream', 21, 120, 'icecream_url', 5),

('oatmeal', 6, 180, 'oatmeal_url', 1),
('grapes', 14,300, 'grapes_url', 2),

('yogurt' ,7, 90, 'yogurt_url', 3),
('raw chicken', 2, 270, 'raw_chicken_url', 4),

('doughnuts', 7, 90, 'doughnuts_url', 5);

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
