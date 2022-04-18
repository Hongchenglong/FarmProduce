/*
 Navicat Premium Data Transfer

 Source Server         : farm_produce
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : oeong.com:3306
 Source Schema         : farm_produce

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 18/04/2022 15:21:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业地址',
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员',
  `phone` int(11) NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `enterprise_account_uindex`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for enviroment
-- ----------------------------
DROP TABLE IF EXISTS `enviroment`;
CREATE TABLE `enviroment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `air_temperature` decimal(4, 2) NULL DEFAULT NULL COMMENT '空气温度',
  `air_humidity` decimal(4, 2) NULL DEFAULT NULL COMMENT '空气湿度',
  `CO2` decimal(4, 2) NULL DEFAULT NULL COMMENT '二氧化碳',
  `illuminance` decimal(4, 2) NULL DEFAULT NULL COMMENT '光照度',
  `soil_temperature` decimal(4, 2) NULL DEFAULT NULL COMMENT '土壤温度',
  `soil_humidity` decimal(4, 2) NULL DEFAULT NULL COMMENT '土壤湿度',
  `date` date NULL DEFAULT NULL COMMENT '日期',
  `greenhouse_id` int(11) NULL DEFAULT NULL COMMENT '大棚id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for environment_threshold
-- ----------------------------
DROP TABLE IF EXISTS `environment_threshold`;
CREATE TABLE `environment_threshold`  (
  `id` int(11) NULL DEFAULT NULL,
  `air_temp_max` decimal(4, 2) NULL DEFAULT NULL,
  `air_temp_min` decimal(4, 2) NULL DEFAULT NULL,
  `air_hum_max` decimal(4, 2) NULL DEFAULT NULL,
  `air_hum_min` decimal(4, 2) NULL DEFAULT NULL,
  `CO2_max` decimal(4, 2) NULL DEFAULT NULL,
  `CO2_min` decimal(4, 2) NULL DEFAULT NULL,
  `illuminance_max` decimal(4, 2) NULL DEFAULT NULL,
  `illuminance_min` decimal(4, 2) NULL DEFAULT NULL,
  `soil_temp_max` decimal(4, 2) NULL DEFAULT NULL,
  `soil_temp_min` decimal(4, 2) NULL DEFAULT NULL,
  `greenhouse_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '环境因素阈值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for greenhouse
-- ----------------------------
DROP TABLE IF EXISTS `greenhouse`;
CREATE TABLE `greenhouse`  (
  `id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图像地址',
  `enterprise_id` int(11) NULL DEFAULT NULL COMMENT '企业id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '大棚表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(11) NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for produce
-- ----------------------------
DROP TABLE IF EXISTS `produce`;
CREATE TABLE `produce`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `greenhouse_id` int(11) NULL DEFAULT NULL COMMENT '大棚id',
  `intermediate_stage` date NULL DEFAULT NULL COMMENT '中间阶段',
  `finished_stage` date NULL DEFAULT NULL COMMENT '完成阶段',
  `original_stage` date NULL DEFAULT NULL COMMENT '原始阶段',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图像地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '农产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrcode
-- ----------------------------
DROP TABLE IF EXISTS `qrcode`;
CREATE TABLE `qrcode`  (
  `id` int(11) NULL DEFAULT NULL,
  `qrcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '二维码表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trace
-- ----------------------------
DROP TABLE IF EXISTS `trace`;
CREATE TABLE `trace`  (
  `id` int(11) NULL DEFAULT NULL,
  `qrcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '溯源码',
  `enterprise_id` int(11) NULL DEFAULT NULL COMMENT '企业id',
  `greenhouse_id` int(11) NULL DEFAULT NULL COMMENT '大棚id',
  `nurture_worker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培育人员',
  `product_worker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产人员',
  `quality_worker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质检人员',
  `transport_worker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运输人员',
  `produce_id` int(11) NULL DEFAULT NULL COMMENT '农产品id',
  `update_date` date NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '溯源表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
