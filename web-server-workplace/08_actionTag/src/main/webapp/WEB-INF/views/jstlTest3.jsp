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
	<c:choose>
		<c:when test="${num == 100 }">
			<h1>100입력</h1>
		</c:when>
		<c:when test="${num == 50 }">
			<h2>505050</h2>
		</c:when>
		<c:when test="${num == 1 }">
			<h2>111111</h2>
		</c:when>
		<c:otherwise>
			<h2>아무것도 해당되지 않으면 실행</h2>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty member}">
			<h2>member 이 비어있음</h2>
		</c:when>
		<c:otherwise>
			<h2>${member.name}</h2>		
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty member2}">
			<h2>member2 이 비어있음</h2>
		</c:when>
		<c:otherwise>
			<h2>${member2.name}</h2>		
		</c:otherwise>
	</c:choose>
</body>
</html>