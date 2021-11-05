<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
	<div>
		<span id="user">개인회원</span> <span id="maker">법인회원</span>
	</div>
	<div id="join">
	<form action="/joinFrm" method="post">
		<fieldset>
			<div class="join-form">
			<label for="joinId">이메일 (ID)</label> 
			<input type="text" id="joinId"	name="joinId" placeholder="이메일을 입력해주세요" >
			<p class="comment" id="checkId"></p>
			</div>
			<div class="join-form">
			<label for="joinPw">비밀번호</label>
			 <input type="password" id="joinPw"	name="joinPw" placeholder="4자리 이상의 비밀번호 입력(대/소문자,숫자)" >
			<p class="comment"></p>
			</div>
			<div class="join-form">
			<label for="joinPwRe">비밀번호 확인</label>
			 <input type="password"	id="joinPwRe" name="joinPwRe">
			<p class="comment"></p>
			</div>
			<div class="join-form">
			<label for="joinName">이름</label>
			 <input type="text" id="joinName" name="joinName">
			 <p class="comment"></p>
			</div>
			<div class="join-form">
			<label for="joinPhone">전화번호</label> 
			<input type="text" id="joinPhone" name="joinPhone" placeholder="010-0000-0000">
			<p class="comment"></p>
			</div>
			<input type="hidden" name ="joinLevel" id="joinLevel" value="2">
			<input type="submit" value="추가정보입력" name="joinMore" style="display:none;">
			<input type="submit" value="회원가입 " name="joinSub" >
		</fieldset>
	</form>
	</div>
	</div>
	<script>
		$("#maker").on('click', function() {
		  $("input[name=joinSub]").css("display","none");
		  $("input[name=joinMore]").css("display","block");
		  $("input[name=joinLevel]").val("3");
		});
		$("#user").on('click', function() {
			  $("input[name=joinSub]").css("display","block");
			  $("input[name=joinMore]").css("display","none");
			  $("input[name=joinLevel]").val("2");
			});


		$(function () {
			
			var comments = $(".comment");
			comments.css("color", "red");
			var result = true;
			var reId = true;
			var rePw = true;
			var rePwRe = true;
			var reName = true;
			var rePhone = true;
			
			$("#joinId").keyup(function(){	 
				comments.eq(0).html("");
				reId = true;
				var regId = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+$/;
				var joinId = $(this).val();
				if (regId.test(joinId)) {
					$.ajax({
						url : "/checkId",
						data : {joinId : joinId},
						type : "get",
						success : function(data) {
							if (data == 1) {
								comments.eq(0).html("사용중인 아이디입니다");
								reId = false;
							}
						}
					});
				} else {
					comments.eq(0).html("잘못된 이메일 형식입니다");
					reId = false;
				}	
			});
			$("#joinPw").change(function() {			
				comments.eq(1).html("");
				rePw = true;
				var regPw = /^[a-zA-Z0-9]{4,}$/;
				var pwVal = $("#joinPw").val();
				if (!regPw.test(pwVal)) {
					comments.eq(1).html("4자리 이상의 비밀번호를 입력해주세요");
				 rePw = false;
				}
			});
			$("#joinPwRe").change(function() {
				comments.eq(2).html("");
				rePwRe = true;
				var pwVal = $("#joinPw").val();
				var pwReVal = $("#joinPwRe").val();
				if (pwVal != pwReVal) {
					comments.eq(2).html("비밀번호가 일치하지 않습니다");
					rePwRe = false;
				}
			});
			$("#joinName").change(function() {		
				comments.eq(3).html("");
				reName = true;
				var regName = /[가-힣]{1,}$/;
				var nameVal = $("#joinName").val();
				if (!regName.test(nameVal)) {
				comments.eq(3).html("최소 1글자 이상의 한글을 입력해주세요");
				reName = false;
				}
			}); 
			$("#joinPhone").change(function() {
				comments.eq(4).html("");
				rePhone = true;
				var regPhone=/^\d{3}-\d{3,4}-\d{4}$/;
				var phoneVal = $("#joinPhone").val();
				if(!regPhone.test(phoneVal)){
					comments.eq(4).html("연락처를 입력해주세요");
					rePhone = false;
				}
			});
			$("input[type=submit]").click(function() {
				 if(reId != true || rePw != true || rePwRe != true || reName != true || rePhone != true) {                            
		               alert("형식이 맞지 않습니다");   
		               return false;          
		         }
		});
		});
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>