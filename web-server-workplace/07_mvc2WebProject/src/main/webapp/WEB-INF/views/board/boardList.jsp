<%@page import="java.util.ArrayList"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    
    	ArrayList<Board> boards = (ArrayList<Board>)request.getAttribute("boards");
    
    	int start = (Integer)request.getAttribute("start");
    	
    	String pageNavi = (String)request.getAttribute("pageNavi");

    	String type = (String)request.getAttribute("type");

    	String keyword = (String)request.getAttribute("keyword");
    	
    	keyword = keyword==null? "":keyword;

    	int i = 0;
    	
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
		
		<div id="searchBox">
			<form action="/searchBoard" method="post">
<%-- 				<input type="hidden" name="reqPage" value="1">	--%>
				<select class="form-control" name="type">
					<%if(type==null) {%>
						<option value="title" >제목</option>
						<option value="writer">작성자</option>
					<%} else if(type.equals("title")){ %>
						<option value="title" selected>제목</option>
						<option value="writer">작성자</option>
					<%} else {%>
						<option value="title">제목</option>
						<option value="writer" selected>작성자</option>
					<%} %>
				</select>
				<input type="text" name="keyword" class="form-control" value="<%=keyword %>">
				<button type="submit" class="btn btn-primary">검색</button>
			</form>
		</div>
		
		
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
			<%=board.getBoardTitle() %>[<%=board.getBcCount() %>]</a></td>
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
<style>
	#searchBox{
		width:400px;
	}
	select[name=type]{
		display:inline-block;
		width:30%;
	}
	input[name=keyword]{
		display:inline-block;
		width:50%;
	}
	
</style>

</html>