create database hadoop;
use hadoop;
CREATE TABLE IF NOT EXISTS `user`
(
    `id`       INT UNSIGNED AUTO_INCREMENT,
    `username`    VARCHAR(40) NOT NULL,
    `email`   VARCHAR(40)  NOT NULL,
    `password` VARCHAR(40)  NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
insert into user (username, email, password)
values ('root','example@example.com','123456');
select * from user;
select * from user where username='root'