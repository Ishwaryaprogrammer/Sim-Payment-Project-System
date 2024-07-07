/* Query to create the database spps*/
CREATE DATABASE `hel` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

/* Query to create table users */
CREATE TABLE `user` (
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Query to create table category */
CREATE TABLE `category` (
  `category_id` int NOT NULL,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Query to create table customer */
CREATE TABLE `customer` (
  `customer_id` varchar(15) NOT NULL,
  `customer_name` varchar(100) NOT NULL,
  `category_id` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `type` enum('Customer','Partner','Agent') DEFAULT NULL,
  `contact_details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `category_id` (`category_id`),
  KEY `idx_customer_id` (`customer_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Query to create table project */
CREATE TABLE `project` (
  `ProjectID` varchar(20) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `customer_id` varchar(15) DEFAULT NULL,
  `EndClientDetail` varchar(50) DEFAULT NULL,
  `EndClientCountry` varchar(10) DEFAULT NULL,
  `PONo` varchar(20) DEFAULT NULL,
  `PODate` date DEFAULT NULL,
  `POInitialValue` float(10,2) DEFAULT NULL,
  `GSTPercent` float DEFAULT NULL,
  `PORev1Value` float(10,2) DEFAULT NULL,
  `PORev1Date` date DEFAULT NULL,
  `PORev2Value` float(10,2) DEFAULT NULL,
  `PORev2Date` date DEFAULT NULL,
  `PORev3Value` float(10,2) DEFAULT NULL,
  `PORev3Date` date DEFAULT NULL,
  `PORev4Value` float(10,2) DEFAULT NULL,
  `PORev4Date` date DEFAULT NULL,
  `Currency` varchar(5) DEFAULT NULL,
  `PaymentTerms` varchar(50) DEFAULT NULL,
  `ProjectLead` varchar(20) DEFAULT NULL,
  `ExpectedCompletionDate` date DEFAULT NULL,
  `ActualCompletionDate` date DEFAULT NULL,
  `CompletionPercent` float DEFAULT NULL,
  KEY `idx_project_id` (`ProjectID`),
  KEY `fk_custoid` (`customer_id`),
  CONSTRAINT `fk_custoid` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE,
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `CHK_CompletionPercent` CHECK (((`CompletionPercent` >= 0) and (`CompletionPercent` <= 100))),
  CONSTRAINT `CHK_GSTPercent` CHECK (((`GSTPercent` >= 0) and (`GSTPercent` <= 100)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/* Query to create table invoice */
CREATE TABLE `invoice` (
  `customer_id` varchar(20) DEFAULT NULL,
  `ProjectID` varchar(20) DEFAULT NULL,
  `po_no` varchar(20) DEFAULT NULL,
  `invoice_no` varchar(20) NOT NULL,
  `invoice_date` date DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `invoice_type` enum('Milestone','Expense') DEFAULT NULL,
  `inv_amount_in_euro` decimal(12,2) DEFAULT NULL,
  `inv_amount_in_usd` decimal(12,2) DEFAULT NULL,
  `inv_amount_in_inr` decimal(12,2) DEFAULT NULL,
  `gst_amount` decimal(12,2) DEFAULT NULL,
  `tds_deducted` decimal(12,2) DEFAULT NULL,
  `retention_amount` decimal(12,2) DEFAULT NULL,
  `amount_received` decimal(12,2) DEFAULT NULL,
  `amount_received_in_inr` decimal(12,2) DEFAULT NULL,
  `received_date` date DEFAULT NULL,
  `firc_details` varchar(50) DEFAULT NULL,
  `delay` int GENERATED ALWAYS AS ((to_days(`received_date`) - to_days(`invoice_date`))) VIRTUAL,
  PRIMARY KEY (`invoice_no`),
  KEY `fk_project_id` (`ProjectID`),
  KEY `fk_customer_id` (`customer_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_project_id` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ProjectID`) ON DELETE CASCADE,
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

