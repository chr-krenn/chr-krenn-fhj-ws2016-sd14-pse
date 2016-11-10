#create user table

CREATE TABLE IF NOT EXISTS user
(
  id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(32)  NOT NULL,
  password   VARCHAR(512)  NOT NULL,
  salt       VARCHAR(16) NOT NULL,
  `created`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = INNODB;
  
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `created`, `modified`) VALUES (NULL, 'student1', 'edfd3e005a2da55ee99bb4b49afa33c6fa1bc945d93db54f5cba483355462acefdfd77ce1602449c9365d4eafb342e210ed0d33df4ceed40b92fa515bab5f89d', 'CRzTaAbyUxxEUs56', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `created`, `modified`) VALUES (NULL, 'student2', '6378ce16c796bee6b162f7370914e290cc8debac1548d29d1939934ad76177b37f4bb6b54bd8bcf6855ad7c36f35025a8b158e39d3e284c0c72f17765200e974', 'TJIaMD0HUR1uUiLx', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `created`, `modified`) VALUES (NULL, 'student3', '7230e34d69b2ede090042643840fb5581d6f5342925b0c592010899a11d56a3b82ceaca581d32d773d14718be4d5436719dc475bb66949895e0835e389aa0323', 'StLTK5akHsvhm84K', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `created`, `modified`) VALUES (NULL, 'student4', 'b64b5d1e09e3587fdfdebb3dbef2c6d31e79fa96233542a99071787b591e5f2d814ca44231d1362115c1ebc95930068a9fc6b5f6609c623ea049ca2e7bb8ee15', 'uEXAJ6Dhh28iNG6n', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `created`, `modified`) VALUES (NULL, 'student5', '59de5ccb6b7f9865794ad60d25e1858e77abf565c0fb58dea64b041fda7aeaedaeb6cd314691902a257b00d149537c754effaa97ac17ed30a488db22444c8f2e', 'HszDgIvOkTCeBT1W', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


CREATE TABLE user_roles( 
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	username VARCHAR(255) NOT NULL REFERENCES user(username), 
	role VARCHAR(32) NOT NULL,
	roleGroup VARCHAR(32) NOT NULL DEFAULT 'Roles')
ENGINE = INNODB;

INSERT INTO `user_roles` (`username`, `role`, `roleGroup`) VALUES ('student1', 'user', 'Roles');
INSERT INTO `user_roles` (`username`, `role`, `roleGroup`) VALUES ('student2', 'user', 'Roles');  
INSERT INTO `user_roles` (`username`, `role`, `roleGroup`) VALUES ('student3', 'user', 'Roles');  
INSERT INTO `user_roles` (`username`, `role`, `roleGroup`) VALUES ('student4', 'user', 'Roles');  
INSERT INTO `user_roles` (`username`, `role`, `roleGroup`) VALUES ('student5', 'user', 'Roles');   
  
#create table department
CREATE TABLE IF NOT EXISTS department (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO `pse`.`department` (`id`, `name`) VALUES ('1', 'Sales'), ('2', 'HR');


#create table person_status
CREATE TABLE IF NOT EXISTS person_status (
  name varchar(255) NOT NULL PRIMARY KEY
) ENGINE=InnoDB;

INSERT INTO person_status VALUES('online');
INSERT INTO `pse`.`person_status` (`name`) VALUES ('Abwesend'), ('');

#create table person
CREATE TABLE IF NOT EXISTS person (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  address varchar(255) DEFAULT NULL,
  firstname varchar(255) NOT NULL,
  imageurl varchar(255) DEFAULT NULL,
  lastname varchar(255) NOT NULL,
  place varchar(255) DEFAULT NULL,
  department_id bigint(20) DEFAULT NULL REFERENCES department(id),
  status_name varchar(255) NOT NULL REFERENCES person_status(name),
  user_id bigint(20) NOT NULL REFERENCES user(id)
) ENGINE=InnoDB;

INSERT INTO `pse`.`person` (`id`, `address`, `firstname`, `imageurl`, `lastname`, `place`, `department_id`, `status_name`, `user_id`) VALUES (NULL, 'teststraße 1', 'Typ', 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRxOXdV15ruEqhPAJJ5cMxWFBIqLexBFQIMLYWQ8aTjxFFjxnGBKuUOwCU', 'Test', 'Graz', '1', 'Online', '1');
INSERT INTO `pse`.`person` (`id`, `address`, `firstname`, `imageurl`, `lastname`, `place`, `department_id`, `status_name`, `user_id`) VALUES (NULL, 'teststraße 2', 'Max', 'https://i.ytimg.com/vi/S4UG_l-CHF4/maxresdefault.jpg', 'Mustermann', 'Wien', '2', 'Abwesend', '2');

#create table hobby
CREATE TABLE IF NOT EXISTS hobby (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  value varchar(255) DEFAULT NULL,
  person_id bigint(20) NOT NULL REFERENCES person(id)
) ENGINE=InnoDB;

#create table knowledge
CREATE TABLE IF NOT EXISTS knowledge (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  value varchar(255) DEFAULT NULL,
  person_id bigint(20) NOT NULL REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#create table mailaddress
CREATE TABLE IF NOT EXISTS mailaddress (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `value` varchar(255) DEFAULT NULL,
  `person_id` bigint(20) NOT NULL REFERENCES person(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS phonenumber (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  value varchar(255) DEFAULT NULL,
  person_id bigint(20) NOT NULL REFERENCES person(id)
) ENGINE=InnoDB;

#create news table

CREATE TABLE IF NOT EXISTS news
(
  id         BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title      VARCHAR(256)  NOT NULL,
  message    VARCHAR(1024) NOT NULL,
  author     BIGINT  NOT NULL REFERENCES person (id),
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = INNODB;


#create message table
CREATE TABLE IF NOT EXISTS message
(
  	id         BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    author_id     BIGINT  NOT NULL REFERENCES `user` (id),
    recipient_id     BIGINT  NOT NULL REFERENCES `user` (id),
    #community_id     BIGINT  NOT NULL REFERENCES `user` (id), TODO: uncomment as soon as the community table exists
    title      VARCHAR(256)  NOT NULL,
    content    VARCHAR(1024) NOT NULL,
    `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modified` TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = INNODB;

#create comment table
CREATE TABLE IF NOT EXISTS comment
(
  id                BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  author_id         BIGINT NOT NULL REFERENCES `user` (id),
  parentmessage_id  BIGINT NOT NULL REFERENCES `message` (id),
  text              VARCHAR(1024) NOT NULL,
  `created`         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified`        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = INNODB;
