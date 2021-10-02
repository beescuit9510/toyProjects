<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<hr>
	<form action="/login" method="post">
		<fieldset>
			<label for="memberId">아이디 입력 : </label>
			<input type="text" name="memberId" id="memberId"><br>
			<label for="memberPw">비밀번호 입력 : </label>
			<input type="text" name="memberPw" id="memberPw"><br>
			<input type="submit" value="로그인">
			<input type="reset" value="초기화">
		</fieldset>
	</form>


</body>
</html>