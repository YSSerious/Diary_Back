CREATE TABLE `role`
(
    `id`   BIGINT NOT NULL AUTO_INCREMENT,
    `name` varchar(20) UNIQUE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user`
(
    `id`       varchar(255)       NOT NULL,
    `username` varchar(20) UNIQUE NOT NULL,
    `password` varchar(120)       NOT NULL,
    `email`    varchar(50) UNIQUE NOT NULL,
    PRIMARY KEY (`id`)
) AUTO_INCREMENT = 100000;

CREATE TABLE `user_role`
(
    `user_id` varchar(255) NOT NULL,
    `role_id` BIGINT NOT NULL,
    CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

CREATE TABLE `record`
(
    `id`           varchar(255) NOT NULL,
    `zonedatetime` VARCHAR(255) NOT NULL,
    `user_id`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_user_id_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) AUTO_INCREMENT = 100000;

CREATE TABLE `waterrecord`
(
    `id`     varchar(255) NOT NULL,
    `volume` INT          NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_water_record_record_id` FOREIGN KEY (`id`) REFERENCES `record` (`id`)
);

CREATE TABLE `weightrecord`
(
    `id`     varchar(255) NOT NULL,
    `volume` DOUBLE       NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_weight_record_record_id` FOREIGN KEY (`id`) REFERENCES `record` (`id`)
);

CREATE TABLE `gymrecord`
(
    `id` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_gym_record_record_id` FOREIGN KEY (`id`) REFERENCES `record` (`id`)
);

CREATE TABLE `bodyresponserecord`
(
    `id`           varchar(255) NOT NULL,
    `generalstate` varchar(255) NOT NULL,
    `nose`         varchar(255) NOT NULL,
    `eyes`         varchar(255) NOT NULL,
    `chin`         varchar(255) NOT NULL,
    `forehead`     varchar(255) NOT NULL,
    `note`         TEXT,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_bodyresponse_record_record_id` FOREIGN KEY (`id`) REFERENCES `record` (`id`)
);

CREATE TABLE `foodcategory`
(
    `id`   BIGINT NOT NULL AUTO_INCREMENT,
    `name` varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `food`
(
    `id`              BIGINT NOT NULL AUTO_INCREMENT,
    `name`            varchar(255)         NOT NULL,
    `ingrams`         BOOLEAN DEFAULT TRUE NOT NULL,
    `kilocalories`    double               NOT NULL,
    `proteins`        double               NOT NULL,
    `carbohydrates`   double               NOT NULL,
    `fats`            double               NOT NULL,
    `foodHealthy`     varchar(255)         NOT NULL,
    `foodcategory_id` BIGINT NOT NULL,
    `note`            TEXT,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_food_category_id_foodcategory_id` FOREIGN KEY (`foodcategory_id`) REFERENCES `foodcategory` (`id`)
);

CREATE TABLE `foodrecord`
(
    `id` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_food_record_record_id` FOREIGN KEY (`id`) REFERENCES `record` (`id`)
);

CREATE TABLE `foodvolume`
(
    `id`            varchar(255) NOT NULL,
    `volume`        double       NOT NULL,
    `foodrecord_id` varchar(255) NOT NULL,
    `food_id`       BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_foodrecord_id_foodvolume_foodrecord_id` FOREIGN KEY (`foodrecord_id`) REFERENCES `foodrecord` (`id`),
    CONSTRAINT `FK_food_id_foodvolume_food_id` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) AUTO_INCREMENT = 100000;

CREATE TABLE `pill`
(
    `id`              BIGINT NOT NULL AUTO_INCREMENT,
    `name`            varchar(255)         NOT NULL,
    `ingrams`         BOOLEAN DEFAULT FALSE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `pillrecord`
(
    `id` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_pill_record_record_id` FOREIGN KEY (`id`) REFERENCES `record` (`id`)
);

CREATE TABLE `pillvolume`
(
    `id`            varchar(255) NOT NULL,
    `volume`        double       NOT NULL,
    `pillrecord_id` varchar(255) NOT NULL,
    `pill_id`       BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_pillrecord_id_pillvolume_pillrecord_id` FOREIGN KEY (`pillrecord_id`) REFERENCES `pillrecord` (`id`),
    CONSTRAINT `FK_pill_id_pillvolume_pill_id` FOREIGN KEY (`pill_id`) REFERENCES `pill` (`id`)
) AUTO_INCREMENT = 100000;

INSERT INTO user(id, username, password, email)
VALUES ('2d3deea7-bb02-4a72-a891-1f7e5d9fe617',	'oleksii', '$2a$10$zkuhBkkbFcwcWj6dQ7cpsuMqYPy/WjtWEkLk4R2HHCyh0dxeKdAEu',	'kozakaleksei@gmail.com');

INSERT INTO role(id, name)
VALUES (1, 'ROLE_USER');
INSERT INTO role(id, name)
VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_role(user_id, role_id)
VALUES ('2d3deea7-bb02-4a72-a891-1f7e5d9fe617',	1);
INSERT INTO user_role(user_id, role_id)
VALUES ('2d3deea7-bb02-4a72-a891-1f7e5d9fe617',	2);

insert into `pill` (id, name)
values (1, 'Pancreatin 250 mg');

insert into `foodcategory` (id, name)
values (1, 'Carbohydrate');
insert into `foodcategory` (id, name)
values (2, 'Protein');
insert into `foodcategory` (id, name)
values (3, 'Sugar');
insert into `foodcategory` (id, name)
values (4, 'Other');
insert into `foodcategory` (id, name)
values (5, 'Fruit');
insert into `foodcategory` (id, name)
values (6, 'Vegetable');
insert into `foodcategory` (id, name)
values (7, 'Fast food');
insert into `foodcategory` (id, name)
values (8, 'Puzata Hata');
insert into `foodcategory` (id, name)
values (9, 'Unknown');
insert into foodcategory (id, name)
values (10, "McDonald's");
insert into foodcategory (id, name)
values (11, 'Alcohol');
insert into foodcategory (id, name)
values (12, 'Evrasia');
insert into foodcategory (id, name)
values (13, "Domino's");

insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (1, 'Buckwheat', 329, 12.6, 68, 2.6, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (2, 'Macaroni', 356, 12.8, 70.9, 2, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (3, 'Chicken fillet', 165, 31, 0, 3.6, 'GOOD', 2);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (4, 'Chicken egg', false, 77, 6.2, 0.3, 5.6, 'GOOD', 2);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (5, 'Steamed chicken cutlet', false, 341, 9, 7, 30, 'NORMAL', 8);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (6, 'Potato', 77, 2, 17, 0.1, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (7, 'Soy sauce', 73, 2.1, 16.2, 0, 'NOT_GOOD', 4, 'Default portion: 18 g;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (8, 'Chumak mango sauce', 163, 0.1, 37.9, 1.1, 'NOT_GOOD', 4, 'Default portion: 20 g;');
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (9, 'Mandarin', false, 37, 0.57, 9.34, 0.22, 'NORMAL', 5);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (10, 'Unknown (1000)', false, 1000, 0, 0, 0, 'BAD', 9);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (11, 'Rice (Long-grain)', 378, 7.6, 75.2, 1, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (12, 'Meat dumplings (Galya baluvana, turkey)', 144, 11.47, 18.56, 5.84, 'NORMAL', 7);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (13, 'Dumplings with potato', false, 348, 12, 60, 12, 'NORMAL', 8, '6 pieces portion');
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (14, 'Puree potatoes', false, 284, 5, 43, 10, 'NORMAL', 8);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (15, 'Candy Korovka (Roshen)', false, 51.7, 0.38, 11.2, 0.6, 'BAD', 3);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (16, 'Solyanka', false, 158, 11.1, 8, 9.11, 'NOT_GOOD', 8);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (17, 'McChicken Bacon', false, 461, 24, 46, 20, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (18, 'Big Tasty', false, 828, 44, 50, 50, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (19, 'French Fries (Big)', false, 434, 5.2, 54, 21, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (20, 'Cheese sauce', false, 88, 0.5, 1, 9, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (21, 'Cocoa', false, 210, 8.1, 33.7, 4.5, 'BAD', 10);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (22, 'Cognac', 240, 0, 1.5, 0, 'DRINK', 11);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (23, 'Pie (s. caramel/chocolate)', false, 328, 3.7, 40, 17, 'BAD', 10);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (24, 'Onion (green)', 19, 1.3, 4.6, 0, 'NORMAL', 6);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (25, 'Radish', 19, 1.2, 3.4, 0.1, 'GOOD', 6);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (26, 'Hotategai nigiri', false, 68, 5, 13, 5, 'NORMAL', 12);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (27, 'Sake nіgіri', false, 77, 5.2, 15, 5.2, 'NORMAL', 12);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (28, 'Pork teriyaki', 321, 15.9, 2.4, 27.5, 'NOT_GOOD', 8);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (29, 'Zrazy', 123, 8, 9.6, 6.1, 'NOT_GOOD', 8, 'One piece: 107 g;');
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (30, 'Shake (strawberry)', false, 415, 11, 74.5, 7.6, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (31, 'Jam Menz & Gasser (Orange)', false, 70, 1.4, 18.2, 0, 'BAD', 4);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (32, 'Corn', 96, 3.4, 18.6, 1.5, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (33, 'White aerated chocolate (Millennium)', 564, 6.9, 54.9, 35.2, 'BAD', 3, '90.0;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (34, 'Whiskey', 240, 0, 0.03, 0, 'DRINK', 11);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (35, 'Hawaiian pizza', 191.3, 10, 23.8, 5.3, 'BAD', 13);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (36, 'Country pizza', 253.8, 8.6, 23.8, 13.4, 'BAD', 13);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (37, 'Parmesan bites', 282.4, 11, 35.6, 10.1, 'BAD', 13);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (38, 'Jam (Strawberry)', 275, 0.25, 68, 0.12, 'BAD', 3);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (39, 'Oil (Olive)', 898, 0, 0, 99.3, 'NORMAL', 4, 'One soup spoon: 17 g;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (40, 'Snickers', 483, 8.6, 60.7, 22.6, 'BAD', 3, 'One piece: 37.5 g;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (41, 'Lion', 493, 3.8, 66.7, 23.3, 'BAD', 3, 'One piece: 30 g;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (42, 'Onion', 47, 1.4, 10.4, 0, 'NORMAL', 6);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (43, 'Sausages (veal, tm.Saltovskie)', 316, 10, 0, 35, 'NORMAL', 2);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (44, 'Salmon', 219, 20.8, 0, 15.1, 'NORMAL', 2);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (45, 'Kinder Chocolate', 566, 8.7, 53.5, 35, 'BAD', 3);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (46, 'Pringles (crab)', 566, 4.9, 51, 31, 'BAD', 4);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (47, 'Donut vanilla', false, 374, 4.1, 36, 23, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (48, 'Royal cheeseburger (double)', false, 749, 52, 36, 44, 'BAD', 10);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (49, 'Chicken thighs', 185, 21.3, 0.1, 11.1, 'NORMAL', 2);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (50, 'Fat', 768.65, 2.76, 0.99, 84.27, 'NORMAL', 4);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (51, 'Oatmeal', 318.6, 11.86, 58.57, 5.08, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (52, 'Ice cream (Three bears)', 254, 4.6, 25.8, 14.1, 'BAD', 3);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (53, 'Unknown (1500)', false, 1500, 0, 0, 0, 'BAD', 9);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (54, 'Unknown (500)', false, 1500, 0, 0, 0, 'BAD', 9);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (55, 'Pizza BBQ', 218, 9.7, 26.3, 7.9, 'BAD', 13);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (56, 'Sausages (beef tm. Select)', 207, 12, 1.4, 17, 'NORMAL', 2, 'One sausage: 95 g;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (57, 'Turkey fillet', 111, 24.6, 0, 0.7, 'GOOD', 2);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (58, 'French fries with cheese sauce and bacon', false, 504, 12, 45, 29, 'BAD', 10);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (59, 'Boom choc', 554, 16, 37.7, 37.7, 'BAD', 3);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (60, 'Cucumber', 15, 0.8, 2.8, 0.1, 'GOOD', 6);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (61, 'Banana', 96, 1.5, 21, 0.5, 'NORMAL', 5, 'One medium piece: 100 g;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (62, 'Couscous with vegetables', 166, 6, 30.7, 1.1, 'GOOD', 8);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (63, 'Shrimp', false, 319, 16.9, 28.3, 15, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (64, 'Curry sauce', false, 42, 0.1, 9.5, 0.3, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (65, 'Chicken wings (1 piece)', false, 292, 32, 4.1, 16, 'BAD', 10);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (66, 'Red wine (dry)', 68, 0.2, 0.3, 0, 'DRINK', 11);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (67, 'Salted caramel', 486, 2.6, 103.7, 51.3, 'BAD', 3);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (68, 'Oatmeal cookies', 437, 6.5, 71.8, 14.4, 'BAD', 3);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (69, 'Cappuccino', 30, 1.5, 2.5, 1.5, 'NOT_GOOD', 4, 'From Aroma kava or Mcdonalds, or from somewhere else... 100ml portion.');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (70, 'Cabbage (kapusta)', 25, 1.3, 6, 0.1, 'GOOD', 6);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (71, 'Tomato', 24, 1.1, 3.8, 0.2, 'GOOD', 6);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (72, 'Bread (Vidensky)', 275, 13.8, 45.4, 3.9, 'NORMAL', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id, note)
values (73, 'Chumak sweet chili sauce', 148, 0.4, 37.5, 0.1, 'NOT_GOOD', 4, 'Default portion: 20 g;');
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodcategory_id)
values (74, 'Sausages (Rimskaya)', 471.2, 23, 0.3, 42, 'NORMAL', 2);
