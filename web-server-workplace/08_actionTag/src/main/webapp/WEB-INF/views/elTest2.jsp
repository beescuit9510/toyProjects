<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>회원정보출력</h1>
	<h3 style="color:red;">변수는 모두 private이기때문에 getter를 이용해서 값을 가져오는 것임.</h3>
	<hr>
	<h2>이름 : ${member.name}</h2>
	<h2>나이 : ${member.age}</h2>
	<h2>주소 : ${member.addr}</h2>
	<h2>TEST : ${member.TEST}</h2>
	<h2>test : ${member.test}</h2>
</body>
</html>