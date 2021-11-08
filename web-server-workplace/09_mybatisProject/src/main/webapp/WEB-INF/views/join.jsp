<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
</head>
<body>
	<h1>회원가입</h1>
	<hr/>
	<form action="/join" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="memberPw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="memberName"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="가입하기"></th>
			</tr>
		</table>
	</form>
	<a href="/">메인으로</a>
</body>
</html>
			<!-- 			
		<fieldset>
			<legend>로그인</legend>
			<label>이름</label><input type="text" name="memberName"><br>
			<label>아이디</label><input type="text" name="memberId"><br>
			<label>비밀번호</label><input type="password" name="memberPw"><br>
			<label>전화번호</label><input type="text" name="phone"><br>
			<label>주소</label><input type="text" name="address"><br>
		</fieldset>
			 -->