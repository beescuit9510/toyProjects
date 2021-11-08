<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
</head>
<body>
	<h1>마이페이지</h1>
	<hr/>
	<form action="/updateMember" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input readonly type="text" name="memberId" placeholder="${sessionScope.member.memberId }"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="memberPw" placeholder="${sessionScope.member.memberPw }"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input readonly type="text" name="memberName" placeholder="${sessionScope.member.memberName }"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" placeholder="${sessionScope.member.phone }"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address" placeholder="${sessionScope.member.address }"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="수정하기"></th>
			</tr>
		</table>
	</form>
	<a href="/">메인으로</a>
</body>
</html>