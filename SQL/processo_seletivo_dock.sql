-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: localhost    Database: processo_dock
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conta` (
  `idConta` int NOT NULL AUTO_INCREMENT,
  `idPessoa` int NOT NULL,
  `saldo` double NOT NULL,
  `limiteSaqueDiario` double NOT NULL,
  `flagAtivo` tinyint(1) NOT NULL,
  `tipoConta` int NOT NULL,
  `dataCriacao` datetime NOT NULL,
  PRIMARY KEY (`idConta`),
  KEY `conta_FK_1` (`idPessoa`),
  CONSTRAINT `conta_FK_1` FOREIGN KEY (`idPessoa`) REFERENCES `pessoa` (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
INSERT INTO `conta` VALUES (2,1,101.22,1560.55,0,1,'2021-02-14 00:00:00'),(3,1,2060.88,2000,1,1,'2021-02-14 00:00:00');
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `idPessoa` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `dataNascimento` datetime NOT NULL,
  PRIMARY KEY (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Everton Mendonça','00000000000','1987-09-22 00:00:00'),(2,'Deise Luz','00000000000','1987-06-09 00:00:00'),(3,'Deise Luz','00000000000','1987-06-09 00:00:00'),(4,'Deise Luz','00000000000','1987-06-09 00:00:00'),(5,'Deise Luz','00000000000','1987-06-09 00:00:00'),(6,'Deise Luz','00000000000','1987-06-09 00:00:00'),(7,'Joao Silva','13446454588','1987-06-09 00:00:00');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacao`
--

DROP TABLE IF EXISTS `transacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transacao` (
  `idTransacao` int NOT NULL AUTO_INCREMENT,
  `idConta` int DEFAULT NULL,
  `valor` double NOT NULL,
  `dataTransacao` datetime NOT NULL,
  `tipoTransacao` int NOT NULL,
  PRIMARY KEY (`idTransacao`),
  KEY `transacao_FK` (`idConta`),
  CONSTRAINT `transacao_FK` FOREIGN KEY (`idConta`) REFERENCES `conta` (`idConta`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacao`
--

LOCK TABLES `transacao` WRITE;
/*!40000 ALTER TABLE `transacao` DISABLE KEYS */;
INSERT INTO `transacao` VALUES (3,2,100.78,'2021-02-14 00:00:00',1),(4,2,0.22,'2021-02-14 00:00:00',1),(5,2,0.22,'2021-02-14 00:00:00',1),(6,3,0.22,'2021-02-14 00:00:00',1),(7,3,10.22,'2021-02-14 00:00:00',1),(8,3,10,'2021-02-14 00:00:00',1),(9,3,100.22,'2021-02-14 00:00:00',1),(10,3,15,'2021-02-14 00:00:00',1),(11,3,15,'2021-02-14 00:00:00',1),(12,3,10000.22,'2021-02-14 00:00:00',1),(13,3,1500,'2021-02-14 00:00:00',1),(14,3,1500,'2021-02-14 00:00:00',1),(15,3,2500,'2021-02-14 00:00:00',2),(16,3,2500,'2021-02-14 00:00:00',2),(17,3,10,'2021-02-14 00:00:00',2);
/*!40000 ALTER TABLE `transacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-14 17:58:49
