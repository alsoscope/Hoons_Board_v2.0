<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hoon's Board v2.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://okky.kr/assets/application-786c768e787a9741d1ce50a030efb766.css"/>
<link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>

<style>
	.fileDrop {
		width: 100%;
		height: 200px;
		border: 1px dotted blue;
		background: white;
		margin: auto;
	}
	.popup {
		position: absolute;
	}
	.back {
		background-color: gray;
		opacity: 0.5;
		widows: 100%;
		height: 300%;
		overflow: hidden;
		z-index: 1101;
	}
	.front {
		z-index: 1110;
		opacity: 1;
		border: 1px;
		margin: auto;
	}
	.show {
		position: relative;
		max-width: 1200px;
		max-height: 800px;
		overflow: auto;
	}
</style>
</head>

<body>

	<!-- nav -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Hoon's Board v2.0</a>
			</div>
	
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				<li id="main">
					<a href="/main">메인</a>
				</li>
				<li id="board">	
					<a href="/boards">게시판</a>
				</li>
				<li id="contact">	
					<a href="/cotact">Contact</a>
				</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li style="padding: 10px"><h5 style="color: white;">
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">메뉴 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><i class="fa fa-sign-in"></i> 로그인</a></li>
							<li><a href="#"><i class="fa fa-user"></i> 회원가입</a></li>
							<li><a href="#"><i class="fa fa-sign-out"></i> 로그아웃</a></li>
							<li><a href="#"><i class="fa fa-user"></i> 내 정보</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-trash-o"></i> 회원탈퇴</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<script>
		$(document).ready(function() {
			var url = location.href;
			var idxMain = url.indexOf("main");
		    var idxBoard = url.indexOf("board");
		    var idxContact = url.indexOf("contact");
	
		    if(idxMain != -1) {
		        $("#main").addClass("active");
		    }
		    if(idxBoard != -1) {
		        $("#board").addClass("active");
		    }
		    if(idxContact != -1) {
		        $("#contact").addClass("active");
		    }
		});	
	</script>
	