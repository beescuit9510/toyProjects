<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringMVC_WebSocket 프로젝트</title>
</head>
<body>
	<h2>SpringMVC_WebSocket 프로젝트</h2>
	<hr/>
	<a href="/test1.do">test1</a><br/>
	<a href="/test2.do">test2</a>
	<c:choose>
		<c:when test="${empty sessionScope.m }">
			<form action="/login.do" method="post">
				<fieldset>
				<legend>로그인</legend>
				아이디 : <input type="text" name="memberId"/><br/>
				비밀번호 : <input type="password" name="memberPw"/><br/>
				<input type="submit" value="로그인"/>
				<input type="reset" value="초기화"/>
				<a href="/joinFrm.do">회원가입</a>
				</fieldset>
			</form>
		</c:when>
		<c:otherwise>
			<h2>[${sessionScope.m.memberName }] 님 환영합니다.</h2>
				<a href="/logout.do">로그아웃</a><br/>
				<a href="/mypage.do?memerId=${sessionScope.m.memberId }">마이페이지</a><br/>
				<a href="/updatePwFrm.do">비밀번호 변경</a><br/>
				<a href="/pwChangeFrm.do">비밀번호 변경2</a><br/>
				<a href="/allMember.do">전체 회원 조회</a><br/>
				<a href="/allMemberAjax.do">전체 회원 조회(ajax)</a><br/>
				<a href="/boardList.do">게시판 목록</a><br/>
		</c:otherwise>
	</c:choose>
</body>
</html>