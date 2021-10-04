<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<form action="/join" method="post" mame="joinFrm">
			<legend>회원가입</legend>
			<div class="from-group">
				<label class="control-label" for="memberId">아이디</label>
				<input type="text" name="memberId" id="memberId" class="form-control">
			</div>
			<div class="from-group">
				<label class="control-label" for="memberPw">비밀번호</label>
				<input type="text" name="memberPw" id="memberPw" class="form-control">
			</div>
			<div class="from-group">
				<label class="control-label" for="memberName">이름</label>
				<input type="text" name="memberName" id="memberName" class="form-control">
			</div>
			<div class="from-group">
				<label class="control-label" for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" class="form-control" placeholder="010-0000-0000">
			</div>
			<div class="from-group">
				<label class="control-label" for="address">주소</label>
				<input type="text" name="address" id="address" class="form-control">
			</div>
			<button type="submit" class="btn btn-outline-primary btn-lg btn-block">회원가입</button>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>