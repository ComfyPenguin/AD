-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: cine2
-- ------------------------------------------------------
-- Server version	8.0.18

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

DROP DATABASE IF EXISTS CineTest;

CREATE DATABASE CineTest;

USE CineTest;

--
-- Table structure for table `Director`
--
DROP TABLE IF EXISTS `Director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Director` (
  `Nombre` varchar(255) NOT NULL,
  `Anyo_Nacimiento` int(11) NOT NULL,
  `idDirector` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idDirector`),
  KEY `id` (`idDirector`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Director`
--

LOCK TABLES `Director` WRITE;
/*!40000 ALTER TABLE `Director` DISABLE KEYS */;
INSERT INTO `Director` VALUES ('George Lucas',1944,1),('JJ Abrams',1966,2),('Rian Johnson',1973,4),('Ron Howard',1954,5),('Gareth Edwards',1975,6);
/*!40000 ALTER TABLE `Director` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `Pelis`
--

DROP TABLE IF EXISTS `Pelis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pelis` (
  `idPelis` int(11) NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(45) NOT NULL,
  `Anyo` varchar(45) NOT NULL,
  PRIMARY KEY (`idPelis`),
  KEY `idPelis` (`idPelis`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pelis`
--

LOCK TABLES `Pelis` WRITE;
/*!40000 ALTER TABLE `Pelis` DISABLE KEYS */;
INSERT INTO `Pelis` VALUES (1,'La Amenaza Fantasma','1999'),(2,'El Ataque de los Clones','2002'),(3,'La Venganza de los Sith','2005'),(4,'Una Nueva Esperanza','1977'),(5,'El Imperio Contraataca','1980'),(6,'El Retorno del Jedi','1984'),(7,'El Despertar de la Fuerza','2015'),(8,'Los Últimos Jedi','2017'),(9,'Rogue One: A Star Wars Story','2016'),(10,'Solo: A Star Wars Story','2018'),(11,'El Ascenso de Skywalker','2019');
/*!40000 ALTER TABLE `Pelis` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `Director_Peli`
--

DROP TABLE IF EXISTS `Director_Peli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Director_Peli` (
  `Peli` int(11) NOT NULL,
  `Director` int(11) NOT NULL,
  PRIMARY KEY (`Peli`,`Director`),
  KEY `Director` (`Director`),
  CONSTRAINT `Director_Peli_ibfk_1` FOREIGN KEY (`Director`) REFERENCES `Director` (`idDirector`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Director_Peli_ibfk_2` FOREIGN KEY (`Peli`) REFERENCES `Pelis` (`idPelis`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Director_Peli`
--

LOCK TABLES `Director_Peli` WRITE;
/*!40000 ALTER TABLE `Director_Peli` DISABLE KEYS */;
INSERT INTO `Director_Peli` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,2),(11,2),(8,4),(10,5),(9,6);
/*!40000 ALTER TABLE `Director_Peli` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-05  8:20:37
