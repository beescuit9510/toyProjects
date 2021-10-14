<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>member : ${member }</h2>
	<h2>member2 : ${member2 }</h2>
	<br>
	<p>test="조건식을 넣는 곳"</p>
	<c:if test="${not empty member }">
		<h3>${member.name }</h3>
	</c:if>
	<c:if test="${empty member }">
		<h3>member 데이터가 없습니다.</h3>
	</c:if>
	<c:if test="${not empty member2 }">
		<h3>${member2.name }</h3>
	</c:if>
	<c:if test="${empty member2 }">
		<h3>member2 데이터가 없습니다.</h3>
	</c:if>
</body>
</html>