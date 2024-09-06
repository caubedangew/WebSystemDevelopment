-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: parkinglotdb
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `parkinglot_id` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_comment_parkinglotid` (`parkinglot_id`),
  KEY `fk_comment_user_id` (`user_id`),
  CONSTRAINT `fk_comment_parkinglotid` FOREIGN KEY (`parkinglot_id`) REFERENCES `parkinglot` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (8,1,10,'Bãi xe rộng rãi, thoáng mát','2024-09-03 00:09:10','2024-09-03 00:09:10'),(9,3,10,'Tuyệt vời','2024-09-03 00:10:49','2024-09-03 00:10:49'),(10,1,10,'Giá cả phải chăng, nhân viên nhiệt tình','2024-09-03 00:47:33','2024-09-03 00:47:33'),(13,6,18,'Hello','2024-09-06 00:36:01','2024-09-06 00:36:01'),(14,6,18,'Good Night','2024-09-06 00:36:53','2024-09-06 00:36:53'),(15,3,18,'Best Parking Lot','2024-09-06 00:37:27','2024-09-06 00:37:27');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkinglot`
--

DROP TABLE IF EXISTS `parkinglot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parkinglot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT (0),
  `price` double DEFAULT (0),
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkinglot`
--

LOCK TABLES `parkinglot` WRITE;
/*!40000 ALTER TABLE `parkinglot` DISABLE KEYS */;
INSERT INTO `parkinglot` VALUES (10,'3000 Huynh Tan Phat',20,15000,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1725590075/lvqc0g4o4f3eglcvlqh3.png'),(11,'123 Sydney',10,30000,NULL),(13,'2000 Nguyen Kiem, quan Go Vap',40,5000,NULL),(14,'4800 Vo Van Tan, quan 3, Tp.HCM',40,10000,NULL),(15,'2400 Ho Hao Hon, quan 1, Tp. HCM',40,20000,NULL),(18,'ABC',5,20000,'https://res.cloudinary.com/dxxwcby8l/image/upload/v1725063186/ly2gtwyupc13kbfmdwfh.png');
/*!40000 ALTER TABLE `parkinglot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingspace`
--

DROP TABLE IF EXISTS `parkingspace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parkingspace` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stt` int DEFAULT (0),
  `status` varchar(4) DEFAULT 'FREE',
  `parkinglot_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parkinglotid` (`parkinglot_id`),
  CONSTRAINT `fk_parkinglotid` FOREIGN KEY (`parkinglot_id`) REFERENCES `parkinglot` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingspace`
--

LOCK TABLES `parkingspace` WRITE;
/*!40000 ALTER TABLE `parkingspace` DISABLE KEYS */;
INSERT INTO `parkingspace` VALUES (1,1,'BUSY',10),(4,2,'FREE',10);
/*!40000 ALTER TABLE `parkingspace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `parkingspace_id` int NOT NULL,
  `time_interval` double DEFAULT '0',
  `total_amount` double DEFAULT '0',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_receipt_user_id` (`user_id`),
  KEY `fk_receipt_parkingspace_id` (`parkingspace_id`),
  CONSTRAINT `fk_receipt_parkingspace_id` FOREIGN KEY (`parkingspace_id`) REFERENCES `parkingspace` (`id`),
  CONSTRAINT `fk_receipt_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (5,1,1,5,75000,'2024-09-03 16:09:59','2024-09-03 16:09:59'),(6,3,1,2,30000,'2023-06-03 16:09:59','2024-09-03 16:10:15');
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(10) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) NOT NULL,
  `user_role` varchar(15) NOT NULL,
  `is_active` tinyint(1) DEFAULT (true),
  `isUsing2FA` tinyint(1) DEFAULT (false),
  `secret` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `email_UNIQUE` (`email`),
  KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Vu','Thien','admin@gmail.com',NULL,'admin','$2a$10$g/VHm3dA73z3bWXLiX9nMO9AeLZXoVV2l7ueqZIEmOxncr3bx7lwu','https://res.cloudinary.com/dn84ltxow/image/upload/v1724492944/ssod81tv3qut27rg4wzh.png','ROLE_ADMIN',NULL,0,NULL),(3,'ABC','ABC','abc@gmail.com',NULL,'abc','$2a$10$6yccLM88aWyd5u4EfrXl1eg8fOG/xjsgxE501EDo5pvSD5KRIYQwa','https://res.cloudinary.com/dn84ltxow/image/upload/v1724505013/k8jbn9qbx61sq4fagk5w.png','ROLE_USER',NULL,0,NULL),(6,'João','Souza Silva','teste@exemplo.us','3121286801','joaoss','$2a$10$v86j44qeIFmyKIHZMnfNYOQ7.vf1eQCkYCdLD4z.VMYl5rLB9HgpK','https://res.cloudinary.com/dxxwcby8l/image/upload/v1725557402/iq9sbftfpahyj7tlmsdc.png','ROLE_USER',NULL,NULL,NULL),(7,'Jon','Doe','test@example.us','5553428400','jondoe','$2a$10$vl0Bij1HQuGYaAf6JkTIqOsr7lhu3mZcY6gssAdCN0TXsS0GO7kc.','https://res.cloudinary.com/dxxwcby8l/image/upload/v1725558470/bujzngtb2cjsz6ygtpe9.png','ROLE_USER',NULL,NULL,NULL),(8,'Vu','Thien','abc@gmail.com','0123456789','thienvu','$2a$10$cVOPh3sR/6o1NcQkR7V2c.Slq6oS2OMXq2FLalJwpqflmibPODVCG','https://res.cloudinary.com/dxxwcby8l/image/upload/v1725558572/bfhp6cnztqd3npmxzbbv.png','ROLE_USER',NULL,NULL,NULL),(9,'xyz','xyz','abcd@gmail.com','0909009900','xyz','$2a$10$9mbHBWRymyLcfc8Smqe0fug3w2WzznHC9WwRqbvZWFOAamwpWbvW2','https://res.cloudinary.com/dxxwcby8l/image/upload/v1725590578/iiqad6dudokddxwxutlq.png','ROLE_USER',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-06 20:54:04
