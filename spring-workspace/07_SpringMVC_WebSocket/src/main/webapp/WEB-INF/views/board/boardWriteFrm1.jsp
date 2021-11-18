<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성글 보기</title>
</head>
<body>
	<form action="/boardWrite1.do" method="post">
		<table border="1">
			<tr>
				<th>보드 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>작성글</th>
			</tr>
			<tr>
				<td>보드번호</td>
			<!--  
				<td><input type="text" name="boardNo" readonly/></td>
			-->
				<td><input type="text" name="boardTitle" /></td>
				<td><input type="text" name="boardWriter" value=${sessionScope.m.memberId } readonly/></td>
				<td>보드 날짜</td>
			<!--  
				<td><input type="text" name="boardDate" readonly/></td>
			-->
				<td><textarea name="boardContent"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="작성">
	</form>

</body>
</html>