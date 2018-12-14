/*
Navicat MySQL Data Transfer

Source Server         : Oliver
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : oliver

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-12-13 23:21:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `user` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data
-- ----------------------------
INSERT INTO `data` VALUES ('test', 'tony', 'b65b2c02-9b43-4212-a85b-745aa23ac549.jpg', '2018-12-12 08:43:30');
INSERT INTO `data` VALUES ('test', 'nihao', '2f5765ce-8cd6-44d8-a1d7-7a3afd432ea7.jpg', '2018-12-11 23:40:02');
INSERT INTO `data` VALUES ('test', 'saojian', '4613cc69-d538-41bf-8a30-d35f30c0d76b.jpg', '2018-12-12 09:50:34');
INSERT INTO `data` VALUES ('test', 'nihaoA', '4fafa570-76f6-4ed4-834d-bd0f055413db.jpg', '2018-12-11 23:47:03');
INSERT INTO `data` VALUES ('test', 'Tony', '', '2018-12-12 09:12:29');
INSERT INTO `data` VALUES ('test', 'nihao', '31ae0944-3b0d-4236-ae46-53139e1bb87e.jpg', '2018-12-12 09:50:34');
INSERT INTO `data` VALUES ('Kevin', '??????????', 'b3424e8b-6b32-4678-b89c-239546df81e3.jpg', '2018-12-12 18:01:15');
INSERT INTO `data` VALUES ('test', 'saojian', '4ac57040-f30c-422f-9e89-2bf81ae6faf2.jpg', '2018-12-12 09:54:22');
INSERT INTO `data` VALUES ('test', '??', '', '2018-12-12 18:01:15');
INSERT INTO `data` VALUES ('Kevin', '???', '', '2018-12-12 18:16:55');
INSERT INTO `data` VALUES ('Kevin', '??', '', '2018-12-12 18:16:55');
INSERT INTO `data` VALUES ('Kevin', '???????', '', '2018-12-12 18:45:06');
INSERT INTO `data` VALUES ('test', '?', '', '2018-12-12 19:18:04');
