-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: dott
-- ------------------------------------------------------
-- Server version	5.7.27

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
-- Current Database: `dott`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `dott` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `dott`;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL,
  `cost` decimal(19,2) NOT NULL,
  `shipping_fee` decimal(19,2) NOT NULL,
  `tax_amount` decimal(19,2) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd1g72rrhgq1sf7m4uwfvuhlhe` (`product_id`),
  CONSTRAINT `FKd1g72rrhgq1sf7m4uwfvuhlhe` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table` (
  `id` int(11) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `grand_total` double NOT NULL,
  `order_date` datetime NOT NULL,
  `shipping_address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table_items`
--

DROP TABLE IF EXISTS `order_table_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_table_items` (
  `order_table_id` int(11) NOT NULL,
  `items_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_table_id`,`items_id`),
  UNIQUE KEY `UK_edmq4vvg9pbwn3bltll242805` (`items_id`),
  CONSTRAINT `FKdpkm31xke01d1oaoojmqvj3k2` FOREIGN KEY (`order_table_id`) REFERENCES `order_table` (`id`),
  CONSTRAINT `FKhalwmtc7rikos0ycpp61vob74` FOREIGN KEY (`items_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table_items`
--

LOCK TABLES `order_table_items` WRITE;
/*!40000 ALTER TABLE `order_table_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_table_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) NOT NULL,
  `creation` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-02 12:16:37

DELETE FROM order_table_items;
DELETE FROM item;
DELETE FROM product;
DELETE FROM order_table;
INSERT INTO product (id, name, category, weight, price, creation) VALUES
(1,'Camisa 1','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(2,'Camisa 2','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(3,'Camisa 3','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(4,'Camisa 4','Vestuario',40,40,TIMESTAMPADD(YEAR,-1, CURRENT_DATE())),
(5,'Camisa 5','Vestuario',40,40,TIMESTAMPADD(MONTH,-12, CURRENT_DATE())),
(6,'Camisa 6','Vestuario',40,40,TIMESTAMPADD(MONTH,-11, CURRENT_DATE())),
(7,'Camisa 7','Vestuario',40,40,TIMESTAMPADD(MONTH,-10, CURRENT_DATE())),
(8,'Camisa 8','Vestuario',40,40,TIMESTAMPADD(MONTH,-9, CURRENT_DATE())),
(9,'Camisa 9','Vestuario',40,40,TIMESTAMPADD(MONTH,-8, CURRENT_DATE())),
(10,'Camisa 10','Vestuario',40,40,TIMESTAMPADD(MONTH,-7, CURRENT_DATE())),
(11,'Camisa 11','Vestuario',40,40,TIMESTAMPADD(MONTH,-6, CURRENT_DATE())),
(12,'Camisa 12','Vestuario',40,40,TIMESTAMPADD(MONTH,-5, CURRENT_DATE())),
(13,'Camisa 13','Vestuario',40,40,TIMESTAMPADD(MONTH,-4, CURRENT_DATE())),
(14,'Camisa 14','Vestuario',40,40,TIMESTAMPADD(MONTH,-3, CURRENT_DATE())),
(15,'Camisa 15','Vestuario',40,40,TIMESTAMPADD(MONTH,-2, CURRENT_DATE())),
(16,'Camisa 16','Vestuario',40,40,TIMESTAMPADD(MONTH,-1, CURRENT_DATE())),
(17,'Camisa 17','Vestuario',40,40,CURRENT_DATE()),
(18,'Camisa 18','Vestuario',40,40,CURRENT_DATE());

INSERT INTO order_table (id, customer_name, contact, shipping_address, grand_total, order_date) VALUES (1, 'JOAO DA SILVA', '333 333 333', 'Rua Joao Nogueira', 1500.0, TIMESTAMPADD(YEAR,-1, CURRENT_DATE()));
INSERT INTO order_table (id, customer_name, contact, shipping_address, grand_total, order_date) VALUES (2, 'MIGUEL FIGUEREDO', '333 333 333', 'Rua Joao Nogueira', 1000.0, TIMESTAMPADD(MONTH ,-8, CURRENT_DATE()));


INSERT INTO item (id, cost, shipping_fee, tax_amount, product_id) VALUES
(1, 70, 10, 7, 1),
(2, 70, 10, 7, 2),
(3, 70, 10, 7, 3),
(4, 70, 10, 7, 4),
(5, 70, 10, 7, 5),
(6, 70, 10, 7, 6),
(7, 70, 10, 7, 7),
(8, 70, 10, 7, 8),
(9, 70, 10, 7, 9),
(10, 70, 10, 7, 10),
(11, 70, 10, 7, 11),
(12, 70, 10, 7, 12),
(13, 70, 10, 7, 13),
(14, 70, 10, 7, 14),
(15, 70, 10, 7, 15),
(16, 70, 10, 7, 16),
(17, 70, 10, 7, 17);


INSERT INTO order_table_items (order_table_id, items_id) VALUES
(1, 1), (1, 2), (1, 3),
(2,4), (2,5), (2,6), (2,10), (2,11), (2,12), (2,13), (2,14), (2,15), (2,16);

