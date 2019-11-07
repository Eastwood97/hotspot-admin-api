
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for camera_cat_info
-- ----------------------------
DROP TABLE IF EXISTS `camera_cat_info`;
CREATE TABLE `camera_cat_info` (
  `id` bigint(64) NOT NULL COMMENT '主键id',
  `full_dat_id` varchar(128) DEFAULT NULL COMMENT '带背景原图数据ID',
  `full_img_id` varchar(128) DEFAULT NULL COMMENT '人脸原图文件ID',
  `video_id` varchar(128) DEFAULT NULL COMMENT '视频文件ID',
  `capture_time` datetime NOT NULL COMMENT '抓拍时间yyyyMMddHHmmss\n',
  `quality` int(16) NOT NULL DEFAULT '0' COMMENT '识别质量，一般为0，目标非0',
  `target_id` bigint(64) DEFAULT NULL COMMENT '如果是目标，有target_id',
  `dev_id` bigint(64) NOT NULL COMMENT '前端设备ID',
  `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人脸抓拍结果';

-- ----------------------------
-- Table structure for camera_compare_result
-- ----------------------------
DROP TABLE IF EXISTS `camera_compare_result`;
CREATE TABLE `camera_compare_result` (
  `id` bigint(64) NOT NULL COMMENT '人脸比对结果',
  `full_dat_id` varchar(128) DEFAULT NULL COMMENT '带背景原图数据ID',
  `full_img_id` varchar(128) DEFAULT NULL COMMENT '抓拍人脸原图文件ID',
  `target_faces` varchar(1024) DEFAULT NULL COMMENT '布控人脸图（列表：(TargetID, 照片ID，相似度）)\n',
  `video_id` varchar(128) DEFAULT NULL COMMENT '视频文件ID，一般人脸为空,目标位为视频文件ID',
  `capture_time` datetime DEFAULT NULL COMMENT '抓拍时间yyyyMMddHHmmss',
  `dev_id` bigint(64) DEFAULT NULL COMMENT '前端设备ID',
  `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人脸比对结果';

-- ----------------------------
-- Table structure for camera_hot_relation
-- ----------------------------
DROP TABLE IF EXISTS `camera_hot_relation`;
CREATE TABLE `camera_hot_relation` (
  `target_id` bigint(64) NOT NULL,
  `target_num_id` bigint(64) DEFAULT NULL COMMENT '采集号码ID',
  `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人脸和码址的关联';

-- ----------------------------
-- Table structure for camera_target_face
-- ----------------------------
DROP TABLE IF EXISTS `camera_target_face`;
CREATE TABLE `camera_target_face` (
  `target_id` bigint(64) NOT NULL COMMENT 'ID',
  `target_name` varchar(128) NOT NULL COMMENT '目标姓名',
  `desc` varchar(1024) DEFAULT NULL COMMENT '描述（备注信息）',
  `file_name` varchar(128) DEFAULT NULL COMMENT '照片文件名（列表，最多三个）',
  `operator_id` bigint(64) DEFAULT NULL COMMENT '创建人员ID',
  `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目标人脸';

-- ----------------------------
-- Table structure for hot_compare_result
-- ----------------------------
DROP TABLE IF EXISTS `hot_compare_result`;
CREATE TABLE `hot_compare_result` (
  `id` varchar(64) NOT NULL COMMENT '该条记录的ID',
  `imsi` varchar(32) DEFAULT NULL,
  `imei` varchar(32) DEFAULT NULL,
  `isdn` varchar(32) DEFAULT NULL,
  `mode` varchar(32) DEFAULT NULL COMMENT '获取网络制式',
  `capture_time` datetime NOT NULL COMMENT '抓拍时间yyyyMMddHHmmss',
  `frt_dev_id` bigint(64) NOT NULL COMMENT '前端设备ID',
  `target_id` bigint(64) NOT NULL COMMENT '目标ID',
  `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='号码比对结果';

-- ----------------------------
-- Table structure for hot_front_device
-- ----------------------------
DROP TABLE IF EXISTS `hot_front_device`;
CREATE TABLE `hot_front_device` (
  `dev_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `group_id` bigint(64) NOT NULL COMMENT '区域分组编号，设备分组，默认为0',
  `dev_name` varchar(128) NOT NULL COMMENT '设备名称',
  `dev_type` tinyint(8) NOT NULL COMMENT '设备类型',
  `dev_num` varchar(128) NOT NULL COMMENT '设备编号',
  `ip_addr` varchar(64) NOT NULL COMMENT '设备IP',
  `ipv6_addr` varchar(64) DEFAULT NULL COMMENT '设备IPV6地址',
  `port` int(16) NOT NULL COMMENT '设备端口号',
  `lng` varchar(64) DEFAULT NULL COMMENT '经度',
  `lat` varchar(64) DEFAULT NULL COMMENT '纬度',
  `place` varchar(128) NOT NULL COMMENT '位置描述',
  `description` varchar(128) DEFAULT NULL COMMENT '设备详细描述',
  `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`dev_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='前端设备表';

-- ----------------------------
-- Table structure for hot_num_info
-- ----------------------------
DROP TABLE IF EXISTS `hot_num_info`;
CREATE TABLE `hot_num_info` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '该条记录的Id',
  `imsi` varchar(32) NOT NULL COMMENT 'Imsi',
  `imei` varchar(32) DEFAULT NULL COMMENT 'Imei',
  `isdn` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `mode` varchar(32) DEFAULT NULL COMMENT 'GSM\\WCDMA\\LTE',
  `capture_time` datetime NOT NULL COMMENT '捕获时间',
  `target_id` bigint(64) DEFAULT NULL COMMENT '如果是目标，有target_id',
  `dev_id` bigint(64) NOT NULL COMMENT '抓取的该号码的前端设备id',
  `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热点号码抓取信息';

-- ----------------------------
-- Table structure for hot_target_info
-- ----------------------------
DROP TABLE IF EXISTS `hot_target_info`;
CREATE TABLE `hot_target_info` (
  `target_id` bigint(64) NOT NULL COMMENT 'ID',
  `target_name` varchar(128) NOT NULL COMMENT '目标姓名',
  `desc` varchar(1024) DEFAULT NULL COMMENT '描述（备注信息）',
  `imsi` varchar(32) DEFAULT NULL,
  `imei` varchar(32) DEFAULT NULL,
  `isdn` varchar(32) DEFAULT NULL,
 `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  `operator_id` bigint(64) DEFAULT NULL COMMENT '创建人员ID',
  PRIMARY KEY (`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目标号码';

-- ----------------------------
-- Table structure for operator_info
-- ----------------------------
DROP TABLE IF EXISTS `operator_info`;
CREATE TABLE `operator_info` (
  `operator_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '人员ID',
  `group_id` tinyint(8) NOT NULL COMMENT '人员分组编号，0为管理员，1为普通工作人员',
  `operator_name` varchar(128) NOT NULL COMMENT '人员名称',
  `operator_number` varchar(128) DEFAULT NULL COMMENT '人员手机号码',
 `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime DEFAULT NOW() COMMENT '更新时间',
  `description` varchar(128) DEFAULT NULL COMMENT '详细描述',
  PRIMARY KEY (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作人员表';

SET FOREIGN_KEY_CHECKS = 1;





DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permission` varchar(63) DEFAULT NULL COMMENT '权限',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL COMMENT '角色名称',
  `desc` varchar(1023) DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(63) NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(255) DEFAULT '''' COMMENT '头像图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  `role_ids` varchar(127) DEFAULT '[]' COMMENT '角色列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';


LOCK TABLES `admin` WRITE;
INSERT INTO `admin` VALUES (1,'admin123','$2a$10$.rEfyBb/GURD9P2p0fRg/OAJGloGNDkJS4lY0cQHeqDgsbdTylBpu',NULL,NULL,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','2018-02-01 00:00:00','2018-02-01 00:00:00',0,'[1]'),(4,'promotion123','$2a$10$wDZLOLLnzZ1EFZ3ldZ1XFOUWDEX6TnQCUFdJz4g.PoMaLTzS8TjWq','',NULL,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','2019-01-07 15:16:59','2019-01-07 15:17:34',0,'[3]'),(5,'mall123','$2a$10$aCtsc4rG6KmxQ59.IkYse.oRyKuwQoU2CPCmxSdB.d5nXq6aw/z4O','',NULL,'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','2019-01-07 15:17:25','2019-01-07 15:21:05',0,'[2]');

UNLOCK TABLES;

DROP TABLE IF EXISTS `hotspot_log`;

CREATE TABLE `hotspot_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '管理员',
  `ip` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '管理员地址',
  `type` int(11) DEFAULT NULL COMMENT '操作分类',
  `action` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作动作',
  `status` tinyint(1) DEFAULT NULL COMMENT '操作状态',
  `result` varchar(127) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作结果，或者成功消息，或者失败消息',
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '补充信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';


