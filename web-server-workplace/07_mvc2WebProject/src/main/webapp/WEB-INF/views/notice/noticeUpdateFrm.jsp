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
<title>공지사항 수정</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<!-- 파일을 전송하는 form은 method=post enctype="multipart/form-data" 고정속성! -->
		<form action="/noticeUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>공지사항 수정</legend>
				<table class="table" style="width:100%;">
					<tr class="table-active" style="width:100%;">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="noticeTitle" class="form-control" value=<%=notice.getNoticeTitle() %>>
						</td>
					</tr>
					<tr class="table-active">
						<th>작성자</th>
						<td>
							<%=notice.getNoticeWriter() %>
						</td>
						<th>첨부파일</th>
						<td style="text-align:left; width:350px; height:100px;">
						<%-- <input value="file">에는 value를 설정할 수 없음 --%>
							<input type="hidden" name="status" value=1>
							<%if(notice.getFilename()!= null){ %>
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=notice.getFilename() %></span>
								<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">삭제</button>
								
								<input type="file" name="upfile" style="display:none;">
								
								<input type="hidden" name="oldFilename" value=<%=notice.getFilename() %>>
								<input type="hidden" name="oldFilepath" value=<%=notice.getFilepath() %>>
							<%} else{%>
								<input type="file" name="upfile">

							<%} %>

						<%--
								<input type="file" name="upfile" value=<%=notice.getFilepath() %>>
						 --%>
							<%-- cos 라이브러리는 multiple 이 불가능 
							<input type="file" name="upfile" mutitple>
							--%>
						</td>
					</tr>
					<tr class="table-active">
						<th>내용</th>
						<td colspan="3">
							<textarea id="noticeContent" name="noticeContent" class="form-control"><%=notice.getNoticeContent() %></textarea>
						</td>
					</tr>
					<tr class="table-active">
						<th colspan="4">
							<button type="submit" class="btn btn-danger btn-block">공지수정완료</button>
						</th>
					</tr>
				</table>
			</fieldset>
			<input type="hidden" name="noticeNo" value=<%=notice.getNoticeNo() %>>
<%-- 

	<form action="/noticeUpdate?noticeNo=" method="post" enctype="multipart/form-data">
	action으로 넘기면 get방식이라서 method로 이미 post로 지정했기때문에 한번에 할수있게 input으로 넘겨주는 것임.
--%>			
			

		</form>

	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>


</body>
<script src="/summernote/jquery-3.3.1.js"></script>
<script src="/summernote/summernote-lite.js"></script>
<script src="/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/summernote/summernote-lite.css">
<script>
	$(".delFile").click(function () {
		$(".delFile").hide();
		$(this).next().show();
		$("[name=status]").val(2);
	})
	
	$(function(){
		$("#noticeContent").summernote({
			height: 400,
			lang : "ko-KR"
		});
	});
	

</script>
</html>