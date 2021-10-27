<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container {
	margin: 0 auto;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div>LOGIN</div>
		<form action="/loginFrm" method="post">
			<fieldset>
				<div class="login-form">
				아이디<br> <input type="text" name="loginEmail" id="id">
				<p id="idMsg"></p>
				</div>
				<div class="login-form">
				비밀번호<br> <input type="password" name="pw" id="pw">
				<p id="pwMsg"></p>
				</div>
				<input type="button" name="loginBtn" id="loginBtn" value="로그인">
			</fieldset>
		</form>
	</div>
	<script>
	$(function() {
		$("#id").keyup(function() {
			$("#idMsg").html("");
			var regId = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+$/;
			var idVal =  $("#id").val();
			if(!regId.test(idVal)){
				$("#idMsg").html("잘못된 이메일 형식입니다");
				$("#idMsg").css("color","red");
			}
	});
		$("#pw").change(function() {
			$("#pwMsg").html("");
			var regPw = /^[a-zA-Z0-9]{1,}$/;
			var pwVal = $("#pw").val();
			if(!regPw.test(pwVal)){
				$("#pwMsg").html("비밀번호를 입력해주세요");
				$("#pwMsg").css("color","red");
			}
		});
		$("#loginBtn").click(function() {
			var loginEmail = $("#id").val();
			var pw = $("#pw").val();
			$.ajax({
				url: "/loginCheck",
				data : {loginEmail : loginEmail,
					pw : pw },
				type : "post",
				success : function(data) {
					if(data == 0){
						$("input[name=loginBtn]").prop("type","reset");
						$("#idMsg").html("");
						$("#pwMsg").html("가입하지 않은 이메일이거나 잘못된 비밀번호입니다");
						$("#pwMsg").css("color","red");
					}else if(data == 1){
						$("input[name=loginBtn]").prop("type","submit");
					}
				}
			});
		});
	});

	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>