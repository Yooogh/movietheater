 drop table Member;

 create table Member(
 id VARCHAR2(10) PRIMARY KEY, 
 password VARCHAR2(20) NOT NULL, 
 name VARCHAR2(12 char) NOT NULL,
 birth date NOT NULL,
 points number DEFAULT 0 NOT NULL,
 created date DEFAULT SYSDATE NOT NULL,
 admin number(1) DEFAULT 0 NOT NULL
 );

drop table Movie;

create table Movie(
-- MovieNo NUMBER PRIMARY KEY,
--CODE NUMBER NOT NULL UNIQUE,
-- MovieNo NUMBER NOT NULL UNIQUE,
 MovieNo NUMBER NOT NULL PRIMARY KEY,
 name VARCHAR2(12) NOT NULL,
 publisher VARCHAR2(12) NOT NULL,
 rate NUMBER(2) NOT NULL,
 runtime NUMBER NOT NULL,
 director VARCHAR2(12),
 genre VARCHAR2(12),
 begindate date,
 summary VARCHAR2(4000),
 price NUMBER,
-- CONSTRAINT MV_KEY PRIMARY KEY(name, director)
);

drop table Plex;

create table Plex(
    Plex_No number PRIMARY KEY,
    name VARCHAR2(20) NOT NULL
);

drop table Reservation;

create table Reservation(
resNo VARCHAR2(10) PRIMARY KEY, 
memberId VARCHAR2(10) REFERENCES Member(id), 
resDate date NOT NULL,
seat VARCHAR2(10) NOT NULL,
price number,
Plex_No number REFERENCES Plex(Plex_No)
);
 

-- drop sequence resNoplus;
-- drop sequence MovieNoPlus;

create sequence resNoplus start with 1 increment by 1 maxvalue 99999;
-- create sequence MovieNoPlus start with 1 increment by 1 maxvalue 99999;