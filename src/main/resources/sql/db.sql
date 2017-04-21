drop database if exists beetlsql;
create database if not exists beetlsql default character SET utf8 collate utf8_general_ci;
use beetlsql;
CREATE TABLE `user` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (64) DEFAULT NULL,
	`age` INT (4) DEFAULT NULL,
	`user_name` VARCHAR (64) DEFAULT NULL COMMENT '用户名称',
	`roleId` INT (11) DEFAULT NULL COMMENT '用户角色',
	`create_date` datetime NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;