-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--

CREATE SCHEMA `myguard` ;
USE `myguard`;


-- Host: 127.0.0.1    Database: myguard
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `counter`
--

DROP TABLE IF EXISTS `counter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counter` (
  `id` bigint NOT NULL,
  `count_hs_week` int DEFAULT NULL,
  `count_hs_weekend` int DEFAULT NULL,
  `count_on_call` int DEFAULT NULL,
  `month_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKslf9mpjmf5y4o1sin7qutq11b` (`month_id`),
  KEY `FKlfv7yoffmoa0v73q8numt468a` (`user_id`),
  CONSTRAINT `FKlfv7yoffmoa0v73q8numt468a` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKslf9mpjmf5y4o1sin7qutq11b` FOREIGN KEY (`month_id`) REFERENCES `month` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counter`
--

LOCK TABLES `counter` WRITE;
/*!40000 ALTER TABLE `counter` DISABLE KEYS */;
INSERT INTO `counter` VALUES (1,12,0,0,1,1),(2,0,24,1,1,6);
/*!40000 ALTER TABLE `counter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `counter_seq`
--

DROP TABLE IF EXISTS `counter_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counter_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counter_seq`
--

LOCK TABLES `counter_seq` WRITE;
/*!40000 ALTER TABLE `counter_seq` DISABLE KEYS */;
INSERT INTO `counter_seq` VALUES (101);
/*!40000 ALTER TABLE `counter_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `month`
--

DROP TABLE IF EXISTS `month`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `month` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `year` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `month`
--

LOCK TABLES `month` WRITE;
/*!40000 ALTER TABLE `month` DISABLE KEYS */;
INSERT INTO `month` VALUES (1,'June','WEEK',2023);
/*!40000 ALTER TABLE `month` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `month_seq`
--

DROP TABLE IF EXISTS `month_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `month_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `month_seq`
--

LOCK TABLES `month_seq` WRITE;
/*!40000 ALTER TABLE `month_seq` DISABLE KEYS */;
INSERT INTO `month_seq` VALUES (51);
/*!40000 ALTER TABLE `month_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `on_call`
--

DROP TABLE IF EXISTS `on_call`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `on_call` (
  `id` bigint NOT NULL,
  `duration` int NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `month_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5cm38xhckq4dvu0n8yf0guoep` (`month_id`),
  KEY `FKipclwolnck2vdjvqp6as5uulu` (`user_id`),
  CONSTRAINT `FK5cm38xhckq4dvu0n8yf0guoep` FOREIGN KEY (`month_id`) REFERENCES `month` (`id`),
  CONSTRAINT `FKipclwolnck2vdjvqp6as5uulu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `on_call`
--

LOCK TABLES `on_call` WRITE;
/*!40000 ALTER TABLE `on_call` DISABLE KEYS */;
INSERT INTO `on_call` VALUES (1,12,'2023-06-02 08:00:00.000000','night','2023-06-01 20:00:00.000000',1,1),(2,12,'2023-06-03 20:00:00.000000','day','2023-06-03 08:00:00.000000',1,6),(3,12,'2023-06-04 08:00:00.000000','night','2023-06-03 20:00:00.000000',1,6);
/*!40000 ALTER TABLE `on_call` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `on_call_seq`
--

DROP TABLE IF EXISTS `on_call_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `on_call_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `on_call_seq`
--

LOCK TABLES `on_call_seq` WRITE;
/*!40000 ALTER TABLE `on_call_seq` DISABLE KEYS */;
INSERT INTO `on_call_seq` VALUES (101);
/*!40000 ALTER TABLE `on_call_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unavailability`
--

DROP TABLE IF EXISTS `unavailability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unavailability` (
  `id` bigint NOT NULL,
  `duration` int NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `month_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo39e4xuvxs34jba05jr28syhd` (`month_id`),
  KEY `FK81vftju44ohw8d1d6ei18hj03` (`user_id`),
  CONSTRAINT `FK81vftju44ohw8d1d6ei18hj03` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKo39e4xuvxs34jba05jr28syhd` FOREIGN KEY (`month_id`) REFERENCES `month` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unavailability`
--

LOCK TABLES `unavailability` WRITE;
/*!40000 ALTER TABLE `unavailability` DISABLE KEYS */;
/*!40000 ALTER TABLE `unavailability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unavailability_seq`
--

DROP TABLE IF EXISTS `unavailability_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unavailability_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unavailability_seq`
--

LOCK TABLES `unavailability_seq` WRITE;
/*!40000 ALTER TABLE `unavailability_seq` DISABLE KEYS */;
INSERT INTO `unavailability_seq` VALUES (1);
/*!40000 ALTER TABLE `unavailability_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enrolment` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `personalid` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'john.doe@example.com','123456','Doe','John','$2a$10$aPIt9vbkOTE3lXwt31Gx5uCjxTV/y05j22GT7dlokthm4z0ZqDepm','35512874','USER','PSYCHOLOGY','john.doe'),(2,'psuarez69@gmail.com','234234','Suarez','Pedro','$2a$10$vLzxwNZ9A3t93Kx4Ki2EfuBaUfTi/USfw.T.sExN3irTD3v/2Fe9O','4100334344','USER','PSYCHOLOGY','pSuarez'),(3,'jane.smith@example.com','789012','Smith','Jane','$2a$10$x9onETLlukMCNviwwaawXO/trFaA0JrLyPsRzsM7n39CA4Xd4bZ0a','12345678','USER','PSYCHOLOGY','jane.smith'),(4,'alex.wilson@example.com','987654','Wilson','Alex','$2a$10$sfFzyK0ljD0TxdKBsLYX0uWAs53OLVhlEJkdHpMDpWelMWFlDtA2e','87654321','USER','PSYCHIATRY','alex.wilson'),(5,'sarah.johnson@example.com','456789','Johnson','Sarah','$2a$10$9c2mRPK201rbbx.llAzFdegJzBJcbulZnmQAb0fZ9zXMcb5hNYq7q','78901234','USER','SOCIAL_WORK','sarah.johnson'),(6,'michael.brown@example.com','345678','Brown','Michael','$2a$10$4sLEhoDMRhkikVfVzVnNHejyYvK5NHf74ziLd48WBjlKplgnSBYiG','56789012','USER','PSYCHIATRY','michael.brown');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (1);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-29 22:40:50
