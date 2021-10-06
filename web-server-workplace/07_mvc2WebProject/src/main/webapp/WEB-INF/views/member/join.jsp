<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<form action="/checkId" name="checkIdFrm" method="post" >
			<input type="hidden" name="checkId">
		</form>
		<form action="/join" method="post" name="joinFrm">
			<legend>회원가입</legend>
			<div class="from-group">
				<label class="control-label" for="memberId" style="display:block;">아이디</label>
				<input type="text" name="memberId" id="memberId" class="form-control" 
				style="width:90%; display:inline-block;">
				<button type="button" id="idChk" class="btn btn-secondary">중복체크</button>
			</div>
			<br>
			<div class="from-group">
				<label class="control-label" for="memberPw">비밀번호</label>
				<input type="text" name="memberPw" id="memberPw" class="form-control">
			</div>
			<br>
			<div class="from-group">
				<label class="control-label" for="memberName">이름</label>
				<input type="text" name="memberName" id="memberName" class="form-control">
			</div>
			<br>
			<div class="from-group">
				<label class="control-label" for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" class="form-control" placeholder="010-0000-0000">
			</div>
			<br>
			<div class="from-group">
				<label class="control-label" for="address">주소</label>
				<input type="text" name="address" id="address" class="form-control">
			</div>
			<br><br>
			<button type="submit" class="btn btn-outline-primary btn-lg btn-block">회원가입</button>
			<br><br>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script>
	$("#idChk").click(function () {
		var memberId = $(this).prev().val();
		if(memberId == ""){
			alert("아이디를 입력하세요!");
			return;
		}
		
		$("[name=checkId]").val(memberId)
		
		//아이디 중복 체크 과정을 진행할 새로운창 open;
		window.open("","checkId","left=300, top=300, width=300, height=200");
		
		// 아이디 중복체크 from과 새로 열린 창을 연결;
		$("[name=checkIdFrm]").attr("target","checkId");
		
		//form 태그를 submit;
		$("[name=checkIdFrm]").submit();

	})
</script>
</html>