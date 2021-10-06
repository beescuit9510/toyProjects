<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Notice notice = (Notice)request.getAttribute("notice");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글보기</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<div class="container">
	<fieldset>
		<legend>공지사항</legend>
		<table class="table" id="noticeView" style="width100%;">
			<tr class="table-info">
				<th colspan="4"><%=notice.getNoticeTitle() %></th>
			</tr>
			<tr class="table-light">
				<th>작성자</th>
				<th><%=notice.getNoticeWriter() %></th>
				<th>작성일</th>
				<th><%=notice.getRegDate() %></th>
			</tr>
			<tr class="table-light">
				<th>첨부파일</th>
				<th>
					<%if(notice.getFilename()!=null){ %>
					<img src="/img/file.png" width="16px">
					<a href="/fileDown?noticeNo=<%=notice.getNoticeNo() %>"><%=notice.getFilename() %></a>
					<%} %>
				</th>
				<th>조회수</th>
				<th><%=notice.getReadCount() %></th>
			</tr>
			<tr class="table-light">
				<th>내용</th>
				<th colspan="3"><%=notice.getNoticeContentBr() %></th>
			</tr>
			<tr class="table-light">
				<th colspan="4" style="text-align:center;">
					<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
					<% if(member!=null && member.getMemberId().equals(notice.getNoticeWriter())) {%>
					<button class="btn btn-info" >수정하기</button>
					<button class="btn btn-info" >삭제하기</button>
					<%} %>
				</th>
			</tr>
		</table>
	</fieldset>
</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>


</body>
</html>