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
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<!-- 파일을 전송하는 form은 method=post enctype="multipart/form-data" 고정속성! -->
		<form action="/updateBoard" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>자유게시판 글 수정</legend>
				<table class="table" style="width:100%;">
					<tr class="table-active" style="width:100%;">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="boardTitle" class="form-control" value=<%=board.getBoardTitle()%>>
						</td>
					</tr>
					<tr class="table-active">
						<th>작성자</th>
						<td><%=member.getMemberId() %>
							<input type="hidden" name="boardWriter" value=<%=member.getMemberId() %>>
						</td>
						<th>첨부파일</th>
						<td style="text-align:left; width:350px; height:100px;">						
							<input type="hidden" name="status" value=1>
							<%if(board.getFilename()!= null){ %>
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=board.getFilename() %></span>
								<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">삭제</button>
								
								<input type="file" name="upfile" style="display:none;">
								
								<input type="hidden" name="oldFilename" value=<%=board.getFilename() %>>
								<input type="hidden" name="oldFilepath" value=<%=board.getFilepath() %>>
							<%} else{%>
								<input type="file" name="upfile">

							<%} %>
						
						
							<%-- cos 라이브러리는 multiple 이 불가능 
							<input type="file" name="upfile" mutitple>
							--%>
						</td>
					</tr>
					<tr class="table-active">
						<th>내용</th>
						<td colspan="3">
							<textarea style="min-height:400px;" name="boardContent" class="form-control"><%=board.getBoardContent() %></textarea>
						</td>
					</tr>
					<tr class="table-active">
						<th colspan="4">
							<button type="submit" class="btn btn-danger btn-block">글 등록</button>
						</th>
					</tr>
				</table>
			</fieldset>
			
		<input type="hidden" name="boardNo" value=<%=board.getBoardNo() %>>
			
		</form>

	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>

</body>
<script>
	$(".delFile").click(function () {
		$(".delFile").hide();
		$(this).next().show();
		$("[name=status]").val(2);
	})
</script>

</html>