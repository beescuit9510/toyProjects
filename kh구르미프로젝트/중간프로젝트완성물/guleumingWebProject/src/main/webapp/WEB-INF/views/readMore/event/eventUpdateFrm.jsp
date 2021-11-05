<%@page import="table.model.vo.Event"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Event e = (Event)request.getAttribute("e");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구르밍</title>
<style>
.write-event{
	margin-bottom: 20px;
}
.table{
	margin-top: 30px;
}
.event-title{
	width: 1200px;		
	height: 40px;
	padding: 12px;
	margin-bottom: 15px;
	outline: none;
}
.back-btn{
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
.back-btn:hover{
	border: 1px solid orange;
	background: #fff;
	color: orange;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/jquery-3.3.1.js"></script>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="container">
		<!-- 파일을 전송하는 form은 method="post" enctype="multipart/form-data" 고정속성 -->
		<form action="/eventUpdate" method="post" enctype="multipart/form-data">
		<fieldset style="border:none;">
				<legend><h2 class="write-event">이벤트 수정</h2></legend>
				<hr>
				<table class="table" style="width:100%;">
							<input type="hidden" name="eventNo" value="<%=e.getEventNo() %>">
					<tr class="table-active">
						<!-- <th>제목</th> -->
						<td colspan="3">
							<input type="text" name="eventTitle" class="event-title" value="<%=e.getEventTitle() %>" autocomplete="off">
						</td>
					</tr>
					<tr class="table-active">
						<!-- <th>작성자</th> -->
						<td>
							<%--  <%=m.getMemberId() %>--%><!-- admin -->
							<input type="hidden" name="eventWriter" value=1<%--"<%=m.getMemberId()%> "--%>>
						</td>
						<!-- <th>첨부파일</th>
						<td style="text-align:left;">
							<input type="file" name="upfile">
						</td> -->
							<input type="hidden" name="status" value="1">
					</tr>
					<tr class="table-active">
						<!-- <th>내용</th> -->
						<td colspan="3" style="text-align:left;">
							<textarea id="eventContent" type="text" name="eventContent" value=<%=e.getEventContent() %>></textarea>
						</td>
					</tr>
					<tr class="table-active">
						<th colspan="4">
							<button type="submit" class="btn">이벤트수정</button>
							<button type="button" class="back-btn" onclick="history.go(-1);">돌아가기</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
		$(function(){
			$("#eventContent").summernote({
				height: 400,
				lang : "ko-KR",
				callbacks:{
					onImageUpload : function(files){
						uploadImage(files[0],this);
					}
				}
			});
		});
		function uploadImage(file,editor){
			//form와 같은 효과를 내는 객체생성
			var form = new FormData();
			form.append("file",file);
			$.ajax({
				url : "/uploadImage",
				type : "post",
				data : form,
				processData : false,
				contentType : false,
				success : function(data){
					//결과로 받은 업로드 경로를 이용해서 에디터에 이미지 추가
					$(editor).summernote("insertImage",data);
				}
			});
			//processData : 기본값이 true {key1:value1, key2:value2, key3:value3}
			//				-> 파일전송 시 String이 아닌 파일형태로 전송하기 위해서 기본설정을 제거
			//contentType : 기본값 "application/x-www-form-urlencoded; charset=UTF-8"
			// 				-> 파일전송 시 enctype="multipart/form-data"로 변환하기 위해 기본값 제거
		}
	</script>
</body>
</html>