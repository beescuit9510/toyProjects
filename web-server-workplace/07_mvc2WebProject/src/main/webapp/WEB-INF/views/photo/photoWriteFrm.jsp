<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진게시판 글 작성</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<div class="container">
	<form action="/photoWrite" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>사진게시판 작성</legend>
			<table class="table" style="width:100%">
				<tr class="table-active">
					<th>작성자</th>
					<td>
						<input type="hidden" name="photoWriter" value="<%=member.getMemberId() %>">
						<%=member.getMemberId() %>						
					</td>
					<th>첨부파일</th>
					<td style="text-align:left">
						<input type="file" name="img" 
						accept=".jpg,.jpeg,.png,.gif" 
						onchange="loading(this);"
						>			
					</td>
				</tr>
				<tr class="table-active">
					<th>이미지</th>
					<td colspan="3">
						<div class="img-viewer">
							<img id="img-view" width="500px">
						</div>
					</td>
				</tr>
				<tr class="table-active">
					<th>내용</th>
					<td colspan="3">
						<textarea name="photoComment" class="form-control"></textarea>
					</td>
				</tr>
				<tr class="table-active">
					<td colspan="4">
						<button type="submit" class="btn btn-danger btn-block">등록하기</button>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>

</body>
<script>
	function loading(obj) {
		var files = obj.files; 
		// input type=file에서 선택한 파일을 배열로 가져옴
		console.log(files);
		console.log(files.length);
		
		if(files.length > 0){//첨부파일이 있는 경우	
			var reader = new FileReader();
			//파일에 대한 정보를 읽어오는 객체;
			
			reader.readAsDataURL(files[0]);
			//파일 경로를 읽어옴;
			
			//경로를 다 읽어오면 실행할 함수 설정
			reader.onload = function(e){
				$("#img-view").attr("src",e.target.result);
				//읽어온 경로를 img태그의 scr 속성에 설정;
			}
	
		}else{//첨부파일잉 없는 경우
			console.log("첨부파일이 없음");
			$("#img-view").attr("src","");
			
			
		}
		
	}
</script>
</html>