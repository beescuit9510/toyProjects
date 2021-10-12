<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<h3>1. EMAIL 보내기</h3>
		<form action="/sendMail" method="post">
			<input type="text" name="email" class="short form-control" placeholder="email주소입략"><br><br>
			<input type="text" name="emailTitle" class="form-control" placeholder="제목"><br><br>
			<textarea class="form-control" name="emailContent"></textarea>
			<button class="btn btn-primary btn-block" type="submit">메일전송</button>
		</form>
		<h3>2. EMAIL 보내기</h3>
		<input type="text" id="email">
		<button class="btn btn-primary btn-block" onclick="sendMail()">인증하기</button>
		<!-- ajax로 랜덤코드 6자리 전송 -->
	</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>