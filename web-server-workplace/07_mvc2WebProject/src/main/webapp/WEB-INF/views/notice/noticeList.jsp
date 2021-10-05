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
	<fieldset>
		<legend>공지사항</legend>
		<%if(member != null && member.getMemberLevel() == 1 ) {%>
			<div>
				<a class="btn btn-info writeBtn" href="/noticeWriteFrm">글쓰기</a>
			</div>
		<%} %>
	</fieldset>
</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>