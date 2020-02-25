CREATE DATABASE  IF NOT EXISTS `db_zs` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_zs`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: db_zs
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `sys_data_dic`
--

DROP TABLE IF EXISTS `sys_data_dic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_dic` (
  `ID` varchar(36) NOT NULL,
  `TYPE_ID` varchar(36) DEFAULT NULL,
  `DIC_VALUE` varchar(100) DEFAULT NULL,
  `BUS_CODE` varchar(32) DEFAULT NULL,
  `DIC_CODE` varchar(32) DEFAULT NULL COMMENT '101 101001',
  `ORDERS` decimal(5,0) DEFAULT NULL COMMENT '备用',
  `REMARK` varchar(100) DEFAULT NULL,
  `STATUS` varchar(36) DEFAULT NULL COMMENT '00 启用 99：禁用',
  `CREATE_ID` varchar(36) DEFAULT NULL,
  `CREATE_TIME` varchar(19) DEFAULT NULL,
  `UPDATE_ID` varchar(36) DEFAULT NULL,
  `UPDATE_TIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_dic`
--

LOCK TABLES `sys_data_dic` WRITE;
/*!40000 ALTER TABLE `sys_data_dic` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_data_dic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dic_type`
--

DROP TABLE IF EXISTS `sys_dic_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dic_type` (
  `PK_ID` varchar(36) NOT NULL,
  `DIC_NAME` varchar(100) DEFAULT NULL,
  `BUS_CODE` varchar(100) DEFAULT NULL,
  `DIC_CODE` varchar(100) DEFAULT NULL COMMENT '101 101001',
  `DIC_ORIGIN` varchar(10) DEFAULT NULL COMMENT '00 内部 01 SQL',
  `DIC_SQL` varchar(100) DEFAULT NULL,
  `IS_TREE` char(1) DEFAULT NULL,
  `REMARK` varchar(100) DEFAULT NULL,
  `STATUS` varchar(36) DEFAULT NULL COMMENT '00 启用 99：禁用',
  `CREATE_ID` varchar(36) DEFAULT NULL,
  `CREATE_TIME` varchar(19) DEFAULT NULL,
  `UPDATE_ID` varchar(36) DEFAULT NULL,
  `UPDATE_TIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dic_type`
--

LOCK TABLES `sys_dic_type` WRITE;
/*!40000 ALTER TABLE `sys_dic_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dic_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tacl`
--

DROP TABLE IF EXISTS `tacl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tacl` (
  `ID` varchar(36) NOT NULL,
  `PRINCIPALTYPE` varchar(10) DEFAULT NULL,
  `PRINCIPALID` varchar(36) DEFAULT NULL,
  `RESOURCETYPE` varchar(10) DEFAULT NULL,
  `RESOURCEID` varchar(36) DEFAULT NULL,
  `ACLSTATE` decimal(5,0) DEFAULT NULL,
  `ACLTRISTATE` decimal(5,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tacl`
--

LOCK TABLES `tacl` WRITE;
/*!40000 ALTER TABLE `tacl` DISABLE KEYS */;
INSERT INTO `tacl` VALUES ('08dce576-9c35-446a-9b17-56c9ca0a666e','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','02739745-8601-499a-a103-bf1800b97f24',NULL,NULL),('0a14a1c9-9368-4474-adb3-b4e91985952f','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','e9365401-866d-41a6-a6df-6ff40ec76bcf',NULL,NULL),('0abaffe5-a75c-44e3-84b3-04f4b99c6704','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','0a0a40d3-afa4-4c8c-b75d-70fb3e4f3107',NULL,NULL),('0d547ddc-5065-40b8-ad0e-c1e09c162c9b','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','28e64e0c-6623-487f-a7cc-7715f7434989',NULL,NULL),('0f208282-782b-40ed-944e-df8ad280670b','role','1','menu','0e5058fc-e938-44df-bd2c-db921af551a2',NULL,NULL),('1cc0705a-5fee-4262-a7ed-9f538fec69f5','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','ca3ab255-5cb6-43a5-887c-dec31cdb15f3',NULL,NULL),('1ed9ba67-644d-4bd3-9e2a-fa14fa6063fb','role','1','menu','3a14d4fa-7dd2-40e4-b099-89e690be370e',NULL,NULL),('1ee4aa78-7c99-40b4-ae32-8848a1c89cad','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','def4484a-dff5-41d5-8565-774576651e0b',NULL,NULL),('23ce7a42-cf55-4bbc-9dc1-2d329ee230dd','role','1','menu','1',NULL,NULL),('2c5a0936-7215-4da1-8dbd-4399589a226e','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','8a6edb53-f04b-4f8f-972d-280ec4e6ac49',NULL,NULL),('2e02c310-edd5-444d-a068-44b296431537','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','e9365401-866d-41a6-a6df-6ff40ec76bcf',NULL,NULL),('353fe1e5-2a6f-4eb2-8e67-ed9a25cddf4a','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','2e1480ef-5466-457d-a228-fc585adadc97',NULL,NULL),('3d97d0ca-3809-4a80-9e19-a7e90a5f53a8','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','ca3ab255-5cb6-43a5-887c-dec31cdb15f3',NULL,NULL),('43641b92-78df-482b-af04-d822a515348d','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','20b517e6-7606-4a3f-b551-1782c8002f35',NULL,NULL),('48eee2ef-dc6f-4a95-908d-3a47f36061c2','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','f62e5b56-aec3-45f1-9c9a-848983650a68',NULL,NULL),('4e4e0a05-87d0-41b4-a3bc-19bbabcedee1','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','8a6edb53-f04b-4f8f-972d-280ec4e6ac49',NULL,NULL),('4ee04d14-d1b3-4044-b7d3-b5cf8403e8f1','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','f62e5b56-aec3-45f1-9c9a-848983650a68',NULL,NULL),('5257bda1-31bf-414d-bb48-bfcb062def0c','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','395b1ccf-88e8-4aa4-be85-110b3b1cc422',NULL,NULL),('58df014b-6c8e-46da-941f-61b742f85e63','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','69ca88e9-dd8d-4c68-b533-5c09df18d041',NULL,NULL),('59c4c66e-cabc-44b2-bf06-0062e30f76ef','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','01b90933-4848-44e3-9b10-b9478a5468d4',NULL,NULL),('63fbc590-48c1-4eff-a26a-508165c5a8fa','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','3a14d4fa-7dd2-40e4-b099-89e690be370e',NULL,NULL),('680726cf-5546-4117-a242-2218f894a7b2','role','1','menu','b22461d2-f0ad-488a-b6c0-461722c3a94f',NULL,NULL),('6aaf3cc1-303b-410e-97a2-c677f72d6950','role','1','menu','9dd85301-0d66-4973-8052-8e1203b5fdaa',NULL,NULL),('6b12b9f0-3f13-46ef-83b4-8f46c88c5bd9','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','9dd85301-0d66-4973-8052-8e1203b5fdaa',NULL,NULL),('6b281bf4-f9f4-4599-bf96-00c5f2634fe7','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','2e1480ef-5466-457d-a228-fc585adadc97',NULL,NULL),('6e510136-3f0e-4700-97c3-67cc7101f3c8','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','bd90249a-f081-472b-a478-9f020e6a0162',NULL,NULL),('709610e0-93fc-43e3-9b7e-db62bcc83835','role','1','menu','b148724d-cfa8-4c89-a7dc-02e41d6de9b3',NULL,NULL),('72a20d3b-dc94-42f9-a012-1adb1ca575f5','role','1','menu','4848adb2-b799-4544-8c1e-f2bfd8ed625b',NULL,NULL),('76d94ab4-f6ba-40e6-a9b7-9d594b641769','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','ac74ef29-e252-4f31-9eef-b6aeab49472a',NULL,NULL),('7f6ae534-7d53-4329-81a0-ffd48627cef9','role','1','menu','a0bf5f13-a2b4-4fa1-a708-2dab006b8692',NULL,NULL),('80db7765-5108-4fbc-897e-8fae2142810a','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','0a0a40d3-afa4-4c8c-b75d-70fb3e4f3107',NULL,NULL),('8381fe67-5393-4206-bc54-778499d4630a','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','2fb423a2-798d-49ce-a76b-ccafe48ea0aa',NULL,NULL),('85f43c8a-9d14-4608-9489-a0ac6dcccb61','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','60721f12-ce1d-4173-9ab1-35fc7b8f91cf',NULL,NULL),('89c12ad6-1880-41f5-a31c-b131e6a52366','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','98c95cda-84b3-49d7-9c75-f0b5c185fc2e',NULL,NULL),('8cbb9fe5-48c1-4998-9093-db60b9dc8aae','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','ddec19c1-0dd0-4031-bbe6-41d093588e4b',NULL,NULL),('94d524bf-800f-4496-883b-0f8bbc745d5a','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','9dd85301-0d66-4973-8052-8e1203b5fdaa',NULL,NULL),('957f3651-9166-4d46-9eb0-61856e95ceec','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','2fb423a2-798d-49ce-a76b-ccafe48ea0aa',NULL,NULL),('95c71c75-ee71-4ebc-963a-d2143198bbf3','role','1','menu','ffed6fd4-4aa4-4d62-8522-af604653da70',NULL,NULL),('9b66baf4-04d1-4122-b1d6-f4739df143e7','role','1','menu','a71ec8d3-57f5-4ddc-8d84-18f82ae115ee',NULL,NULL),('a44085af-2924-4845-8693-e7cce021ecb2','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','01b90933-4848-44e3-9b10-b9478a5468d4',NULL,NULL),('a98f1d72-4af3-4fb8-8e7a-96e496d51deb','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','7e655e0a-b34d-415d-8de9-69c6e0403811',NULL,NULL),('ab99ed70-3987-4482-9f26-d995b85a7f8f','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','def4484a-dff5-41d5-8565-774576651e0b',NULL,NULL),('adf6bd81-d10b-4a77-88bf-e2ac346f9bc3','role','1','menu','def4484a-dff5-41d5-8565-774576651e0b',NULL,NULL),('ae384a3c-7629-41a2-b5de-ff85bee0236f','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','3a14d4fa-7dd2-40e4-b099-89e690be370e',NULL,NULL),('afa7d836-54b9-40af-9a4b-0efa40d30186','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','7e655e0a-b34d-415d-8de9-69c6e0403811',NULL,NULL),('b0a62e05-5e0d-47e1-a9cd-665b0d6dc637','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','5813d856-dfae-4da9-849d-7e0eabc9f1fc',NULL,NULL),('b7dd1dcb-bf4c-489e-8c25-15f696a082f3','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','98c95cda-84b3-49d7-9c75-f0b5c185fc2e',NULL,NULL),('ba4b5760-0f9c-442d-98cd-3066b05f5112','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','02739745-8601-499a-a103-bf1800b97f24',NULL,NULL),('baecc732-ef2e-4710-984c-a7bbc3522639','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','ddec19c1-0dd0-4031-bbe6-41d093588e4b',NULL,NULL),('bb53a014-809c-4058-aa96-ba226f0a77a5','role','1','menu','e1e7da83-ebf3-493c-bbd0-b7a98882a863',NULL,NULL),('bf2c050e-50d5-4073-b373-d02ddbd7b360','role','1','menu','ddec19c1-0dd0-4031-bbe6-41d093588e4b',NULL,NULL),('bfeec335-a3da-4d41-9eb1-ab56f42c2456','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','ffed6fd4-4aa4-4d62-8522-af604653da70',NULL,NULL),('c24be15d-8864-45d5-8292-34c8d68d5af6','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','5813d856-dfae-4da9-849d-7e0eabc9f1fc',NULL,NULL),('c989f247-e1ef-4754-91a9-d7de7947683b','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','c5b9dbae-facc-4f0d-a280-3b4474c3d330',NULL,NULL),('c9f12e05-74d7-4c20-8d7c-a2924a7134ed','role','1','menu','1e40e3a4-1d69-43c7-a73e-12600c04a35f',NULL,NULL),('cc444a44-206c-4297-af88-38956de562af','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','ffed6fd4-4aa4-4d62-8522-af604653da70',NULL,NULL),('da9045ec-6953-48aa-9e51-717b47bd87f7','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','88d1f686-a6b9-46b0-bf0f-2c80180c6783',NULL,NULL),('dfd00b5a-a672-41b2-9a44-333f4192e488','role','50e1aa44-43f1-4ec2-b01a-5493bd43608e','menu','495e11e5-d087-46c2-87fb-17281a07d2a4',NULL,NULL),('e22e54b7-de56-40d4-8734-c0b380430da3','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','20b517e6-7606-4a3f-b551-1782c8002f35',NULL,NULL),('e5082252-0cad-44ec-940c-07fa01226c14','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','c5b9dbae-facc-4f0d-a280-3b4474c3d330',NULL,NULL),('e772f43e-5365-41fc-9208-3e3d7eadcbda','role','990cae75-6dca-4f2f-92d4-792aaf3771b2','menu','60721f12-ce1d-4173-9ab1-35fc7b8f91cf',NULL,NULL),('e9df1d62-5cf0-4bbb-a821-bb68b76c2318','role','1','menu','60721f12-ce1d-4173-9ab1-35fc7b8f91cf',NULL,NULL),('f18c697d-fc28-497d-8873-3d6e7adbeb50','role','1bedf97c-dfc9-4c59-a805-13bf378d631a','menu','28e64e0c-6623-487f-a7cc-7715f7434989',NULL,NULL),('f46a3edc-14b9-48e7-9124-4ef6abfffafe','role','1','menu','20b517e6-7606-4a3f-b551-1782c8002f35',NULL,NULL),('f57c37db-d0ef-4b81-89d3-bcd6a6f0bc6d','role','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','menu','bd90249a-f081-472b-a478-9f020e6a0162',NULL,NULL),('f5c3d302-2b2f-4971-83d1-f3615b7ed2ea','role','1','menu','01b90933-4848-44e3-9b10-b9478a5468d4',NULL,NULL),('f70e8a0e-aa21-4f72-a052-105a67ac5f14','role','1','menu','28e64e0c-6623-487f-a7cc-7715f7434989',NULL,NULL),('ffd9583a-a0a5-4b4f-909a-15c61a041fe3','role','1','menu','8a6edb53-f04b-4f8f-972d-280ec4e6ac49',NULL,NULL);
/*!40000 ALTER TABLE `tacl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tcompany`
--

DROP TABLE IF EXISTS `tcompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tcompany` (
  `ID` varchar(36) NOT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `ABBREVIATION` varchar(100) DEFAULT NULL,
  `MARK` varchar(100) DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `GRADE` varchar(100) DEFAULT NULL,
  `AREA` varchar(100) DEFAULT NULL,
  `LINKMAN` varchar(100) DEFAULT NULL,
  `TEL` varchar(50) DEFAULT NULL,
  `FAX` varchar(50) DEFAULT NULL,
  `ZIPCODE` varchar(6) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tcompany`
--

LOCK TABLES `tcompany` WRITE;
/*!40000 ALTER TABLE `tcompany` DISABLE KEYS */;
INSERT INTO `tcompany` VALUES ('1b9bfefe-acb8-4370-afda-8a62c2a09051','1','','','','','','','','','','','','');
/*!40000 ALTER TABLE `tcompany` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdepartment`
--

DROP TABLE IF EXISTS `tdepartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdepartment` (
  `ID` varchar(36) NOT NULL,
  `MARK` varchar(100) DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `GRADE` varchar(100) DEFAULT NULL,
  `LINKMAN` varchar(100) DEFAULT NULL,
  `LINKMANEXP` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdepartment`
--

LOCK TABLES `tdepartment` WRITE;
/*!40000 ALTER TABLE `tdepartment` DISABLE KEYS */;
INSERT INTO `tdepartment` VALUES ('3a0d04c6-05df-4dc0-b485-a5b9b578e056','001','1','1','1','1');
/*!40000 ALTER TABLE `tdepartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_attence`
--

DROP TABLE IF EXISTS `tf_attence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_attence` (
  `attence_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `attence_data` varchar(50) DEFAULT NULL,
  `attence_time` varchar(50) DEFAULT NULL,
  `attence_status` varchar(50) DEFAULT NULL,
  `attence_result` varchar(50) DEFAULT NULL,
  `attence_place` varchar(100) DEFAULT NULL,
  `attence_photo` varchar(100) DEFAULT NULL,
  `attence_spare1` varchar(100) DEFAULT NULL,
  `attence_spare2` varchar(100) DEFAULT NULL,
  `attence_spare3` varchar(100) DEFAULT NULL,
  `attence_spare4` varchar(100) DEFAULT NULL,
  `attence_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`attence_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_attence`
--

LOCK TABLES `tf_attence` WRITE;
/*!40000 ALTER TABLE `tf_attence` DISABLE KEYS */;
INSERT INTO `tf_attence` VALUES ('a','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-01',NULL,'1','0',NULL,NULL,'a',NULL,NULL,NULL,NULL),('aa','1','2016-06-09',NULL,'0','1',NULL,NULL,'g',NULL,NULL,NULL,NULL),('ab','1','2016-06-10',NULL,'1','1',NULL,NULL,'h',NULL,NULL,NULL,NULL),('b','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-01',NULL,'0','1',NULL,NULL,'a',NULL,NULL,NULL,NULL),('c','1','2016-06-02',NULL,'1','0',NULL,NULL,'b',NULL,NULL,NULL,NULL),('d','1','2016-06-02',NULL,'0','1',NULL,NULL,'b',NULL,NULL,NULL,NULL),('e','1','2016-06-04',NULL,'0','0',NULL,NULL,'c',NULL,NULL,NULL,NULL),('f','1','2016-06-04',NULL,'1','1',NULL,NULL,'c',NULL,NULL,NULL,NULL),('g','1','2016-06-06',NULL,'0','0',NULL,NULL,'d',NULL,NULL,NULL,NULL),('h','1','2016-06-06',NULL,'1','0',NULL,NULL,'d',NULL,NULL,NULL,NULL),('i','1','2016-06-07',NULL,'1','0',NULL,NULL,'e',NULL,NULL,NULL,NULL),('k','1','2016-06-08',NULL,'1','0',NULL,NULL,'f',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_attence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_attence_main`
--

DROP TABLE IF EXISTS `tf_attence_main`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_attence_main` (
  `am_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `am_data` varchar(50) DEFAULT NULL,
  `am_start` varchar(50) DEFAULT NULL,
  `am_startT` varchar(50) DEFAULT NULL,
  `am_startR` varchar(50) DEFAULT NULL,
  `am_splace` varchar(100) DEFAULT NULL,
  `am_sphoto` varchar(100) DEFAULT NULL,
  `am_end` varchar(50) DEFAULT NULL,
  `am_endT` varchar(50) DEFAULT NULL,
  `am_endR` varchar(50) DEFAULT NULL,
  `am_eplace` varchar(100) DEFAULT NULL,
  `am_ephoto` varchar(100) DEFAULT NULL,
  `am_spare1` varchar(100) DEFAULT NULL,
  `am_spare2` varchar(100) DEFAULT NULL,
  `am_spare3` varchar(100) DEFAULT NULL,
  `am_spare4` varchar(100) DEFAULT NULL,
  `am_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`am_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_attence_main`
--

LOCK TABLES `tf_attence_main` WRITE;
/*!40000 ALTER TABLE `tf_attence_main` DISABLE KEYS */;
INSERT INTO `tf_attence_main` VALUES ('341e19a9-062a-4e46-8a0e-875e23bb3284','','2016-06-29','1','11:46:56','1','长春市南关区财富领域','1467172016148.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4079314d-3025-459c-b58d-4d2d0d476807','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-11','1','08:42:51','1','长春市南关区财富领域','1468197771834.png','1','08:42:55','1','长春市南关区财富领域','1468197775684.png',NULL,NULL,NULL,NULL,NULL),('422de71a-f91e-4d28-995c-bbdeaad1cb94','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','1','15:36:54','1','长春市南关区财富领域','1468049814321.jpg','1','15:39:53','1','长春市南关区财富领域','1468049993972.jpg',NULL,NULL,NULL,NULL,NULL),('58794159-4088-4ab5-9fb6-45a63eeb588b','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-18','1','08:53:18','1','长春市南关区财富领域','1468803198645.png','1','08:53:25','1','长春市南关区财富领域','1468803205346.png',NULL,NULL,NULL,NULL,NULL),('a','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-01','1','07:00:00','0','aaaaaaa','1467172016148.jpg','1','17:00:00','1','bbbbbb','1467103605397.jpg',NULL,NULL,NULL,NULL,NULL),('a3b3dfb5-81f0-4998-ad25-bdd151971076','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','1','15:19:31','1','长春市南关区财富领域','1468394371142.png','1','15:21:56','1','长春市南关区财富领域','1468394516394.png',NULL,NULL,NULL,NULL,NULL),('b','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-02','1','07:00:00','0','22222','1467103605397.jpg','',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('b6ea7db4-7025-4971-a9f1-743afbfe7d83','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-01','1','11:19:44','1','长春市南关区财富领域','1467343184787.jpg','1','11:20:48','1','长春市南关区财富领域','1467343248332.jpg',NULL,NULL,NULL,NULL,NULL),('c','1','2016-06-04','1',NULL,'1',NULL,NULL,'1',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('d','1','2016-06-06','1',NULL,'0',NULL,NULL,'1',NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('dc5281b0-0e1b-4b65-a236-595c2c3b2244','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','1','13:30:08','1','长春市南关区财富领域','1467264608705.jpg','1','13:30:20','1','长春市南关区财富领域','1467264620879.jpg',NULL,NULL,NULL,NULL,NULL),('e','1','2016-06-07','1','','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('f','1','2016-06-08',NULL,NULL,NULL,NULL,NULL,'1',NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ff082b6c-1cc1-48a2-a4ae-9a76a0cd83ca','52aeccc8-d962-4b15-bef2-42e2684ebb15','2016-07-14','1','11:36:26','1','长春市南关区财富领域','1468467386974.png',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('g','1','2016-06-09','1',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('h','1','2016-06-10',NULL,NULL,NULL,NULL,NULL,'1',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_attence_main` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_businesslist`
--

DROP TABLE IF EXISTS `tf_businesslist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_businesslist` (
  `bl_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `bl_data` varchar(50) DEFAULT NULL,
  `bl_time` varchar(50) DEFAULT NULL,
  `bl_name` varchar(100) DEFAULT NULL,
  `bl_content` varchar(100) DEFAULT NULL,
  `bl_status` varchar(50) DEFAULT NULL,
  `bl_place` varchar(100) DEFAULT NULL,
  `bl_photo` varchar(100) DEFAULT NULL,
  `bl_money` varchar(100) DEFAULT NULL,
  `bl_customer` varchar(100) DEFAULT NULL,
  `bl_spare1` varchar(100) DEFAULT NULL,
  `bl_spare2` varchar(100) DEFAULT NULL,
  `bl_spare3` varchar(100) DEFAULT NULL,
  `bl_spare4` varchar(100) DEFAULT NULL,
  `bl_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_businesslist`
--

LOCK TABLES `tf_businesslist` WRITE;
/*!40000 ALTER TABLE `tf_businesslist` DISABLE KEYS */;
INSERT INTO `tf_businesslist` VALUES ('02914713-dc1f-4e27-84ad-a8bca0977aeb','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','17:06:22','长春订单','成功','赢单','未获取到位置信息','1468400782830.png','10','3d3d014b-2c96-40f9-ac1b-49b81fea0998',NULL,NULL,NULL,NULL,NULL),('07eab8c1-6f5c-427d-9e2d-6218e2d44178','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-14','11:33:07','此言差矣','吃醋雨uvuv雨','赢单','长春市南关区财富领域','1468467187907.png','888','2ad88b87-2c0a-4ad8-96bd-879baaa3ec82',NULL,NULL,NULL,NULL,NULL),('2a774840-79d4-45c9-ab53-8f77d01fa5e9','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-06','10:12:47','U不温度我不','英文版祖辈','赢单','长春市南关区财富领域',NULL,'88888888','c8a5a40f-5537-4b42-94d1-27c81b462b4c',NULL,NULL,NULL,NULL,NULL),('39ec4765-f8d1-467a-a434-13db8b0fac32','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','17:08:12','失败','就喜欢上课','订单失败','未获取到位置信息','1468400892491.png','100','6e2044cc-86b8-454b-ad0c-3df3b1f12bc0',NULL,NULL,NULL,NULL,NULL),('5c819d74-d9a8-4f65-8eab-81d4925fd949','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:38:04','判断','真的假的','','长春市南关区财富领域','1468395484454.png','500','88368f76-0b56-4695-b88d-5c7f0d5f2274',NULL,NULL,NULL,NULL,NULL),('7665017c-d5b9-48ea-b5b8-9e04b67b1e67','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','17:05:16','财富中心订单','不就','赢单','未获取到位置信息','1468400716209.png','18','19dd4bcb-f791-4f28-9f87-fa5fc1288e6e',NULL,NULL,NULL,NULL,NULL),('970511c7-1793-4b33-8186-e04d7e4a58c2','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:43:39','低调低调','哈哈哈哈古古怪怪','赢单','长春市南关区财富领域','1468395425164.png','20000','877a536b-558f-4931-8976-b02ff177e8c3',NULL,NULL,NULL,NULL,NULL),('a1c662d6-649a-486e-b658-cc059a931630','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:36:20','我改了','改了吗','跟进中','长春市南关区财富领域','',NULL,'9b473027-14a7-4674-9654-bbf0a36970f8',NULL,NULL,NULL,NULL,NULL),('bbca77c8-f3c5-4d15-818d-693246d9d007','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-14','09:42:37','当饭吃','嘻嘻嘻这些','赢单','未获取到位置信息','1468460557007.png','500','9b98f509-0e7b-4353-b399-dcebcfd50269',NULL,NULL,NULL,NULL,NULL),('ca2edf32-f47a-4b1a-9a28-5eb092cc9e04','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-11','13:23:39',NULL,NULL,'','长春市南关区财富领域','',NULL,'3d2f60f6-81b0-424d-880c-ab8e446213bd',NULL,NULL,NULL,NULL,NULL),('cdfa6b3d-dabd-4ac8-8d73-186f560e7c63','52aeccc8-d962-4b15-bef2-42e2684ebb15','2016-07-14','11:34:30','ghb','ghg','跟进中','未获取到位置信息','1468467270828.png',NULL,'1f7b2383-3dcb-4443-bab3-47cd946c2c24',NULL,NULL,NULL,NULL,NULL),('ef2c0cb8-19b8-43b0-9122-f05cfd7b6394','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:53:01','签单','签单500元','跟进中','长春市南关区财富领域','','1000','a020c9dc-9dcf-451a-85d4-642b04ea4587',NULL,NULL,NULL,NULL,NULL),('f29a7087-b121-4bcf-9c6e-76895d59b6c5','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:33:35','还阔以','有土豪','跟进中','长春市南关区财富领域','',NULL,'98f96cf8-68a7-46c9-9d8a-c12260fb8c6d',NULL,NULL,NULL,NULL,NULL),('f9301066-4480-49b4-b968-9aa42b07d0f6','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:33:35','还阔以','有土豪','跟进中','长春市南关区财富领域','',NULL,'bf607a62-794a-4c96-9785-a79021d7c54e',NULL,NULL,NULL,NULL,NULL),('fc4998ed-b4ce-4063-96c5-0cc9d957c091','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-07','10:06:39','fcyrxxe','付车费重复粗大u付发二姐夫u度 复u付许多许多许多学信网','跟进中','长春市南关区财富领域',NULL,'6000000','e9e6daf1-c982-42f7-9e05-f61015c36e5c;c8a5a40f-5537-4b42-94d1-27c81b462b4c','0024efc2-9d2c-43ee-af51-76b294fd60ec',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_businesslist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_businesslist_deal`
--

DROP TABLE IF EXISTS `tf_businesslist_deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_businesslist_deal` (
  `bld_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `bl_id` varchar(50) DEFAULT NULL,
  `bld_data` varchar(50) DEFAULT NULL,
  `bld_time` varchar(50) DEFAULT NULL,
  `bld_content` varchar(100) DEFAULT NULL,
  `bld_status` varchar(50) DEFAULT NULL,
  `bld_place` varchar(100) DEFAULT NULL,
  `bld_money` varchar(50) DEFAULT NULL,
  `bld_visitData` varchar(50) DEFAULT NULL,
  `bld_visit` varchar(50) DEFAULT NULL,
  `bld_spare1` varchar(100) DEFAULT NULL,
  `bld_spare2` varchar(100) DEFAULT NULL,
  `bld_spare3` varchar(100) DEFAULT NULL,
  `bld_spare4` varchar(100) DEFAULT NULL,
  `bld_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bld_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_businesslist_deal`
--

LOCK TABLES `tf_businesslist_deal` WRITE;
/*!40000 ALTER TABLE `tf_businesslist_deal` DISABLE KEYS */;
INSERT INTO `tf_businesslist_deal` VALUES ('019db3bb-94d6-4d5c-b7db-099fc698e3b9','c43344b3-9148-433e-b609-cb700483a7b7','2a774840-79d4-45c9-ab53-8f77d01fa5e9','2016-07-06','10:12:47','i歌词好吃哦次哈戳戳vvu','赢单','长春市南关区财富领域','88888888','2016-12-12 12:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('08ff9639-2957-4823-90a4-cab18e1d15d2','c43344b3-9148-433e-b609-cb700483a7b7','970511c7-1793-4b33-8186-e04d7e4a58c2','2016-07-13','15:43:39','度夫妇符合豆腐夫妇到宿舍多余的是的遥远的她衷心度夫妇符合豆腐夫妇到宿舍多余的是的遥远的她衷心感谢','赢单','长春市南关区财富领域','20000',NULL,'',NULL,NULL,NULL,NULL,NULL),('0cc70049-c68f-4f8b-baf6-7979cb49e417','c43344b3-9148-433e-b609-cb700483a7b7','02914713-dc1f-4e27-84ad-a8bca0977aeb','2016-07-13','17:06:22','刚看出来','赢单',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('1dd29ae6-762d-4c79-bcf8-d5cb59a763de','c43344b3-9148-433e-b609-cb700483a7b7','970511c7-1793-4b33-8186-e04d7e4a58c2','2016-07-13','15:37:05','明天回访','跟进中',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('4011d266-a1ee-491d-9280-fa4ec7199ec3','c43344b3-9148-433e-b609-cb700483a7b7','07eab8c1-6f5c-427d-9e2d-6218e2d44178','2016-07-14','11:33:07','个雨v吃','赢单',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('6193b6f2-b5e3-4dd4-a7b3-be91fe1497b8','c43344b3-9148-433e-b609-cb700483a7b7','2a774840-79d4-45c9-ab53-8f77d01fa5e9','2016-07-05','15:51:00','紧紧','赢单',NULL,NULL,'2016-09-29 07:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('622bb473-6687-4af3-9174-4d4a8881b284','c43344b3-9148-433e-b609-cb700483a7b7','a1c662d6-649a-486e-b658-cc059a931630','2016-07-09','16:36:20','你很多','跟进中',NULL,NULL,'2016-08-02 02:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('69a95b07-7cd5-45b2-9cc5-c9ad5797bbd9','c43344b3-9148-433e-b609-cb700483a7b7','fc4998ed-b4ce-4063-96c5-0cc9d957c091','2016-07-07','10:06:39','','','长春市南关区财富领域',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('7efdf440-4070-46b2-b82f-1c481b984ccc','c43344b3-9148-433e-b609-cb700483a7b7','ca2edf32-f47a-4b1a-9a28-5eb092cc9e04','2016-07-11','13:23:39','深呼吸','',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('81836905-5ce5-4b10-81aa-51338f1e52f9','c43344b3-9148-433e-b609-cb700483a7b7','fc4998ed-b4ce-4063-96c5-0cc9d957c091','2016-07-06','16:17:59','么个金凤凰直接关系欧豪吃哦花擦哦花擦开个车小欧很喜欢','跟进中','长春市南关区财富领域',NULL,'2208-08-26 23:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('956728bb-9232-4b4a-9380-8ff537f5e26f','c43344b3-9148-433e-b609-cb700483a7b7','7665017c-d5b9-48ea-b5b8-9e04b67b1e67','2016-07-13','17:05:16','回来','赢单',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('98feff71-a443-466d-9bd8-3f57fb7b8fc0','52aeccc8-d962-4b15-bef2-42e2684ebb15','cdfa6b3d-dabd-4ac8-8d73-186f560e7c63','2016-07-14','11:34:30','','跟进中',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('9f5de29d-0e86-48f7-9833-2f6ff646f363','c43344b3-9148-433e-b609-cb700483a7b7','ef2c0cb8-19b8-43b0-9122-f05cfd7b6394','2016-07-09','16:53:01','下次见面什么时候','跟进中',NULL,NULL,'2016-05-06 30:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('a7c5ed06-868f-460a-9556-f2fdcbcb5697','c43344b3-9148-433e-b609-cb700483a7b7','970511c7-1793-4b33-8186-e04d7e4a58c2','2016-07-13','15:43:33','度夫妇符合豆腐夫妇到宿舍多余的是的遥远的她衷心度夫妇符合豆腐夫妇到宿舍多余的是的遥远的她衷心感谢','赢单','长春市南关区财富领域','20000',NULL,'',NULL,NULL,NULL,NULL,NULL),('aadcf390-5ac1-4b90-933e-310555cd1502','c43344b3-9148-433e-b609-cb700483a7b7','bbca77c8-f3c5-4d15-818d-693246d9d007','2016-07-14','09:42:37','','赢单',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('b53e771c-e680-43c1-8369-7ee81e3c9ac9','c43344b3-9148-433e-b609-cb700483a7b7','f9301066-4480-49b4-b968-9aa42b07d0f6','2016-07-09','16:33:35','好的好的回电话','跟进中',NULL,NULL,'2016-03-12 45:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('b7b1693e-76df-4c08-a42b-b56eccf32741','c43344b3-9148-433e-b609-cb700483a7b7','39ec4765-f8d1-467a-a434-13db8b0fac32','2016-07-13','17:08:12','老师不管','订单失败',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('bb55a27a-c04b-4586-ad0a-6a184d2df66f','c43344b3-9148-433e-b609-cb700483a7b7','f29a7087-b121-4bcf-9c6e-76895d59b6c5','2016-07-09','16:33:35','好的好的回电话','跟进中',NULL,NULL,'2016-03-12 45:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('c90246e2-b5fe-4b20-9b9d-0a09aad0c124','c43344b3-9148-433e-b609-cb700483a7b7','fc4998ed-b4ce-4063-96c5-0cc9d957c091','2016-07-05','15:36:14','复发会飞飞讽刺台词套餐覅','赢单',NULL,NULL,'2016-09-09 09:00:00','提醒',NULL,NULL,NULL,NULL,NULL),('d39e8fa2-66b8-4575-8c09-c40b5027572d','c43344b3-9148-433e-b609-cb700483a7b7','fc4998ed-b4ce-4063-96c5-0cc9d957c091','2016-07-06','10:18:03','发现了很喜欢喜欢','跟进中','长春市南关区财富领域',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('f80c5a4a-1123-4d13-a840-833d45bd2f5a','c43344b3-9148-433e-b609-cb700483a7b7','5c819d74-d9a8-4f65-8eab-81d4925fd949','2016-07-13','15:38:04','好的好的','',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_businesslist_deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_businessreport`
--

DROP TABLE IF EXISTS `tf_businessreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_businessreport` (
  `br_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `br_data` varchar(50) DEFAULT NULL,
  `br_time` varchar(50) DEFAULT NULL,
  `br_name` varchar(100) DEFAULT NULL,
  `br_content` varchar(100) DEFAULT NULL,
  `br_status` varchar(100) DEFAULT NULL,
  `br_place` varchar(100) DEFAULT NULL,
  `br_photo` varchar(100) DEFAULT NULL,
  `br_money` varchar(100) DEFAULT NULL,
  `br_spare1` varchar(100) DEFAULT NULL,
  `br_spare2` varchar(100) DEFAULT NULL,
  `br_spare3` varchar(100) DEFAULT NULL,
  `br_spare4` varchar(100) DEFAULT NULL,
  `br_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`br_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_businessreport`
--

LOCK TABLES `tf_businessreport` WRITE;
/*!40000 ALTER TABLE `tf_businessreport` DISABLE KEYS */;
INSERT INTO `tf_businessreport` VALUES ('083b4024-d7f4-495c-afc5-4c2d3b76024e','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:34:13','测试订单','50000000','业务拜访','长春市南关区财富领域','1468395253212.png',NULL,NULL,NULL,NULL,NULL,NULL),('0bec0962-fbb1-479d-9284-9a56d9096a94','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','17:01:40','','','订单业绩','长春市南关区财富领域',NULL,'','06efc018-64d7-4824-882c-fd3eb834fe60',NULL,NULL,NULL,NULL),('0e41724a-2239-4f7f-ab75-6ce6cb5d61b2','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','17:01:46','','','订单业绩','长春市南关区财富领域',NULL,'','1b42a6d8-d391-4d50-89d2-6db5665b0722',NULL,NULL,NULL,NULL),('0fc0f871-e203-4688-addc-1c1ea1410276','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','17:02:04','','','订单业绩','长春市南关区财富领域',NULL,'','d3804045-ac9b-4450-bda4-1af2b6f5f321',NULL,NULL,NULL,NULL),('1165567e-e007-4702-94f5-e9434482aa43','52aeccc8-d962-4b15-bef2-42e2684ebb15','2016-07-14','11:34:19','R语音','v句u就不','业务拜访','未获取到位置信息','1468467259049.png',NULL,NULL,NULL,NULL,NULL,NULL),('1fa98515-767a-4288-b848-39ba3204ba3c','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','15:02:26','','','业务拜访','长春市南关区财富领域',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('21564dd0-475b-4378-b095-6d9de314a561','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-11','11:26:52','','','业务拜访','长春市南关区财富领域','1468207612929.png',NULL,NULL,NULL,NULL,NULL,NULL),('27f4552b-174a-48e4-bab3-65a9b7002d87','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:49:29','拜访财富领域','今天财富领域拜访完','业务拜访','长春市南关区财富领域','1468054169128.png',NULL,NULL,NULL,NULL,NULL,NULL),('2ee25d50-e358-4a7d-9a77-3072631a1b66','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-12','13:50:14','','','业务拜访','长春市南关区财富领域','1468302614182.png',NULL,NULL,NULL,NULL,NULL,NULL),('358ff857-ea57-47fc-9799-55d8050446f4','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-05','09:12:52','测试图片','像不像你星级酒店你喜欢喜欢喜欢别迟到回电话不错不错不放假','业务拜访','长春市南关区财富领域','',NULL,NULL,NULL,NULL,NULL,NULL),('35c72333-92a3-4c89-bece-346e4a54451f','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:34:11','测试订单','50000000','业务拜访','长春市南关区财富领域','1468395251714.png',NULL,NULL,NULL,NULL,NULL,NULL),('3c48a22a-e1bd-4ead-8f83-6f4370fa10a7','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:34:14','测试订单','50000000','业务拜访','长春市南关区财富领域','1468395254426.png',NULL,NULL,NULL,NULL,NULL,NULL),('3c968ca7-fcd7-4832-a764-0385187d26ff','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','16:59:12','你in啊想你爱心啊必须','就是不说帮你想你呢，安心那些年希不希望补习班无锡小傻逼下班','订单业绩','未获取到位置信息',NULL,'3000','3ebb0705-9745-47e5-8e7d-b7cfbb2657a9','9e6c6443-6c81-4996-bc76-946e7a3037dd',NULL,NULL,NULL),('458dd7c5-0bf1-4ac1-9017-73cfb207a421','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-14','11:33:43','不被','v好v聚不聚','业务拜访','长春市南关区财富领域','1468467223965.png',NULL,NULL,NULL,NULL,NULL,NULL),('4c3e9fac-da85-4d2c-b75e-5c1c63f631df','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:24:26','','','业务拜访','长春市南关区财富领域','1468052666444.png',NULL,NULL,NULL,NULL,NULL,NULL),('51104026-5ef7-48ed-9d4e-085f788ae47e','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:30:36','四天第几个打飞机坐飞机转发惊喜估计','提打几个字几分钟几分钟飞机这几个洗干净坐飞机下飞机','业务拜访','长春市南关区财富领域','1468395036538.png',NULL,NULL,NULL,NULL,NULL,NULL),('55460c47-a25c-4a3c-bc74-1f7128690847','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:34:15','测试订单','50000000','业务拜访','长春市南关区财富领域','1468395255697.png',NULL,NULL,NULL,NULL,NULL,NULL),('6524f93c-e5b2-4155-84fc-50d6979ffe52','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-01','08:30:23','','','订单业绩','长春市南关区财富领域',NULL,'','a296f2e4-f2a3-42c5-bb6c-5e6ac39c2889','5c8f39fd-d7b7-4b02-83c4-2658ea426bf2',NULL,NULL,NULL),('655ccff2-ceea-44f4-8d73-4d972eb70a1c','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-05','15:31:10','顾东西还不下班五百字','在v还是v在维基百科酒吧惊喜不急我爸自己吧自己我爸本地我鼻子我爸租必须必对比行不必须要','订单业绩','长春市南关区财富领域',NULL,'884404846',NULL,NULL,NULL,NULL,NULL),('66b3df29-0398-4787-ab69-f37b9c20ea3c','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:34:11','测试订单','50000000','业务拜访','长春市南关区财富领域','1468395251342.png',NULL,NULL,NULL,NULL,NULL,NULL),('676f29c5-1f35-4f37-b73e-70552d0b7c7f','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','17:04:16','完成财富业绩','拜访了长春西站','业务拜访','未获取到位置信息','1468400656137.png',NULL,NULL,NULL,NULL,NULL,NULL),('7196a491-c163-4563-8322-50d84962e20f','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-01','08:33:01','啊哈哈哈','我和我和分(⊙o⊙)… 恶搞饿和服务去桂林路 我v土特产','订单业绩','长春市南关区财富领域',NULL,'','45cb21a6-5a7d-4676-952c-6bd8f006c7c8',NULL,NULL,NULL,NULL),('73ac958f-582d-4b67-93a3-0e966dc673a0','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','15:08:50','','','业务拜访','长春市南关区财富领域',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('846d52ca-7b30-4b79-80b1-871ea204b7bb','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','17:01:48','','','订单业绩','长春市南关区财富领域',NULL,'','35c05a34-5bf2-4b88-a962-52ba1587a7e4',NULL,NULL,NULL,NULL),('8ea4faae-802e-44af-8185-16e1c9d47f65','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:31:14','哈哈哈哈古古怪怪','哈哈哈还好还好哈','业务拜访','长春市南关区财富领域','1468395074804.png',NULL,NULL,NULL,NULL,NULL,NULL),('966b09d4-88b8-42cf-9d17-d7e39a7626f9','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-05','16:03:54','回到家就是','不想难兄难弟男的女的','业务拜访','长春市南关区财富领域',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('9b815255-9737-4c35-874e-42a84e53b976','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','17:01:08','','','业务拜访','未获取到位置信息',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('9daa2c76-79ce-4a9c-9271-1b810a770100','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:32:08','哈哈哈哈','老子今天签了500万','业务拜访','长春市南关区财富领域','1468053128408.png',NULL,NULL,NULL,NULL,NULL,NULL),('9e0a948b-5a8a-4066-9701-3be677a88ee0','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','15:41:25','不上班睡觉睡觉','v是不是不上班那你在','业务拜访','长春市南关区财富领域','1468050085325.png',NULL,NULL,NULL,NULL,NULL,NULL),('9e197e10-f99d-4077-97c9-0e966a2cd87c','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','14:44:26','腥风血雨','ooxx','业务拜访','长春市南关区财富领域','',NULL,NULL,NULL,NULL,NULL,NULL),('b0216742-8657-4f7e-848a-f3dbfebd3ada','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:48:23','拜访','你说呢苦中苦','业务拜访','长春市南关区财富领域','1468054103039.png',NULL,NULL,NULL,NULL,NULL,NULL),('d8ad68c2-d2dc-4355-be02-c9b42c8318b7','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','17:01:47','','','订单业绩','长春市南关区财富领域',NULL,'','a8560bbb-bb77-4a91-96f8-3fe9882b157d',NULL,NULL,NULL,NULL),('def1936a-b65d-4720-9cc8-34c30250a0ea','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-05','15:30:26','他此言差矣','波规格i个i滚粗翡翠谷复复，7，以后，一定，7，7，复42杜，杜，复，','业务拜访','长春市南关区财富领域',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('dfb22918-9c7a-4a8e-9177-cc3f990a5345','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-05','15:35:11','','','业务拜访','长春市南关区财富领域',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('f11cc745-d76f-4c43-85a6-5b310915783f','c43344b3-9148-433e-b609-cb700483a7b7','2016-06-30','17:01:39','','','订单业绩','长春市南关区财富领域',NULL,'','329b4c4f-f8d4-427c-980b-f9eb76ac3418',NULL,NULL,NULL,NULL),('fedc5512-0c36-4b6a-870c-bb6ef1be8191','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','16:30:14','#吱口令#长按','吓屎姐姐了','业务拜访','长春市南关区财富领域','1468053014404.png',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_businessreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_common`
--

DROP TABLE IF EXISTS `tf_common`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_common` (
  `common_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `common_content` varchar(500) DEFAULT NULL,
  `common_spare1` varchar(100) DEFAULT NULL,
  `common_spare2` varchar(100) DEFAULT NULL,
  `common_spare3` varchar(100) DEFAULT NULL,
  `common_spare4` varchar(100) DEFAULT NULL,
  `common_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`common_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_common`
--

LOCK TABLES `tf_common` WRITE;
/*!40000 ALTER TABLE `tf_common` DISABLE KEYS */;
INSERT INTO `tf_common` VALUES ('0cdc1291-2521-474b-80aa-a7464c3596c5','c43344b3-9148-433e-b609-cb700483a7b7','啊啊啊',NULL,NULL,NULL,NULL,NULL),('2c4dd06f-7a18-42f7-85f6-3b4c9cf0fd44','c43344b3-9148-433e-b609-cb700483a7b7','西单',NULL,NULL,NULL,NULL,NULL),('b4d61028-7925-4c9e-9598-fe9f6d82d3d3','c43344b3-9148-433e-b609-cb700483a7b7','天安门',NULL,NULL,NULL,NULL,NULL),('e3d48cf3-dddb-41dc-93e6-8269b51ed84b','c43344b3-9148-433e-b609-cb700483a7b7','南湖公园',NULL,NULL,NULL,NULL,NULL),('e91c84db-e164-4056-a5e0-a352dbbda3c3','c43344b3-9148-433e-b609-cb700483a7b7','长春西站',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_common` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_customer`
--

DROP TABLE IF EXISTS `tf_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_customer` (
  `customer_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `customer_complete` varchar(50) DEFAULT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `customer_phone` varchar(50) DEFAULT NULL,
  `customer_company` varchar(50) DEFAULT NULL,
  `customer_address` varchar(100) DEFAULT NULL,
  `customer_sex` varchar(50) DEFAULT NULL,
  `customer_marry` varchar(50) DEFAULT NULL,
  `customer_photo` varchar(100) DEFAULT NULL,
  `customer_mobile` varchar(50) DEFAULT NULL,
  `customer_birthday` varchar(50) DEFAULT NULL,
  `customer_bir` varchar(50) DEFAULT NULL,
  `customer_birdate` varchar(50) DEFAULT NULL,
  `customer_like` varchar(100) DEFAULT NULL,
  `customer_status` varchar(50) DEFAULT NULL,
  `customer_job` varchar(50) DEFAULT NULL,
  `customer_remark` varchar(100) DEFAULT NULL,
  `customer_allmoney` varchar(50) DEFAULT NULL,
  `customer_spare1` varchar(100) DEFAULT NULL,
  `customer_spare2` varchar(100) DEFAULT NULL,
  `customer_spare3` varchar(100) DEFAULT NULL,
  `customer_spare4` varchar(100) DEFAULT NULL,
  `customer_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_customer`
--

LOCK TABLES `tf_customer` WRITE;
/*!40000 ALTER TABLE `tf_customer` DISABLE KEYS */;
INSERT INTO `tf_customer` VALUES ('06efc018-64d7-4824-882c-fd3eb834fe60','c43344b3-9148-433e-b609-cb700483a7b7','0','000','111','222','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','3',NULL,NULL,'2016-07-01',NULL),('0d21ed22-a9ea-45dd-8c7d-9daff701089c','c43344b3-9148-433e-b609-cb700483a7b7','0','这事','','','长春市南关区财富领域','男','','1468396162824.png','13578965423','','','1','','7557','','','0','0',NULL,NULL,'2016-07-13',NULL),('19dd4bcb-f791-4f28-9f87-fa5fc1288e6e','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-13',NULL),('1b42a6d8-d391-4d50-89d2-6db5665b0722','c43344b3-9148-433e-b609-cb700483a7b7','1','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','5',NULL,NULL,'2016-07-01',NULL),('1e0b0ee9-4b48-4a90-937e-759350001fa7','c43344b3-9148-433e-b609-cb700483a7b7','0','喜欢吃吃就吃','','','长春市南关区财富领域','男','','1468050612233.png','18646456665','','','1','','','','','0','0',NULL,NULL,'2016-07-09',NULL),('1f7b2383-3dcb-4443-bab3-47cd946c2c24','52aeccc8-d962-4b15-bef2-42e2684ebb15','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-14',NULL),('2ad88b87-2c0a-4ad8-96bd-879baaa3ec82','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-14',NULL),('329b4c4f-f8d4-427c-980b-f9eb76ac3418','c43344b3-9148-433e-b609-cb700483a7b7','0','777','888','999','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','1',NULL,NULL,'2016-07-01',NULL),('35c05a34-5bf2-4b88-a962-52ba1587a7e4','c43344b3-9148-433e-b609-cb700483a7b7','1','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','2',NULL,NULL,'2016-07-01',NULL),('3d2f60f6-81b0-424d-880c-ab8e446213bd','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-11',NULL),('3d3d014b-2c96-40f9-ac1b-49b81fea0998','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-13',NULL),('3ebb0705-9745-47e5-8e7d-b7cfbb2657a9','c43344b3-9148-433e-b609-cb700483a7b7','1','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','0',NULL,NULL,'2016-07-01',NULL),('45cb21a6-5a7d-4676-952c-6bd8f006c7c8','c43344b3-9148-433e-b609-cb700483a7b7','1','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','2',NULL,NULL,'2016-07-01',NULL),('580b84bd-d056-4ebf-9e70-c2090fc7bde7','c43344b3-9148-433e-b609-cb700483a7b7','0','','','','长春市南关区财富领域','男','',NULL,'10086','','','1','爱吃、爱睡、还比较懒！','多次合作','','','0','0',NULL,NULL,'2016-07-01',NULL),('5b2d39d4-66ab-49ce-bdbc-5cb415ad3836','c43344b3-9148-433e-b609-cb700483a7b7','0','gxhxgxw','','','长春市南关区财富领域','男','',NULL,'','','','1','和576576542','','','','0','0',NULL,NULL,'2016-07-09',NULL),('6e2044cc-86b8-454b-ad0c-3df3b1f12bc0','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-13',NULL),('768c70be-7f22-4274-b037-ce2bc03c895b','c43344b3-9148-433e-b609-cb700483a7b7','0','胖哥','043094994','是不是八点半','长春市南关区财富领域','男','',NULL,'188888888','9.9','是','1','大宝贝但并不是半死不活','愿赌服输','V棒棒哒宝贝','v是不是并不是宝贝','0','0',NULL,NULL,'2016-07-01',NULL),('877a536b-558f-4931-8976-b02ff177e8c3','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-13',NULL),('88368f76-0b56-4695-b88d-5c7f0d5f2274','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-13',NULL),('98f96cf8-68a7-46c9-9d8a-c12260fb8c6d','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-09',NULL),('9b473027-14a7-4674-9654-bbf0a36970f8','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-09',NULL),('9b98f509-0e7b-4353-b399-dcebcfd50269','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-14',NULL),('a020c9dc-9dcf-451a-85d4-642b04ea4587','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-09',NULL),('a296f2e4-f2a3-42c5-bb6c-5e6ac39c2889','c43344b3-9148-433e-b609-cb700483a7b7','1','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','1',NULL,NULL,'2016-07-01',NULL),('a4c7194c-ba96-4cb3-ada8-8b423a61f1c4','c43344b3-9148-433e-b609-cb700483a7b7','0','大王','','','长春市南关区财富领域','男','','1468053466383.png','','','','1','','','','','0','0',NULL,NULL,'2016-07-09',NULL),('a8560bbb-bb77-4a91-96f8-3fe9882b157d','c43344b3-9148-433e-b609-cb700483a7b7','1','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','123123','9',NULL,NULL,'2016-07-01',NULL),('aa72202a-5b6a-4577-b267-6fb81e08d06a','c43344b3-9148-433e-b609-cb700483a7b7','0','大王','','','长春市南关区财富领域','男','','1468053469114.png','','','','1','','','','','0','0',NULL,NULL,'2016-07-09',NULL),('adcaab00-d480-4ff0-a634-5b1f03f4c4bc','c43344b3-9148-433e-b609-cb700483a7b7','0','胖哥','043094994','是不是八点半','长春市南关区财富领域','男','已婚',NULL,'188888888','9.9','是',NULL,'大宝贝但并不是半死不活','愿赌服输','V棒棒哒宝贝','v是不是并不是宝贝','99999','0',NULL,NULL,'2016-07-01',NULL),('bf607a62-794a-4c96-9785-a79021d7c54e','c43344b3-9148-433e-b609-cb700483a7b7','1','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-09',NULL),('c8a5a40f-5537-4b42-94d1-27c81b462b4c','c43344b3-9148-433e-b609-cb700483a7b7','1','aaa','bbb','ccc','ddd','123','321',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-01',NULL),('d3804045-ac9b-4450-bda4-1af2b6f5f321','c43344b3-9148-433e-b609-cb700483a7b7','1','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','0','1',NULL,NULL,'2016-07-01',NULL),('e9e6daf1-c982-42f7-9e05-f61015c36e5c','c43344b3-9148-433e-b609-cb700483a7b7','1','yyy','uuu','iii','ooo','ppp','mmm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0','1',NULL,NULL,'2016-07-01',NULL),('fba29850-c717-4853-a21c-5ee7539bf67c','c43344b3-9148-433e-b609-cb700483a7b7','0','呵呵哈哈哈','','','长春市南关区财富领域','','','1468396597212.png','','','','1','','','','','0','0',NULL,NULL,'2016-07-13',NULL);
/*!40000 ALTER TABLE `tf_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_jobplan`
--

DROP TABLE IF EXISTS `tf_jobplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_jobplan` (
  `jp_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `jp_data` varchar(50) DEFAULT NULL,
  `jp_time` varchar(50) DEFAULT NULL,
  `jp_time1` varchar(50) DEFAULT NULL,
  `jp_time2` varchar(50) DEFAULT NULL,
  `jp_title` varchar(50) DEFAULT NULL,
  `jp_remark` varchar(100) DEFAULT NULL,
  `jp_spare1` varchar(100) DEFAULT NULL,
  `jp_spare2` varchar(100) DEFAULT NULL,
  `jp_spare3` varchar(100) DEFAULT NULL,
  `jp_spare4` varchar(100) DEFAULT NULL,
  `jp_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`jp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_jobplan`
--

LOCK TABLES `tf_jobplan` WRITE;
/*!40000 ALTER TABLE `tf_jobplan` DISABLE KEYS */;
INSERT INTO `tf_jobplan` VALUES ('05e6c498-8a80-48f8-9d7c-5e1c8b3cdee8','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-11','16:36:16','2016-01-01','2016-02-01','雨羊城通','顾不太长途车v鱼鱼u',NULL,NULL,NULL,NULL,NULL),('2464ddb4-ed4f-4542-99d8-5d47cc9a090a','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-06','15:56:04','2016-06-09','2017-08-09','心情蛋炒饭起床',NULL,NULL,NULL,NULL,NULL,NULL),('3a81fec4-a0bc-4478-8433-4b469c7e2906','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-06','16:51:45','1855-10-10','2056-12-25','跟你说合格','苏DVDv对吧办法发不发吃吧发吧有呀呀呀的DVD不那你不不不v吃方法',NULL,NULL,NULL,NULL,NULL),('45332bf7-6f68-427a-88a3-37b3e8c34e7e','52aeccc8-d962-4b15-bef2-42e2684ebb15','2017-07-24','15:30:12','2017-07-24','2017-07-24','asdad','xcxzc',NULL,NULL,NULL,NULL,NULL),('532c21ad-0404-4e83-bb7c-1de9e17f2831','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-06','16:48:10','--','--','',NULL,NULL,NULL,NULL,NULL,NULL),('5a5fa9ba-123e-46bb-b8a9-aeea8a9280ee','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-13','15:52:24','2016-01-01','2016-07-01','吃醋重新','滚滚滚古古怪怪嗯啦皮，来啦来啦来啦吗？非常非常吃完额度非常宣传册反反复复反反复复非常差吃吃吃vvvvvv吃吃醋吃吃吃',NULL,NULL,NULL,NULL,NULL),('70c32e4b-bbf8-41b7-8f35-dd27349130cb','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-06','16:49:12','2019-11-11','2020-11-11','好的吧不行不行不',NULL,NULL,NULL,NULL,NULL,NULL),('8a8a174a-4303-48fe-9610-becc2be75e1c','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-11','16:37:45','2016-01-01','2016-02-01','很快','不理不必哔哔哔比不',NULL,NULL,NULL,NULL,NULL),('b3197f05-5df5-4c78-bd20-819c930ab1b4','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-09','13:53:33','--','--','','',NULL,NULL,NULL,NULL,NULL),('cc388e6d-01dd-4914-a437-ca9efd575c4d','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-06','15:58:23','2016-03-26','2019-02-20','赤壁床头',NULL,NULL,NULL,NULL,NULL,NULL),('f754f851-8aed-4649-bfff-68a085094487','c43344b3-9148-433e-b609-cb700483a7b7','2016-07-11','16:35:34','2018-11-18','2019-11-01','哦了哦了哦了哦了','嘻嘻哈哈嘿嘿呵呵',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_jobplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_loss`
--

DROP TABLE IF EXISTS `tf_loss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_loss` (
  `loss_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `loss_name` varchar(100) DEFAULT NULL,
  `loss_money` varchar(50) DEFAULT NULL,
  `loss_status` varchar(50) DEFAULT NULL,
  `loss_end` varchar(50) DEFAULT NULL,
  `loss_spare1` varchar(100) DEFAULT NULL,
  `loss_spare2` varchar(100) DEFAULT NULL,
  `loss_spare3` varchar(100) DEFAULT NULL,
  `loss_spare4` varchar(100) DEFAULT NULL,
  `loss_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`loss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_loss`
--

LOCK TABLES `tf_loss` WRITE;
/*!40000 ALTER TABLE `tf_loss` DISABLE KEYS */;
INSERT INTO `tf_loss` VALUES ('0024efc2-9d2c-43ee-af51-76b294fd60ec','c43344b3-9148-433e-b609-cb700483a7b7','5555','69858','杂费',NULL,NULL,'2016-07-05',NULL,NULL,NULL),('025bf65f-21ee-4ae8-9a0e-c89d579cb2c2','52aeccc8-d962-4b15-bef2-42e2684ebb15','测试一','94363','业务',NULL,NULL,'2016-07-14',NULL,NULL,NULL),('175c92c4-1536-4d66-aeee-f3c8cba96e91','c43344b3-9148-433e-b609-cb700483a7b7','0','900','业务',NULL,NULL,'2016-07-08',NULL,NULL,NULL),('1f708a79-1eb5-49aa-8cdc-189d86fe14de','c43344b3-9148-433e-b609-cb700483a7b7','看过吃吃吃欧皓辰','0','业务',NULL,NULL,'2016-07-13',NULL,NULL,NULL),('23596994-1328-4b6b-b3ad-15a29e6a789f','c43344b3-9148-433e-b609-cb700483a7b7','信口开河','1394','业务',NULL,NULL,'2016-07-08',NULL,NULL,NULL),('35db2055-33ed-4669-ab65-d5adcab0bad6','c43344b3-9148-433e-b609-cb700483a7b7','滚滚滚古古怪怪','58','业务',NULL,NULL,'2016-07-13',NULL,NULL,NULL),('38595157-d5ca-4929-921e-7d7bae2bb0c1','c43344b3-9148-433e-b609-cb700483a7b7','111111','1111','业务',NULL,NULL,'2016-07-13',NULL,NULL,NULL),('5c8f39fd-d7b7-4b02-83c4-2658ea426bf2','c43344b3-9148-433e-b609-cb700483a7b7','现在在','78','杂费',NULL,NULL,'2016-07-05',NULL,NULL,NULL),('6943190a-651e-4282-870c-366803784ff4','c43344b3-9148-433e-b609-cb700483a7b7','滚滚滚古古怪怪','113','业务',NULL,NULL,'2016-07-13',NULL,NULL,NULL),('76750a0b-a299-48ec-b28c-d7f8ce11501c','c43344b3-9148-433e-b609-cb700483a7b7','信口开河','1444','杂费',NULL,NULL,'2016-07-08',NULL,NULL,NULL),('802bbbe1-6bb5-4ce9-94cb-fc6c2e8c6e37','c43344b3-9148-433e-b609-cb700483a7b7','看过吃吃吃欧皓辰','0','业务',NULL,NULL,'2016-07-13',NULL,NULL,NULL),('93f0aa8f-47ab-4aa9-9460-f2e5721ed8d9','52aeccc8-d962-4b15-bef2-42e2684ebb15','','0','业务',NULL,NULL,'2016-07-14',NULL,NULL,NULL),('9e6c6443-6c81-4996-bc76-946e7a3037dd','c43344b3-9148-433e-b609-cb700483a7b7','家啊小姐','300','业务',NULL,NULL,'2016-07-05',NULL,NULL,NULL),('ad1aa4bb-36dc-4580-b361-e579682c2d18','c43344b3-9148-433e-b609-cb700483a7b7','滚滚滚古古怪怪','113','业务',NULL,NULL,'2016-07-13',NULL,NULL,NULL),('b545aab5-b827-41da-b03d-7aec7a3bd322','c43344b3-9148-433e-b609-cb700483a7b7','Test3','8773','业务',NULL,NULL,'2016-07-13',NULL,NULL,NULL),('c0b44adf-c6a2-4fec-812f-7027e695a82a','c43344b3-9148-433e-b609-cb700483a7b7','','0','业务',NULL,NULL,'2016-07-11',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_loss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_loss_deal`
--

DROP TABLE IF EXISTS `tf_loss_deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_loss_deal` (
  `ld_id` varchar(50) NOT NULL,
  `loss_id` varchar(50) DEFAULT NULL,
  `ld_money` varchar(50) DEFAULT NULL,
  `ld_remark` varchar(100) DEFAULT NULL,
  `ld_spare1` varchar(100) DEFAULT NULL,
  `ld_spare2` varchar(100) DEFAULT NULL,
  `ld_spare3` varchar(100) DEFAULT NULL,
  `ld_spare4` varchar(100) DEFAULT NULL,
  `ld_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ld_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_loss_deal`
--

LOCK TABLES `tf_loss_deal` WRITE;
/*!40000 ALTER TABLE `tf_loss_deal` DISABLE KEYS */;
INSERT INTO `tf_loss_deal` VALUES ('1c0fc518-cada-4542-a678-fd00f83406a3','35db2055-33ed-4669-ab65-d5adcab0bad6','58','非常吃醋','波规格','2016-07-13 16:26:32',NULL,NULL,NULL),('1cbd6967-ebcb-455f-98d2-f6ae2af192e1','76750a0b-a299-48ec-b28c-d7f8ce11501c','800','发货呢不过关','认个错','2016-07-08 10:42:50',NULL,NULL,NULL),('27c5fd28-36c2-4bb2-ac63-11df7d1c6d4a','38595157-d5ca-4929-921e-7d7bae2bb0c1','1111','11111','111111','2016-07-13 16:28:23',NULL,NULL,NULL),('27d2e52b-a2fa-4100-b250-2092ed615686','b545aab5-b827-41da-b03d-7aec7a3bd322','33','33','333','2016-07-13 16:32:18',NULL,NULL,NULL),('2a13067c-2df0-4be2-9f6d-264968b84f0b','23596994-1328-4b6b-b3ad-15a29e6a789f','800','发货呢不过关','认个错','2016-07-08 10:42:39',NULL,NULL,NULL),('34673b91-4001-4402-a8ad-bc9399645905','025bf65f-21ee-4ae8-9a0e-c89d579cb2c2','566','标点符号','嘿嘿哈哈呵呵','2016-07-14 11:40:42',NULL,NULL,NULL),('45c5000f-8f71-4ea5-8a43-31df68dfdaaa','ad1aa4bb-36dc-4580-b361-e579682c2d18','58','非常吃醋','波规格','2016-07-13 16:26:55',NULL,NULL,NULL),('47d80772-30c8-4b4f-aed6-9b3c6d771f7d','025bf65f-21ee-4ae8-9a0e-c89d579cb2c2','88888','景福宫小鸡小鸡宫心计学习机相机个G8洗复习就发相机相机小嘎这卡学习卡搞砸可惜可惜看关系怪咖显卡','父子关系开心G8IT洗','2016-07-14 11:37:39',NULL,NULL,NULL),('4d069e06-d030-4ece-9437-bcdb0b09d9e3','025bf65f-21ee-4ae8-9a0e-c89d579cb2c2','56','是是是','谢谢','2016-07-14 11:40:52',NULL,NULL,NULL),('5feef7e2-6bb3-4b28-96da-2b901343d528','175c92c4-1536-4d66-aeee-f3c8cba96e91','600','R次语音','套餐语音','2016-07-08 10:36:48',NULL,NULL,NULL),('62e51a57-683a-4431-932d-00436643dfa0','76750a0b-a299-48ec-b28c-d7f8ce11501c','594','百战不殆嫁鸡随鸡','不不不','2016-07-08 10:42:50',NULL,NULL,NULL),('6730c241-0f4a-47d0-9789-c32e96ad476c','ad1aa4bb-36dc-4580-b361-e579682c2d18','55','吃吃吃','吃吃吃','2016-07-13 16:26:55',NULL,NULL,NULL),('72a57020-b4d8-4281-b092-c2cf530cbc05','025bf65f-21ee-4ae8-9a0e-c89d579cb2c2','888','训看看就好和你们看好你你','放假过得更好','2016-07-14 11:37:18',NULL,NULL,NULL),('737c8a75-9bbd-45ce-a462-e91b9ebee044','025bf65f-21ee-4ae8-9a0e-c89d579cb2c2','3965','发货举行','嘻嘻哈哈呵呵','2016-07-14 11:38:07',NULL,NULL,NULL),('874482f9-b733-44fe-a922-3facd83d0ef3','175c92c4-1536-4d66-aeee-f3c8cba96e91','300','春天TVv好v','任性同喜同喜','2016-07-08 10:36:48',NULL,NULL,NULL),('8c460da9-75ed-411d-8b72-2db0c6fa35bd','b545aab5-b827-41da-b03d-7aec7a3bd322','44','44','44','2016-07-13 16:33:13',NULL,NULL,NULL),('a3eec6e9-b490-4857-890e-68abdac210a3','b545aab5-b827-41da-b03d-7aec7a3bd322','8696','嘻嘻嘻好想好想好想好想花擦可惜小嘎显卡显卡呼吸可惜看哈嘻哈可惜对哦一下好辛苦查查看','吃吃吃想开点太辛苦','2016-07-14 11:31:10',NULL,NULL,NULL),('abc','0024efc2-9d2c-43ee-af51-76b294fd60ec','123','ooo','PPP','2016-07-01 10:10:10',NULL,NULL,NULL),('bb09db43-2270-4711-bd7a-008ab7ec6c0e','23596994-1328-4b6b-b3ad-15a29e6a789f','594','百战不殆嫁鸡随鸡','不不不','2016-07-08 10:42:39',NULL,NULL,NULL),('cc5c0116-b6d1-4e69-973c-cb7335389d58','6943190a-651e-4282-870c-366803784ff4','58','非常吃醋','波规格','2016-07-13 16:26:52',NULL,NULL,NULL),('cccc','0024efc2-9d2c-43ee-af51-76b294fd60ec','70','yyy','ttt','2016-07-01 11:10:10',NULL,NULL,NULL),('cccd','0024efc2-9d2c-43ee-af51-76b294fd60ec','88','rrr','ggg','2016-07-01 11:33:10',NULL,NULL,NULL),('d4415ace-b4fd-406f-a301-78ef45bfefc4','76750a0b-a299-48ec-b28c-d7f8ce11501c','50','发语音吃完','参考参考','2016-07-11 16:39:32',NULL,NULL,NULL),('e56cb5a3-453c-47cd-b048-67fcaddcc551','6943190a-651e-4282-870c-366803784ff4','55','吃吃吃','吃吃吃','2016-07-13 16:26:52',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tf_loss_deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_message`
--

DROP TABLE IF EXISTS `tf_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_message` (
  `message_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `message_name` varchar(50) DEFAULT NULL,
  `message_content` varchar(100) DEFAULT NULL,
  `message_time` varchar(50) DEFAULT NULL,
  `message_read` varchar(50) DEFAULT NULL,
  `message_spare1` varchar(100) DEFAULT NULL,
  `message_spare2` varchar(100) DEFAULT NULL,
  `message_spare3` varchar(100) DEFAULT NULL,
  `message_spare4` varchar(100) DEFAULT NULL,
  `message_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_message`
--

LOCK TABLES `tf_message` WRITE;
/*!40000 ALTER TABLE `tf_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `tf_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_task`
--

DROP TABLE IF EXISTS `tf_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_task` (
  `task_id` varchar(50) NOT NULL,
  `task_title` varchar(50) DEFAULT NULL,
  `task_date` varchar(50) DEFAULT NULL,
  `task_month` varchar(50) DEFAULT NULL,
  `task_money` varchar(50) DEFAULT NULL,
  `task_door` varchar(50) DEFAULT NULL,
  `task_remark` varchar(100) DEFAULT NULL,
  `task_part` varchar(50) DEFAULT NULL,
  `task_person` varchar(100) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `task_addtime` varchar(50) DEFAULT NULL,
  `task_spare1` varchar(100) DEFAULT NULL,
  `task_spare2` varchar(100) DEFAULT NULL,
  `task_spare3` varchar(100) DEFAULT NULL,
  `task_spare4` varchar(100) DEFAULT NULL,
  `task_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_task`
--

LOCK TABLES `tf_task` WRITE;
/*!40000 ALTER TABLE `tf_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `tf_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_task_deal`
--

DROP TABLE IF EXISTS `tf_task_deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_task_deal` (
  `td_id` varchar(50) NOT NULL,
  `task_id` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `td_spare1` varchar(100) DEFAULT NULL,
  `td_spare2` varchar(100) DEFAULT NULL,
  `td_spare3` varchar(100) DEFAULT NULL,
  `td_spare4` varchar(100) DEFAULT NULL,
  `td_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`td_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_task_deal`
--

LOCK TABLES `tf_task_deal` WRITE;
/*!40000 ALTER TABLE `tf_task_deal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tf_task_deal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_timing`
--

DROP TABLE IF EXISTS `tf_timing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tf_timing` (
  `timing_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `br_id` varchar(50) DEFAULT NULL,
  `timing_status` varchar(50) DEFAULT NULL,
  `timing_finish` varchar(50) DEFAULT NULL,
  `timing_begin` varchar(50) DEFAULT NULL,
  `timing_time` varchar(50) DEFAULT NULL,
  `timing_content` varchar(100) DEFAULT NULL,
  `timing_spare1` varchar(100) DEFAULT NULL,
  `timing_spare2` varchar(100) DEFAULT NULL,
  `timing_spare3` varchar(100) DEFAULT NULL,
  `timing_spare4` varchar(100) DEFAULT NULL,
  `timing_spare5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`timing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_timing`
--

LOCK TABLES `tf_timing` WRITE;
/*!40000 ALTER TABLE `tf_timing` DISABLE KEYS */;
/*!40000 ALTER TABLE `tf_timing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tlogin`
--

DROP TABLE IF EXISTS `tlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tlogin` (
  `ID` varchar(36) NOT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(32) DEFAULT NULL,
  `LOGINIP` varchar(100) DEFAULT NULL,
  `ENABLESTATE` char(1) DEFAULT NULL COMMENT '0禁用,1启用',
  `ENABLETIME` varchar(19) DEFAULT NULL,
  `LOGINTIME` varchar(19) DEFAULT NULL,
  `CREATETIME` varchar(19) DEFAULT NULL,
  `UPDATETIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tlogin`
--

LOCK TABLES `tlogin` WRITE;
/*!40000 ALTER TABLE `tlogin` DISABLE KEYS */;
INSERT INTO `tlogin` VALUES ('52aeccc8-d962-4b15-bef2-42e2684ebb15','mr','mrsoft','0:0:0:0:0:0:0:1','1',NULL,'2017-07-24 15:33:44','2016-06-24 10:54:03',NULL),('96804794-2d94-48aa-8c9b-d929308b0689','admin_superMR','000','0:0:0:0:0:0:0:1','1','','2017-07-24 15:18:27','2014-06-21 20:17:38','2016-03-21 09:46:20'),('c43344b3-9148-433e-b609-cb700483a7b7','u1','000','0:0:0:0:0:0:0:1','1',NULL,'2017-07-24 15:16:58','2016-06-24 10:53:46',NULL);
/*!40000 ALTER TABLE `tlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tloginrole`
--

DROP TABLE IF EXISTS `tloginrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tloginrole` (
  `LOGINID` varchar(36) DEFAULT NULL,
  `ROLEID` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tloginrole`
--

LOCK TABLES `tloginrole` WRITE;
/*!40000 ALTER TABLE `tloginrole` DISABLE KEYS */;
INSERT INTO `tloginrole` VALUES ('0c76917e-b728-4f30-89bb-6e2e0ebe5487','1bedf97c-dfc9-4c59-a805-13bf378d631a'),('96804794-2d94-48aa-8c9b-d929308b0689','1'),('14836575-2690-4cc1-a419-bd2be23b6824','990cae75-6dca-4f2f-92d4-792aaf3771b2'),('c43344b3-9148-433e-b609-cb700483a7b7','1bedf97c-dfc9-4c59-a805-13bf378d631a'),('52aeccc8-d962-4b15-bef2-42e2684ebb15','1bedf97c-dfc9-4c59-a805-13bf378d631a');
/*!40000 ALTER TABLE `tloginrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmenu`
--

DROP TABLE IF EXISTS `tmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmenu` (
  `ID` varchar(36) NOT NULL,
  `PID` varchar(36) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PATH` varchar(100) DEFAULT NULL,
  `ICON` varchar(100) DEFAULT NULL,
  `ORDERNUM` decimal(5,0) DEFAULT NULL,
  `CREATETIME` varchar(19) DEFAULT NULL,
  `UPDATETIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmenu`
--

LOCK TABLES `tmenu` WRITE;
/*!40000 ALTER TABLE `tmenu` DISABLE KEYS */;
INSERT INTO `tmenu` VALUES ('0e5058fc-e938-44df-bd2c-db921af551a2','1','角色管理','/back/role.do?cmd=main',NULL,2,'2014-06-20 00:24:13','2014-06-20 01:52:28'),('1',NULL,'系统管理',NULL,NULL,0,NULL,'2014-06-21 18:04:48'),('1e40e3a4-1d69-43c7-a73e-12600c04a35f','1','功能管理','','',1,'2014-06-20 00:26:06','2014-07-06 18:29:39'),('4848adb2-b799-4544-8c1e-f2bfd8ed625b','1e40e3a4-1d69-43c7-a73e-12600c04a35f','资源管理','/back/resource.do?cmd=main',NULL,1,'2014-06-20 00:26:31','2014-06-20 01:52:38'),('a0bf5f13-a2b4-4fa1-a708-2dab006b8692','1e40e3a4-1d69-43c7-a73e-12600c04a35f','菜单管理','/back/menu.do?cmd=main',NULL,0,'2014-06-20 00:26:19','2014-06-20 01:52:35'),('a71ec8d3-57f5-4ddc-8d84-18f82ae115ee','b22461d2-f0ad-488a-b6c0-461722c3a94f','组织机构信息','/back/company.do?cmd=updateInput&flag=setting','',0,'2014-06-20 00:23:17','2016-03-21 09:06:31'),('b148724d-cfa8-4c89-a7dc-02e41d6de9b3','1','用户管理','/back/login.do?cmd=list',NULL,1,'2014-06-20 00:24:00','2014-06-21 18:03:22'),('b22461d2-f0ad-488a-b6c0-461722c3a94f','1','机构管理','','',0,'2014-06-20 00:23:01','2016-03-21 09:06:01'),('e1e7da83-ebf3-493c-bbd0-b7a98882a863','b22461d2-f0ad-488a-b6c0-461722c3a94f','组织机构树','/back/company.do?cmd=main',NULL,1,'2014-06-20 00:23:37','2014-06-20 01:52:17');
/*!40000 ALTER TABLE `tmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tparty`
--

DROP TABLE IF EXISTS `tparty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tparty` (
  `ID` varchar(36) NOT NULL,
  `PID` varchar(36) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `DISCRIMINATE` varchar(10) DEFAULT NULL COMMENT 'company：公司表\r\n            department：部门表\r\n            position：岗位表\r\n            \r\n            ',
  `ORDERNUM` decimal(5,0) DEFAULT NULL,
  `CREATETIME` varchar(19) DEFAULT NULL,
  `UPDATETIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tparty`
--

LOCK TABLES `tparty` WRITE;
/*!40000 ALTER TABLE `tparty` DISABLE KEYS */;
INSERT INTO `tparty` VALUES ('1b9bfefe-acb8-4370-afda-8a62c2a09051',NULL,'个人公司',NULL,'company',NULL,'2016-03-21 09:19:13','2016-06-24 10:52:50'),('3a0d04c6-05df-4dc0-b485-a5b9b578e056','1b9bfefe-acb8-4370-afda-8a62c2a09051','销售一部','1','department',1,'2016-03-21 09:20:12','2016-06-24 10:53:00');
/*!40000 ALTER TABLE `tparty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tperson`
--

DROP TABLE IF EXISTS `tperson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tperson` (
  `ID` varchar(36) NOT NULL,
  `DEPARTMENTID` varchar(36) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ENGNAME` varchar(100) DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `SEX` char(1) DEFAULT NULL COMMENT '0女,1男',
  `PHONE` varchar(50) DEFAULT NULL,
  `TEL` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `ZIPCODE` varchar(6) DEFAULT NULL,
  `CREATETIME` varchar(19) DEFAULT NULL,
  `UPDATETIME` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tperson`
--

LOCK TABLES `tperson` WRITE;
/*!40000 ALTER TABLE `tperson` DISABLE KEYS */;
INSERT INTO `tperson` VALUES ('14836575-2690-4cc1-a419-bd2be23b6824','1b9bfefe-acb8-4370-afda-8a62c2a09051','管理员','','','','','','','','','2016-04-06 16:45:25','2016-04-20 09:11:02'),('52aeccc8-d962-4b15-bef2-42e2684ebb15','3a0d04c6-05df-4dc0-b485-a5b9b578e056','明日','','','1','','','','','','2016-06-24 10:54:03',NULL),('96804794-2d94-48aa-8c9b-d929308b0689','1b9bfefe-acb8-4370-afda-8a62c2a09051','超级管理员','','','1','','','','','','2014-06-21 20:17:38','2016-03-21 09:46:20'),('c43344b3-9148-433e-b609-cb700483a7b7','3a0d04c6-05df-4dc0-b485-a5b9b578e056','张三','','','1','','','','','','2016-06-24 10:53:46',NULL);
/*!40000 ALTER TABLE `tperson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tposition`
--

DROP TABLE IF EXISTS `tposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tposition` (
  `ID` varchar(36) NOT NULL,
  `MARK` varchar(100) DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `GRADE` varchar(100) DEFAULT NULL,
  `ISLEADER` char(1) DEFAULT NULL,
  `LEADERLEVEL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tposition`
--

LOCK TABLES `tposition` WRITE;
/*!40000 ALTER TABLE `tposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `tposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tresource`
--

DROP TABLE IF EXISTS `tresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tresource` (
  `ID` varchar(36) NOT NULL,
  `MENUID` varchar(36) DEFAULT NULL,
  `PID` varchar(36) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PATH` varchar(100) DEFAULT NULL,
  `ORDERNUM` decimal(5,0) DEFAULT NULL,
  `CREATETIME` varchar(19) DEFAULT NULL,
  `UPDATETIME` varchar(19) DEFAULT NULL,
  `TYPE` varchar(10) DEFAULT NULL,
  `EXEMODE` varchar(10) DEFAULT NULL,
  `CLASSSTYLE` varchar(100) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tresource`
--

LOCK TABLES `tresource` WRITE;
/*!40000 ALTER TABLE `tresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `tresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trole`
--

DROP TABLE IF EXISTS `trole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trole` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `CREATETIME` varchar(19) DEFAULT NULL,
  `UPDATETIME` varchar(19) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trole`
--

LOCK TABLES `trole` WRITE;
/*!40000 ALTER TABLE `trole` DISABLE KEYS */;
INSERT INTO `trole` VALUES ('1','管理员角色',NULL,'2014-07-12 11:48:25','至高无上，拥有所有的权限！\r\n'),('1bedf97c-dfc9-4c59-a805-13bf378d631a','手机用户','2016-03-04 09:50:13','2016-06-24 10:52:03','');
/*!40000 ALTER TABLE `trole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `troletask`
--

DROP TABLE IF EXISTS `troletask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `troletask` (
  `PK_ID` varchar(36) NOT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `dict_code` varchar(36) DEFAULT NULL,
  `fun_id` varchar(10) DEFAULT NULL COMMENT '10 初筛查看  11 初筛回执  ',
  `create_id` varchar(100) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `update_id` varchar(100) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `troletask`
--

LOCK TABLES `troletask` WRITE;
/*!40000 ALTER TABLE `troletask` DISABLE KEYS */;
INSERT INTO `troletask` VALUES ('106b9d63-1dcc-43d5-9426-0e12d52f309c','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','10','admin','2015-09-23 21:46:49',NULL,NULL),('17217d61-84aa-48f0-997d-84f598689861','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','80','admin','2015-09-23 21:46:06',NULL,NULL),('28c610b8-aada-4c06-b126-bb01faeae15e','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','10','admin','2015-09-23 21:46:06',NULL,NULL),('3a8f2824-3a89-4192-bbee-e6bdd5c795eb','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','50','admin','2015-09-23 21:46:06',NULL,NULL),('3d800512-5d45-4f99-a5f6-3e9a26cec6b2','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','4cde5820-1b05-4af2-aad1-d663d454ef9d','00','admin','2015-05-15 10:49:30',NULL,NULL),('40d9d67b-2b86-404a-957e-5b8346aa310d','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','80','admin','2015-09-23 21:46:50',NULL,NULL),('4951f543-5dd2-4c66-952e-c59ecaac010e','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','60','admin','2015-09-23 21:46:06',NULL,NULL),('7423cd47-76c7-4dc6-a1ae-0863cf2db9d2','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','20','admin','2015-09-23 21:46:49',NULL,NULL),('81c61ca4-d67b-4f11-b423-733539824342','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','4cde5820-1b05-4af2-aad1-d663d454ef9d','30','admin','2015-05-15 10:49:30',NULL,NULL),('86c9a605-3a49-4e14-a70e-990d0436a9ec','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','20','admin','2015-09-23 21:46:06',NULL,NULL),('878e2f9d-5bc3-42a5-936e-3f9a3c74bfdd','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','30','admin','2015-09-23 21:46:49',NULL,NULL),('ac10569b-e54f-4520-9c8e-2533b74ef8e0','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','4cde5820-1b05-4af2-aad1-d663d454ef9d','10','admin','2015-05-15 10:49:30',NULL,NULL),('b4bab84d-9242-48ab-b376-f0139634224e','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','70','admin','2015-09-23 21:46:50',NULL,NULL),('c1c1165f-ba5b-4e3f-9697-80232bab9a81','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','30','admin','2015-09-23 21:46:06',NULL,NULL),('c2282611-0dbe-4581-8762-beb02a0cca81','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','70','admin','2015-09-23 21:46:06',NULL,NULL),('d0f76423-694c-4959-a87a-8fcc32f7b0b5','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','50','admin','2015-09-23 21:46:50',NULL,NULL),('d23e9137-43f5-4d23-bec4-06c0681158ba','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','00','admin','2015-09-23 21:46:06',NULL,NULL),('e43af096-e7f3-4980-a670-76849f2413f1','617a9faa-f57d-4571-bb1f-c2fd17ef1c8c','4cde5820-1b05-4af2-aad1-d663d454ef9d','70','admin','2015-05-15 10:49:30',NULL,NULL),('e8b8067b-d9f2-49b2-b45b-2874d56e6f20','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','40','admin','2015-09-23 21:46:50',NULL,NULL),('f5c910a9-aa38-4bd8-942b-dbd93c57bbd3','1','4cde5820-1b05-4af2-aad1-d663d454ef9d','40','admin','2015-09-23 21:46:06',NULL,NULL),('fec386b3-7114-4f89-9c86-e7549fe455b4','50e1aa44-43f1-4ec2-b01a-5493bd43608e','4cde5820-1b05-4af2-aad1-d663d454ef9d','60','admin','2015-09-23 21:46:50',NULL,NULL);
/*!40000 ALTER TABLE `troletask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ttask`
--

DROP TABLE IF EXISTS `ttask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ttask` (
  `pk_id` varchar(36) NOT NULL,
  `col_dept` varchar(36) DEFAULT NULL,
  `col_role` varchar(36) DEFAULT NULL,
  `col_uid` varchar(36) DEFAULT NULL,
  `col_desc` varchar(500) DEFAULT NULL,
  `col_type` varchar(10) DEFAULT NULL,
  `col_url` varchar(500) DEFAULT NULL,
  `col_state` varchar(10) DEFAULT NULL COMMENT '1 未办理\r\n            3 已办理',
  `col_finaldate` varchar(20) DEFAULT NULL,
  `open_type` varchar(10) DEFAULT NULL,
  `col_id` varchar(36) DEFAULT NULL,
  `dept_code` varchar(36) DEFAULT NULL,
  `send_name` varchar(100) DEFAULT NULL,
  `create_id` varchar(100) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `update_id` varchar(100) DEFAULT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ttask`
--

LOCK TABLES `ttask` WRITE;
/*!40000 ALTER TABLE `ttask` DISABLE KEYS */;
/*!40000 ALTER TABLE `ttask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_zs'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-24 15:38:31
