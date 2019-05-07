CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) NOT NULL DEFAULT '' COMMENT 'salt',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '该记录是否还存在',
  PRIMARY KEY (`id`),
  KEY `ix_mobile` (`mobile`) USING BTREE,
  KEY `ix_created_at` (`created_at`) USING BTREE,
  KEY `ix_updated_at` (`updated_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账户表'