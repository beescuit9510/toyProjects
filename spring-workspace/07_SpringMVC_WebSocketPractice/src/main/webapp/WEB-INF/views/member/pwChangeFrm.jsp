<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>비밀번호 변경</h1>
	<hr/>
	<h3>변경페이지로 이동</h3>
	<form action="pwCheck.do?memberId=${m.memberId}" method="post">
		현재 비밀번호 입력 : <input type="password" name="memberPw"/>
		<input type="submit" value="확인"/>
	</form>
	<hr/>
	<form action="pwChange1.do?memberId=${m.memberId}" method="post">
		현재 비밀번호 입력 : <input type="password" name="oldPassword"/>
		새 비밀번호 입력 : <input type="password" name="newPassword"/>
		<input type="submit" value="변경하기"/>
	</form>
</body>
</html>