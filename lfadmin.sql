/*
 Navicat Premium Data Transfer

 Source Server         : lfadmin
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : lfadmin

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 04/08/2024 14:59:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `department_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '部门名称',
  `phone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '部门地址',
  `pid` bigint NOT NULL COMMENT '所属部门编号',
  `parent_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属部门名称',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES (1, '广州LF信息技术有限公司', '020-8888888', '广州市天汇路666号', 0, '顶级部门', 0, 0);
INSERT INTO `sys_department` VALUES (2, '软件技术部', '020-88881001', '广州市天汇路667号', 1, '广州LF信息技术有限公司', 1, 0);
INSERT INTO `sys_department` VALUES (3, '人事管理部', '020-88881002', '广州市天汇路666号', 1, '广州LF信息技术有限公司', 1, 0);
INSERT INTO `sys_department` VALUES (4, '市场管理部', '020-88881003', '广州市天汇路888号', 1, '广州LF信息技术有限公司', 1, 0);
INSERT INTO `sys_department` VALUES (5, '软件研发部', '020-88881234', '广州市天汇路999号', 1, '广州LF信息技术有限公司', 2, 0);
INSERT INTO `sys_department` VALUES (6, 'Java技术部', '020-12345678', '广州市天汇路123号', 2, '软件技术部', 45, 0);
INSERT INTO `sys_department` VALUES (7, 'JAVA技术部', '123456789', '深圳市南山区', 2, '软件技术部', 123, 1);
INSERT INTO `sys_department` VALUES (8, '北京字节跳动科技有限公司', '400-140-2108', '北京市海淀区知春路甲48号2号楼10A室', 0, '顶级部门', 1, 1);
INSERT INTO `sys_department` VALUES (9, '广州科技有限公司', '020-88881345', '广州市天汇路123号', 0, '顶级部门', 1, 0);
INSERT INTO `sys_department` VALUES (10, '广州销售部', '020-88881346', '广州市天汇路124号', 9, '广州科技有限公司', 2, 0);
INSERT INTO `sys_department` VALUES (11, '广州技术部', '020-88881347', '广州市天汇路125号', 9, '广州科技有限公司', 3, 0);
INSERT INTO `sys_department` VALUES (12, '广州客服部', '020-88881348', '广州市天汇路126号', 9, '广州科技有限公司', 4, 0);
INSERT INTO `sys_department` VALUES (13, '广州人事部', '020-88881349', '广州市天汇路127号', 9, '广州科技有限公司', 5, 0);
INSERT INTO `sys_department` VALUES (14, '广州技术部研发组', '020-88881350', '广州市天汇路128号', 11, '广州技术部', 6, 0);
INSERT INTO `sys_department` VALUES (15, '广州技术部支持组', '020-88881351', '广州市天汇路129号', 11, '广州技术部', 7, 0);
INSERT INTO `sys_department` VALUES (16, '广州技术部运维组', '020-88881352', '广州市天汇路130号', 11, '广州技术部', 8, 0);
INSERT INTO `sys_department` VALUES (17, '广州技术部测试组', '020-88881353', '广州市天汇路131号', 11, '广州技术部', 9, 0);
INSERT INTO `sys_department` VALUES (18, '北京科技有限公司', '010-88881354', '北京市朝阳区建国路88号', 0, '顶级部门', 10, 0);
INSERT INTO `sys_department` VALUES (19, '北京销售部', '010-88881355', '北京市朝阳区建国路89号', 18, '北京科技有限公司', 11, 0);
INSERT INTO `sys_department` VALUES (20, '北京技术部', '010-88881356', '北京市朝阳区建国路90号', 18, '北京科技有限公司', 12, 0);
INSERT INTO `sys_department` VALUES (21, '北京客服部', '010-88881357', '北京市朝阳区建国路91号', 18, '北京科技有限公司', 13, 0);
INSERT INTO `sys_department` VALUES (22, '北京人事部', '010-88881358', '北京市朝阳区建国路92号', 18, '北京科技有限公司', 14, 0);
INSERT INTO `sys_department` VALUES (23, '北京技术部研发组', '010-88881359', '北京市朝阳区建国路93号', 20, '北京技术部', 15, 0);
INSERT INTO `sys_department` VALUES (24, '北京技术部支持组', '010-88881360', '北京市朝阳区建国路94号', 20, '北京技术部', 16, 0);
INSERT INTO `sys_department` VALUES (25, '北京技术部运维组', '010-88881361', '北京市朝阳区建国路95号', 20, '北京技术部', 17, 0);
INSERT INTO `sys_department` VALUES (26, '北京技术部测试组', '010-88881362', '北京市朝阳区建国路96号', 20, '北京技术部', 18, 0);
INSERT INTO `sys_department` VALUES (27, '上海科技有限公司', '021-88881363', '上海市浦东新区陆家嘴88号', 0, '顶级部门', 19, 0);
INSERT INTO `sys_department` VALUES (28, '上海销售部', '021-88881364', '上海市浦东新区陆家嘴89号', 27, '上海科技有限公司', 20, 0);
INSERT INTO `sys_department` VALUES (29, '上海技术部', '021-88881365', '上海市浦东新区陆家嘴90号', 27, '上海科技有限公司', 21, 0);
INSERT INTO `sys_department` VALUES (30, '上海客服部', '021-88881366', '上海市浦东新区陆家嘴91号', 27, '上海科技有限公司', 22, 0);
INSERT INTO `sys_department` VALUES (31, '上海人事部', '021-88881367', '上海市浦东新区陆家嘴92号', 27, '上海科技有限公司', 23, 0);
INSERT INTO `sys_department` VALUES (32, '上海技术部研发组', '021-88881368', '上海市浦东新区陆家嘴93号', 29, '上海技术部', 24, 0);
INSERT INTO `sys_department` VALUES (33, '上海技术部支持组', '021-88881369', '上海市浦东新区陆家嘴94号', 29, '上海技术部', 25, 0);
INSERT INTO `sys_department` VALUES (34, '上海技术部运维组', '021-88881370', '上海市浦东新区陆家嘴95号', 29, '上海技术部', 26, 0);
INSERT INTO `sys_department` VALUES (35, '上海技术部测试组', '021-88881371', '上海市浦东新区陆家嘴96号', 29, '上海技术部', 27, 0);
INSERT INTO `sys_department` VALUES (36, '深圳科技有限公司', '0755-88881372', '深圳市福田区深南大道200号', 0, '顶级部门', 28, 0);
INSERT INTO `sys_department` VALUES (37, '深圳销售部', '0755-88881373', '深圳市福田区深南大道201号', 36, '深圳科技有限公司', 29, 0);
INSERT INTO `sys_department` VALUES (38, '深圳技术部', '0755-88881374', '深圳市福田区深南大道202号', 36, '深圳科技有限公司', 30, 0);
INSERT INTO `sys_department` VALUES (39, '深圳客服部', '0755-88881375', '深圳市福田区深南大道203号', 36, '深圳科技有限公司', 31, 0);
INSERT INTO `sys_department` VALUES (40, '深圳人事部', '0755-88881376', '深圳市福田区深南大道204号', 36, '深圳科技有限公司', 32, 0);
INSERT INTO `sys_department` VALUES (41, '深圳技术部研发组', '0755-88881377', '深圳市福田区深南大道205号', 38, '深圳技术部', 33, 0);
INSERT INTO `sys_department` VALUES (42, '深圳技术部支持组', '0755-88881378', '深圳市福田区深南大道206号', 38, '深圳技术部', 34, 0);
INSERT INTO `sys_department` VALUES (43, '深圳技术部运维组', '0755-88881379', '深圳市福田区深南大道207号', 38, '深圳技术部', 35, 0);
INSERT INTO `sys_department` VALUES (44, '深圳技术部测试组', '0755-88881380', '深圳市福田区深南大道208号', 38, '深圳技术部', 36, 0);
INSERT INTO `sys_department` VALUES (45, '北京字节跳动科技有限公司', '400-140-2108', '北京市海淀区知春路甲48号2号楼10A室', 0, '顶级部门', 37, 0);
INSERT INTO `sys_department` VALUES (46, '北京字节跳动销售部', '400-140-2109', '北京市海淀区知春路甲48号2号楼10B室', 45, '北京字节跳动科技有限公司', 38, 0);
INSERT INTO `sys_department` VALUES (47, '北京字节跳动技术部', '400-140-2110', '北京市海淀区知春路甲48号2号楼10C室', 45, '北京字节跳动科技有限公司', 39, 0);
INSERT INTO `sys_department` VALUES (48, '北京字节跳动客服部', '400-140-2111', '北京市海淀区知春路甲48号2号楼10D室', 45, '北京字节跳动科技有限公司', 40, 0);
INSERT INTO `sys_department` VALUES (49, '北京字节跳动人事部', '400-140-2112', '北京市海淀区知春路甲48号2号楼10E室', 45, '北京字节跳动科技有限公司', 41, 0);
INSERT INTO `sys_department` VALUES (50, '北京字节跳动技术部研发组', '400-140-2113', '北京市海淀区知春路甲48号2号楼10F室', 47, '北京字节跳动技术部', 42, 0);
INSERT INTO `sys_department` VALUES (51, '北京字节跳动技术部支持组', '400-140-2114', '北京市海淀区知春路甲48号2号楼10G室', 47, '北京字节跳动技术部', 43, 0);
INSERT INTO `sys_department` VALUES (52, '北京字节跳动技术部运维组', '400-140-2115', '北京市海淀区知春路甲48号2号楼10H室', 47, '北京字节跳动技术部', 44, 0);
INSERT INTO `sys_department` VALUES (53, '北京字节跳动技术部测试组', '400-140-2116', '北京市海淀区知春路甲48号2号楼10I室', 47, '北京字节跳动技术部', 45, 0);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT ' 岗位编号',
  `job_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '岗位名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '总经理', NULL, '2024-08-03 21:22:12', 0, '公司最高层领导，负责全面管理和决策。');
INSERT INTO `sys_job` VALUES (2, '项目经理', NULL, '2024-08-03 20:23:55', 0, '负责项目的整体规划、执行和交付，确保项目按时、按质完成。');
INSERT INTO `sys_job` VALUES (3, '后端开放工程师', NULL, '2024-08-03 20:24:01', 0, '负责服务器端应用程序的开发和维护，确保数据处理和业务逻辑的实现。');
INSERT INTO `sys_job` VALUES (4, '前端开发工程师', NULL, '2024-08-03 20:24:06', 0, '负责客户端应用程序的开发和维护，确保用户界面的友好性和功能的实现。');
INSERT INTO `sys_job` VALUES (5, '测试人员', NULL, '2024-08-03 20:24:13', 0, '负责软件产品的测试，发现并报告缺陷，确保产品质量。');
INSERT INTO `sys_job` VALUES (6, '运维人员', NULL, '2024-08-03 20:24:19', 0, '负责系统的日常维护、监控和故障处理，确保系统的稳定运行。');
INSERT INTO `sys_job` VALUES (7, '机房管理者', NULL, '2024-08-03 20:24:24', 0, '负责机房设备的管理和维护，确保物理环境的安全和设备的正常运行。');
INSERT INTO `sys_job` VALUES (8, '网络工程师', NULL, '2024-08-03 20:24:29', 0, '负责公司网络的规划、实施和维护，确保网络的安全和畅通。');
INSERT INTO `sys_job` VALUES (9, '123', '2024-08-03 20:07:29', '2024-08-03 20:24:50', 1, NULL);
INSERT INTO `sys_job` VALUES (10, '1234', '2024-08-03 20:08:22', '2024-08-03 20:24:52', 1, NULL);
INSERT INTO `sys_job` VALUES (11, '123456', '2024-08-03 20:08:59', '2024-08-03 20:24:54', 1, NULL);
INSERT INTO `sys_job` VALUES (12, '人力资源经理', '2024-08-03 20:25:14', '2024-08-03 20:25:14', 0, '负责公司的人力资源规划、招聘、培训、绩效考核和员工关系管理。');
INSERT INTO `sys_job` VALUES (13, '财务经理', '2024-08-03 20:25:29', '2024-08-03 20:25:29', 0, '负责公司财务管理、预算编制、财务报告和资金运作，确保财务健康和合规。');
INSERT INTO `sys_job` VALUES (14, '市场经理', '2024-08-03 20:25:39', '2024-08-03 20:25:39', 0, '负责市场调研、市场策略制定和市场推广，提升公司品牌知名度和市场份额。');
INSERT INTO `sys_job` VALUES (15, 'UI/UX设计师', '2024-08-03 20:25:56', '2024-08-03 20:25:56', 0, '负责用户界面和用户体验的设计，确保产品的易用性和美观性。');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `label` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父权限ID',
  `parent_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '父权限名称',
  `code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '授权标识符',
  `path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '授权路径',
  `type` tinyint NULL DEFAULT NULL COMMENT '权限类型(0-目录 1-菜单 2-按钮)',
  `icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图标',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '系统管理', 0, '顶级菜单', 'sys:manager', '/system', 'system', '/system/system', 0, 'el-icon-setting', '2022-04-25 14:40:32', '2024-08-03 17:26:24', NULL, 0, 0);
INSERT INTO `sys_permission` VALUES (2, '部门管理', 1, '系统管理', 'sys:department', '/department', 'department', '/system/department/department', 1, 'el-icon-s-grid', '2022-04-25 14:40:32', '2024-07-31 15:31:51', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (3, '新增', 2, '部门管理', 'sys:department:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (4, '修改', 2, '部门管理', 'sys:department:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (5, '删除', 2, '部门管理', 'sys:department:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (6, '用户管理', 1, '系统管理', 'sys:user', '/userList', 'userList', '/system/user/userList', 1, 'el-icon-s-custom', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (7, '新增', 6, '用户管理', 'sys:user:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (8, '修改', 6, '用户管理', 'sys:user:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (9, '删除', 6, '用户管理', 'sys:user:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (10, '角色管理', 1, '系统管理', 'sys:role', '/roleList', 'roleList', '/system/role/roleList', 1, 'el-icon-user', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (11, '新增', 10, '角色管理', 'sys:role:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (12, '修改', 10, '角色管理', 'sys:role:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (13, '删除', 10, '角色管理', 'sys:role:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (14, '菜单管理', 1, '系统管理', 'sys:menu', '/menuList', 'menuList', '/system/menu/menuList', 1, 'el-icon-notebook-2', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (15, '新增', 14, '权限管理', 'sys:menu:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (16, '修改', 14, '权限管理', 'sys:menu:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (17, '删除', 14, '权限管理', 'sys:menu:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (18, '一级菜单', 0, '顶级菜单', 'menu', '/menu', 'menu', '/menu', 0, 'el-icon-menu', '2022-04-25 14:40:32', '2024-08-03 14:48:26', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (19, '二级菜单1', 18, '一级菜单', 'menu', '/menu/menu2-1', 'menu2-1', '/menu/menu2-1', 1, 'el-icon-menu', '2022-04-25 14:40:32', '2024-08-03 14:51:44', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (20, '新增', 19, '供应商管理', 'sys:provider:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (21, '修改', 19, '供应商管理', 'sys:provider:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (22, '删除', 19, '供应商管理', 'sys:provider:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (23, '分配角色', 6, '用户管理', 'sys:user:assign', '', '', '', 2, 'el-icon-setting', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (24, '分配权限', 10, '角色管理', 'sys:role:assign', '', '', '', 2, 'el-icon-setting', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (25, '查询', 2, '部门管理', 'sys:department:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (26, '查询', 6, '用户管理', 'sys:user:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (27, '查询', 10, '角色管理', 'sys:role:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (28, '查询', 14, '菜单管理', 'sys:menu:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (29, '二级菜单2', 18, '一级菜单', 'menu', '/menu/menu2-2', 'menu2-2', '/menu/menu2-2', 1, 'el-icon-menu', NULL, '2024-08-03 14:56:07', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (31, '岗位管理', 1, '系统管理', 'sys:job', '/jobList', 'jobList', '/system/job/jobList', 1, 'el-icon-user-solid', '2024-08-03 14:27:26', '2024-08-03 14:31:12', NULL, 6, 0);
INSERT INTO `sys_permission` VALUES (32, '新增', 31, '岗位管理', 'sys:job:add', '', '', '', 2, 'el-icon-plus', '2024-08-03 14:30:49', '2024-08-03 14:30:49', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (33, '修改', 31, '岗位管理', 'sys:job:edit', '', '', '', 2, 'el-icon-edit', '2024-08-03 14:32:28', '2024-08-03 14:32:28', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (34, '删除', 31, '岗位管理', 'sys:job:delete', '', '', '', 2, 'el-icon-delete', '2024-08-03 14:32:49', '2024-08-03 14:32:49', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (35, '查询', 31, '岗位管理', 'sys:job:select', '', '', '', 2, 'el-icon-search', '2024-08-03 14:33:38', '2024-08-03 14:34:10', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (36, '三级菜单1', 29, '二级菜单2', 'menu', '/menu/menu2-2/menu3-1', 'menu3-1', '/menu/menu2-2/menu3-1', 1, 'el-icon-menu', '2024-08-03 15:01:40', '2024-08-03 15:02:26', NULL, 6, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_All', '超级管理员', 1, '2022-04-25 14:44:23', '2024-08-03 17:26:20', '拥有最高权限', 0);
INSERT INTO `sys_role` VALUES (2, 'ROLE_SYSTEM', '系统管理员', 1, '2022-04-25 14:44:23', '2022-04-25 14:44:23', '拥有系统管理功能模块的权限', 0);
INSERT INTO `sys_role` VALUES (3, 'ROLE_RESOURCE', '资料管理员', 1, NULL, NULL, '拥有资料管理模块的功能权限', 0);
INSERT INTO `sys_role` VALUES (5, 'ROLE_WAREHOUSE', '仓库管理员', 1, '2024-07-31 15:31:12', '2024-07-31 20:47:02', '拥有仓库管理权限11', 0);
INSERT INTO `sys_role` VALUES (6, '123', '123', 1, '2024-07-31 18:46:49', '2024-08-03 17:23:54', '123', 0);
INSERT INTO `sys_role` VALUES (7, '123', '123', 1, '2024-08-03 17:47:10', '2024-08-03 17:47:22', '1234', 1);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_Id` bigint NOT NULL COMMENT '角色ID',
  `permission_Id` bigint NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (3, 19);
INSERT INTO `sys_role_permission` VALUES (3, 20);
INSERT INTO `sys_role_permission` VALUES (3, 21);
INSERT INTO `sys_role_permission` VALUES (3, 22);
INSERT INTO `sys_role_permission` VALUES (3, 18);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 16);
INSERT INTO `sys_role_permission` VALUES (2, 28);
INSERT INTO `sys_role_permission` VALUES (2, 18);
INSERT INTO `sys_role_permission` VALUES (2, 19);
INSERT INTO `sys_role_permission` VALUES (2, 20);
INSERT INTO `sys_role_permission` VALUES (2, 21);
INSERT INTO `sys_role_permission` VALUES (2, 22);
INSERT INTO `sys_role_permission` VALUES (2, 29);
INSERT INTO `sys_role_permission` VALUES (2, 30);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 14);
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 27);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 28);
INSERT INTO `sys_role_permission` VALUES (1, 31);
INSERT INTO `sys_role_permission` VALUES (1, 32);
INSERT INTO `sys_role_permission` VALUES (1, 33);
INSERT INTO `sys_role_permission` VALUES (1, 34);
INSERT INTO `sys_role_permission` VALUES (1, 35);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (1, 29);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录名称(用户名)',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录密码',
  `is_account_non_expired` tinyint NOT NULL COMMENT '帐户是否过期(1-未过期，0-已过期)',
  `is_account_non_locked` tinyint NOT NULL COMMENT '帐户是否被锁定(1-未过期，0-已过期)',
  `is_credentials_non_expired` tinyint NOT NULL COMMENT '密码是否过期(1-未过期，0-已过期)',
  `is_enabled` tinyint NOT NULL COMMENT '帐户是否可用(1-可用，0-禁用)',
  `real_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '真实姓名',
  `nick_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `job_id` bigint NULL DEFAULT NULL COMMENT '所属岗位ID',
  `job_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '所属岗位名称',
  `department_id` bigint NULL DEFAULT NULL COMMENT '所属部门ID',
  `department_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `gender` tinyint NOT NULL COMMENT '性别(0-男，1-女)',
  `phone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'https://manong-authority.oss-cn-guangzhou.aliyuncs.com/avatar/default-avatar.gif' COMMENT '用户头像',
  `is_admin` tinyint NULL DEFAULT 0 COMMENT '是否是管理员(1-管理员)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$lzcVqXufsdFtLRHi.8yXiuQ5ihtScllTiDKk4KJf0gVCQcK5Z6KDa', 1, 1, 1, 1, '李明明', '超级管理员', 1, '总经理', 1, '广州码农信息技术有限公司', 0, '13242587416', 'admin@163.com', 'https://lf-admin.oss-cn-beijing.aliyuncs.com/avatar/2024/08/04/fed11ef862034c58b3aa79076347645d.jpg', 1, NULL, '2024-08-04 14:47:52', 0);
INSERT INTO `sys_user` VALUES (2, 'liming', '$2a$10$WwhJ8dBezfyMFIn19.ELru58K65k6N2tgewtv2sWdClKiRCjC55wG', 1, 1, 1, 1, '黎明', '黎明', 2, '项目经理', 4, '市场管理部', 0, '13262365414', '', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, NULL, '2024-08-04 12:50:39', 0);
INSERT INTO `sys_user` VALUES (3, 'zhangsan', '$2a$10$iBQbmrAEBE5B84/U3RY7c.zhObI4aIpjl807FV4LzL/uay7arIcpu', 1, 1, 1, 1, '张三', '张三', 3, '后端开发工程师', 3, '人事管理部', 0, '13245678965', 'zhangsan@163.com', 'https://lf-admin.oss-cn-beijing.aliyuncs.com/avatar/2024/08/02/38a2409f2ea643978fdb403b1d4295c7.jpg', 0, NULL, '2024-08-03 21:50:20', 0);
INSERT INTO `sys_user` VALUES (4, 'lisi', '$2a$10$QywHvELdRoFGiU6LKpd/X.LYpfaXETtS0pD4Nem2K3c0iMQwaZuAm', 1, 1, 1, 1, '李四', '李四', 4, '前端开发工程师', 2, '软件技术部', 0, '13754214568', '', 'https://lf-admin.oss-cn-beijing.aliyuncs.com/avatar/2024/08/02/47d354c35f2940dea645251e2d9841d2.jpg', 0, NULL, '2024-08-02 00:25:01', 0);
INSERT INTO `sys_user` VALUES (5, 'wangwu', '$2a$10$O8uyPZFS9PLfR8JN.aMRi.l/YeykYYuKH.cg/HBAR.N4NJeNg8hQK', 1, 1, 1, 1, '王五', '王五', 5, '测试人员', 5, '软件研发部', 0, '13212345678', '123@qq.com', 'https://lf-admin.oss-cn-beijing.aliyuncs.com/avatar/2024/08/02/162a544b0b004fdcb506208e5c608044.jpg', 0, NULL, '2024-08-03 21:25:21', 0);
INSERT INTO `sys_user` VALUES (6, 'zhaoliu', '$2a$10$r45wkEYLHlteEr0KLI8y3.G506ylhQrEJkmGM.i2eHkcCnFvfbhCS', 1, 1, 1, 1, '赵六', '赵六', 6, '运维人员', 2, '软件技术部', 0, '13212345676', '', 'https://lf-admin.oss-cn-beijing.aliyuncs.com/avatar/2024/08/02/a5ab3a05b7f441e197031f15c6685ed4.jpg', 0, NULL, '2024-08-02 12:06:54', 0);
INSERT INTO `sys_user` VALUES (7, '123', '$2a$10$XPWg1LQSSVP/Kvz8nDrxD.ACpQ.FsJpQZVbLTLmDzBTV8eH.Yulxi', 1, 1, 1, 1, '123', '123', 7, '机房管理者', 1, '广州LF信息技术有限公司', 0, '13912311231', '123', '', 0, '2024-08-01 13:26:05', '2024-08-01 13:26:05', 0);
INSERT INTO `sys_user` VALUES (8, 'lfybtx', '$2a$10$uLu1xxnqgAEOIF0LFElS9OJgYgXUJoVHZ8g/Ej/XcWISbth1hzOpy', 1, 1, 1, 1, 'lfybtx', 'lfybtx', 8, '总经理', 6, 'Java技术部', 0, '13956568888', '123456@qq.com', 'https://lf-admin.oss-cn-beijing.aliyuncs.com/avatar/2024/08/02/fa4a88a5bf1c45ad8e7d9f1e6846b342.jpg', 1, '2024-08-02 13:18:14', '2024-08-03 21:24:28', 0);
INSERT INTO `sys_user` VALUES (9, '123456', '$2a$10$9fYMPZxD0A3vU6W5OkdXKu934ddMsOApuOakTggOaSPk5okfWkXk.', 0, 0, 0, 0, '123456', '13234', 8, '网络工程师', 5, '软件研发部', 0, '13988888888', '', '', 0, '2024-08-03 22:14:20', '2024-08-03 22:14:20', 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `role_id` bigint NOT NULL COMMENT '角色编号'
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (4, 2);
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (8, 1);

SET FOREIGN_KEY_CHECKS = 1;
