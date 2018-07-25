<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%--     pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ include file="../include/header.jsp" %> --%>
	
<!-- 	<div class="container"> -->
<!-- 		<div class="col-lg-4"></div> -->
<!-- 			<div class="col-lg-4"> -->
<!-- 				<div class="jumbotron" style="padding-top: 20px;" align="center"> -->
<!-- 					<form action="/user/loginPost" method="post"> -->
<%-- 						<c:if test="${param.login_error == 1 }"> --%>
<!-- 						<div class="alert alert-warning" role="alert"> -->
<!-- 	                        <ul> -->
<!-- 	                            <li><font color="red">아이디 또는 비밀번호 오류</font></li> -->
<!-- 	                        </ul> -->
<!--                     	</div> -->
<%--                     	</c:if> --%>
<!-- 						<h3>로그인</h3> -->
<!-- 						<div class="form-group"> -->
<%-- 							<input class="form-control" type="text" id="uid" name="uid" value="${cookie.savedId.value }" placeholder="아이디" autocomplete="off"> --%>
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<input class="form-control" type="password" id="pw" name="pw" placeholder="비밀번호"> -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<input style="margin-right: 5px" type="checkbox" name="usecookie">로그인 유지 -->
<%-- 							<input type="hidden" name="uri" value="${param.uri }"> --%>
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<input id="formform" class="btn btn-primary form-control" type="submit" value="로그인"> -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<a href="#"> -->
<!-- 								<img alt="네이버 아이디로 로그인하기" src="/resources/dist/img/login_naver.png" style="height: 40px;"> -->
<!-- 							</a> -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<h6>아직 회원이 아니신가요?</h6> -->
<!-- 							<input onclick="location.href='/user/join'" class="btn btn-primary form-control" type="button" value="회원가입"> -->
<!-- 						</div> -->
<!-- 					</form> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		<div class="col-lg-4"></div> -->
<!-- 	</div> -->
	
<%-- <%@ include file="../include/footer.jsp" %> --%>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  <%
    String clientId = "p1SdIeQnCgBNAcOrb_fu";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("/user/naverLoginPost", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
  </body>
</html>