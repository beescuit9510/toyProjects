<%@page import="kr.or.iei.member.model.service.MemberService"%>
<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.stream.Stream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//1. 인코딩 전달되는 /값이 없을땐 생략
    	//2. 전달한 값 추출 / 값이 없을땐 생략
    	//3. 비지니스 로직
    	ArrayList<Member> members = new MemberService().allMember();
    	
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