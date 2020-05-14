/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : unarke

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2019-12-25 17:21:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shop_info_users
-- ----------------------------
DROP TABLE IF EXISTS `shop_info_users`;
CREATE TABLE `shop_info_users` (
  `id` int(11) NOT NULL COMMENT '店铺用户表（店主或店员）：店铺用户ID',
  `username` varchar(255) NOT NULL COMMENT '账号：唯一用户名，英文数字',
  `nickname` varchar(255) NOT NULL COMMENT '昵称：默认和账号名一样',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱（账号）：绑定可使用',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话（账号）：绑定可使用',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `birth` varchar(255) DEFAULT NULL COMMENT '出生日期：如20001103',
  `salt` varchar(255) NOT NULL COMMENT '密码盐',
  `password` varchar(255) NOT NULL COMMENT '密码：加密后的密码',
  `isauth` int(11) NOT NULL COMMENT '是否认证：是否进行过身份认证',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_info_users
-- ----------------------------
INSERT INTO `shop_info_users` VALUES ('449676', '胡少001', 'Unarke333290', null, '15111888341', null, null, '157700883570320066096', '7634f8fe3807ee631fc8ba1dadce69d3', '0');
INSERT INTO `shop_info_users` VALUES ('547226', '空闲001', 'Unarke275930', null, '17323964195', null, null, '157700895682311808975', '410ee6d6b6f476997d293330452a6256', '0');

-- ----------------------------
-- Table structure for shop_tmp_userscode
-- ----------------------------
DROP TABLE IF EXISTS `shop_tmp_userscode`;
CREATE TABLE `shop_tmp_userscode` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '短信验证码临时存储id',
  `telephone` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '电话',
  `code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '短信验证码',
  PRIMARY KEY (`id`,`telephone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shop_tmp_userscode
-- ----------------------------
INSERT INTO `shop_tmp_userscode` VALUES ('8', '15111888341', '7912');
INSERT INTO `shop_tmp_userscode` VALUES ('9', '17323964195', '4646');

-- ----------------------------
-- Table structure for shop_user_token
-- ----------------------------
DROP TABLE IF EXISTS `shop_user_token`;
CREATE TABLE `shop_user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'token码id',
  `token` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'token码',
  `shop_user_id` int(11) NOT NULL COMMENT '商户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of shop_user_token
-- ----------------------------
INSERT INTO `shop_user_token` VALUES ('6', '9b72cdeb8cc4e689f3717a858a292b17', '449676');
INSERT INTO `shop_user_token` VALUES ('7', 'c9ac46231a45b34a995fd300c6e379fe', '547226');
