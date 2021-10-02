<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입 정보입력</h1>
	<hr>
	<form action="/join" method="post">
		<fieldset>
			<legend>회원가입</legend>
			<label for="memberId">아이디</label>
			<input type="text" name="memberId"><br>
			<label for="memberPw">비밀번호</label>
			<input type="password" name="memberPw"><br>
			<label for="memberName">이름</label>
			<input type="text" name="memberName"><br>
			<label for="phone">전화번호</label>
			<input type="text" name="phone"><br>
			<label for="address">주소</label>
			<input type="text" name="address"><br>
			<input type="submit" value="회원가입">
			<input type="reset" value="초기화">
		</fieldset>
	</form>
</body>
</html>