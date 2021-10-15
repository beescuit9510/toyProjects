<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<fieldset>
			<legend>공지사항(New)</legend>
			<c:if
				test="${not empty sessionScope.member && sessionScope.member.memberLevel eq 1}">
				<div>
					<a class="btn btn-info writeBtn" href="/noticeWriteFrm">글쓰기</a>
				</div>
			</c:if>
			<div id="searchBox">
				<form action="/searchNotice" method="post">
					<%-- <input type="hidden" name="reqPage" value="1">	--%>
					<select class="form-control" name="type">
						<c:choose>
							<c:when test="${not empty type}">
								<c:when test="${type eq 'title' }">
									<option value="title" selected>제목</option>
									<option value="writer">작성자</option>
								</c:when>
								<c:otherwise>
									<option value="title">제목</option>
									<option value="writer" selected>작성자</option>
								</c:otherwise>
							</c:when>
							<c:otherwise>
								<option value="title">제목</option>
								<option value="writer">작성자</option>
							</c:otherwise>
						</c:choose>
					</select><input type="text" name="keyword" class="form-control"
						value="${keyword }">
					<button type="submit" class="btn btn-primary">검색</button>
				</form>
			</div>
			<table class="table-hover" style="width: 100%;">
				<tr class="table primary">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${notices}" var="notice" varStatus="i">
					<tr>
						<td>${i.index+start }</td>
						<td style="text-align: left; width: 60%;"><a
							href='/noticeView?noticeNo${notice.noticeNo}'>
								${notice.noticeTitle}</a> [${notice.ncCount }]</td>
						<td>${notice.noticeWriter}</td>
						<td>${notice.regDate}</td>
						<td>${notice.readCount}</td>
					</tr>
				</c:forEach>
			</table>
			<div id="pageNavi">${pageNavi}</div>
		</fieldset>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
<style>
#searchBox {
	width: 400px;
}

select[name=type] {
	display: inline-block;
	width: 30%;
}

input[name=keyword] {
	display: inline-block;
	width: 50%;
}
</style>
</html>