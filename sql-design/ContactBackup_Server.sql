CREATE DATABASE contactsbak;

USE contactsbak;

CREATE TABLE users(
	userid INTEGER NOT NULL AUTO_INCREMENT,
	accountname VARCHAR(320) NOT NULL,
	password VARCHAR(20) NOT NULL,
	PRIMARY KEY (userid)
)ENGINE=InnoDB;

CREATE TABLE contacts(
	contactid INTEGER NOT NULL AUTO_INCREMENT,
	userid INTEGER NOT NULL,
	name VARCHAR(50),
	phone_1 VARCHAR(15),
	phone_2 VARCHAR(15),
	phone_3 VARCHAR(15),
	phone_4 VARCHAR(15),
	email_1 VARCHAR(320),
	email_2 VARCHAR(320),
	address_1 VARCHAR(100),
	address_2 VARCHAR(100),
	birthday DATE,
	organization VARCHAR(100),
	data_1 VARCHAR(50),
	data_2 VARCHAR(50),
	data_3 VARCHAR(50),
	PRIMARY KEY (contactid),
	CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES users(userid) ON UPDATE RESTRICT ON DELETE RESTRICT
)ENGINE=InnoDB;

INSERT INTO  users(accountname, password) VALUES('iamtangfei@gmail.com', '123');
INSERT INTO  users(accountname, password) VALUES('suxiangyang@gmail.com', '123');
INSERT INTO contacts(userid, phone_1) VALUES(1, '110');
INSERT INTO contacts(userid, phone_1) VALUES(1, '110');
INSERT INTO contacts(userid, phone_1) VALUES(1, '110');
