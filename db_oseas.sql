-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_oseas
-- ------------------------------------------------------
-- Server version	5.5.54-0ubuntu0.14.04.1

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
-- Table structure for table `circulacao`
--

DROP TABLE IF EXISTS `circulacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `circulacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_hora` datetime DEFAULT NULL,
  `vendedor_id` int(11) NOT NULL,
  `valor_total` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_circulacao_vendedor_idx` (`vendedor_id`),
  CONSTRAINT `fk_circulacao_vendedor` FOREIGN KEY (`vendedor_id`) REFERENCES `vendedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `circulacao`
--

LOCK TABLES `circulacao` WRITE;
/*!40000 ALTER TABLE `circulacao` DISABLE KEYS */;
INSERT INTO `circulacao` VALUES (4,'2017-05-28 15:05:13',6,161),(5,'2017-05-29 19:31:06',11,288.75),(6,'2017-05-29 19:56:49',12,275),(7,'2017-05-29 20:01:42',9,275),(8,'2017-05-30 00:57:54',11,321),(9,'2017-05-30 01:05:29',7,240.00000000000006),(10,'2017-05-30 02:23:57',6,2961),(11,'2017-05-30 02:33:42',13,656),(12,'2017-05-30 02:59:04',6,344),(13,'2017-05-30 10:18:37',6,64),(14,'2017-05-30 10:22:12',10,230),(15,'2017-05-30 10:46:34',13,556),(16,'2017-05-30 10:57:28',11,324),(17,'2017-05-30 11:06:58',10,224),(18,'2017-05-30 11:44:38',9,315),(19,'2017-05-30 11:50:12',10,59.2),(20,'2017-05-30 11:55:02',6,210),(21,'2017-05-30 11:57:42',9,35.2);
/*!40000 ALTER TABLE `circulacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qtd_estoque` int(11) DEFAULT NULL,
  `produto_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`produto_id`),
  KEY `fk_estoque_produto1_idx` (`produto_id`),
  CONSTRAINT `fk_estoque_produto1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (19,190,38),(20,142,39),(21,100,40),(22,242,41),(23,485,42),(24,300,43),(25,249,44);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `preco_final` double DEFAULT NULL,
  `preco_atacado` double DEFAULT NULL,
  `descricao` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (38,'MPX/02','Broca',20,10,'Broca muito interessante'),(39,'MPD','Disco Voador',4.800000000000001,3,'Ele voa na cabeça de um.'),(40,'CAFE','Café',3.5999999999999996,3,'Cafezinho muito bom'),(41,'MPX/03','Broca 9mm',18.2,14,'Broca para madeira etc, etc...'),(42,'MPX/04','Broca 8mm',195,150,'Broca pra parede etc etc'),(43,'MPD/4','Broca 7mm',2.8,2,'Broca para parece etc etc'),(44,'MPDF','Disco',4.800000000000001,3,'Disco para madeira etc etc');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_circulando`
--

DROP TABLE IF EXISTS `produto_circulando`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_circulando` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qtd_circulando` int(11) DEFAULT NULL,
  `circulacao_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`produto_id`),
  KEY `fk_produto_circulando_circulacao1_idx` (`circulacao_id`),
  KEY `fk_produto_circulando_produto1_idx` (`produto_id`),
  CONSTRAINT `fk_produto_circulando_circulacao1` FOREIGN KEY (`circulacao_id`) REFERENCES `circulacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_circulando_produto1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_circulando`
--

LOCK TABLES `produto_circulando` WRITE;
/*!40000 ALTER TABLE `produto_circulando` DISABLE KEYS */;
INSERT INTO `produto_circulando` VALUES (1,10,8,39),(2,15,8,41),(3,30,9,39),(4,20,9,44),(5,10,10,40),(6,15,10,42),(7,0,11,38),(8,60,11,40),(9,50,11,43),(10,15,11,44),(11,10,11,43),(12,0,12,38),(13,30,12,44),(14,10,13,40),(15,10,13,43),(16,5,14,41),(17,10,14,39),(18,20,15,41),(19,25,15,44),(20,20,15,40),(21,0,16,43),(22,2,16,38),(23,12,16,39),(24,2,17,38),(25,1,17,44),(26,5,18,43),(27,7,18,41),(28,3,19,43),(29,5,19,44),(30,5,20,41),(31,3,20,43),(32,4,21,44),(33,2,21,43);
/*!40000 ALTER TABLE `produto_circulando` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_vendido`
--

DROP TABLE IF EXISTS `produto_vendido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_vendido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qtd_produto` int(11) DEFAULT NULL,
  `produto_id` int(11) NOT NULL,
  `circulacao_id` int(11) NOT NULL,
  `data_venda` datetime NOT NULL,
  PRIMARY KEY (`id`,`produto_id`),
  KEY `fk_produto_vendido_produto1_idx` (`produto_id`),
  KEY `fk_produto_vendido_circulacao1_idx` (`circulacao_id`),
  CONSTRAINT `fk_produto_vendido_circulacao1` FOREIGN KEY (`circulacao_id`) REFERENCES `circulacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_vendido_produto1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_vendido`
--

LOCK TABLES `produto_vendido` WRITE;
/*!40000 ALTER TABLE `produto_vendido` DISABLE KEYS */;
INSERT INTO `produto_vendido` VALUES (1,5,38,11,'2017-05-30 02:53:37'),(2,5,38,11,'2017-05-30 02:53:37'),(3,10,38,12,'2017-05-30 03:19:22'),(4,5,38,14,'2017-05-30 10:31:56'),(5,7,38,14,'2017-05-30 10:31:56'),(6,10,38,15,'2017-05-30 10:47:16'),(7,25,38,15,'2017-05-30 10:47:16'),(8,18,38,15,'2017-05-30 10:47:16'),(9,10,43,16,'2017-05-30 10:57:57'),(10,6,38,16,'2017-05-30 10:57:57'),(11,10,39,16,'2017-05-30 10:57:57'),(12,7,38,17,'2017-05-30 11:07:33'),(13,3,44,17,'2017-05-30 11:07:33'),(14,1,38,17,'2017-05-30 11:16:05'),(15,1,44,17,'2017-05-30 11:16:05'),(16,0,43,16,'2017-05-30 11:26:55'),(17,2,38,16,'2017-05-30 11:26:55'),(18,-2,39,16,'2017-05-30 11:26:55'),(19,10,43,18,'2017-05-30 11:45:17'),(20,8,41,18,'2017-05-30 11:45:18'),(21,1,43,19,'2017-05-30 11:50:54'),(22,5,44,19,'2017-05-30 11:50:54'),(23,5,41,20,'2017-05-30 11:55:38'),(24,7,43,20,'2017-05-30 11:55:38'),(25,1,44,21,'2017-05-30 11:58:23'),(26,2,43,21,'2017-05-30 11:58:23');
/*!40000 ALTER TABLE `produto_vendido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `percentual` double NOT NULL,
  `valor_a_receber` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (6,'Catatau','000.000.000-14',20,0),(7,'Zé Colméia','000.000.000-10',15,0),(9,'Matias das Chagax','432.000.943-12',20,NULL),(10,'Botinni','987.321.912-12',10,NULL),(11,'Felipe','000.000.000-00',100,NULL),(12,'Carlla','401.051.992-49',200,NULL),(13,'Fernadinho Almeida','000.003.012.12',35,70);
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-30 12:18:22
