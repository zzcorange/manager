/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 05/12/2018 16:53:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chengzi_menu
-- ----------------------------
DROP TABLE IF EXISTS `chengzi_menu`;
CREATE TABLE `chengzi_menu`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `parent_id` int(255) NULL DEFAULT NULL COMMENT '父菜单id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chengzi_menu
-- ----------------------------
INSERT INTO `chengzi_menu` VALUES (1, -1, '权限管理', '');
INSERT INTO `chengzi_menu` VALUES (2, 1, '菜单管理', '/web/auth/menu.html');
INSERT INTO `chengzi_menu` VALUES (3, 1, '用户管理', '/web/auth/user.html');
INSERT INTO `chengzi_menu` VALUES (4, 1, '权限组管理', '/web/auth/role.html');
INSERT INTO `chengzi_menu` VALUES (5, 1, '权限管理', '/web/auth/auth.html');

SET FOREIGN_KEY_CHECKS = 1;
