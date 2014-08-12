CREATE DATABASE contactsbak;

USE contactsbak;

CREATE TABLE users(
	userid MEDIUMINT NOT NULL AUTO_INCREMENT,
	accountname varchar(320) NOT NULL,
	password varchar(20) NOT NULL,
	PRIMARY KEY (userid)
);

CREATE TABLE contacts(
  userid integer NOT NULL,
  contactid integer NOT NULL,
  name varchar(50),
  phone_1 varchar(15),
  phone_2 varchar(15),
  phone_3 varchar(15),
  phone_4 varchar(15),
  email_1 varchar(320),
  email_2 varchar(320),
  address_1 varchar(100),
  address_2 varchar(100),
  birthday date,
  organization varchar(320),
  data_1 varchar(50),
  data_2 varchar(50),
  data_3 varchar(50),
  PRIMARY KEY (userid, contactid)
);