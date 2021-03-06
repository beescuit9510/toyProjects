<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>

</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<form action="/findId" method="post" name="findIdPwFrm">
			<legend>아이디 찾기</legend>
			<div class="from-group">
				<label class="control-label" for="memberName">이름</label>
				<input type="text" name="memberName" id="memberName" class="form-control"><br>
				<label class="control-label" for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" class="form-control">
				<br><br>
			<button type="button" class="btn btn-outline-primary btn-lg btn-block" value="아이디 찾기">아이디 찾기</button>
			</div>
		</form>
		<br>
		<br>
		<br>
		<br>
		<form action="/findPw" method="post" name="findIdPwFrm">
			<legend>비밀번호 찾기</legend>
			<div class="from-group">
				<label class="control-label" for="memberId">아이디</label>
				<input type="text" name="memberId" id="memberId" class="form-control"><br>
				<label class="control-label" for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" class="form-control">
				<br><br>
			<button type="button" class="btn btn-outline-primary btn-lg btn-block" value="비밀번호 찾기">비밀번호 찾기</button>
			</div>
		</form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>

</body>
<script>
	$("button").click(function () {

		window.open("","newPage","left=300, top=300, width=300, height=200");
		
		var i = $(this).val()=="비밀번호 찾기"? 1:0;
		
		console.log($(this).val())
		var form = $("[name=findIdPwFrm]").eq(i);
		
		// 아이디 중복체크 from과 새로 열린 창을 연결;
		form.attr("target","newPage");
		
		//form 태그를 submit;
		form.submit();
	
		
	})
</script>
</html>