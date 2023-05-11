CREATE TABLE `class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tid_idx` (`teacher_id`),
  CONSTRAINT `tid` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
)


CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid_idx` (`class_id`),
  CONSTRAINT `cid` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) 


CREATE TABLE `teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

CREATE TABLE `anywr`.`user` (
  `id` BIGINT(20) NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `token` VARCHAR(255) NULL,
  `role` VARCHAR(45) NULL ,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
