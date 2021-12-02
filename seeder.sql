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
('Grains'),
('Fruits and Vegetables'),
('Dairy'),
('Meats and Poultry'),
('Desserts');


INSERT INTO food_shelf_life(name, shelf_life_fridge_days, shelf_life_freezer_days, food_group_id) VALUES
('Bread',7,180, 1),

('Carrots',21, 365,  2),

('Milk', 7, 180,3 ),

('Turkey', 5, 365,  4),

('Apple Pie', 4, 120,  5),

('Bagels', 7, 120, 1),

('Berries', 5, 300, 2),

('Butter', 60, 240,  3),

('Steak', 5, 120, 4),

('Ice Cream', 21, 120,  5),

('Oatmeal', 6, 180,  1),
('Grapes', 14,300,  2),

('Yogurt' ,7, 90,  3),
('Poultry', 2, 270,  4),

('Doughnuts', 7, 90,  5);


insert into users(id, email, password, phone_number, username) VALUES
(1,'email@email.com', 'yo','123','bob');

insert into fridges(id, name) VALUE (1,'home');

insert into fridge_user(user_id, fridge_id) VALUES (1,1);

INSERT INTO foods(name, date_added,expiration_date, is_in_freezer, fridge_id,food_shelf_life_id) VALUES
('milk', Now(),NOW(),false, 1,3);

INSERT INTO food_shelf_life(name, shelf_life_fridge_days, shelf_life_freezer_days, food_group_id) VALUES
('Eggs', 35, 365, 3),('Mayonnaise', 60, 90, 3),('Ground Beef', 2, 120, 4),('Hot Dogs', 14, 60, 4),('Lunch Meat', 14, 60, 4),('Soup/Stew',4, 90, 2),('Bacon',7, 31, 4),('Sausage', 7, 60, 4),('Lean Fish', 3, 240, 4),('Beans', 10, 30, 2),('Canned Ham', 270, 360, 4),('Cooked Ham',7,60, 4),('Lamb',2,120,4),('Ground Pork',2,120,4),('Pork Chops',5,180,4),('Meat Leftovers',4,90,4),('Gravy',2,120,4),('Fried Chicken',4,120,4),('Fatty Fish',2,90,4),('Cooked Fish',4,120,4),('Smoked Fish',14,60,4),('Fresh Shrimp/Crawfish',2,180,4),('Pizza',4,60,4),('Corn',180,365,2),('Oats',120,240,2),('Rice',120,240,2),('Guacamole',2,120,2),('Apples',30,300,2),('Avocado',10,300,2),('Bananas',9,90,2),('Canned Fruit',360,720,2),('Cherries',10,240,2),('Dried Fruit',360,720,2),('Cranberry Sauce',10,31,2),('Figs',7,240,2),('Grapefruit',35,240,2),('Kiwi',21,310,2),('Lemon/Limes',50,240,2),('Mangoes',14,240,2),('Olives',360,360,2),('Oranges',60,240,2),('Peaches',5,180,2),('Tomatoes',14,240,2),('Frozen Vegetables',240,300,2),('Pasta',7,240,1),('Noodles',7,240,1),('Hard Cheese',90,210,3),('Cottage Cheese',10,90,3),('Soft Cheese',14,180,3),('Cream Cheese',28,60,3),('Tofu',5,150,4),('Barbecue Sauce',240,360,4),('Cake',4,120,5),('Chocolate',180,240,5),('Cookie Dough',5,180,5),('Cookies',21,180,5);

