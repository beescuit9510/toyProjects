<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Board board = (Board)request.getAttribute("board");
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
		<legend>공지사항</legend>
		<table class="table" id="boardView" style="width100%;">
			<tr class="table-info">
				<th colspan="4"><%=board.getBoardTitle() %></th>
			</tr>
			<tr class="table-light">
				<th>작성자</th>
				<th><%=board.getBoardWriter() %></th>
				<th>작성일</th>
				<th><%=board.getRegDate() %></th>
			</tr>
			<tr class="table-light">
				<th>첨부파일</th>
				<th>
					<%if(board.getFilename()!=null){ %>
					<img src="/img/file.png" width="16px">
					<a href='/boardFileDown?boardNo=<%=board.getBoardNo() %>'><%=board.getFilename() %></a>
					<%} %>
				</th>
				<th>조회수</th>
				<th><%=board.getReadCount() %></th>
			</tr>
			<tr class="table-light">
				<th>내용</th>
				<th colspan="3"><%=board.getBoardContentBr() %></th>
			</tr>
			<tr class="table-light">
				<th colspan="4" style="text-align:center;">
					<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
					<% if(member!=null && member.getMemberId().equals(board.getBoardWriter())) {%>
					<a href="/updateBoard?boardNo=<%=board.getBoardNo() %>" class="btn btn-info" >수정하기</a>
					<a href="/deleteBoard?boardNo=<%=board.getBoardNo() %>" class="btn btn-info" >삭제하기</a>
					<%} %>
				</th>
			</tr>
		</table>
	</fieldset>
</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>