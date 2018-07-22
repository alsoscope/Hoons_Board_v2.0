# [Hoon's Board v2.0] CRUD 게시판 구현

<br>

< Used Language, Library >

<ul>
  <li>Used Language</li>
    <ul>
      <li>Java</li>
      <li>JSP</li>
      <li>HTML, CSS</li>
      <li>Java Script</li>
    </ul>
</ul>
<ul>
  <li>Used Open Source Library</li>
    <ul>
      <li>Bootstrap(HTML, CSS)</li>
      <li>JQuery(JavaScript)</li>
      <li>Jackson(JSON)</li>
    </ul>
</ul>

<hr>

< Tool Kit >

<ul>
    <li>EClipse Oxygen, JDK 1.8.0</li>
    <li>Spring 3.2.2 STS</li>
    <li>Apache-Tomcat 9.0(WAS)</li>
    <li>Mysql(DB)</li>
    <li>Github(SVN)</li>
</ul>

<hr>

< Mysql DB 구축 >

create database study;

use study;

<ul>
  <li>tbl_board</li>
</ul>
CREATE TABLE tbl_board (
  bno int(11) NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  content varchar(50) DEFAULT NULL,
  writer varchar(50) DEFAULT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
  viewcnt int(11) DEFAULT 0,
  replycnt int(11) DEFAULT 0,
  likecnt int(11) DEFAULT 0,
    PRIMARY KEY (bno)
);

<ul>
  <li>tbl_reply</li>
</ul>
CREATE TABLE tbl_reply (
  rno int(11) NOT NULL AUTO_INCREMENT,
  bno int(11) NOT NULL,
  replytext varchar(1000) DEFAULT NULL,
  replywriter varchar(50) DEFAULT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
  updatedate timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (rno)
);

<ul>
  <li>tbl_attach</li>
</ul>
CREATE TABLE tbl_attach (
  fullName varchar(150) NOT NULL,
  bno int(11) NOT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (fullName)
);

<ul>
  <li>tbl_user</li>
</ul>
CREATE TABLE tbl_user (
  uid varchar(50) NOT NULL,
  pw varchar(50) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  gender varchar(10) DEFAULT NULL,
  sessionkey varchar(50) NOT NULL DEFAULT 'none',
  sessionlimit timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  thumbnail varchar(150) DEFAULT NULL,
  email varchar(150) NOT NULL,
  joindate timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (uid)
);


alter table tbl_reply add foreign key(rno) references tbl_board(bno);
alter table tbl_reply add foreign key(bno) references tbl_board(bno);

<hr>

< ERD >

<img src="https://user-images.githubusercontent.com/38021616/43047553-c7078ab6-8e13-11e8-934c-449e41e32d68.JPG">