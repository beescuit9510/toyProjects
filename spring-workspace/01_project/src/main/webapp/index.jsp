<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Project</title>
</head>
<body>
	<h2>Spring Project</h2>
	<hr/>
	<form action="/dependency">
		<input type="radio" name="brand" value="Samsung"> 삼성
		<input type="radio" name="brand" value="Lg"> LG
		<input type="submit" value="전송">
	</form>
	<a href="/xmlTest">xml로 객체 생성 테스트</a>
</body>
</html>