/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : springboot_learn

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2020-07-05 11:31:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `emailcode`
-- ----------------------------
DROP TABLE IF EXISTS `emailcode`;
CREATE TABLE `emailcode` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '邮件验证码临时存储id,雪花id',
  `email` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '邮箱',
  `code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮件验证码',
  `timeout` varchar(255) DEFAULT NULL COMMENT '验证码过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of emailcode
-- ----------------------------
INSERT INTO `emailcode` VALUES ('8', '15111888341', '7912', null);
INSERT INTO `emailcode` VALUES ('9', '17323964195', '4646', null);

-- ----------------------------
-- Table structure for `menu_info`
-- ----------------------------
DROP TABLE IF EXISTS `menu_info`;
CREATE TABLE `menu_info` (
  `id` int(11) NOT NULL COMMENT '权限菜单id，主键',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_info
-- ----------------------------

-- ----------------------------
-- Table structure for `phonecode`
-- ----------------------------
DROP TABLE IF EXISTS `phonecode`;
CREATE TABLE `phonecode` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '短信验证码临时存储id,雪花id',
  `telephone` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '电话',
  `code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '短信验证码',
  `timeout` varchar(255) DEFAULT NULL COMMENT '验证码过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of phonecode
-- ----------------------------
INSERT INTO `phonecode` VALUES ('8', '15111888341', '7912', null);
INSERT INTO `phonecode` VALUES ('9', '17323964195', '4646', null);

-- ----------------------------
-- Table structure for `rolemenu`
-- ----------------------------
DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu` (
  `id` int(11) NOT NULL COMMENT '主键，角色权限关系id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `menuId` int(11) NOT NULL COMMENT '菜单id',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolemenu
-- ----------------------------

-- ----------------------------
-- Table structure for `role_info`
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `id` int(11) NOT NULL COMMENT '角色id，主键',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_info
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(16) NOT NULL COMMENT '用户id，雪花id',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话（账号）：绑定可使用',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱（账号）：绑定可使用',
  `nickname` varchar(255) NOT NULL COMMENT '昵称：默认和账号名一样',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `birth` varchar(255) DEFAULT NULL COMMENT '出生日期：如20001103',
  `salt` varchar(255) NOT NULL COMMENT '密码盐',
  `password` varchar(255) NOT NULL COMMENT '密码：加密后的密码',
  `isauth` int(11) NOT NULL COMMENT '是否认证：是否进行过身份认证',
  `token` varchar(255) DEFAULT NULL COMMENT 'token码',
  `token_timeout` varchar(255) DEFAULT NULL COMMENT 'token过期时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('449676', '15111888341', null, 'Unarke333290', '0', null, null, '157700883570320066096', '7634f8fe3807ee631fc8ba1dadce69d3', '0', null, null);
INSERT INTO `user_info` VALUES ('547226', '17323964195', null, 'Unarke275930', '0', null, null, '157700895682311808975', '410ee6d6b6f476997d293330452a6256', '0', null, null);
