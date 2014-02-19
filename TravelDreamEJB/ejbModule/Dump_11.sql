CREATE DATABASE  IF NOT EXISTS `traveldreamdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `traveldreamdb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: traveldreamdb
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `amico`
--

DROP TABLE IF EXISTS `amico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amico` (
  `IDAMICO` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDAMICO`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amico`
--

LOCK TABLES `amico` WRITE;
/*!40000 ALTER TABLE `amico` DISABLE KEYS */;
/*!40000 ALTER TABLE `amico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `componente`
--

DROP TABLE IF EXISTS `componente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `componente` (
  `CODICECOMPONENTE` int(11) NOT NULL,
  `COSTO` int(11) DEFAULT NULL,
  `DATAFINEVALIDITA` date DEFAULT NULL,
  `DATAINIZIOVALIDITA` date DEFAULT NULL,
  `DESCRIZIONE` varchar(255) DEFAULT NULL,
  `LUOGO` varchar(255) DEFAULT NULL,
  `TIPOLOGIA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CODICECOMPONENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `componente`
--

LOCK TABLES `componente` WRITE;
/*!40000 ALTER TABLE `componente` DISABLE KEYS */;
/*!40000 ALTER TABLE `componente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `componente_disponibilitaperdata`
--

DROP TABLE IF EXISTS `componente_disponibilitaperdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `componente_disponibilitaperdata` (
  `Componente_CODICECOMPONENTE` int(11) NOT NULL,
  `DATA` date NOT NULL,
  `CODICECOMPONENTE` int(11) NOT NULL,
  PRIMARY KEY (`Componente_CODICECOMPONENTE`,`DATA`,`CODICECOMPONENTE`),
  KEY `FK_COMPONENTE_DISPONIBILITAPERDATA_DATA` (`DATA`,`CODICECOMPONENTE`),
  CONSTRAINT `FK_COMPONENTE_DISPONIBILITAPERDATA_DATA` FOREIGN KEY (`DATA`, `CODICECOMPONENTE`) REFERENCES `disponibilitaperdata` (`DATA`, `CODICECOMPONENTE`),
  CONSTRAINT `CMPNNTEDISPONIBILITAPERDATACmpnnteCODICECOMPONENTE` FOREIGN KEY (`Componente_CODICECOMPONENTE`) REFERENCES `componente` (`CODICECOMPONENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `componente_disponibilitaperdata`
--

LOCK TABLES `componente_disponibilitaperdata` WRITE;
/*!40000 ALTER TABLE `componente_disponibilitaperdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `componente_disponibilitaperdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilitaperdata`
--

DROP TABLE IF EXISTS `disponibilitaperdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disponibilitaperdata` (
  `DATA` date NOT NULL,
  `CODICECOMPONENTE` int(11) NOT NULL,
  `DISPONIBILITA` int(11) DEFAULT NULL,
  PRIMARY KEY (`DATA`,`CODICECOMPONENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilitaperdata`
--

LOCK TABLES `disponibilitaperdata` WRITE;
/*!40000 ALTER TABLE `disponibilitaperdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `disponibilitaperdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invito`
--

DROP TABLE IF EXISTS `invito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invito` (
  `IDINVITO` bigint(20) NOT NULL,
  `DATA` date DEFAULT NULL,
  `EMAILDESTINATARIO` varchar(255) DEFAULT NULL,
  `EMAILMITTENTE` varchar(255) DEFAULT NULL,
  `STATO` tinyint(1) DEFAULT '0',
  `idPacchettoPersonalizzato` bigint(20) DEFAULT NULL,
  `idPacchetto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDINVITO`),
  KEY `FK_INVITO_idPacchettoPersonalizzato` (`idPacchettoPersonalizzato`,`idPacchetto`),
  CONSTRAINT `FK_INVITO_idPacchettoPersonalizzato` FOREIGN KEY (`idPacchettoPersonalizzato`, `idPacchetto`) REFERENCES `pacchetto` (`IDPACCHETTOPERSONALIZZATO`, `IDPACCHETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invito`
--

LOCK TABLES `invito` WRITE;
/*!40000 ALTER TABLE `invito` DISABLE KEYS */;
/*!40000 ALTER TABLE `invito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacchetto`
--

DROP TABLE IF EXISTS `pacchetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacchetto` (
  `IDPACCHETTOPERSONALIZZATO` bigint(20) NOT NULL,
  `IDPACCHETTO` bigint(20) NOT NULL,
  `DTYPE` varchar(31) DEFAULT NULL,
  `COSTO` int(11) DEFAULT NULL,
  `DATAFINEVALIDITA` date DEFAULT NULL,
  `DATAINIZIOVALIDITA` date DEFAULT NULL,
  `DESCRIZIONE` varchar(255) DEFAULT NULL,
  `DESTINAZIONE` varchar(255) DEFAULT NULL,
  `ETICHETTA` varchar(255) DEFAULT NULL,
  `SCONTO` int(11) DEFAULT NULL,
  `DATADIPARTENZA` date DEFAULT NULL,
  `DATADIRITORNO` date DEFAULT NULL,
  `EMAILUTENTE` varchar(255) DEFAULT NULL,
  `STATO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDPACCHETTOPERSONALIZZATO`,`IDPACCHETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacchetto`
--

LOCK TABLES `pacchetto` WRITE;
/*!40000 ALTER TABLE `pacchetto` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacchetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacchetto_componenti`
--

DROP TABLE IF EXISTS `pacchetto_componenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacchetto_componenti` (
  `codiceComponente` int(11) NOT NULL,
  `idPacchetto` bigint(20) NOT NULL,
  `idPacchettoPersonalizzato` bigint(20) NOT NULL,
  PRIMARY KEY (`codiceComponente`,`idPacchetto`,`idPacchettoPersonalizzato`),
  KEY `FK_Pacchetto_componenti_idPacchettoPersonalizzato` (`idPacchettoPersonalizzato`,`idPacchetto`),
  CONSTRAINT `FK_Pacchetto_componenti_codiceComponente` FOREIGN KEY (`codiceComponente`) REFERENCES `componente` (`CODICECOMPONENTE`),
  CONSTRAINT `FK_Pacchetto_componenti_idPacchettoPersonalizzato` FOREIGN KEY (`idPacchettoPersonalizzato`, `idPacchetto`) REFERENCES `pacchetto` (`IDPACCHETTOPERSONALIZZATO`, `IDPACCHETTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacchetto_componenti`
--

LOCK TABLES `pacchetto_componenti` WRITE;
/*!40000 ALTER TABLE `pacchetto_componenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacchetto_componenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacchetto_componentiselezionati`
--

DROP TABLE IF EXISTS `pacchetto_componentiselezionati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacchetto_componentiselezionati` (
  `codiceComponente` int(11) NOT NULL,
  `idPacchetto` bigint(20) NOT NULL,
  `idPacchettoPersonalizzato` bigint(20) NOT NULL,
  PRIMARY KEY (`codiceComponente`,`idPacchetto`,`idPacchettoPersonalizzato`),
  KEY `PcchttocomponentiSelezionatidPcchttoPersonalizzato` (`idPacchettoPersonalizzato`,`idPacchetto`),
  CONSTRAINT `PcchttocomponentiSelezionatidPcchttoPersonalizzato` FOREIGN KEY (`idPacchettoPersonalizzato`, `idPacchetto`) REFERENCES `pacchetto` (`IDPACCHETTOPERSONALIZZATO`, `IDPACCHETTO`),
  CONSTRAINT `Pacchetto_componentiSelezionati_codiceComponente` FOREIGN KEY (`codiceComponente`) REFERENCES `componente` (`CODICECOMPONENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacchetto_componentiselezionati`
--

LOCK TABLES `pacchetto_componentiselezionati` WRITE;
/*!40000 ALTER TABLE `pacchetto_componentiselezionati` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacchetto_componentiselezionati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',0);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `EMAIL` varchar(255) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `REGISTEREDON` datetime DEFAULT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('impiegato@td.it','Mario','Bianchi','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2014-02-04 00:36:44'),('utente1@td.it','Francesco','Rossi','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2014-02-04 00:35:50'),('utente2@td.it','Andrea','Verdi','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','2014-02-04 00:37:31');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_groups`
--

DROP TABLE IF EXISTS `users_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_groups` (
  `email` varchar(255) DEFAULT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  KEY `FK_USERS_GROUPS_email` (`email`),
  CONSTRAINT `FK_USERS_GROUPS_email` FOREIGN KEY (`email`) REFERENCES `users` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_groups`
--


LOCK TABLES `users_groups` WRITE;
/*!40000 ALTER TABLE `users_groups` DISABLE KEYS */;
INSERT INTO `users_groups` VALUES ('utente1@td.it','USER'),('impiegato@td.it','ADMIN'),('utente2@td.it','USER');
/*!40000 ALTER TABLE `users_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_pacchetto`
--

DROP TABLE IF EXISTS `users_pacchetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_pacchetto` (
  `User_EMAIL` varchar(255) NOT NULL,
  `IDPACCHETTOPERSONALIZZATO` bigint(20) NOT NULL,
  `IDPACCHETTO` bigint(20) NOT NULL,
  PRIMARY KEY (`User_EMAIL`,`IDPACCHETTOPERSONALIZZATO`,`IDPACCHETTO`),
  KEY `FK_USERS_PACCHETTO_IDPACCHETTOPERSONALIZZATO` (`IDPACCHETTOPERSONALIZZATO`,`IDPACCHETTO`),
  CONSTRAINT `FK_USERS_PACCHETTO_IDPACCHETTOPERSONALIZZATO` FOREIGN KEY (`IDPACCHETTOPERSONALIZZATO`, `IDPACCHETTO`) REFERENCES `pacchetto` (`IDPACCHETTOPERSONALIZZATO`, `IDPACCHETTO`),
  CONSTRAINT `FK_USERS_PACCHETTO_User_EMAIL` FOREIGN KEY (`User_EMAIL`) REFERENCES `users` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_pacchetto`
--

LOCK TABLES `users_pacchetto` WRITE;
/*!40000 ALTER TABLE `users_pacchetto` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_pacchetto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-19 15:06:30
