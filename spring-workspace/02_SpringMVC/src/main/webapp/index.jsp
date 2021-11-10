<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
</head>
<body>
	<h1>Spring MVC 구조 구현</h1>
	<hr>
	<form action="/login.do" method="post">
		아이디 : <input type="text" name="memberId"><br>
		비밀번호 : <input type="password" name="memberPw"><br>
		<input type="submit" value="로그인">
	</form>
	<hr>
	<form action="/search.do" method="post">
		조회 할 아이디 : <input type="text" name="memberId"><br>
		<input type="submit" value="조회">
	</form>
</body>
</html>