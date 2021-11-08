<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MybatisTest</title>
</head>
<body>
	<h1>MybatisTest</h1>
	<hr>
	<c:choose>
		<c:when test="${empty sessionScope.member }">
			<form action="/login" method="post">
				<fieldset>
					<legend>로그인</legend>
					<label>아이디</label><input type="text" name="memberId"><br>
					<label>비밀번호</label><input type="password" name="memberPw"><br>
					<input type="submit" value="로그인">
					<input type="reset" value="취소">
					<a href="/joinFrm">회원가입</a>
				</fieldset>
			</form>
		</c:when>
		<c:otherwise>
			<h3>[${sessionScope.member.memberName }]님 환영합니다.</h3>
			<a href="/selectAll">전체 회원 조회</a><br/>
			<a href="/mypage?memberNo=${sessionScope.member.memberNo }">마이페이지</a><br/>
			<a href="/logout">로그아웃</a><br/>
			<a href="/deleteMember?memberId=${sessionScope.member.memberId }">회원탈퇴</a><br/>
		</c:otherwise>
	</c:choose>
</body>
</html>