<%@page import="java.util.ArrayList" %>
<%@page import="kr.or.iei.member.model.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //서블릿에서 request 영역에 등록해놓은 데이터를 추출;
    	ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>회원번호</th><th>아이디</th><th>비밀번호</th><th>이름</th>
			<th>전화번호</th><th>주소</th><th>회원등급</th><th>가입일자</th>
		</tr>
		<% for(Member m : members){%>
		<%= m.getTd() %>
		<%} %>
	</table>

</body>
</html>