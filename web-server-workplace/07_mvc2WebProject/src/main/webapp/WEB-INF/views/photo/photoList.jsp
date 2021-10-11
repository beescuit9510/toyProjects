<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	int totalCount = (Integer)request.getAttribute("totalCount");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진게시판</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<div class="container">
	<fieldset>
		<legend>사진게시판</legend>
		<span><%=totalCount %></span>
		<%if(member!= null){ %>
		<div>
			<a href="/photoWriteFrm" class="btn btn-info writeBtn">글쓰기</a>
		</div>
		<%} %>
	</fieldset>
</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>