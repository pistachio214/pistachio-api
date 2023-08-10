DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict`
(
    `id`          bigint(20) NOT NULL COMMENT '编号',
    `type`        varchar(100)        DEFAULT NULL,
    `description` varchar(100)        DEFAULT NULL,
    `remarks`     varchar(255)        DEFAULT NULL,
    `system`      char(1)             DEFAULT '0',
    `created_at`  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   int(11)             DEFAULT '1',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='字典表';

insert into `sys_dict`(`id`, `type`, `description`, `remarks`, `system`, `created_at`, `updated_at`, `is_delete`)
values (1484154771923578881, 'sex_state', '性别', '性别 1男 0女 2未知', '1', '2022-01-20 21:24:07',
        '2022-01-20 21:24:07', 1),
       (1688495637126971394, 'dicts_type', NULL, NULL, '1', '2023-08-07 18:21:45', '2023-08-07 18:21:45', NULL),
       (1688794906526482433, 'dicts_type', '类型', '表示字典的分类 1系统类', '1', '2023-08-08 14:10:56',
        '2023-08-08 17:48:44', 1);

DROP TABLE IF EXISTS `sys_dict_item`;

CREATE TABLE `sys_dict_item`
(
    `id`          bigint(20) NOT NULL COMMENT '编号',
    `dict_id`     bigint(20) NOT NULL,
    `value`       varchar(100)        DEFAULT NULL,
    `label`       varchar(100)        DEFAULT NULL,
    `type`        varchar(100)        DEFAULT NULL,
    `description` varchar(100)        DEFAULT NULL,
    `remarks`     varchar(255)        DEFAULT NULL,
    `sort`        int(11)    NOT NULL DEFAULT '0' COMMENT '排序（升序）',
    `created_at`  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   int(11)             DEFAULT '1',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='字典项';

insert into `sys_dict_item`(`id`, `dict_id`, `value`, `label`, `type`, `description`, `remarks`, `sort`, `created_at`,
                            `updated_at`, `is_delete`)
values (1484187904907722754, 1484154771923578881, '1', '男', 'sex_state', '表示男性', NULL, 1, '2022-01-20 23:35:46',
        '2022-01-20 23:35:46', 1),
       (1484187995441774593, 1484154771923578881, '0', '女', 'sex_state', '表示女性', '', 2, '2022-01-20 23:36:08',
        '2022-01-20 23:59:35', 1),
       (1484188143530065922, 1484154771923578881, '2', '未知', 'sex_state', '表示没有输入性别', '没有填入性别选项', 3,
        '2022-01-20 23:36:43', '2022-01-20 23:59:40', 1),
       (1688849156531027970, 1688794906526482433, '1', '系统类', 'dicts_type', '表示系统内部字典', '系统内部字典', 1,
        '2023-08-08 17:46:30', '2023-08-08 17:46:30', 1);

DROP TABLE IF EXISTS `sys_exception_log`;

CREATE TABLE `sys_exception_log`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `oper_requ_method` varchar(64)         DEFAULT NULL COMMENT '请求方式',
    `exc_requ_param`   text COMMENT '请求参数',
    `exc_name`         varchar(255)        DEFAULT NULL COMMENT '异常名称',
    `exc_message`      text COMMENT '异常信息',
    `oper_user_id`     bigint(20)          DEFAULT NULL COMMENT '操作者',
    `oper_user_name`   varchar(64)         DEFAULT NULL COMMENT '操作员名称',
    `oper_method`      varchar(255)        DEFAULT NULL COMMENT '操作方法',
    `oper_uri`         varchar(255)        DEFAULT NULL COMMENT '请求URL',
    `oper_ip`          varchar(64)         DEFAULT NULL COMMENT '请求IP',
    `oper_ver`         varchar(64)         DEFAULT NULL COMMENT '操作版号',
    `created_at`       timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`        int(11)             DEFAULT '1' COMMENT '删除标识',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='异常日志表';

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu`
(
    `id`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `parent_id`  bigint(20)   DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
    `name`       varchar(64) NOT NULL,
    `path`       varchar(255) DEFAULT NULL COMMENT '菜单URL',
    `perms`      varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `component`  varchar(255) DEFAULT NULL,
    `type`       int(11)     NOT NULL COMMENT '类型: 0目录;1菜单;2按钮',
    `icon`       varchar(32)  DEFAULT NULL COMMENT '菜单图标',
    `order_num`  int(11)      DEFAULT NULL COMMENT '排序',
    `created_at` datetime    NOT NULL,
    `updated_at` datetime     DEFAULT NULL,
    `status`     int(11)     NOT NULL,
    `is_delete`  int(11)      DEFAULT '1' COMMENT '删除标识',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 49
  DEFAULT CHARSET = utf8 COMMENT ='系统菜单表';

insert into `sys_menu`(`id`, `parent_id`, `name`, `path`, `perms`, `component`, `type`, `icon`, `order_num`,
                       `created_at`, `updated_at`, `status`, `is_delete`)
values (1, 0, '仪表盘', '/dashboard', 'sys:home', NULL, 0, 'DashboardOutlined', 1, '2021-10-06 23:46:52',
        '2022-01-19 21:35:23', 1, 1),
       (5, 0, '系统工具', '/utils', 'sys:tools', NULL, 0, 'RadiusSettingOutlined', 2, '2021-01-15 19:06:11',
        '2022-01-19 21:35:18', 1, 1),
       (6, 5, '数字字典', '/utils/dicts', 'sys:dict:list', 'sys/Dict', 1, '', 1, '2021-01-15 19:07:18',
        '2021-01-18 16:32:13', 1, 1),
       (21, 0, '系统设置', '/system', 'sys:home', NULL, 0, 'SettingOutlined', 2, '2021-10-04 00:00:00',
        '2022-01-08 15:50:29', 1, 1),
       (22, 21, '人员管理', '/system/users', 'sys:user:list', 'sys/User', 1, '', 1, '2021-01-15 19:03:45',
        '2021-11-10 11:15:05', 1, 1),
       (23, 21, '角色管理', '/system/roles', 'sys:role:list', 'sys/Role', 1, '', 2, '2021-01-15 19:03:45',
        '2021-01-15 19:03:48', 1, 1),
       (24, 21, '菜单管理', '/system/menus', 'sys:menu:list', 'sys/Menu', 1, '', 3, '2021-01-15 19:03:45',
        '2021-01-15 19:03:48', 1, 1),
       (27, 23, '添加角色', '', 'sys:role:save', '', 2, '', 1, '2021-01-15 23:02:25', '2021-01-17 21:53:14', 0, 1),
       (29, 22, '添加用户', NULL, 'sys:user:save', NULL, 2, NULL, 1, '2021-01-17 21:48:32', NULL, 1, 1),
       (30, 22, '修改用户', NULL, 'sys:user:update', NULL, 2, NULL, 2, '2021-01-17 21:49:03', '2021-01-17 21:53:04', 1,
        1),
       (31, 22, '删除用户', NULL, 'sys:user:delete', NULL, 2, NULL, 3, '2021-01-17 21:49:21', NULL, 1, 1),
       (32, 23, '分配角色', NULL, 'sys:user:role', NULL, 2, NULL, 4, '2021-01-17 21:49:58', NULL, 1, 1),
       (33, 22, '重置密码', NULL, 'sys:user:repass', NULL, 2, NULL, 5, '2021-01-17 21:50:36', NULL, 1, 1),
       (34, 23, '修改角色', NULL, 'sys:role:update', NULL, 2, NULL, 2, '2021-01-17 21:51:14', NULL, 1, 1),
       (35, 23, '删除角色', NULL, 'sys:role:delete', NULL, 2, NULL, 3, '2021-01-17 21:51:39', NULL, 1, 1),
       (36, 23, '分配权限', NULL, 'sys:role:perm', NULL, 2, NULL, 5, '2021-01-17 21:52:02', NULL, 1, 1),
       (37, 24, '添加菜单', NULL, 'sys:menu:save', NULL, 2, NULL, 1, '2021-01-17 21:53:53', '2021-01-17 21:55:28', 1,
        1),
       (38, 24, '修改菜单', NULL, 'sys:menu:update', NULL, 2, NULL, 2, '2021-01-17 21:56:12', NULL, 1, 1),
       (39, 24, '删除菜单', NULL, 'sys:menu:delete', NULL, 2, NULL, 3, '2021-01-17 21:56:36', NULL, 1, 1),
       (40, 22, '修改头像', NULL, 'sys:user:save:avatar', NULL, 2, NULL, 4, '2022-01-08 23:26:01',
        '2022-01-08 23:26:14', 1, 1),
       (41, 6, '字典列表', NULL, 'sys:dict:list', NULL, 2, NULL, 1, '2022-01-19 21:35:58', '2022-01-19 21:38:13', 1, 1),
       (42, 6, '编辑字典', NULL, 'sys:dict:edit', NULL, 2, NULL, 1, '2022-01-19 22:49:02', '2022-01-19 22:49:02', 1, 1),
       (43, 6, '新增字典', NULL, 'sys:dict:save', NULL, 2, NULL, 1, '2022-01-19 22:49:26', '2022-01-19 22:49:26', 1, 1),
       (44, 6, '删除字典', NULL, 'sys:dict:delete', NULL, 2, NULL, 1, '2022-01-19 22:54:57', '2022-01-19 22:54:57', 1,
        1),
       (45, 6, '字典项列表', NULL, 'sys:dict:item:list', NULL, 2, NULL, 1, '2022-01-20 22:26:00', '2022-01-20 22:26:00',
        1, 1),
       (46, 6, '新增数据字典项', NULL, 'sys:dict:item:save', NULL, 2, NULL, 1, '2022-01-20 23:32:30',
        '2022-01-20 23:32:30', 1, 1),
       (47, 6, '删除数据字典项', NULL, 'sys:dict:item:delete', NULL, 2, NULL, 1, '2022-01-20 23:42:49',
        '2022-01-20 23:42:49', 1, 1),
       (48, 6, '更新数据字典项', NULL, 'sys:dict:item:edit', NULL, 2, NULL, 1, '2022-01-20 23:47:07',
        '2022-01-20 23:47:07', 1, 1);

DROP TABLE IF EXISTS `sys_oper_log`;

CREATE TABLE `sys_oper_log`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `oper_modul`       varchar(64)         DEFAULT NULL COMMENT '功能模块',
    `oper_type`        varchar(64)         DEFAULT NULL COMMENT '操作类型',
    `oper_desc`        varchar(64)         DEFAULT NULL COMMENT '操作描述',
    `oper_requ_method` varchar(64)         DEFAULT NULL COMMENT '请求方式',
    `oper_requ_param`  text COMMENT '请求参数',
    `oper_resp_param`  text COMMENT '返回参数',
    `oper_user_id`     bigint(20)          DEFAULT NULL COMMENT '操作者',
    `oper_user_name`   varchar(64)         DEFAULT NULL COMMENT '操作员名称',
    `oper_method`      varchar(255)        DEFAULT NULL COMMENT '操作方法',
    `oper_uri`         varchar(255)        DEFAULT NULL COMMENT '请求URL',
    `oper_ip`          varchar(64)         DEFAULT NULL COMMENT '请求IP',
    `oper_ver`         varchar(64)         DEFAULT NULL COMMENT '操作版号',
    `created_at`       timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`        int(11)             DEFAULT '1' COMMENT '删除标识',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1689183353875464195
  DEFAULT CHARSET = utf8 COMMENT ='日志记录表';

insert into `sys_oper_log`(`id`, `oper_modul`, `oper_type`, `oper_desc`, `oper_requ_method`, `oper_requ_param`,
                           `oper_resp_param`, `oper_user_id`, `oper_user_name`, `oper_method`, `oper_uri`, `oper_ip`,
                           `oper_ver`, `created_at`, `updated_at`, `is_delete`)
values (1689183353875464194, '字典模块 - 添加字典', '新增', '添加字典', 'POST',
        '{\"type\":\"5555\",\"description\":\"5555\",\"system\":\"1\",\"remarks\":\"55555\"}',
        '{\"code\":200,\"message\":\"success\",\"data\":{\"type\":\"5555\",\"description\":\"5555\",\"remarks\":\"55555\",\"system\":\"1\",\"id\":1689183353808355330,\"isDelete\":1}}',
        1, 'admin', 'com.pistachio.admin.controller.system.SysDictController.save', '/sys-dict/save', '127.0.0.1',
        '0.0.1', '2023-08-09 15:54:29', '2023-08-09 15:54:29', 1);

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `name`       varchar(64) NOT NULL,
    `code`       varchar(64) NOT NULL,
    `remark`     varchar(64)          DEFAULT NULL COMMENT '备注',
    `created_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`     int(11)     NOT NULL,
    `is_delete`  int(11)              DEFAULT '1' COMMENT '删除标识',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`) USING BTREE,
    UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8 COMMENT ='系统角色表';

insert into `sys_role`(`id`, `name`, `code`, `remark`, `created_at`, `updated_at`, `status`, `is_delete`)
values (1, '超级管理员', 'administer', '系统默认最高权限，不可以编辑和任意修改', '2021-01-16 13:29:03',
        '2022-01-05 22:33:59', 1, 1),
       (3, '普通用户', 'normal', '只有基本查看功能', '2021-01-04 10:09:14', '2022-01-06 20:32:27', 1, 1);

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id`    bigint(20) NOT NULL,
    `menu_id`    bigint(20) NOT NULL,
    `created_at` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`     int(11)    NOT NULL,
    `is_delete`  int(11)             DEFAULT '1' COMMENT '删除标识',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 167
  DEFAULT CHARSET = utf8 COMMENT ='系统角色菜单链接表';

insert into `sys_role_menu`(`id`, `role_id`, `menu_id`, `created_at`, `updated_at`, `status`, `is_delete`)
values (1, 1, 1, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (2, 1, 5, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (3, 1, 6, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (4, 1, 21, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (5, 1, 22, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (6, 1, 23, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (7, 1, 24, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (8, 1, 27, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (9, 1, 29, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (10, 1, 30, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (11, 1, 31, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (12, 1, 32, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (13, 1, 33, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (14, 1, 34, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (15, 1, 35, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (16, 1, 36, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (17, 1, 37, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (18, 1, 38, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (19, 1, 39, '2021-12-29 10:20:57', '2021-12-29 10:20:57', 1, 0),
       (34, 3, 1, '2022-01-08 15:54:37', NULL, 1, 1),
       (35, 3, 5, '2022-01-08 15:54:37', NULL, 1, 1),
       (36, 3, 6, '2022-01-08 15:54:37', NULL, 1, 1),
       (37, 1, 1, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (38, 1, 5, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (39, 1, 6, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (40, 1, 21, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (41, 1, 22, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (42, 1, 23, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (43, 1, 24, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (44, 1, 27, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (45, 1, 29, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (46, 1, 30, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (47, 1, 31, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (48, 1, 32, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (49, 1, 33, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (50, 1, 34, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (51, 1, 35, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (52, 1, 36, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (53, 1, 37, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (54, 1, 38, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (55, 1, 39, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (56, 1, 41, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (57, 1, 42, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (58, 1, 43, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (59, 1, 44, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (60, 1, 40, '2022-01-20 21:23:33', '2022-01-20 21:23:33', 1, 0),
       (61, 1, 1, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (62, 1, 5, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (63, 1, 6, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (64, 1, 21, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (65, 1, 22, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (66, 1, 23, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (67, 1, 24, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (68, 1, 27, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (69, 1, 29, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (70, 1, 30, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (71, 1, 31, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (72, 1, 32, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (73, 1, 33, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (74, 1, 34, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (75, 1, 35, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (76, 1, 36, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (77, 1, 37, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (78, 1, 38, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (79, 1, 39, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (80, 1, 41, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (81, 1, 42, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (82, 1, 43, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (83, 1, 44, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (84, 1, 40, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (85, 1, 45, '2022-01-20 22:26:10', '2022-01-20 22:26:10', 1, 0),
       (86, 1, 1, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (87, 1, 5, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (88, 1, 6, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (89, 1, 21, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (90, 1, 22, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (91, 1, 23, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (92, 1, 24, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (93, 1, 27, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (94, 1, 29, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (95, 1, 30, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (96, 1, 31, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (97, 1, 32, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (98, 1, 33, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (99, 1, 34, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (100, 1, 35, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (101, 1, 36, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (102, 1, 37, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (103, 1, 38, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (104, 1, 39, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (105, 1, 41, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (106, 1, 42, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (107, 1, 43, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (108, 1, 44, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (109, 1, 40, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (110, 1, 45, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (111, 1, 46, '2022-01-20 23:32:40', '2022-01-20 23:32:40', 1, 0),
       (112, 1, 1, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (113, 1, 5, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (114, 1, 6, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (115, 1, 21, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (116, 1, 22, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (117, 1, 23, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (118, 1, 24, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (119, 1, 27, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (120, 1, 29, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (121, 1, 30, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (122, 1, 31, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (123, 1, 32, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (124, 1, 33, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (125, 1, 34, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (126, 1, 35, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (127, 1, 36, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (128, 1, 37, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (129, 1, 38, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (130, 1, 39, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (131, 1, 41, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (132, 1, 42, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (133, 1, 43, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (134, 1, 44, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (135, 1, 40, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (136, 1, 45, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (137, 1, 46, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (138, 1, 47, '2022-01-20 23:42:56', '2022-01-20 23:42:56', 1, 0),
       (139, 1, 1, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (140, 1, 5, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (141, 1, 6, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (142, 1, 21, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (143, 1, 22, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (144, 1, 23, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (145, 1, 24, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (146, 1, 27, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (147, 1, 29, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (148, 1, 30, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (149, 1, 31, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (150, 1, 32, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (151, 1, 33, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (152, 1, 34, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (153, 1, 35, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (154, 1, 36, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (155, 1, 37, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (156, 1, 38, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (157, 1, 39, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (158, 1, 41, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (159, 1, 42, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (160, 1, 43, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (161, 1, 44, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (162, 1, 40, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (163, 1, 45, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (164, 1, 46, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (165, 1, 47, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1),
       (166, 1, 48, '2022-01-20 23:47:13', '2022-01-20 23:47:13', 1, 1);

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `nickname`   varchar(64)  DEFAULT NULL COMMENT '昵称',
    `username`   varchar(64)  DEFAULT NULL,
    `password`   varchar(256) DEFAULT NULL,
    `type`       int(11)      DEFAULT '1' COMMENT '用户类型: 1管理员; 2普通用户; ',
    `avatar`     varchar(255) DEFAULT NULL,
    `email`      varchar(64)  DEFAULT NULL,
    `city`       varchar(64)  DEFAULT NULL,
    `last_login` datetime     DEFAULT NULL,
    `created_at` datetime   NOT NULL,
    `updated_at` datetime     DEFAULT NULL,
    `status`     int(11)    NOT NULL,
    `is_delete`  int(11)      DEFAULT '1' COMMENT '删除标识',
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8 COMMENT ='系统用户表';

insert into `sys_user`(`id`, `nickname`, `username`, `password`, `type`, `avatar`, `email`, `city`, `last_login`,
                       `created_at`, `updated_at`, `status`, `is_delete`)
values (1, '超级管理员', 'admin',
        '7533cd73daf8f5f723a97b6728f24bd9bb7d3c032ae7bd95b4928242e75aa1c1babed5aa3040294d63297186e8d4baf4f3c241ca27afb393c80d90de8a2e8b081a6938f2ab0c319a43d506170c602e951df13fc1104e36847dec3f00c8abfa1ed7bd6860b7d05918b6e98e6502da49942ac8f8eef69d7a7be0359c279d68e2bd',
        1, NULL, NULL, NULL, NULL, '2021-10-02 22:44:46', NULL, 1, 1),
       (3, '测试管理员', 'test', '$2a$10$nIoa6i2ZFfIevdTE4.x84.Jnj6nls7ybUSLB2Jm9JUSGt.uj6FM2i', 1,
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        'test@qq.com', NULL, '2021-01-30 08:20:22', '2021-01-30 08:55:57', '2022-01-08 15:56:07', 1, 1);

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`    bigint(20) NOT NULL,
    `role_id`    bigint(20) NOT NULL,
    `created_at` datetime   NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `status`     int(11)    NOT NULL,
    `is_delete`  int(11)  DEFAULT '1' COMMENT '删除标识',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8 COMMENT ='系统管理员角色链接表';

insert into `sys_user_role`(`id`, `user_id`, `role_id`, `created_at`, `updated_at`, `status`, `is_delete`)
values (13, 2, 3, '2021-01-17 21:56:36', NULL, 1, 1),
       (22, 1, 1, '2022-01-06 22:28:14', NULL, 0, 1),
       (23, 1, 3, '2022-01-06 22:28:14', NULL, 0, 1),
       (26, 3, 3, '2022-01-06 22:28:21', NULL, 0, 1),
       (28, 6, 1, '2022-01-07 15:48:49', NULL, 1, 1);
