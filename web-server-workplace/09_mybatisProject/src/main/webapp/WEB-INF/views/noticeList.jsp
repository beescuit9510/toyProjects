<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice List</title>
</head>
<body>
	<h1>공지사항</h1>
	<hr>
	<table border="1">
		<tr>
			<th>번호</th><th>제목</th><th>작성자</th><th>조회수</th><th>작성일</th>
		</tr>
		<c:forEach items="${list }" var="n" varStatus="i">
			<tr>
				<td>${start+i.index }</td>
				<td><a href="/noticeView?noticeNo=${n.noticeNo }">${n.noticeTitle }</a></td>
				<td>${n.noticeWriter }</td>
				<td>${n.readCount }</td>
				<td>${n.regDate }</td>
			</tr>
		</c:forEach>
	</table>
	<div id="paaeNavi">${pageNavi }</div>
	<a href="/">메인으로</a>
</body>
</html>