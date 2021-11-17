<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>비밀번호 변경2</h1>
	<hr/>
	<h3>변경페이지로 이동</h3>
	<form action="pwChange2.do?memberId=${m.memberId}" method="post">
		변경할 비밀번호 입력 : <input type="password" name="memberPW"/>
		<input type="submit" value="확인"/>
	</form>
</body>
</html>