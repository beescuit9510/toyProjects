<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>쪽지함</h1>
	<hr/>
	<h2>받은 쪽지함</h2>
	<table border="1">
		<tr>
			<th>번호</th><th>보낸이</th><th>내용</th><th>날짜</th><th>읽음여부</th>
		</tr>
		<c:forEach items="${list }" var="dm" >
	 		<c:if test="${list.receiver eq m.memberId }">
				<tr>
					<td>${dm.dmNo }</td>
					<td>${list.sender}</td>
					<td>${list.dmContent }</td>
					<td>${list.dmTime }</td>
					<td>${list.readStatus }</td>
				</tr>
			</c:if>	
		</c:forEach>
	</table>
	<hr/>
	<h2>보낸 쪽지함</h2>
	<table border="1">
		<tr>
			<th>번호</th><th>받은사람</th><th>내용</th><th>날자</th><th>읽음여부</th>
		</tr>
		<c:forEach items="${list }" var="dm" >
			<c:if test="${list.sender eq m.memberId }">
				<tr>
					<td>${dm.dmNo }</td>
					<td>${list.receiver}</td>
					<td>${list.dmContent }</td>
					<td>${list.dmTime }</td>
					<td>${list.readStatus }</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</html>