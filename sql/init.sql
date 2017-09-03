-- 账户表
DROP TABLE IF EXISTS tb_account;


CREATE TABLE `tb_account` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `last_login` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- QA表
DROP TABLE IF EXISTS tb_qa;

CREATE TABLE `tb_qa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `content` blob,
  `read_number` int(11) DEFAULT NULL,
  `like_number` int(11) DEFAULT NULL,
  `reply_number` int(11) DEFAULT NULL,
  `img` varchar(500) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
