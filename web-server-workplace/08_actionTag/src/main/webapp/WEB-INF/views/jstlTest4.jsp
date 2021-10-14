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
	<table border="1">
		<tr>
			<th>번호(count)</th>
			<th>번호(index)</th>
			<th>첫번째 여부(index)</th>
			<th>두번째 여부(index)</th>
			<th>current(toString)</th>
			<th>이름</th>
			<th>나이</th>
			<th>주소</th>
		</tr>
		<c:forEach items="${members}" var="member" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${i.index }</td>
				<td>${i.first }</td>
				<td>${i.last }</td>
				<td>${i.current }</td>
				<td>${member.name }</td>
				<td>${member.age }</td>
				<td>${member.addr }</td>
			</tr>
		</c:forEach>
	</table>
	<c:forEach begin="1" end="10" step="2" varStatus="i">
		<h3>${i.count }</h3>
	</c:forEach>
</body>
</html>