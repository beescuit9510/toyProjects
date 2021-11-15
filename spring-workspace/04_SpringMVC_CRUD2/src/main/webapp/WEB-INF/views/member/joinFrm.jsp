<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinFrm</title>
</head>
<body>
	<h1>회원가입</h1>
	<hr/>
	<form action="/join.do" method="post">
		<fieldset>
			아이디 : <input type="text" name="memberId"/><span id="idChk"></span><br/>
			비밀번호 : <input type="text" name="memberPw"/><span id="pwChk"></span><br/>
			이름 : <input type="text" name="memberName"/><br/>
			주소 : <input type="text" name="address"/><br/>
			<input type="submit" value="회원가입"><br/>
			<input type="reset" value="취소">
		</fieldset>
	</form>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$("[name=memberId]").keyup(function() {
		var memberId = $(this).val();
		$.ajax({
			url:"/idCheck.do",
			data:{memberId:memberId},
			method:"post",
			success:function(data){
				$("#idChk").html(data==0?"사용 가능한 아이디입니다.":"사용 불가능한 아이디입니다.");					
				$("#idChk").css("color",data==0?"blue":"red");
				
			}
		})
	})
</script>
</html>
