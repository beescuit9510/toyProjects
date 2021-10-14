<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>	
	<%-- 
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	--%>

	<div class="container">
		<!-- 파일을 전송하는 form은 method=post enctype="multipart/form-data" 고정속성! -->
		<form action="/noticeWrite" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>공지사항 작성(Editor 모드)</legend>
				<table class="table" style="width:100%;">
					<tr class="table-active" style="width:100%;">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="noticeTitle" class="form-control">
						</td>
					</tr>
					<tr class="table-active">
						<th>작성자</th>
						<td><%=member.getMemberId() %>
							<input type="hidden" name="noticeWriter" value=<%=member.getMemberId() %>>
						</td>
						<th>첨부파일</th>
						<td style="text-align:left;">
							<input type="file" name="upfile">
							<%-- cos 라이브러리는 multiple 이 불가능 
							<input type="file" name="upfile" mutitple>
							--%>
						</td>
					</tr>
					<tr class="table-active">
			<%-- 	<tr class="table-light">	--%>
						<th>내용</th>
						<td colspan="3">
							<textarea id="noticeContent" name="noticeContent" class="form-control"></textarea>
						</td>
					</tr>
					<tr class="table-active">
						<th colspan="4">
							<button type="submit" class="btn btn-danger btn-block">공지사항등록</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>

	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>

</body>
<script src="/summernote/jquery-3.3.1.js"></script>
<script src="/summernote/summernote-lite.js"></script>
<script src="/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/summernote/summernote-lite.css">
<script>
	$(function(){
		$("#noticeContent").summernote({
			height: 400,
			lang : "ko-KR",
			callbacks:{
				onImageUpload : function(files){
					uploadImage(files[0], this);
					console.log("이미지업로드");
				}
			}
		});
	});
	
	function uploadImage(file, editor){
		console.log(file);

		var form = new FormData();
		form.append("file",file);
		
		$.ajax({
			url:"/uploadImage",
			type:"POST",
			data:form,
			processData : false,
			contentType : false,
			success: function(data){
				console.log(data);
				$(editor).summernote("insertImage",data);
			}
		});
		// processData : 기본값이 true 
					//-> 파일전송시 String이 아닌 파일형태로 전송하기위해서 기본설정을 제거!
		//{key1:value1, key2:value2, key3:value3};
		//key:value 형태는 servlet에서 데이터 추출시 자료형이 무조건 String
		//file은 자료형을 String으로 보내기엔 너무 크고 변환도 해야하기 때문에 어려움.
		//디폴트 -> String 그래서 false로 바꿔준것.
		
		//contentType : 기본값 "application/x-www-form-urlencoded; charset=UTF-8"
					//-> 파일전송시 enctype="multipart/form-data"로 변환하기 위해 기본값 제거;
	}
</script>
</html>