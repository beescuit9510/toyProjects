<%@page import="kr.or.iei.member.model.vo.Member" %>
<%@page import="kr.or.iei.member.model.service.MemberService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
    	request.setCharacterEncoding("utf-8");

    	int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    	String memberPw = request.getParameter("memberPw");
    	String phone = request.getParameter("phone");
    	String address = request.getParameter("address");
    	
    	Member member = new Member();
    	
    	member.setMemberNo(memberNo);
    	member.setMemberPw(memberPw);
    	member.setPhone(phone);
    	member.setAddress(address);
    	
    	int result = new MemberService().update(member);
    	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>