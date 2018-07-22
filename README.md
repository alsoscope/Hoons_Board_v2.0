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

<p><b> - database</b><p>
create database study;

use study;

<p><b> - tbl_board</b><p>

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


<p><b> - tbl_reply</b><p>
  
CREATE TABLE tbl_reply (
  rno int(11) NOT NULL AUTO_INCREMENT,
  bno int(11) NOT NULL,
  replytext varchar(1000) DEFAULT NULL,
  replywriter varchar(50) DEFAULT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
  updatedate timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (rno)
);


<p><b> - tbl_attach</b><p>
  
CREATE TABLE tbl_attach (
  fullName varchar(150) NOT NULL,
  bno int(11) NOT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (fullName)
);

<p><b> - tbl_user</b><p>
  
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

<p><b> - foreign key</b></p>

alter table tbl_reply add foreign key(rno) references tbl_board(bno);

alter table tbl_reply add foreign key(bno) references tbl_board(bno);

<hr>

< ERD >

<img src="https://user-images.githubusercontent.com/38021616/43047553-c7078ab6-8e13-11e8-934c-449e41e32d68.JPG" width="700px;">

<hr>

< URL >

<hr>

< Function >

<ol>
  <b><li>회원가입</li></b>
    <ul>
      <li>
        jquery ajax를 이용해, 입력된 uid, email 값을 파라미터로 하여 컨트롤러에 전달, 
        tbl_user 테이블을 조회하여 아이디, 이메일 중복 결과를 text 데이터 타입으로 리턴
      </li>
      <li>JSP</li>
      <li>HTML, CSS</li>
      <li>Java Script</li>
    </ul>
</ol>
