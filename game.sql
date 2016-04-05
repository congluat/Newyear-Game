CREATE DATABASE NEWYEARCLGAME;

USE NEWYEAR;

drop table Players;

CREATE TABLE Players(
	Id INT NOT NULL PRIMARY KEY,
	Email VARCHAR(200) NOT NULL UNIQUE,
	Name NVARCHAR(200),
	Phone VARCHAR(50),
	Code NVARCHAR(100),
	Status INT DEFAULT 0 NOT NULL,
);

select * from Players
--NEWYEARCLGAME