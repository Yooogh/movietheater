 drop table Member;

 create table Member(
 id VARCHAR2(10) PRIMARY KEY, 
 password VARCHAR2(20) NOT NULL, 
 name VARCHAR2(5 char) NOT NULL,
 birth date NOT NULL,
 points number DEFAULT 0 NOT NULL,
 created date DEFAULT SYSDATE NOT NULL,
 admin number(1) DEFAULT 0 NOT NULL CONSTRAINT boolean_admin_CK CHECK(admin = '0' OR admin = '1')
 );

drop table Movie;

create table Movie(
 name VARCHAR2(12 char) PRIMARY KEY,
 publisher VARCHAR2(12 char) NOT NULL,
 rate NUMBER(2) NOT NULL,
 runtime VARCHAR2(12 char) NOT NULL,
 director VARCHAR2(12 char),
 genre VARCHAR2(12 char),
 begindate date,
 summary VARCHAR2(4000),
 price NUMBER
);

drop table Plex;

create table Plex(
    PlexNo number PRIMARY KEY,
    name VARCHAR2(12 char) NOT NULL,
    R number(2) NOT NULL,
    C number(2) NOT NULL
);

drop table Reservation;

create table Reservation(
resNo VARCHAR2(10) PRIMARY KEY, 
memberId VARCHAR2(10) REFERENCES Member(id), 
resDate date NOT NULL,
seq number,
seat VARCHAR2(2) NOT NULL,
price number,
PlexNo number REFERENCES Plex(PlexNo),
);
 

drop sequence resNoplus;
drop sequence MovieNoPlus;
drop sequence PlexNoPlus;

create sequence resNoplus start with 1 increment by 1 maxvalue 99999;
create sequence MovieNoPlus start with 1 increment by 1 maxvalue 99999;
create sequence PlexNoPlus start with 1 increment by 1 maxvalue 99999;