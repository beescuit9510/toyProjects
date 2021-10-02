<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="kr.or.iei.member.model.service.MemberService" %>
    <%@page import="kr.or.iei.member.model.vo.Member" %>
    <%
    	request.setCharacterEncoding("utf-8");
    	String memberId= request.getParameter("memberId");
    	String memberPw= request.getParameter("memberPw");
    	String memberName= request.getParameter("memberName");
    	String phone  = request.getParameter("phone");
    	String address = request.getParameter("address");
    	
    	Member member = new Member(memberId,memberPw,memberName,phone,address);
    	MemberService service = new MemberService();
    	
    	int result = service.insertMember(member);
    	
    	String resultToPrint = result>0? "회원가입 성공":"회원가입 실패";
    	String href = result>0? "/05_jspDbProject/views/loginFrm.jsp":"/05_jspDbProject/index.jsp";
    	String whereToGo = result>0? "로그인하러 가기":"메인으로 가기";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입 결과</h2>
	<hr>
	<h2><%=resultToPrint %></h2>
	<h2><a href=<%=href %>><%=whereToGo %></a></h2>
</body>
</html>