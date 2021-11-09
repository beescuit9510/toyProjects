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
					<a href="/joinFrm">회원가입</a><br/>
					<a href="/searchFrm">아이디/비밀번호 찾기</a>
				</fieldset>
			</form>
		</c:when>
		<c:otherwise>
			<h3>[${sessionScope.member.memberName }]님 환영합니다.</h3>
			<a href="/selectAll">전체 회원 조회</a><br/>
			<a href="/mypage?memberNo=${sessionScope.member.memberNo }">마이페이지</a><br/>
			<a href="/logout">로그아웃</a><br/>
			<a href="/deleteMember?memberId=${sessionScope.member.memberId }">회원탈퇴</a><br/>
			<a href="/noticeList?reqPage=1">공지사항목록</a><br/>
			<hr>
			<h2>동적쿼리 if문</h2>
			<h3>전체회원 조회 시 포함 될 조회 항목</h3>
			<form action="/ifTest" method="post">
				<input type="checkbox" name="ckName"> 이름
				<input type="checkbox" name="ckPhone"> 전화번호
				<input type="checkbox" name="ckAddress"> 주소
				<br>
				<input type="submit" value="조회">
			</form>
			<hr>
			<h2>동적쿼리 choose</h2>
			<h3>아이디 또는 이름으로 검색</h3>
			<form action="/chooseTest" method="post">
				<select name="type">
					<option value="id">아이디</option>
					<option value="name">이름</option>
				</select>
				<input type="text" name="keyword">
				<input type="submit" value="검색">
			</form>
			<hr>
			<h2>동적쿼리 trim</h2>
			<h3>이름과 주소를 입력하면 입력값이 포함된 회원 조회해서 출력</h3>
			<p>단, 이름이나 주소를 1개만 입력하면 1개에 대해서만 조회해서 출력 (둘다 입력시 두개 모두)</p>
			<form action="/trimTest" method="post">
				이름 : <input name="memberName" value=""><br/>
				주소 : <input  name="address" value=""><br/>
				<input type="submit" value="검색">
			</form>
			<hr>
			<h2>동적쿼리 foreach</h2>
			<form action="/foreachTest" method="post">
				<input type="checkbox" name="memberId" value="byunduck"> byunduck <br/>
				<input type="checkbox" name="memberId" value="bunny"> bunny <br/>
				<input type="checkbox" name="memberId" value="deathEater"> deathEater <br/>
				<input type="submit" value="선택회원정보죄회">
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>