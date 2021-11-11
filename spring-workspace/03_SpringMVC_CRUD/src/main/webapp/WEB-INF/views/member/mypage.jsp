<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
</head>
<body>
	<h2>마이페이지</h2>
	<form action="/updateMember.do" method="post">
		<fieldset>
			아이디 : <input type="text" name="memberId" value="${m.memberId }" readonly/><br/>
			비밀번호 : <input type="password" name="memberPw" value="${m.memberPw }" /><br/>
			이름 : <input type="text" name="memberName" value="${m.memberName }" readonly/><br/>
			주소 : <input type="text" name="address" value="${m.address }" /><br/>
			가입일 : <input type="text" name="enrollDate" value="${m.enrollDate }" readonly/><br/>
			<input type="submit" value="정보수정"/><br/>
			<a href="/">메인으로</a>
		</fieldset>
	</form>
</body>
</html>