/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : shiro_db

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-12-20 14:03:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_icon` varchar(255) DEFAULT NULL,
  `menu_link` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_sort` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `resource_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', null, '', '用户管理', '1', '0', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO `sys_permission` VALUES ('2', null, null, '用户添加', '2', '1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `sys_permission` VALUES ('3', '', '', '用户删除', '3', '1', 'userInfo:del', 'button', 'userInfo/userDel');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('2', 'VIP会员', 'vip');

-- ----------------------------
-- Table structure for sys_role_has_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_has_sys_permission`;
CREATE TABLE `sys_role_has_sys_permission` (
  `sys_role_id` int(11) NOT NULL,
  `sys_permission_id` int(11) NOT NULL,
  KEY `FK5eiscuj2hr3yrikfjlww059os` (`sys_role_id`),
  KEY `FKcwgr7cutrhdmledcp7bb9xohi` (`sys_permission_id`),
  CONSTRAINT `FK5eiscuj2hr3yrikfjlww059os` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKcwgr7cutrhdmledcp7bb9xohi` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_has_sys_permission
-- ----------------------------
INSERT INTO `sys_role_has_sys_permission` VALUES ('1', '1');
INSERT INTO `sys_role_has_sys_permission` VALUES ('1', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员', '123456', '8d78869f470951332959580424d4bf4f', '0', 'admin');

-- ----------------------------
-- Table structure for sys_user_has_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_has_sys_role`;
CREATE TABLE `sys_user_has_sys_role` (
  `sys_user_id` int(11) NOT NULL,
  `sys_role_id` int(11) NOT NULL,
  KEY `FKj10l5ajhu6lu2iab18uroraul` (`sys_role_id`),
  KEY `FKohap4u7rx0muckgyiy591y48r` (`sys_user_id`),
  CONSTRAINT `FKj10l5ajhu6lu2iab18uroraul` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKohap4u7rx0muckgyiy591y48r` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_has_sys_role
-- ----------------------------
INSERT INTO `sys_user_has_sys_role` VALUES ('1', '1');
