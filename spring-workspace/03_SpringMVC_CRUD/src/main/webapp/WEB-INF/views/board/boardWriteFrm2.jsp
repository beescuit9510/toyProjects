<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 작성(첨부파일 포함)</h1>
	<hr/>
	<form action="/boardWrite2.do" method="post" enctype="multipart/form-data">
		제목 : <input type="text" name="boardTitle"/><br/>
		작성자 : <input type="text" name="boardWriter" value="${sessionScope.m.memberId }" readonly/><br/>
		첨부파일 : <input type="file" name="files" multiple/><br/>
		내용 : <textarea rows="3" cols="10" name="boardContent"></textarea><br/>
		<input type="submit" value="게시글작성"/>
	</form>
</body>
</html>