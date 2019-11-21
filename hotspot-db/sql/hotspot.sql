/*
 Navicat Premium Data Transfer

 Source Server         : mysql57
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : hotspot

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 21/11/2019 08:43:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '\'' COMMENT '头像图片',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `role_ids` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '[]' COMMENT '角色列表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin123', '$2a$10$.rEfyBb/GURD9P2p0fRg/OAJGloGNDkJS4lY0cQHeqDgsbdTylBpu', NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2018-02-01 00:00:00', '2019-11-18 17:05:51', 0, '[1]');
INSERT INTO `admin` VALUES (4, 'promotion123', '$2a$10$wDZLOLLnzZ1EFZ3ldZ1XFOUWDEX6TnQCUFdJz4g.PoMaLTzS8TjWq', '', NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2019-01-07 15:16:59', '2019-01-07 15:17:34', 0, '[3]');
INSERT INTO `admin` VALUES (5, 'mall123', '$2a$10$aCtsc4rG6KmxQ59.IkYse.oRyKuwQoU2CPCmxSdB.d5nXq6aw/z4O', '', NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2019-01-07 15:17:25', '2019-01-07 15:21:05', 0, '[2]');

-- ----------------------------
-- Table structure for camera_cat_info
-- ----------------------------
DROP TABLE IF EXISTS `camera_cat_info`;
CREATE TABLE `camera_cat_info`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `full_dat_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '带背景原图数据ID',
  `full_img_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人脸原图文件ID',
  `video_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频文件ID',
  `capture_time` datetime(0) NOT NULL COMMENT '抓拍时间yyyyMMddHHmmss\n',
  `quality` int(16) NOT NULL DEFAULT 0 COMMENT '识别质量，一般为0，目标非0',
  `target_id` bigint(64) NULL DEFAULT NULL COMMENT '如果是目标，有target_id',
  `dev_id` bigint(64) NOT NULL COMMENT '前端设备ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人脸抓拍结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for camera_compare_result
-- ----------------------------
DROP TABLE IF EXISTS `camera_compare_result`;
CREATE TABLE `camera_compare_result`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '人脸比对结果',
  `full_dat_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '带背景原图数据ID',
  `full_img_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抓拍人脸原图文件ID',
  `target_faces` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '布控人脸图（列表：(TargetID, 照片ID，相似度）)\n',
  `video_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频文件ID，一般人脸为空,目标位为视频文件ID',
  `capture_time` datetime(0) NULL DEFAULT NULL COMMENT '抓拍时间yyyyMMddHHmmss',
  `dev_id` bigint(8) NULL DEFAULT NULL COMMENT '前端设备ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `target_face_img` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标人脸图片',
  `compare_Score` double(8, 0) NULL DEFAULT NULL COMMENT '相似度',
  `capture_face_img` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抓拍人脸的图像',
  `library_Name` tinyint(8) NULL DEFAULT NULL COMMENT '库名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人脸比对结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for camera_hot_relation
-- ----------------------------
DROP TABLE IF EXISTS `camera_hot_relation`;
CREATE TABLE `camera_hot_relation`  (
  `target_id` bigint(64) NOT NULL AUTO_INCREMENT,
  `target_num_id` bigint(64) NULL DEFAULT NULL COMMENT '采集号码ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`target_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人脸和码址的关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for camera_target_face
-- ----------------------------
DROP TABLE IF EXISTS `camera_target_face`;
CREATE TABLE `camera_target_face`  (
  `target_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `target_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标姓名',
  `desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述（备注信息）',
  `file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片文件名（列表，最多三个）',
  `operator_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人员ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`target_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '目标人脸' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hot_compare_result
-- ----------------------------
DROP TABLE IF EXISTS `hot_compare_result`;
CREATE TABLE `hot_compare_result`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '该条记录的ID',
  `imsi` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imei` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mode` tinyint(8) NULL DEFAULT NULL COMMENT '获取网络制式',
  `capture_time` datetime(0) NOT NULL COMMENT '抓拍时间yyyyMMddHHmmss',
  `frt_dev_id` bigint(64) NOT NULL COMMENT '前端设备ID',
  `target_id` bigint(64) NOT NULL COMMENT '目标ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `attribution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属地',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '号码比对结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hot_front_device
-- ----------------------------
DROP TABLE IF EXISTS `hot_front_device`;
CREATE TABLE `hot_front_device`  (
  `dev_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `group_id` bigint(64) NOT NULL DEFAULT 0 COMMENT '区域分组编号，设备分组，默认为0',
  `dev_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称',
  `dev_type` tinyint(8) NOT NULL COMMENT '设备类型',
  `dev_num` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备编号',
  `ip_addr` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备IP',
  `ipv6_addr` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备IPV6地址',
  `port` int(16) NOT NULL COMMENT '设备端口号',
  `lng` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `place` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '位置描述',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备详细描述',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(8) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`dev_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '前端设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hot_front_device
-- ----------------------------
INSERT INTO `hot_front_device` VALUES (1, 0, '1', 1, '1', '1', '', 1, NULL, NULL, NULL, '', '2019-11-12 21:04:57', '2019-11-12 21:04:57', NULL);
INSERT INTO `hot_front_device` VALUES (2, 2, '2', 2, '2', '2', '2', 2, NULL, NULL, NULL, '', '2019-11-12 21:05:10', '2019-11-12 21:05:10', NULL);
INSERT INTO `hot_front_device` VALUES (3, 3, '3', 3, '33', '3', '3', 3, NULL, NULL, NULL, '3', '2019-11-12 21:05:19', '2019-11-12 21:05:19', NULL);
INSERT INTO `hot_front_device` VALUES (4, 4, '4546', 4, '4', '4', '4', 1, NULL, NULL, NULL, '4', '2019-11-12 21:05:31', '2019-11-12 21:39:45', NULL);

-- ----------------------------
-- Table structure for hot_num_info
-- ----------------------------
DROP TABLE IF EXISTS `hot_num_info`;
CREATE TABLE `hot_num_info`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '该条记录的Id',
  `imsi` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Imsi',
  `imei` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Imei',
  `isdn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `mode` tinyint(8) NOT NULL COMMENT '1为GSM\\2为WCDMA\\3为LTE',
  `capture_time` datetime(0) NOT NULL COMMENT '捕获时间',
  `target_id` bigint(64) NULL DEFAULT NULL COMMENT '如果是目标，有target_id',
  `dev_id` bigint(64) NOT NULL COMMENT '抓取的该号码的前端设备id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `attribution` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属地',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '热点号码抓取信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hot_num_info
-- ----------------------------
INSERT INTO `hot_num_info` VALUES (1, '1', '1', '1', 1, '2019-11-19 08:57:45', 1, 1, '2019-11-19 08:57:09', '2019-11-20 08:57:30', '1');

-- ----------------------------
-- Table structure for hot_target_info
-- ----------------------------
DROP TABLE IF EXISTS `hot_target_info`;
CREATE TABLE `hot_target_info`  (
  `target_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `target_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目标姓名',
  `desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述（备注信息）',
  `imsi` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imei` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_id` bigint(64) NULL DEFAULT NULL COMMENT '创建人员ID',
  PRIMARY KEY (`target_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '目标号码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hot_target_info
-- ----------------------------
INSERT INTO `hot_target_info` VALUES (1, '小王', 'test01', '456', '121', '1888888', '2019-11-09 19:03:37', '2019-11-11 13:40:41', 1);
INSERT INTO `hot_target_info` VALUES (2, '22', '11', '11', '11', '11', '2019-11-15 10:32:21', '2019-11-15 10:32:48', 1);

-- ----------------------------
-- Table structure for hotspot_log
-- ----------------------------
DROP TABLE IF EXISTS `hotspot_log`;
CREATE TABLE `hotspot_log`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `admin` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '管理员',
  `ip` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '管理员地址',
  `type` int(11) NULL DEFAULT NULL COMMENT '操作分类',
  `action` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作动作',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '操作状态',
  `result` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作结果，或者成功消息，或者失败消息',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '补充信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for operator_info
-- ----------------------------
DROP TABLE IF EXISTS `operator_info`;
CREATE TABLE `operator_info`  (
  `operator_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '人员ID',
  `group_id` tinyint(8) NOT NULL COMMENT '人员分组编号，0为管理员，1为普通工作人员',
  `operator_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员名称',
  `operator_number` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员手机号码',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细描述',
  PRIMARY KEY (`operator_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(11) NULL DEFAULT NULL COMMENT '角色ID',
  `permission` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 1, '*', '2019-01-01 00:00:00', '2019-01-01 00:00:00', 0);
INSERT INTO `permission` VALUES (2, 2, 'admin:category:read', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (3, 2, 'admin:category:update', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (4, 2, 'admin:category:delete', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (5, 2, 'admin:category:create', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (6, 2, 'admin:category:list', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (7, 2, 'admin:brand:create', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (8, 2, 'admin:brand:list', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (9, 2, 'admin:brand:delete', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (10, 2, 'admin:brand:read', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (11, 2, 'admin:brand:update', '2019-01-07 15:18:53', '2019-01-07 15:18:53', 0);
INSERT INTO `permission` VALUES (12, 3, 'admin:ad:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (13, 3, 'admin:ad:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (14, 3, 'admin:ad:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (15, 3, 'admin:ad:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (16, 3, 'admin:ad:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (17, 3, 'admin:groupon:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (18, 3, 'admin:groupon:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (19, 3, 'admin:groupon:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (20, 3, 'admin:groupon:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (21, 3, 'admin:groupon:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (22, 3, 'admin:topic:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (23, 3, 'admin:topic:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (24, 3, 'admin:topic:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (25, 3, 'admin:topic:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (26, 3, 'admin:topic:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (27, 3, 'admin:coupon:list', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (28, 3, 'admin:coupon:delete', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (29, 3, 'admin:coupon:read', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (30, 3, 'admin:coupon:create', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);
INSERT INTO `permission` VALUES (31, 3, 'admin:coupon:update', '2019-01-07 15:18:57', '2019-01-07 15:18:57', 0);

-- ----------------------------
-- Table structure for related_num
-- ----------------------------
DROP TABLE IF EXISTS `related_num`;
CREATE TABLE `related_num`  (
  `id` bigint(8) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `imei` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `imsi` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `isdn` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `number_id` bigint(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `desc` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '所有模块的权限', 1, '2019-01-01 00:00:00', '2019-01-01 00:00:00', 0);
INSERT INTO `role` VALUES (2, '商场管理员', '只有商场模块的操作权限', 1, '2019-01-01 00:00:00', '2019-01-07 15:15:12', 0);
INSERT INTO `role` VALUES (3, '推广管理员', '只有推广模块的操作权限', 1, '2019-01-01 00:00:00', '2019-01-07 15:15:24', 0);

SET FOREIGN_KEY_CHECKS = 1;
