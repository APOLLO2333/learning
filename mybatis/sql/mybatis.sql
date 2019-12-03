# Host: localhost  (Version: 5.7.17)
# Date: 2019-11-29 14:06:09
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "person"
#

CREATE TABLE `person` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "person"
#

INSERT INTO `person` VALUES (1,'song1',11),(2,'song2',12),(3,'song3',12);
