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

  
#create table community
CREATE TABLE IF NOT EXISTS department (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user_roles(
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	username VARCHAR(255) NOT NULL REFERENCES user(username), 
	role VARCHAR(32) NOT NULL,
	roleGroup VARCHAR(32) NOT NULL DEFAULT 'Roles')
ENGINE = INNODB;

  
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

CREATE TABLE IF NOT EXISTS person_image (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  data longblob NOT NULL,
  person_id bigint(20) NOT NULL UNIQUE REFERENCES person(id),
  content_type text NOT NULL
) ENGINE=InnoDB;

#create contact
CREATE TABLE IF NOT EXISTS contact (
  person1_id bigint(20) NOT NULL REFERENCES person(id),
  person2_id bigint(20) NOT NULL REFERENCES person(id),
  PRIMARY KEY (person1_id, person2_id)
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
