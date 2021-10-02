<%@page import="kr.or.iei.member.model.service.MemberService"%>
<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    request.setCharacterEncoding("utf-8");
    
    String memberId = request.getParameter("memberId");
    String memberPw = request.getParameter("memberPw");
    
    Member member = new MemberService().selectOneMember(memberId, memberPw);
    
    
    boolean r = member!=null;
    
    String wellcome = "";
    String resultToPrint = "로그인 실패";
    String href = "/05_jspDbProject/views/loginFrm.jsp";
    String pagee = "로그인 페이지로 이동";
    
	if(r) {
    wellcome = "["+member.getMemberId()+"]님 환영합니다.";
	resultToPrint = "로그인 성공";
	href = "/05_jspDbProject/index.jsp";
	pagee = "메인페이지로 이동";
	}
	
	// 세션에 member라는 키로 로그인한 회원 정보를 저장
	session.setAttribute("member", member);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 결과</h2>
	<hr>
	<p><%=resultToPrint %></p>
	<h3><%=wellcome %></h3>

	<% if(r) { %>
		<h2></h2>
		<table border="1">
			<tr>
				<th>회원번호</th><th>아이디</th><th>비밀번호</th><th>이름</th>
				<th>전화번호</th><th>주소</th><th>회원등급</th><th>가입일자</th>
			</tr>
			<%= member.getTd() %>
		</table>
	
	<% } %>
	<h4><a href=<%=href %>><%=pagee %></a></h4>
</body>
</html>