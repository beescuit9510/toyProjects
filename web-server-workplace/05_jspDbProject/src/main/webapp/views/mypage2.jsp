<%@page import="kr.or.iei.member.model.vo.Member" %>
<%@page import="kr.or.iei.member.model.service.MemberService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("utf-8");
    	int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    	Member member = new MemberService().selectOneMember(memberNo);
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>마이페이지</h1>
	<hr>
	<form action="/05_jspDbProject/views/update2.jsp" method="get">
		<fieldset>
			<label for="memberNo">회원번호</label>
			<input type="text" name="memberNo" id="memberNo" value="<%=member.getMemberNo() %>" readonly><br>
			<label for="memberId">회원 아이디</label>
			<input type="text" name="memberId" id="memberId" value="<%=member.getMemberId() %>" readonly><br>
			<label for="memberPw">비밀번호</label>
			<input type="text" name="memberPw" id="memberPw" value="<%=member.getMemberPw() %>"><br>
			
			<%--value="<%=member.getMemberPw() %>" -> "1234" --%>
			<%--value="<%=member.getMemberPw() %> " -> "1234 "  --%>
			
			<label for="memberName">이름</label>				
			<input type="text" name="memberName" id="memberName" value="<%=member.getMemberName() %>" readonly><br>
			<label for="phone">전화번호</label>				
			<input type="text" name="phone" id="phone" value="<%=member.getPhone() %>"><br>
			<label for="address">주소</label>				
			<input type="text" name="address" id="address" value="<%=member.getAddress() %>"><br>
			<label for="memberLevel">회원등급</label>				
			<input type="text" name="memberLevel" id="memberLevel" value="<%=member.getMemberLevel()%>" readonly><br>
			<label for="enrollDate">가입일자</label>				
			<input type="text" name="enrollDate" id="enrollDate" value="<%=member.getEnrollDate() %>" readonly><br>
			<input type="submit" value="정보 변경하기">
		</fieldset>
	</form>
	<h2><a href="/05_jspDbProject/index.jsp">메인페이지로 이동</a></h2>
</body>
</html>