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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `circulacao`
--

LOCK TABLES `circulacao` WRITE;
/*!40000 ALTER TABLE `circulacao` DISABLE KEYS */;
INSERT INTO `circulacao` VALUES (1,'2017-05-27 21:48:52',6,24.2772),(2,'2017-05-27 21:50:30',7,0);
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
  `qtd_circulando` int(11) DEFAULT NULL,
  `produto_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`produto_id`),
  KEY `fk_estoque_produto1_idx` (`produto_id`),
  CONSTRAINT `fk_estoque_produto1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'MPX/1','Broca',1.6524,1.02,'Broca muito boa'),(2,'MPX/3','Disco',1.6099999999999999,1,'Disco que corta até a alma'),(3,'MPZ','Produto',1.6099999999999999,1,'Produto legal'),(4,'fd','Produtao',3.2199999999999998,2,'dadfa'),(5,'kafp','Fresa Matadora',1.6099999999999999,1,'Mata até Leão'),(6,'efsd','fasfs',1.6099999999999999,1,'dafa'),(7,'ajksafk','dsada',1.6,1,'sdjaod'),(8,'fafe','fdsdads',1.6099999999999999,1,'lsa,sdlasl'),(9,'dasfas','dfsafssd',4,2,'dsfadfsadfdsfadfdafadads'),(10,'osjofaoi','fsokjfosao',3.2199999999999998,2,'dioajifaofafisjaofjewo');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_circulando`
--

LOCK TABLES `produto_circulando` WRITE;
/*!40000 ALTER TABLE `produto_circulando` DISABLE KEYS */;
INSERT INTO `produto_circulando` VALUES (2,3,1,1),(3,5,1,2),(4,7,1,3);
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
  `produto_circulando_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`produto_id`),
  KEY `fk_produto_vendido_produto1_idx` (`produto_id`),
  KEY `fk_produto_vendido_circulacao1_idx` (`circulacao_id`),
  KEY `fk_produto_vendido_produto_circulando1` (`produto_circulando_id`),
  CONSTRAINT `fk_produto_vendido_produto_circulando1` FOREIGN KEY (`produto_circulando_id`) REFERENCES `produto_circulando` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_vendido_circulacao1` FOREIGN KEY (`circulacao_id`) REFERENCES `circulacao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_vendido_produto1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_vendido`
--

LOCK TABLES `produto_vendido` WRITE;
/*!40000 ALTER TABLE `produto_vendido` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (6,'Catatau','000.000.000-14',20,NULL),(7,'Zé Colméia','000.000.000-10',15,10);
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

-- Dump completed on 2017-05-28 11:09:07
