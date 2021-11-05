<%@page import="table.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	Member member = new Member();
	member.setcLevel(3);
	member.setcMemberNo(3);
	HttpSession session2 = request.getSession(false);
	session2.setAttribute("member",member);
%>
<!--  JSTL 확장 c 태그 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<title>구르밍</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		메인작성해주실 분이 작성할 부분
	</div>
			<div class="navi">
				<a class="fundedFundings" href="/fundedFundingList">펀딩한 프로젝트</a> 
				<a class="myOwnProjects" href="/myOwnProject">제작한 프로젝트</a>
				<a class="likeList" href="/likeList">관심 펀더 및 펀더</a>
				<a class="mypage" href="/mypage">설정</a>
			</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>

