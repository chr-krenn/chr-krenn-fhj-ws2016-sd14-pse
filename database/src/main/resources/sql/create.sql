#create user table

CREATE TABLE IF NOT EXISTS user
(
  id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(32)  NOT NULL,
  password   VARCHAR(128)  NOT NULL,
  salt       VARCHAR(512) NOT NULL,
  `created`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = INNODB;
  
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `created`, `modified`) VALUES (NULL, 'student', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'null', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

CREATE TABLE user_roles( 
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	username VARCHAR(255) NOT NULL REFERENCES user(username), 
	role VARCHAR(32))
ENGINE = INNODB;

INSERT INTO `user_roles` (`username`, `role`) VALUES ('student', 'user');  
INSERT INTO `user_roles` (`username`, `role`) VALUES ('student', 'admin');  
  
#create table department
CREATE TABLE IF NOT EXISTS department (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

#create table person_status
CREATE TABLE IF NOT EXISTS person_status (
  name varchar(255) NOT NULL PRIMARY KEY
) ENGINE=InnoDB;

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
