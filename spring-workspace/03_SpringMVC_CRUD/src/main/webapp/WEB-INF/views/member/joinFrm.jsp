<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinFrm</title>
</head>
<body>
	<h1>회원가입</h1>
	<hr/>
	<form action="/join.do" method="post">
		<fieldset>
			아이디 : <input type="text" name="memberId"/><br/>
			비밀번호 : <input type="text" name="memberPw"/><br/>
			이름 : <input type="text" name="memberName"/><br/>
			주소 : <input type="text" name="address"/><br/>
			<input type="submit" value="회원가입"><br/>
			<input type="reset" value="취소">
		</fieldset>
	</form>
</body>
</html>