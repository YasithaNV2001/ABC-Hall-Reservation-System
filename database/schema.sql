-- ABC Hall Reservation System - database schema and sample data
-- MySQL 8.0 (originally exported 25 May 2023)
--
-- Usage:
--   mysql -u root -p < database/schema.sql

CREATE DATABASE IF NOT EXISTS `abcdb` DEFAULT CHARACTER SET utf8mb4;
USE `abcdb`;

SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------
-- Table: user
-- ------------------------------------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_key` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(45) NOT NULL,
  `UserType` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `userCreateDate` varchar(45) NOT NULL,
  PRIMARY KEY (`user_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `user` VALUES (1,'2001','ADMIN','1212','C 19|05|2023  22:57:40'),(2,'2002','FRONT_DESK_USER','1212','C 19|05|2023  23:01:40');

-- ------------------------------------------------------
-- Table: customers
-- ------------------------------------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `cust_key` int NOT NULL AUTO_INCREMENT,
  `customer_nic` varchar(45) NOT NULL,
  `cust_name` varchar(45) NOT NULL,
  `telephone_number` varchar(45) NOT NULL,
  `gmail` varchar(45) NOT NULL,
  `user_key` int NOT NULL,
  `customerCreateDate` varchar(45) NOT NULL,
  PRIMARY KEY (`cust_key`),
  KEY `user_key` (`user_key`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `customers` VALUES (1,'213','wqe','1231231','ewwq',1,'U ADMIN 20|05|2023  19:25:19'),(2,'231','weqgd','213','ewq',2,'U FRONT_DESK_USER 20|05|2023  19:30:13');

-- ------------------------------------------------------
-- Table: halls
-- ------------------------------------------------------
DROP TABLE IF EXISTS `halls`;
CREATE TABLE `halls` (
  `hall_key` int NOT NULL AUTO_INCREMENT,
  `hallId` varchar(45) NOT NULL,
  `hallType` varchar(45) NOT NULL,
  `acType` varchar(45) NOT NULL,
  `pricePerDay` varchar(45) NOT NULL,
  `hallCap` varchar(45) NOT NULL,
  `hallState` varchar(90) NOT NULL,
  `inDate` varchar(90) NOT NULL,
  `outDate` varchar(90) NOT NULL,
  PRIMARY KEY (`hall_key`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `halls` VALUES (1,'2001H','Luxury Hall','AC','75000/-','1000','MAINTENANCE','Maintenance ADD 22|05|2023','Maintenance Out 31|05|2023'),(2,'2002H','Standard Hall','NON AC','50000','1500','MAINTENANCE','Maintenance ADD 22|05|2023','Maintenance Out 23|05|2023'),(3,'2000LH','Luxury Hall','NON AC','120000','1000','AVAILABLE','ADD DATE 23|05|2023','continue to BOOK or Maintenance'),(4,'1999LH','Luxury Hall','AC','30000','2000','MAINTENANCE','Maintenance ADD 24|05|2023','Maintenance Out 26|05|2023'),(5,'1890BH','Benquet Hall','AC','125000','450','AVAILABLE','ADD DATE 23|05|2023','continue to BOOK or Maintenance');

-- ------------------------------------------------------
-- Table: booking
-- ------------------------------------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `book_key` int NOT NULL AUTO_INCREMENT,
  `booking_id` varchar(45) NOT NULL,
  `customer_key` int NOT NULL,
  `user_key` int NOT NULL,
  `checkInDate` varchar(45) NOT NULL,
  `checkOutDate` varchar(45) NOT NULL,
  `numberOfDate` int NOT NULL,
  `specificDay` varchar(45) NOT NULL,
  PRIMARY KEY (`book_key`),
  KEY `customer_key_idx` (`customer_key`),
  KEY `user_key_idx` (`user_key`),
  CONSTRAINT `customer_key` FOREIGN KEY (`customer_key`) REFERENCES `customers` (`cust_key`),
  CONSTRAINT `user_key` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
