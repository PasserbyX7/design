SET
  NAMES utf8mb4;
SET
  FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
    `license` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '车牌',
    `img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
    `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态[0:正常,1:借出,2:维修,3:已预定]',
    `desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
    `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '汽车' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `membername` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
    `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '密码',
    `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
    `header` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
    `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别[0:女,1:男]',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
    `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `leasehold`;
CREATE TABLE `leasehold` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `member_id` bigint(20) NOT NULL COMMENT '用户id',
    `car_id` bigint(20) NOT NULL COMMENT '汽车id',
    `lend_time` datetime(0) NULL DEFAULT NULL COMMENT '借出时间',
    `remand_time` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '租凭' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(64) CHARACTER SET utf8mb4 NULL COMMENT '角色名',
    `enname` varchar(64) CHARACTER SET utf8mb4 NULL COMMENT '角色英文名',
    `description` varchar(200) CHARACTER SET utf8mb4 NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;
DROP TABLE IF EXISTS `role`;
CREATE TABLE `member_role_relation` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `member_id` bigint(20) UNSIGNED NULL COMMENT '用户id',
    `role_id` bigint(20) UNSIGNED NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-角色关系' ROW_FORMAT = Dynamic;