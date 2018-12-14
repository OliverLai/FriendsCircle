/*
Navicat MySQL Data Transfer

Source Server         : Oliver
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : oliver

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-12-13 23:21:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `icon` varchar(255) DEFAULT NULL,
  `user` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` varchar(11) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('613b55b0-c857-469b-b6a2-661845547370.jpg', 'Tony', '123', '111');
INSERT INTO `users` VALUES ('21e18bc0-8345-46e9-9317-c35f5058cdcf.jpg', 'Kevin', '222', '222');
INSERT INTO `users` VALUES ('647d56a4-06a0-4c88-b557-811365dfd45b.jpg', 'test', '111', '111');
