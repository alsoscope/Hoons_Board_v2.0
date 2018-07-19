<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

	<div class="container">
		<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;" align="center">
					<form action="/user/join" method="post">
						<h3>회원가입</h3>
						<div class="form-group">
							<input class="form-control" type="text" id="uid" name="uid" placeholder="아이디">
						</div>
						<div class="form-group">
							<input class="form-control" type="password" id="pw" name="pw" placeholder="비밀번호">
						</div>
						<div class="form-group">
							<input class="form-control" type="password" id="pwck" name="pwck" placeholder="비밀번호 확인">
						</div>
						<div class="form-group" align="left">
							<span style="margin-right: 10px; color: gray" id="password-ck-btn" class="fa fa-check" aria-hidden="true"></span>
							<em id="password-ck" style="color: gray">password check</em>
						</div>
						<div class="form-group">
							<input class="form-control" type="text" id="name" name="name" placeholder="이름">
						</div>
						<div class="form-group" style="text-align: center">
							<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-info active">
									<input type="radio" name="gender" value="남자" checked="checked">남자
								</label>
								<label class="btn btn-info">
									<input type="radio" name="gender" value="여자">여자
								</label>
							</div>
						</div>
						<div class="form-group">
							<input class="form-control" type="email" id="email" name="email" placeholder="이메일">
						</div>
							<input class="btn btn-primary form-control" type="submit" value="회원가입">
					</form>
				</div>
			</div>
		<div class="col-lg-4"></div>
	</div>
	
	<script>
		var passwordCkBtn = $("#password-ck-btn");
		var passwordCk = $("#password-ck");
		
		var pw = $("#pw");
		var pwck = $("#pwck");
		
		pw.keyup(function(){
			if ((pw.val() == pwck.val()) && pw.val().length >= 4) {
				passwordCkBtn.css("color", "#6ce945");
				passwordCk.css("color", "black");
			} else {
				passwordCkBtn.css("color", "gray");
				passwordCk.css("color", "gray");
			}
		});
		
		pwck.keyup(function() {
			if ((pwck.val() == pw.val()) && pwck.val().length >= 4) {
				passwordCkBtn.css("color", "#6ce945");
				passwordCk.css("color", "black");
			} else {
				passwordCkBtn.css("color", "gray");
				passwordCk.css("color", "gray");
			}
		});
	</script>

<%@ include file="../include/footer.jsp" %>