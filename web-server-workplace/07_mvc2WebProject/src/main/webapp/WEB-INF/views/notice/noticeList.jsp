<%@page import="notice.model.vo.NoticePageData"%>
<%@page import="notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Notice> notices = (ArrayList<Notice>)request.getAttribute("notices");
    	
    	String pageNavi = (String)request.getAttribute("pageNavi");

    	int start = (Integer)request.getAttribute("start");
    	
    	int i = 0;
    	
    	String keyword = (String)request.getAttribute("keyword");
    	
    	keyword = keyword==null? "":keyword;
    	
    	String type = (String)request.getAttribute("type");

    	NoticePageData noticePageData = (NoticePageData)request.getAttribute("noticePageData");
    	//noticePageData
    	
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
		<%if(member != null && member.getMemberLevel() == 1 ) {%>
			<div>
				<a class="btn btn-info writeBtn" href="/noticeWriteFrm">글쓰기</a>
			</div>
		<%} %>
		<div id="searchBox">
			<form action="/searchNotice" method="post">
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
			<%for (Notice notice : notices){ %>
			<tr>
			<td><%=start + (i++) %></td>
			<%-- <td><%=notice.getNoticeNo() %></td>--%>
			<td style="text-align:left;width:60%;">
			<a href='/noticeView?noticeNo=<%=notice.getNoticeNo()%>'>
			<%=notice.getNoticeTitle() %></a></td>
			<td><%=notice.getNoticeWriter() %></td>
			<td><%=notice.getRegDate() %></td>
			<td><%=notice.getReadCount() %></td>
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