<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</table>
	<a href="/">메인으로</a>
</body>
</html>