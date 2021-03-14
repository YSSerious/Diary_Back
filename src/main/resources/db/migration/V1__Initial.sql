CREATE TABLE `record`
(
    `id`           varchar(255) NOT NULL,
    `zonedatetime` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_bodyresponse_record_record_id` FOREIGN KEY (`id`) REFERENCES `record` (`id`)
);

CREATE TABLE `foodcategory`
(
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name`           varchar(255) UNIQUE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `food`
(
    `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name`           varchar(255)         NOT NULL,
    `ingrams`        BOOLEAN DEFAULT TRUE NOT NULL,
    `defaultportion` double,
    `kilocalories`   double               NOT NULL,
    `proteins`       double               NOT NULL,
    `carbohydrates`  double               NOT NULL,
    `fats`           double               NOT NULL,
    `foodHealthy`    varchar(255)         NOT NULL,
    `foodcategory_id`       BIGINT(20) NOT NULL,
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
    `volume`        INT          NOT NULL,
    `foodrecord_id` varchar(255) NOT NULL,
    `food_id`       BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_foodrecord_id_foodvolume_foodrecord_id` FOREIGN KEY (`foodrecord_id`) REFERENCES `foodrecord` (`id`),
    CONSTRAINT `FK_food_id_foodvolume_food_id` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) AUTO_INCREMENT = 100000;

insert into `foodcategory` (id, name)
values (1, 'Carbohydrate');
insert into `foodcategory` (id, name)
values (2, 'Protein');
insert into `foodcategory` (id, name)
values (3, 'Sugar');
insert into `foodcategory` (id, name)
values (4, 'Sauce');
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
insert into foodcategory (id, name) values (10,"McDonald's");

insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (1, 'Buckwheat', 329, 12.6, 68, 2.6, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (2, 'Macaroni', 356, 12.8, 70.9, 2, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (3, 'Chicken fillet', 165, 31, 0, 3.6, 'GOOD', 2);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (4, 'Chicken egg', false, 77, 6.2, 0.3, 5.6, 'GOOD', 2);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (5, 'Steamed chicken cutlet', false, 341, 9, 7, 30, 'NORMAL', 8);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (6, 'Potato', 77, 2, 17, 0.1, 'GOOD', 1);
insert into `food` (id, name, defaultportion, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (7, 'Soy sauce', 18, 73, 2.1, 16.2, 0, 'NOT_GOOD', 4);
insert into `food` (id, name, defaultportion, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (8, 'Chumak mango sauce', 20, 163, 0.1, 37.9, 1.1, 'NOT_GOOD', 4);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (9, 'Mandarin', false, 37, 0.57, 9.34, 0.22, 'NORMAL', 5);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (10, 'Banquet', false, 1000, 0, 0, 0, 'BAD', 9);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (11, 'Rice (Long-grain)', 378, 7.6, 75.2, 1, 'GOOD', 1);
insert into `food` (id, name, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (12, 'Meat dumplings (Galya baluvana, turkey)', 144, 11.47, 18.56, 5.84, 'NORMAL', 7);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (13, 'Dumplings with potato', false, 348, 12, 60, 12, 'NORMAL', 8);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (14, 'Puree potatoes', false, 284, 5, 43, 10, 'NORMAL', 8);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (15, 'Candy Korovka (Roshen)', false, 51.7, 0.38, 11.2, 0.6, 'BAD', 3);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (16, 'Solyanka', false, 158, 11.1, 8, 9.11, 'NOT_GOOD', 8);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (17, 'McChicken Bacon', false, 461, 24, 46, 20, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (18, 'Big Tasty', false, 828, 44, 50, 50, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (19, 'French Fries (Big)', false, 434, 5.2, 54, 21, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (20, 'Cheese sauce', false, 88, 0.5, 1, 9, 'BAD', 10);
insert into `food` (id, name, inGrams, kilocalories, proteins, carbohydrates, fats, foodHealthy, foodtype_id)
values (21, 'Cocoa', false, 210, 8.1, 33.7, 4.5, 'BAD', 10);
