# [Hoon's Board v2.0] CRUD 게시판 구현

<br>

<b>< Used Language, Library ></b>

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

<b>< Tool Kit ></b>

<ul>
    <li>EClipse Oxygen, JDK 1.8.0</li>
    <li>Spring 3.9.5 STS</li>
    <li>Apache-Tomcat 9.0(WAS)</li>
    <li>Mysql(DB)</li>
    <li>Github(SVN)</li>
</ul>

<hr>

<b>< Mysql DB 구축 ></b>

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

<b>< ERD ></b>

<img src="https://user-images.githubusercontent.com/38021616/43047553-c7078ab6-8e13-11e8-934c-449e41e32d68.JPG" width="700px;">

<hr>

<b>< URL ></b>

<img src="https://user-images.githubusercontent.com/38021616/43052493-38058086-8e61-11e8-85d2-108ff632c4b2.JPG" width="400px;">

<hr>

<b>< Function ></b>

<ol>
  <b><li>회원가입</li></b>
    <ul>
      <li>
        Ajax를 이용해 입력된 uid, email 값을 파라미터로 하여 컨트롤러에 전달 -&raquo; 
        tbl_user 테이블을 조회하여 아이디, 이메일 중복 결과를 text 데이터 타입으로 리턴
      </li>
      <li>비밀번호 4자리 이상 + 비밀번호 재입력 일치 시 -&raquo; 비밀번호 체크 표시 활성화</li>
      <li>입력된 name 값 null 체크</li>
      <li>---> jQeury .keyup() 메소드를 사용, 매 입력시 위 세가지 모두 충족해야만 submit 버튼 활성화</li>
      <ul>
       <li>이메일 인증 기능 구현중...</li>
      </ul>
    </ul>
</ol>

<ol>
  <b><li>로그인</li></b>
    <ul>
      <li>Interceptor를 이용해 </li>
      <li>입력된 uid, pw 값을 파라미터로 하여 컨트롤러에 전달, 해당 사용자가 존재하는지 확인</li>
      <ul>
        <li>네이버로 로그인 하기 기능 구현중...</li>
      </ul>
    </ul>
</ol>

<ol>
  <b><li>파일 첨부</li></b>
    <ul>
      <li>이미지, 일반 파일을 구분하여 처리</li>
      <li>Drop 영역에 파일을 올리면 해당 파일의 이름을 Java script의 FormData 객체에 담아 컨트롤러에 요청 -> 실제 서버의 정해진 경로에 파일 저장, 화면에 목록으로 출력</li>
      <li>각 파일마다 표시된 'x' 클릭시 위에서 실제 저장된 파일 삭제</li>
      <li>submit시 최종적으로 화면에 표시된 파일들의 이름을 files 배열에 담아 컨트롤러에 요청 -> tbl_attach에 데이터 저장</li>
      <ul>
        <li>사용자 썸네일 이미지 관련 처리 구현중...</li>
      </ul>
    </ul>
</ol>
