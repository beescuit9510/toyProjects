<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.m }">
		<form action="login.do" method="post">
			아이디 : <input type="text" name="memberId"/><br/>
			비밀번호 : <input type="password" name="memberPw"/><br/>
			<input type="submit" value="로그인">
		</form>
	</c:if>
	<c:if test="${not empty sessionScope.m }">
		<p>${sessionScope.m.memberId}</p>
		<p>${sessionScope.m.memberPw}</p>
	</c:if>
</body>
</html>