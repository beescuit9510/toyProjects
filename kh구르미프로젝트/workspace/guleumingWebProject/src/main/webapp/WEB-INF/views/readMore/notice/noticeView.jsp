<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구르밍</title>
<style>
fieldset{
	width: 1000px;
	margin: 0 auto;
	border: none;
}
.table{
	margin-top: 30px;
}
.notice-title{
	font-size: 25px;
}
.notice-etc{
	height: 70px;
}
.notice-etc>td{
	padding-left: 30px;
}
.notice-content>td{
	border-top: 1px solid gray;
	padding-left:30px;
	margin-top: 15px;
	font-size:20px;
}
.notice-content>td>p{
	margin-top: 15px;
	font-size:20px;
}
.delete-btn{
	border: 1px solid orange;
	border-radius: 10px;
	box-sizing: border-box;
	background: orange;
    color: #fff;
    text-decoration: none;
    cursor: pointer;
    outline: none;
    display: inline-block;
    width: 150px;
    height: 45px;
    font-size: 18px;
    line-height: 45px;
    border-radius: 5px;
    text-align: center;
    transition-duration: 0.5s;
}
.delete-btn:hover{
	border: 1px solid orange;
	background: #fff;
	color: orange;
}
.table-footer{
	height: 150px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<fieldset>
			<table class="table" id="noticeView" style="width:100%;">
				<tr class="notice-title">
					<th colspan="3">${n.noticeTitle }</th>
				</tr>
				<tr class="notice-etc">
				<c:if test="${n.noticeWriter eq 1 }">
					<td><%-- ${n.noticeWriter } --%> admin</td>
				</c:if>
				<td>${n.writeDate }</td>
				</tr>
				<tr class="notice-content">
					<td colspan="3">
						${n.noticeContent }
					</td>
				</tr>
				<tr class="table-footer">
					<th colspan="3">
						<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
						<c:if test="${not empty sessionScope.member && sessionScope.member.cLevel eq 1 }">
							<a href="/noticeUpdateFrm?noticeNo=${n.noticeNo }" class="btn">수정하기</a>
							<a href="/noticeDelete?noticeNo=${n.noticeNo }" class="delete-btn">삭제하기</a>						
						</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>