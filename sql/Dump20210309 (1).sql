-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: diginamic_ccp2
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `camion`
--

DROP TABLE IF EXISTS `camion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camion` (
  `volume` double DEFAULT NULL,
  `id` int(11) NOT NULL,
  `id_camion_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2v4o7nkg78j5hyd7mgdo1jdq2` (`id_camion_type`),
  CONSTRAINT `FK2v4o7nkg78j5hyd7mgdo1jdq2` FOREIGN KEY (`id_camion_type`) REFERENCES `camion_type` (`id`),
  CONSTRAINT `FKrpiru1kfqhvbon471jb1rn37i` FOREIGN KEY (`id`) REFERENCES `vehicule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camion`
--

LOCK TABLES `camion` WRITE;
/*!40000 ALTER TABLE `camion` DISABLE KEYS */;
INSERT INTO `camion` VALUES (35,8,1),(30,9,2);
/*!40000 ALTER TABLE `camion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camion_type`
--

DROP TABLE IF EXISTS `camion_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `camion_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `montant` double NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tjj14trptht5qjmu7q8wg1070` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camion_type`
--

LOCK TABLES `camion_type` WRITE;
/*!40000 ALTER TABLE `camion_type` DISABLE KEYS */;
INSERT INTO `camion_type` VALUES (1,780.55,'déménagement'),(2,999.99,'frigorifique');
/*!40000 ALTER TABLE `camion_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_postal` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom_rue` varchar(255) NOT NULL,
  `numero_rue` int(11) NOT NULL,
  `numéro_téléphone` varchar(11) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `id_permis` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaxqbm5osw7vbtgbvnqfv8ea86` (`id_permis`),
  CONSTRAINT `FKaxqbm5osw7vbtgbvnqfv8ea86` FOREIGN KEY (`id_permis`) REFERENCES `permis` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,34070,'Montpellier','Rue clementville',26,'0181214495','Montpellier','CERMELLI','Tom',NULL),(3,34000,'Test_test@gmail.com','Rue du Bayle',8,'0165798485','Montpellier','Ploppy','Plopd',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facture`
--

DROP TABLE IF EXISTS `facture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `montant` double DEFAULT NULL,
  `numero_facture` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `id_facture_type` int(11) DEFAULT NULL,
  `id_location` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1xe1pu9mc6njyfp909ke4i7g` (`id_facture_type`),
  KEY `FKpfyi3olfy869fotun278el931` (`id_location`),
  CONSTRAINT `FK1xe1pu9mc6njyfp909ke4i7g` FOREIGN KEY (`id_facture_type`) REFERENCES `facture_type` (`id`),
  CONSTRAINT `FKpfyi3olfy869fotun278el931` FOREIGN KEY (`id_location`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facture`
--

LOCK TABLES `facture` WRITE;
/*!40000 ALTER TABLE `facture` DISABLE KEYS */;
/*!40000 ALTER TABLE `facture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facture_type`
--

DROP TABLE IF EXISTS `facture_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facture_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facture_type`
--

LOCK TABLES `facture_type` WRITE;
/*!40000 ALTER TABLE `facture_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `facture_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `fin_prevu_reservation` datetime(6) DEFAULT NULL,
  `fin_reel_reservation` datetime(6) DEFAULT NULL,
  `debut_reservation` datetime(6) DEFAULT NULL,
  `kilometrage_debut` double DEFAULT NULL,
  `kilometrage_fin` double DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_facture` int(11) DEFAULT NULL,
  `id_vehicule` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKil2ds4uvoi8oy99oqgodgdcs9` (`id_client`),
  KEY `FK69e9otalaovw5qmyv4018997u` (`id_facture`),
  KEY `FKaas9ax7enoh7k0dx5fh9fproe` (`id_vehicule`),
  CONSTRAINT `FK69e9otalaovw5qmyv4018997u` FOREIGN KEY (`id_facture`) REFERENCES `facture` (`id`),
  CONSTRAINT `FKaas9ax7enoh7k0dx5fh9fproe` FOREIGN KEY (`id_vehicule`) REFERENCES `vehicule` (`id`),
  CONSTRAINT `FKil2ds4uvoi8oy99oqgodgdcs9` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cout_reparation` double DEFAULT NULL,
  `debut_debut_maintenance` datetime(6) DEFAULT NULL,
  `date_fin_maintenance` datetime(6) DEFAULT NULL,
  `id_vehicule` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ta7o6qnxeg1u8xbsw1k5r98d` (`id_vehicule`),
  CONSTRAINT `FK6ta7o6qnxeg1u8xbsw1k5r98d` FOREIGN KEY (`id_vehicule`) REFERENCES `vehicule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance`
--

LOCK TABLES `maintenance` WRITE;
/*!40000 ALTER TABLE `maintenance` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permis`
--

DROP TABLE IF EXISTS `permis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_obtention` datetime(6) NOT NULL,
  `numero_permis` text NOT NULL,
  `type` varchar(255) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98w1ncq8sc4udkxf95884j4jw` (`id_client`),
  CONSTRAINT `FK98w1ncq8sc4udkxf95884j4jw` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permis`
--

LOCK TABLES `permis` WRITE;
/*!40000 ALTER TABLE `permis` DISABLE KEYS */;
INSERT INTO `permis` VALUES (1,'2021-03-10 00:00:00.000000','123456789012345','A',1),(3,'2021-03-19 00:00:00.000000','123564890983246','A',1);
/*!40000 ALTER TABLE `permis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnes`
--

DROP TABLE IF EXISTS `personnes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personnes` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnes`
--

LOCK TABLES `personnes` WRITE;
/*!40000 ALTER TABLE `personnes` DISABLE KEYS */;
/*!40000 ALTER TABLE `personnes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `immatriculation` varchar(255) NOT NULL,
  `kilometrage` double NOT NULL,
  `marque` varchar(255) NOT NULL,
  `modele` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicule`
--

LOCK TABLES `vehicule` WRITE;
/*!40000 ALTER TABLE `vehicule` DISABLE KEYS */;
INSERT INTO `vehicule` VALUES (2,'HE-MT3-FR',68,'Ford','Ford Focus','disponible'),(3,'LP-M640-LB',0,'Lamborghini','URUS','disponible'),(5,'TT-A145-GH',0,'Audi','TT Cabriolet','disponible'),(8,'RF-A46-US',6788,'Ryaou','Iveco Eurocargo','disponible'),(9,'RF-A12-DR',0,'TestFrig','Dayo','disponible');
/*!40000 ALTER TABLE `vehicule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voiture`
--

DROP TABLE IF EXISTS `voiture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voiture` (
  `nombre_place` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `id_voiture_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcq5n8alecdo35g5vlfcohapow` (`id_voiture_type`),
  CONSTRAINT `FKcq5n8alecdo35g5vlfcohapow` FOREIGN KEY (`id_voiture_type`) REFERENCES `voiture_type` (`id`),
  CONSTRAINT `FKriq8pawo1ayx2e8x14cit6a17` FOREIGN KEY (`id`) REFERENCES `vehicule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voiture`
--

LOCK TABLES `voiture` WRITE;
/*!40000 ALTER TABLE `voiture` DISABLE KEYS */;
INSERT INTO `voiture` VALUES (5,2,1),(2,3,1),(4,5,3);
/*!40000 ALTER TABLE `voiture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voiture_type`
--

DROP TABLE IF EXISTS `voiture_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voiture_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tarif` double NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s2b5u9k15s0qbncce0eqj4dfb` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voiture_type`
--

LOCK TABLES `voiture_type` WRITE;
/*!40000 ALTER TABLE `voiture_type` DISABLE KEYS */;
INSERT INTO `voiture_type` VALUES (1,9999.99,'sport'),(2,5699.99,'berline'),(3,14999.99,'cabriolet');
/*!40000 ALTER TABLE `voiture_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-09 15:36:22
