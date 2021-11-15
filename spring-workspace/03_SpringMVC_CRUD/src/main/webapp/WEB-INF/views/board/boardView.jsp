<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성글 보기</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>보드 번호</th>
			<td>${board.boardNo }</td>		
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.boardWriter }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.boardTitle }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.boardDate }</td>
		</tr>
		<tr>
			<th>작성글</th>
			<td>${board.boardContent }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><c:forEach items="${board.list }" var="f">
				<a href="boardFileDownload?fileNo=${f.fileNo }">${f.filename }</a>
			</c:forEach></td>
		</tr>
	</table>
	<a href="/">메인으로</a>
</body>
</html>