<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

	<div class="panel-body panel-body-content text-center">
		<p class="lead">
			<strong>${uVO.email }</strong> 로 인증 요청 메일을 보냈습니다.
			<br> 해당 이메일을 확인 하시고, 인증 확인 링크를 눌러 주시기 바랍니다.
		</p>
		<p>이메일 인증이 완료 되지 않을 경우 사이트 이용에 제한이 있습니다.</p>
		<p>
			<strong>※ 서비스에 따라 스팸으로 분류 되있을 수도 있습니다. 스팸함도 꼭 확인해 주시기 바랍니다.</strong>
		</p>
		<a href="/user/login" class="btn btn-primary">로그인</a>
	</div>

<%@ include file="../include/footer.jsp" %>