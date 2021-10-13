<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<h3>1. EMAIL 보내기</h3>
		<form action="/sendMail" method="post">
			<input type="text" name="email" class="short form-control" placeholder="email주소입략"><br><br>
			<input type="text" name="emailTitle" class="form-control" placeholder="제목"><br><br>
			<textarea class="form-control" name="emailContent"></textarea>
			<button class="btn btn-primary btn-block" type="submit">메일전송</button>
		</form>
		<br><br>
		
		<h3>2. EMAIL 보내기</h3>
		<input type="text" id="email">
		<button class="btn btn-primary btn-block" onclick="sendMail()">인증하기</button>
		<!-- ajax로 랜덤코드 6자리 전송 -->
		<br>
		<div id="auth">
			<input type="text" name="authCode" class="short form-control" placeholder="인증코드입력">
			<button class="btn btn-primary" id="authBtn">인증하기</button>
			<span id="timeZone"></span>
			<span id="authMsg"></span>
		</div>
	</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script>
	var mailCode;
	
	function sendMail(){
		var email = $("#email").val();
		$.ajax({
			url:"/sendMail2",
			data: {email:email},
			method:"POST",
			success:function(data){
					console.log(data);
					mailCode = data;
					$("#auth").slideDown();
					authTime();
			}
		});
	};
	
	var intervalId;
	function authTime(){
		$("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>")
		intervalId = window.setInterval(function(){
			timeCount();
		}, 1000);
	}
	
	function timeCount(){
		var sec = Number($("#sec").html());
		var secToSet = sec-1<0? 59:sec-1;

		var min = Number($("#min").html());
		var minToSet = sec-1<0? min-1:min;
		
		if(minToSet == -1){
			mailCode = null;
			ClearInterval(intervalId);
		}
		
		$("#min").html(minToSet);
		$("#sec").html(secToSet<10? "0"+secToSet:secToSet);
		
	}
	
	$("#authBtn").click(function () {
		if(mailCode == null){
			$("#authMsg").html("인증시간 만료");
			$("#authMsg").css("color","red");
		
		}else{

			if($("[name=authCode]").val()==mailCode){
				$("#authMsg").html("인증성공");
				$("#authMsg").css("color","blue");
				$("#timeZone").empty();
				ClearInterval(intervalId);
		
			}else{
				$("#authMsg").html("인증코드를 확인하세요");
				$("#authMsg").css("color","red");
			};
		};
	});
</script>
</html>