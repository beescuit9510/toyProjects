<%@page import="java.util.ArrayList"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Board> boards = (ArrayList<Board>)request.getAttribute("boards");
    
    	String start = (String)request.getAttribute("start");
    	
    	String pageNavi = (String)request.getAttribute("pageNavi");

    	int i = 1;
    	
    %>
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
		<legend>자유게시판</legend>
		<%if(member != null ){%>
			<div>
				<a class="btn btn-info writeBtn" href="/boardWriteFrm">글쓰기</a>
			</div>
		<%} %>
		<table class="table-hover" style="width:100%;">
			<tr class="table primary">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<%for (Board board : boards){ %>
			<tr>
			<td><%=start + (i++) %></td>
			<%-- <td><%=notice.getNoticeNo() %></td>--%>
			<td style="text-align:left;width:60%;">
			<a href='/boardView?boardNo=<%=board.getBoardNo()%>'>
			<%=board.getBoardTitle() %></a></td>
			<td><%=board.getBoardWriter() %></td>
			<td><%=board.getRegDate() %></td>
			<td><%=board.getReadCount() %></td>
			</tr>
			<%} %>
		</table>
		<div id="pageNavi"><%=pageNavi %></div> 		
	</fieldset>
	
	</div>
	
	
	
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>