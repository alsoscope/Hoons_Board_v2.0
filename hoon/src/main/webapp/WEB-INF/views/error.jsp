<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error!</title>
</head>
	<body>
		<h1>
			에러가 발생했습니다. 관리자에게 문의해주세요.(000-000-0000)
		</h1>
		<font color="red">
			<c:out value="${msg }"></c:out>
		</font>
	</body>
</html>