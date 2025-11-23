CREATE DATABASE  IF NOT EXISTS `Instituto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `Instituto`;
-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: Instituto
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

--
-- Table structure for table `Alumno`
--

DROP TABLE IF EXISTS `Alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Alumno` (
  `idAlumno` int(11) NOT NULL AUTO_INCREMENT,
  `ampa` tinyint(1) DEFAULT NULL,
  `clase` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAlumno`),
  KEY `fk_Alu_Cla` (`clase`),
  CONSTRAINT `fk_Alu_Cla` FOREIGN KEY (`clase`) REFERENCES `Clase` (`idClase`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_Alu_Per` FOREIGN KEY (`idAlumno`) REFERENCES `Persona` (`idPersona`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alumno`
--

LOCK TABLES `Alumno` WRITE;
/*!40000 ALTER TABLE `Alumno` DISABLE KEYS */;
INSERT INTO `Alumno` VALUES (1,1,3),(2,0,3),(5,1,4),(6,1,4);
/*!40000 ALTER TABLE `Alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Clase`
--

DROP TABLE IF EXISTS `Clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Clase` (
  `idClase` int(11) NOT NULL AUTO_INCREMENT,
  `curso` int(11) NOT NULL,
  `grupo` char(1) NOT NULL,
  `nivel` varchar(3) NOT NULL,
  `idTutor` int(11) NOT NULL,
  PRIMARY KEY (`idClase`),
  UNIQUE KEY `idTutor` (`idTutor`),
  CONSTRAINT `fk_Cla_Pro` FOREIGN KEY (`idTutor`) REFERENCES `Profesor` (`idProfesor`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Clase`
--

LOCK TABLES `Clase` WRITE;
/*!40000 ALTER TABLE `Clase` DISABLE KEYS */;
INSERT INTO `Clase` VALUES (3,1,'C','BTX',3),(4,2,'A','ESO',4),(5,1,'B','BTX',7);
/*!40000 ALTER TABLE `Clase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Docencia`
--

DROP TABLE IF EXISTS `Docencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Docencia` (
  `idAlumno` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  PRIMARY KEY (`idAlumno`,`idProfesor`),
  KEY `fk_Doc_Pro` (`idProfesor`),
  CONSTRAINT `fk_Doc_Alu` FOREIGN KEY (`idAlumno`) REFERENCES `Alumno` (`idAlumno`) ON UPDATE CASCADE,
  CONSTRAINT `fk_Doc_Pro` FOREIGN KEY (`idProfesor`) REFERENCES `Profesor` (`idProfesor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Docencia`
--

LOCK TABLES `Docencia` WRITE;
/*!40000 ALTER TABLE `Docencia` DISABLE KEYS */;
INSERT INTO `Docencia` VALUES (1,3),(6,3),(1,4),(2,4),(6,4),(1,7),(2,7),(5,7),(6,7);
/*!40000 ALTER TABLE `Docencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Persona`
--

DROP TABLE IF EXISTS `Persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Persona`
--

LOCK TABLES `Persona` WRITE;
/*!40000 ALTER TABLE `Persona` DISABLE KEYS */;
INSERT INTO `Persona` VALUES (1,'Juan Andres','Pérez Gómez',22),(2,'Ana','Mira Pons',21),(3,'Jaime','Nilo Pla',34),(4,'Andres','Pons Piera',38),(5,'Eva','Velló Garcia',20),(6,'Maria','Viñó Pons',20),(7,'Javi','Montes Llanos',40),(8,'Isabel','Grau Sainz',30),(9,'Manolo','Gimenez Estruch',19),(10,'Manuel','Bo Agut',38);
/*!40000 ALTER TABLE `Persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Profesor`
--

DROP TABLE IF EXISTS `Profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Profesor` (
  `idProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `departmento` varchar(20) NOT NULL,
  `jefeDepartamento` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  CONSTRAINT `fk_Pro_Per` FOREIGN KEY (`idProfesor`) REFERENCES `Persona` (`idPersona`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Profesor`
--

LOCK TABLES `Profesor` WRITE;
/*!40000 ALTER TABLE `Profesor` DISABLE KEYS */;
INSERT INTO `Profesor` VALUES (3,'Informática',1),(4,'Matematicas',0),(7,'Informática',0);
/*!40000 ALTER TABLE `Profesor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-30 11:00:03
