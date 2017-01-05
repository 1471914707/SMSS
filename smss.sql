/*
Navicat MySQL Data Transfer

Source Server         : mySql
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : smss

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-01-05 14:40:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for count
-- ----------------------------
DROP TABLE IF EXISTS `count`;
CREATE TABLE `count` (
  `CountName` varchar(255) NOT NULL,
  `CountNum` decimal(20,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of count
-- ----------------------------
INSERT INTO `count` VALUES ('DealCount', '8.00');
INSERT INTO `count` VALUES ('DealPrice', '88.00');
INSERT INTO `count` VALUES ('MemberCount', '1.00');
INSERT INTO `count` VALUES ('ProvideCount', '12.00');
INSERT INTO `count` VALUES ('UserCount', '2.00');
INSERT INTO `count` VALUES ('MerchCount', '428.00');
INSERT INTO `count` VALUES ('MerchPriceCount', '2698.50');

-- ----------------------------
-- Table structure for dealing
-- ----------------------------
DROP TABLE IF EXISTS `dealing`;
CREATE TABLE `dealing` (
  `DealingID` varchar(20) NOT NULL,
  `DealingPrice` decimal(10,2) NOT NULL,
  `DealingDate` datetime NOT NULL,
  `MemberID` varchar(12) DEFAULT NULL,
  `UserID` varchar(10) NOT NULL,
  PRIMARY KEY (`DealingID`),
  KEY `UserID` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dealing
-- ----------------------------
INSERT INTO `dealing` VALUES ('24873426401361939', '16.00', '2017-01-02 11:29:07', null, '1');
INSERT INTO `dealing` VALUES ('24873426401361950', '33.00', '2017-01-03 12:25:50', '1', '1');
INSERT INTO `dealing` VALUES ('24873426401361953', '14.70', '2017-01-03 12:28:40', null, '1');
INSERT INTO `dealing` VALUES ('24873426401361956', '44.00', '2017-01-03 01:22:21', null, '1');
INSERT INTO `dealing` VALUES ('24873426401361961', '9.50', '2017-01-03 23:13:51', null, '1');
INSERT INTO `dealing` VALUES ('24873426401361964', '10.00', '2017-01-03 23:25:41', null, '1');
INSERT INTO `dealing` VALUES ('24873426401361981', '0.50', '2017-01-05 10:49:42', null, '1');
INSERT INTO `dealing` VALUES ('24873426401361983', '0.50', '2017-01-05 11:06:59', null, '1');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `MemberID` varchar(20) NOT NULL,
  `MemberCard` varchar(20) NOT NULL,
  `TotalCost` decimal(15,2) NOT NULL,
  `RegDate` datetime NOT NULL,
  PRIMARY KEY (`MemberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('24873426401361947', '1', '23.50', '2017-01-03 12:13:48');

-- ----------------------------
-- Table structure for merchinfo
-- ----------------------------
DROP TABLE IF EXISTS `merchinfo`;
CREATE TABLE `merchinfo` (
  `MerchID` varchar(20) NOT NULL,
  `MerchName` varchar(50) NOT NULL,
  `MerchPrice` decimal(10,2) NOT NULL,
  `MerchNum` int(6) NOT NULL,
  `BarCode` varchar(50) NOT NULL,
  `ProvideID` varchar(20) NOT NULL,
  PRIMARY KEY (`BarCode`),
  KEY `ProvideID` (`ProvideID`),
  CONSTRAINT `merchinfo_ibfk_1` FOREIGN KEY (`ProvideID`) REFERENCES `provide` (`ProvideID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchinfo
-- ----------------------------
INSERT INTO `merchinfo` VALUES ('24873426401361935', '百事可乐青柠', '3.00', '88', '6908946287346', '24873426401361934');
INSERT INTO `merchinfo` VALUES ('24873426401361968', '棒棒糖', '0.50', '198', '6911316570306', '24873426401361944');
INSERT INTO `merchinfo` VALUES ('24873426401361958', '订书机', '9.50', '18', '6921734902214', '24873426401361957');
INSERT INTO `merchinfo` VALUES ('24873426401361943', '康酷晶砖杯', '14.00', '98', '6952735010141', '24873426401361942');
INSERT INTO `merchinfo` VALUES ('24873426401361959', '盐焗腰果', '18.00', '35', '6956511900176', '24873426401361944');
INSERT INTO `merchinfo` VALUES ('24873426401361945', '软香奶萨萨', '23.00', '34', '6956511907434', '24873426401361944');

-- ----------------------------
-- Table structure for provide
-- ----------------------------
DROP TABLE IF EXISTS `provide`;
CREATE TABLE `provide` (
  `ProvideID` varchar(20) NOT NULL,
  `ProvideName` varchar(50) NOT NULL,
  `ProvideAddress` varchar(255) DEFAULT NULL,
  `ProvidePhone` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ProvideID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of provide
-- ----------------------------
INSERT INTO `provide` VALUES ('24873426401361934', '百事可乐', '广州市白云区', '18813960106');
INSERT INTO `provide` VALUES ('24873426401361942', '韩国康酷株式会社', '浙江省台州市', '13025609673');
INSERT INTO `provide` VALUES ('24873426401361944', '三只松鼠股份有限公司', '安徽省芜湖市', '400-800-4900');
INSERT INTO `provide` VALUES ('24873426401361957', '得力集团有限公司', '浙江宁海得力工业园', '13025609673');
INSERT INTO `provide` VALUES ('24873426401361969', '南昌乐玛文具实业有限公司', '江西升进贤县温训工业园', '0755-21512288');
INSERT INTO `provide` VALUES ('24873426401361970', '源盛文化用品实业公司', '广东省汕头市', '400-800-1805');
INSERT INTO `provide` VALUES ('24873426401361971', '充电剃须刀', '浙江省温州市', '0577-83312228');
INSERT INTO `provide` VALUES ('24873426401361974', '哇哈哈', '台湾', '10086');
INSERT INTO `provide` VALUES ('24873426401361975', '王老吉', '广东省', '400-2111-2112');
INSERT INTO `provide` VALUES ('24873426401361976', '加多宝', '广州市', '400-2124-1231');
INSERT INTO `provide` VALUES ('24873426401361977', '隆江猪脚', '广东省惠来市', '18813960161');
INSERT INTO `provide` VALUES ('24873426401361985', '请填写', '请填写', '请填写');

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `SaleID` varchar(20) NOT NULL,
  `BarCode` varchar(50) NOT NULL,
  `SaleDate` datetime NOT NULL,
  `SaleNum` int(6) NOT NULL,
  `SalePrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`SaleID`),
  KEY `BarCode` (`BarCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES ('24873426401361938', '6908946287346', '2017-01-02 23:29:07', '4', '12.00');
INSERT INTO `sale` VALUES ('24873426401361940', '6908946287346', '2017-01-02 23:31:53', '4', '12.00');
INSERT INTO `sale` VALUES ('24873426401361948', '6952735010141', '2017-01-03 12:25:50', '1', '14.00');
INSERT INTO `sale` VALUES ('24873426401361949', '6956511907434', '2017-01-03 12:25:50', '1', '23.00');
INSERT INTO `sale` VALUES ('24873426401361951', '6911316570306', '2017-01-03 12:28:40', '1', '0.50');
INSERT INTO `sale` VALUES ('24873426401361952', '6952735010141', '2017-01-03 12:28:40', '1', '14.00');
INSERT INTO `sale` VALUES ('24873426401361954', '6911316570306', '2017-01-03 13:22:21', '2', '1.00');
INSERT INTO `sale` VALUES ('24873426401361955', '6908946287346', '2017-01-03 13:22:21', '1', '3.00');
INSERT INTO `sale` VALUES ('24873426401361960', '6921734902214', '2017-01-03 23:13:51', '1', '9.50');
INSERT INTO `sale` VALUES ('24873426401361962', '6911316570306', '2017-01-03 23:25:41', '2', '1.00');
INSERT INTO `sale` VALUES ('24873426401361963', '6908946287346', '2017-01-03 23:25:41', '3', '9.00');
INSERT INTO `sale` VALUES ('24873426401361965', '6911316570306', '2017-01-03 23:31:18', '1', '0.50');
INSERT INTO `sale` VALUES ('24873426401361966', '6921734902214', '2017-01-03 23:31:20', '1', '9.50');
INSERT INTO `sale` VALUES ('24873426401361980', '6911316570306', '2017-01-05 10:49:42', '1', '0.50');
INSERT INTO `sale` VALUES ('24873426401361982', '6911316570306', '2017-01-05 11:06:57', '1', '0.50');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserID` varchar(20) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `UserPW` varchar(12) NOT NULL,
  `UserType` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '121212', '0');
INSERT INTO `user` VALUES ('2', '2', '121212', '1');
INSERT INTO `user` VALUES ('24873426401361979', '2', '121212', '0');

-- ----------------------------
-- Procedure structure for Dealing_delete
-- ----------------------------
DROP PROCEDURE IF EXISTS `Dealing_delete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Dealing_delete`(a VARCHAR(20))
BEGIN
	DECLARE n DECIMAL(10,2);
	set n=(SELECT dealingPrice FROM dealing WHERE DealingID=a);
 DELETE FROM dealing WHERE DealingID=a;
 UPDATE count set CountNum=CountNum-1 WHERE CountName='DealCount';
UPDATE count set CountNum=CountNum-n WHERE CountName='DealPrice';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Dealing_save
-- ----------------------------
DROP PROCEDURE IF EXISTS `Dealing_save`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Dealing_save`(b DECIMAL(10,2),c VARCHAR(30),d VARCHAR(12),e VARCHAR(10),f int)
BEGIN
 INSERT INTO dealing VALUES(UUID_SHORT(),b,c,d,e);
UPDATE count set CountNum=CountNum+1 WHERE CountName='DealCount';
UPDATE count set CountNum=CountNum+b WHERE CountName='DealPrice';
UPDATE count set CountNum=CountNum-f WHERE CountName='MerchCount';
UPDATE count set CountNum=CountNum-b WHERE CountName='MerchPrice';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Dealing_searchByID
-- ----------------------------
DROP PROCEDURE IF EXISTS `Dealing_searchByID`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Dealing_searchByID`(a VARCHAR(20))
BEGIN
 SELECT * FROM dealing WHERE DealingID=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Dealing_searchByPage
-- ----------------------------
DROP PROCEDURE IF EXISTS `Dealing_searchByPage`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Dealing_searchByPage`(a int,b int)
BEGIN
	SELECT * FROM dealing ORDER BY DealingDate DESC LIMIT a,b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Dealing_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `Dealing_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Dealing_update`(a VARCHAR(20),b DECIMAL(10,2),c VARCHAR(30),d VARCHAR(12),e VARCHAR(10))
BEGIN
	DECLARE n1 DECIMAL(10,2);
	DECLARE n2 DECIMAL(10,2);
	set n1=(SELECT dealingPrice FROM dealing WHERE DealingID=a);
  set n2 = b-n1;
 UPDATE dealing set DealingPrice=b,DealingDate=c,MemberID=d,UserID=e WHERE DealingID=a;
	UPDATE count set CountNum=CountNum+n2 WHERE CountName='DealPrice';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for getAllCountInfo
-- ----------------------------
DROP PROCEDURE IF EXISTS `getAllCountInfo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCountInfo`()
BEGIN
	SELECT * FROM count;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Member_delete
-- ----------------------------
DROP PROCEDURE IF EXISTS `Member_delete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Member_delete`(a VARCHAR(20))
BEGIN
	DELETE FROM member WHERE MemberID=a;
	UPDATE count set CountNum=CountNum-1 WHERE CountName='MemberCount';
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Member_save
-- ----------------------------
DROP PROCEDURE IF EXISTS `Member_save`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Member_save`(a VARCHAR(20),b DECIMAL(15,2),c varchar(20))
BEGIN
	INSERT INTO member VALUES(UUID_SHORT(),a,b,c);
	UPDATE count set CountNum=CountNum+1 WHERE CountName='MemberCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Member_searchByID
-- ----------------------------
DROP PROCEDURE IF EXISTS `Member_searchByID`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Member_searchByID`(a VARCHAR(20))
BEGIN
	SELECT * FROM member WHERE MemberID=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Member_searchByPage
-- ----------------------------
DROP PROCEDURE IF EXISTS `Member_searchByPage`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Member_searchByPage`(a int,b INT)
BEGIN
	SELECT * FROM member LIMIT a,b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Member_selectLast
-- ----------------------------
DROP PROCEDURE IF EXISTS `Member_selectLast`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Member_selectLast`()
BEGIN
SELECT * FROM member ORDER BY MemberID DESC LIMIT 0,1;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Member_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `Member_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Member_update`(a VARCHAR(20),b VARCHAR(20),c DECIMAL(15,2),d VARCHAR(20))
BEGIN
	UPDATE member SET MemberCard=b,TotalCost=c,RegDate=d WHERE MemberID=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Member_updateTotalCost
-- ----------------------------
DROP PROCEDURE IF EXISTS `Member_updateTotalCost`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Member_updateTotalCost`(a VARCHAR(20),b DECIMAL(15,2))
BEGIN
	UPDATE member set TotalCost=TotalCost+b WHERE MemberCard=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for MerchInfo_delete
-- ----------------------------
DROP PROCEDURE IF EXISTS `MerchInfo_delete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MerchInfo_delete`(a VARCHAR(20))
BEGIN
	DECLARE n INT;
	DECLARE p DECIMAL(10,2);
	set p = (SELECT MerchPrice FROM merchinfo WHERE MerchID=a);
	set n = (SELECT MerchNum FROM merchinfo WHERE merchID=a);
	DELETE FROM merchinfo WHERE MerchID=a;
	UPDATE count set CountNum=CountNum-n WHERE CountName='MerchCount';
	UPDATE count set CountNum=CountNum-p*n WHERE CountName='MerchPriceCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for MerchInfo_save
-- ----------------------------
DROP PROCEDURE IF EXISTS `MerchInfo_save`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MerchInfo_save`(a VARCHAR(50),b DECIMAL(10,2),c int,d varchar(50),e VARCHAR(20))
BEGIN
	INSERT INTO merchinfo VALUES(UUID_SHORT(),a,b,c,d,e);
	UPDATE count set CountNum=CountNum+c WHERE CountName='MerchCount';
	UPDATE count set CountNum=CountNum+b*c WHERE CountName='MerchPriceCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for MerchInfo_searchByBarCode
-- ----------------------------
DROP PROCEDURE IF EXISTS `MerchInfo_searchByBarCode`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MerchInfo_searchByBarCode`(a VARCHAR(20))
BEGIN
	SELECT * FROM merchinfo WHERE BarCode=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for MerchInfo_searchByPage
-- ----------------------------
DROP PROCEDURE IF EXISTS `MerchInfo_searchByPage`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MerchInfo_searchByPage`(a int,b int)
BEGIN
	SELECT * FROM merchinfo LIMIT a,b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for MerchInfo_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `MerchInfo_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MerchInfo_update`(i VARCHAR(20),a VARCHAR(50),b DECIMAL(10,2),c int,d varchar(50),e VARCHAR(20))
BEGIN
	DECLARE n INT;
	DECLARE P DECIMAL(10,2);
	set n = (SELECT MerchNum FROM merchinfo WHERE merchID=i);
	set p = (SELECT MerchPrice from merchinfo WHERE merchID=i);
	UPDATE merchinfo SET MerchPrice=b,MerchName=a,MerchNum=c,BarCode=d,provideID=e WHERE MerchID=i;
	UPDATE count set CountNum=CountNum+(n-c) WHERE CountName='MerchCount';
	UPDATE count set CountNum=CountNum-n*p+b*(2n-c) WHERE CountName='MerchPriceCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for provide_delete
-- ----------------------------
DROP PROCEDURE IF EXISTS `provide_delete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `provide_delete`(a VARCHAR(20))
BEGIN
	DELETE FROM Provide WHERE ProvideID = a;
	UPDATE count SET CountNum=CountNum-1 WHERE CountName='ProvideCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for provide_save
-- ----------------------------
DROP PROCEDURE IF EXISTS `provide_save`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `provide_save`(b VARCHAR(50),c VARCHAR(255),d VARCHAR(25))
BEGIN
	INSERT INTO provide VALUES(UUID_SHORT(),b,c,d);
UPDATE count SET CountNum=CountNum+1 WHERE CountName='ProvideCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for provide_searchByID
-- ----------------------------
DROP PROCEDURE IF EXISTS `provide_searchByID`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `provide_searchByID`(id VARCHAR(20))
BEGIN
	SELECT * FROM provide WHERE ProvideID=id;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for provide_searchByPage
-- ----------------------------
DROP PROCEDURE IF EXISTS `provide_searchByPage`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `provide_searchByPage`(a INT,b INT)
BEGIN
	SELECT * FROM provide LIMIT a,b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for provide_selectLast
-- ----------------------------
DROP PROCEDURE IF EXISTS `provide_selectLast`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `provide_selectLast`()
BEGIN
	SELECT * FROM provide ORDER BY provideID DESC LIMIT 0,1;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for provide_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `provide_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `provide_update`(a VARCHAR(20),b VARCHAR(50),c VARCHAR(255),d VARCHAR(25))
BEGIN
	UPDATE provide SET ProvideName=b,ProvideAddress=c,ProvidePhone=d WHERE ProvideID=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Sale_delete
-- ----------------------------
DROP PROCEDURE IF EXISTS `Sale_delete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Sale_delete`(a VARCHAR(20))
BEGIN
	
	DELETE FROM sale WHERE SaleID=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Sale_save
-- ----------------------------
DROP PROCEDURE IF EXISTS `Sale_save`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Sale_save`(a VARCHAR(50),b varchar(20),c int,d DECIMAL(10,2))
BEGIN
	INSERT INTO sale VALUES(UUID_SHORT(),a,b,c,d);
	UPDATE merchinfo SET MerchNum=MerchNum-c WHERE BarCode=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Sale_searchByBarCode
-- ----------------------------
DROP PROCEDURE IF EXISTS `Sale_searchByBarCode`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Sale_searchByBarCode`(a VARCHAR(50))
BEGIN
 SELECT * FROM sale WHERE BarCode=a ORDER BY SaleDate DESC;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Sale_searchByPage
-- ----------------------------
DROP PROCEDURE IF EXISTS `Sale_searchByPage`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Sale_searchByPage`(a int,b int)
BEGIN	
	SELECT * FROM sale ORDER BY SaleDate DESC LIMIT a,b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for Sale_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `Sale_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Sale_update`(i VARCHAR(20),b varchar(20),c int,d DECIMAL(10,2),v varchar(50))
BEGIN
	
	DECLARE num1 int DEFAULT 0;
	DECLARE num2 int DEFAULT 0;
	
	set num1 = (SELECT SaleNum FROM sale WHERE SaleID=i);
	set num2 = c-num1;
	UPDATE sale SET SaleDate=b,SaleNum=c,SalePrice=d WHERE SaleID=i;
	UPDATE merchinfo set MerchNum=MerchNum-num2 WHERE BarCode=v;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for User_delete
-- ----------------------------
DROP PROCEDURE IF EXISTS `User_delete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_delete`(a VARCHAR(20))
BEGIN
	DELETE FROM `user` WHERE UserID=a;
	UPDATE count SET CountNum=CountNum-1 WHERE CountName='UserCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for User_save
-- ----------------------------
DROP PROCEDURE IF EXISTS `User_save`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_save`(a VARCHAR(20),b VARCHAR(12),c int)
BEGIN
INSERT INTO `user` VALUES(UUID_SHORT(),A,B,c);
UPDATE count SET CountNum=CountNum+1 WHERE CountName='UserCount';
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for User_searchByID
-- ----------------------------
DROP PROCEDURE IF EXISTS `User_searchByID`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_searchByID`(a VARCHAR(20))
BEGIN
	SELECT * FROM `user` WHERE UserID=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for User_searchByName
-- ----------------------------
DROP PROCEDURE IF EXISTS `User_searchByName`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_searchByName`(a VARCHAR(20))
BEGIN
	SELECT * FROM `user` WHERE UserName=a;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for User_searchByPage
-- ----------------------------
DROP PROCEDURE IF EXISTS `User_searchByPage`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_searchByPage`(a int,b int)
BEGIN
	SELECT * FROM `user` LIMIT a,b;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for User_selectLast
-- ----------------------------
DROP PROCEDURE IF EXISTS `User_selectLast`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_selectLast`()
BEGIN
	SELECT * FROM `user` ORDER BY UserID DESC LIMIT 0,1;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for User_update
-- ----------------------------
DROP PROCEDURE IF EXISTS `User_update`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_update`(a VARCHAR(20),b VARCHAR(20),c VARCHAR(12),d int)
BEGIN
	UPDATE `user` SET UserName=b,UserPW=c,UserType=d WHERE UserID=a;
END
;;
DELIMITER ;
