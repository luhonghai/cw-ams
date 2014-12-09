--
--  Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` VARCHAR(100) NOT NULL DEFAULT '',
  `deleted` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`role_id`)
) DEFAULT CHARSET=utf8;

INSERT INTO role(`role_id`, `name`) VALUES (1, 'Administrator');
INSERT INTO role(`role_id`, `name`) VALUES (2, 'Engineer');

--
--  Table structure for table `User`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL DEFAULT '',
  `last_name` VARCHAR(100) NOT NULL DEFAULT '',
  `email` varchar(80) NOT NULL DEFAULT '',
  `gender` BOOLEAN DEFAULT true, -- Male is true, Female is false
  `username` varchar(80) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `created_date` DATETIME DEFAULT now(),
  `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
--  Table structure for table `engineer`
--  Mapping one to one with `user` table.
--  More information about engineer
--

DROP TABLE IF EXISTS `engineer`;
CREATE TABLE `engineer` (
  `user_id` bigint(20) NOT NULL,
  `availability` BOOLEAN DEFAULT true,
  `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`user_id`)
) DEFAULT CHARSET=utf8;

--
--  Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
CREATE TABLE `qualification` (
  `qualification_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1000),
  `description` VARCHAR(5000),
  `created_date` DATETIME DEFAULT now(),
  `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`qualification_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
--  Table structure for table `priority`
--

DROP TABLE IF EXISTS `priority`;
CREATE TABLE `priority` (
  `priority_id` bigint(20) NOT NULL,
  `name` VARCHAR(100) NOT NULL DEFAULT '',
  `level` FLOAT,
  `deleted` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`priority_id`)
) DEFAULT CHARSET=utf8;

INSERT INTO priority(`priority_id`, `name`, `level`) VALUES (1, 'Critical', 10000);
INSERT INTO priority(`priority_id`, `name`, `level`) VALUES (2, 'Major', 7000);
INSERT INTO priority(`priority_id`, `name`, `level`) VALUES (3, 'Minor', 5000);
INSERT INTO priority(`priority_id`, `name`, `level`) VALUES (4, 'Trivial', 2000);


--
--  Table structure for table `job_status`
--

DROP TABLE IF EXISTS `job_status`;
CREATE TABLE `job_status` (
  `job_status_id` bigint(20) NOT NULL,
  `name` VARCHAR(100) NOT NULL DEFAULT '',
  `level` FLOAT,
  `deleted` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`job_status_id`)
) DEFAULT CHARSET=utf8;

INSERT INTO job_status(`job_status_id`, `name`, `level`) VALUES (1, 'Done', 10000);
INSERT INTO job_status(`job_status_id`, `name`, `level`) VALUES (2, 'Completed', 7000);
INSERT INTO job_status(`job_status_id`, `name`, `level`) VALUES (3, 'In Progress', 5000);
INSERT INTO job_status(`job_status_id`, `name`, `level`) VALUES (4, 'Open', 2000);

--
--  Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(1000),
  `description` VARCHAR(5000),
  `number_of_engineer` INT,
  `qualification_id` BIGINT(20),
  `priority_id` BIGINT(20) DEFAULT 3, -- Default value is Minor priority
  `status_id` BIGINT(20) DEFAULT  4, -- Default value is Open status
  `created_date` DATETIME DEFAULT now(),
  `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`job_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
--  Table structure for table `user_role_mapping`
--

DROP TABLE IF EXISTS `user_role_mapping`;
CREATE TABLE `user_role_mapping` (
  `user_role_mapping_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20),
  `role_id` BIGINT(20),
  `created_date` DATETIME DEFAULT now(),
  `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`user_role_mapping_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
--  Table structure for table `engineer_qualification_mapping`
--

DROP TABLE IF EXISTS `engineer_qualification_mapping`;
CREATE TABLE `engineer_qualification_mapping` (
  `engineer_qualification_mapping_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `engineer_id` BIGINT(20),
  `qualification_id` BIGINT(20),
  `created_date` DATETIME DEFAULT now(),
  `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`engineer_qualification_mapping_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
--  Table structure for table `engineer_job_mapping`
--

DROP TABLE IF EXISTS `engineer_job_mapping`;
CREATE TABLE `engineer_job_mapping` (
  `engineer_job_mapping_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `engineer_id` BIGINT(20),
  `job_id` BIGINT(20),
  `created_date` DATETIME DEFAULT now(),
  `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`engineer_job_mapping_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into `user`(`first_name`,`last_name`,`email`,`gender`,`username`,`password`)
VALUES ('Admin','System','admin@aircraft.com', true,'admin','21232f297a57a5a743894a0e4a801fc3');
insert into `user_role_mapping`(`user_id`, `role_id`) VALUES (1,1);

