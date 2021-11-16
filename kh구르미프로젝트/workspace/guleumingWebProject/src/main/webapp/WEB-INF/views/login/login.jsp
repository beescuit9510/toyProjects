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
<title>구르밍</title>
<style>
.container {
	margin: 0 auto;
}
 span>a{
         color: rgb(126, 126, 126);
          font-weight: 900;
      }
.login{
	margin-top:50px;
	margin-bottom:80px;
	}
#loginBtn{
    margin-top: 30px;
    color: white;
    font-weight: 900;
    font-size: 20px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container login_form">
		<div class="login">
		<h1 class="login_title">LOGIN</h1>
		<form action="/loginFrm" method="post">
			<div class="login_box">
				<span>아이디</span><p id="idMsg" class="login_msg"></p>
				<input type="text" name="loginEmail" id="id" class="input_01">
			</div>
			<div class="login_box2">
				<span>비밀번호</span><p id="pwMsg" class="login_msg"></p>
				<input type="password" name="pw" id="pw" class="input_01">
			</div>
			  <span><a href="/join">회원가입 <img src="/img/KimMinji/right_grey.png" width="10px"></a></span>
			<input type="button"  value="로그인" name="loginBtn" id="loginBtn" class="btn btn_100 login_btn">
			 <p id="idMsg" class="login_msg"></p>
		</form>
		</div>
	</div>
	<script>
	$(function() {
		$("#id").keyup(function() {
			$("#idMsg").html("");
			var regId = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+$/;
			var idVal =  $("#id").val();
			if(!regId.test(idVal)){
				$("#idMsg").html("잘못된 이메일 형식입니다");
			}
	});
		$("#pw").change(function() {
			$("#pwMsg").html("");
			var regPw = /^[a-zA-Z0-9]{1,}$/;
			var pwVal = $("#pw").val();
			if(!regPw.test(pwVal)){
				$("#pwMsg").html("비밀번호를 입력해주세요");
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