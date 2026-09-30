-- ABC Hall Reservation System - database schema and sample data
-- MySQL 8.0
--
-- Usage:
--   mysql -u root -p < database/schema.sql
--
-- All customer and booking rows below are fictional sample data.

CREATE DATABASE IF NOT EXISTS `abcdb` DEFAULT CHARACTER SET utf8mb4;
USE `abcdb`;

SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------
-- Table: user (system users who log in)
-- ------------------------------------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_key` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(45) NOT NULL,
  `UserType` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `userCreateDate` varchar(45) NOT NULL,
  PRIMARY KEY (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user` VALUES
  (1,'2001','ADMIN','1212','C 19|05|2023  22:57:40'),
  (2,'2002','FRONT_DESK_USER','1212','C 19|05|2023  23:01:40');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `customers` VALUES
  (1,'199912345678','Kamal Perera','0711234567','kamal.perera@example.com',1,'C 30|05|2023  10:15:00'),
  (2,'200087654321','Nimali Silva','0722345678','nimali.silva@example.com',2,'C 30|05|2023  11:40:00'),
  (3,'199556781234','Ruwan Fernando','0773456789','ruwan.fernando@example.com',1,'C 30|05|2023  14:05:00');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `halls` VALUES
  (1,'01SD','Standard Hall','NON AC','25000.0','500','AVAILABLE','ADD DATE 30|05|2023','continue to BOOK or Maintenance'),
  (2,'02BQ','Benquet Hall','AC','30000.0','750','AVAILABLE','ADD DATE 30|05|2023','continue to BOOK or Maintenance'),
  (3,'03LX','Luxury Hall','AC','45000.0','1000','AVAILABLE','ADD DATE 30|05|2023','continue to BOOK or Maintenance'),
  (4,'04SD','Standard Hall','NON AC','50000.0','1000','AVAILABLE','ADD DATE 30|05|2023','continue to BOOK or Maintenance'),
  (5,'05BQ','Benquet Hall','NON AC','30000.0','800','AVAILABLE','ADD DATE 30|05|2023','continue to BOOK or Maintenance');

-- ------------------------------------------------------
-- Table: booking
-- ------------------------------------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `booking_key` int NOT NULL AUTO_INCREMENT,
  `cust_key` int NOT NULL,
  `user_key` int NOT NULL,
  `checkInDate` varchar(45) NOT NULL,
  `checkOutDate` varchar(45) NOT NULL,
  `numberOfDate` int NOT NULL,
  `specificDay` varchar(7) NOT NULL,
  `booking_Type` varchar(45) NOT NULL,
  `hall_key` int NOT NULL,
  `payment` double NOT NULL,
  PRIMARY KEY (`booking_key`),
  KEY `customer_key_idx` (`cust_key`),
  KEY `user_key_idx` (`user_key`),
  KEY `hall_key_idx` (`hall_key`),
  CONSTRAINT `cust_key` FOREIGN KEY (`cust_key`) REFERENCES `customers` (`cust_key`),
  CONSTRAINT `hall_key` FOREIGN KEY (`hall_key`) REFERENCES `halls` (`hall_key`),
  CONSTRAINT `user_key` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `booking` VALUES
  (1,1,1,'01-06-2023','03-06-2023',3,'1110000','A Continues Period',1,67500),
  (2,2,1,'30-05-2023','05-06-2023',6,'111101','A Continues Period',1,150000),
  (3,3,1,'28-06-2023','28-06-2023',1,'1000','A Given Date',1,22500),
  (4,2,2,'28-06-2023','30-06-2023',3,'111000','A Continues Period',2,90000);

SET FOREIGN_KEY_CHECKS = 1;
