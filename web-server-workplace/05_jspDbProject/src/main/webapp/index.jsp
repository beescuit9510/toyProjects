<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	// sesstion은 여러 객체를 저장하는 공간으로 object 로 처리
	// object는 모든 클래스의 조상 클래스로 다형성으로 처리가 가능
	// session에서 가져온 데이터 또한 object 즉, 다운캐스팅 후 사용해야함
	// 세션에 member라는 키로 로그인한 회원 정보를 저장
    	Member member = (Member)session.getAttribute("member");
    
    	boolean loggedIn = member!=null;
    	String logInOut= "로그인하기";
    	String href = "/05_jspDbProject/views/loginFrm.jsp";
    	String printAllMember = "";
    	
    	if(loggedIn){
    		logInOut ="로그아웃하기";
    		href = "/05_jspDbProject/views/logout.jsp";
    		printAllMember = member.getMemberLevel()==1?"<h3><a href='/05_jspDbProject/views/allMember.jsp'> 전체 조회하기</a></h3>":"";
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>  
<body>
	<h1>회원관리</h1>
	<hr>
	<% if(!loggedIn) {%>
	<h3><a href="/05_jspDbProject/views/joinFrm.jsp">회원가입</a></h3>	
	<% } %>
	<%=printAllMember %>
	<h3><a href=<%=href %>><%=logInOut %></a></h3>
	<%if(loggedIn){ %>
	<h3><a href="/05_jspDbProject/views/mypage.jsp">마이페이지-1</a></h3>	 
	<h3><a href="/05_jspDbProject/views/mypage2.jsp?memberNo=<%=member.getMemberNo() %>">마이페이지-2</a></h3>	 
	<% }%>
</body>
</html>