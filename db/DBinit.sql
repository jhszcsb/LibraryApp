/*
 * TODO: rentals should be inserted for customers only
 * TODO: check other constraints...
 * */


DROP TABLE RENTAL;
DROP TABLE USERS_ROLES;
DROP TABLE BOOK;
DROP TABLE USERS;

CREATE TABLE USERS
(
	userid INT NOT NULL,
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	password VARCHAR(255) NOT NULL,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	roles VARCHAR(255)
);

CREATE TABLE BOOK
(
	bookid INT NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	image BLOB,
	contenttype VARCHAR(255),
	genre VARCHAR(255) NOT NULL,
	availablecopies INT NOT NULL
);

CREATE TABLE RENTAL
(
	rentalid INT NOT NULL PRIMARY KEY,
	bookid INT NOT NULL,
	username VARCHAR(255) NOT NULL,
	rentaldate DATE NOT NULL,
	returndate DATE NOT NULL,
	status VARCHAR(255) NOT NULL,
   FOREIGN KEY(username) REFERENCES USERS(name),
   FOREIGN KEY(bookid) REFERENCES BOOK(bookid)
);

CREATE TABLE USERS_ROLES
(
	users_name VARCHAR(255) NOT NULL PRIMARY KEY,
	roles VARCHAR(255) NOT NULL,
	FOREIGN KEY(users_name) REFERENCES USERS(name)
);

INSERT INTO USERS (USERID, NAME, PASSWORD, FIRSTNAME, LASTNAME, ROLES) VALUES 
(100, 'Csabi', 'aaa', 'Csaba', 'Juhasz', 'ADMIN'), 
(101, 'Lilla', 'bbb', 'Lilla', 'Ledneczki', 'CUSTOMER'), 
(102, 'Gergely', 'ccc', 'Gergely', 'Juhasz', 'ADMIN'), 
(103, 'Melinda', 'ddd', 'Melinda', 'Kiss', 'CUSTOMER');

INSERT INTO USERS_ROLES (USERS_NAME, ROLES) VALUES 
('Csabi', 'ADMIN'), 
('Lilla', 'CUSTOMER'), 
('Gergely', 'ADMIN'), 
('Melinda', 'CUSTOMER');

INSERT INTO BOOK(BOOKID, NAME, AUTHOR, GENRE, AVAILABLECOPIES) VALUES 
(100, 'Dune', 'Frank Herbert', 'Fiction', 10), 
(101, 'Creativity, Inc.', 'Ed Catmull', 'Non Fiction', 10), 
(102, 'Lécume des jours', 'Boris Vian', 'Romance', 10), 
(103, 'On The Road', 'Jack Kerouac', 'Realistic Fiction', 10), 
(104, 'The Hunger Games', 'Suzanne Collins', 'Fiction', 10), 
(105, 'Mockingjay', 'Suzanne Collins', 'Fiction', 10), 
(106, 'The Catcher in the Rye', 'J.D. Salinger', 'Realistic Fiction', 10);

INSERT INTO RENTAL (RENTALID, BOOKID, USERNAME, RENTALDATE, RETURNDATE, STATUS) VALUES 
(100, 101, 'Lilla', '2015-01-01', '2015-01-01', 'REQUESTED'), 
(101, 102, 'Lilla', '2015-01-01', '2015-01-01', 'RECEIVABLE'), 
(102, 101, 'Gergely', '2015-05-10', '2015-05-10', 'RENTED');

