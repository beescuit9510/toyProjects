<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>if test</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<c:if test="${not empty ckName }">
				<th>이름</th>
			</c:if>
			<c:if test="${not empty ckPhone }">
				<th>전화번호</th>
			</c:if>
			<c:if test="${not empty ckAddress }">
				<th>주소</th>
			</c:if>
		</tr>
		<c:forEach items="${list }" var="m" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${m.memberId }</td>
				<c:if test="${not empty ckName }">
					<td>${m.memberName }</td>
				</c:if>
				<c:if test="${not empty ckPhone }">
					<td>${m.phone }</td>
				</c:if>
				<c:if test="${not empty ckAddress }">
					<td>${m.address }</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>