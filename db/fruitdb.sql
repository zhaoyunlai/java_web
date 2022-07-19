/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : localhost:3306
 Source Schema         : fruitdb

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 19/07/2022 18:32:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_fruit
-- ----------------------------
DROP TABLE IF EXISTS `t_fruit`;
CREATE TABLE `t_fruit`  (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int(11) NULL DEFAULT NULL,
  `fcount` int(11) NULL DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1060 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_fruit
-- ----------------------------
INSERT INTO `t_fruit` VALUES (2, '西瓜', 20, 233, '西瓜很好吃hama ');
INSERT INTO `t_fruit` VALUES (4, '菠萝', 5, 63, 'OK');
INSERT INTO `t_fruit` VALUES (8, '红富士', 5, 52, '红富士很好吃');
INSERT INTO `t_fruit` VALUES (9, '香蕉', 3, 50, '香蕉很好吃');
INSERT INTO `t_fruit` VALUES (11, '山竹', 8, 55, '山竹是一种神奇的水果');
INSERT INTO `t_fruit` VALUES (12, '甘蔗', 7, 100, '甘蔗是一种神奇的水果');
INSERT INTO `t_fruit` VALUES (13, '萝卜', 5, 55, '萝卜是一种神奇的水果');
INSERT INTO `t_fruit` VALUES (14, '圣女果', 3, 99, '好吃');
INSERT INTO `t_fruit` VALUES (15, '哈密瓜', 7, 77, '哈密瓜很好吃');
INSERT INTO `t_fruit` VALUES (16, '火龙果', 5, 69, '好吃');
INSERT INTO `t_fruit` VALUES (38, '草莓', 10, 299, '草莓好吃');
INSERT INTO `t_fruit` VALUES (40, '西红柿', 20, 120, '西红柿也是水果');
INSERT INTO `t_fruit` VALUES (51, '榴莲0', 15, 100, '榴莲是一种神奇的水果0');
INSERT INTO `t_fruit` VALUES (52, '榴莲1', 15, 100, '榴莲是一种神奇的水果1');
INSERT INTO `t_fruit` VALUES (1051, 'apple', 100, 90, '1');
INSERT INTO `t_fruit` VALUES (1052, 'apple', 100, 90, '1');
INSERT INTO `t_fruit` VALUES (1053, 'apple', 100, 90, '1');
INSERT INTO `t_fruit` VALUES (1055, '信号', 100, 90, '西瓜很好吃sdfa ');
INSERT INTO `t_fruit` VALUES (1058, '莉莉', 0, 1, '莉莉不要钱');
INSERT INTO `t_fruit` VALUES (1059, 'apple14', 32342, 23, '哦哦哦');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 50);

SET FOREIGN_KEY_CHECKS = 1;
