<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>회원 가입</title>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>

</head>

<body>

	<form action="/join">

		<fieldset>

			<legend>회원가입</legend>

			아이디 : <input type="text" id="id" name="id"><span
				id="chkResult"></span><br> 비밀번호 : <input type="password"
				id="pw" name="pw"><br> 이름 : <input type="text"
				id="name" name="name"><br> 나이 : <input type="text"
				id="age" name="age"><br> <input type="submit"
				value="회원가입"> <input type="reset" value="초기화">

		</fieldset>

	</form>

	<script>
		$("#id").keyup(function() {

			var userId = $("#id").val();

			$.ajax({

				url : "/checkId",

				data : {
					userId : userId
				},

				type : "post",

				success : function(data) {

					if (data == "ok") {

						$("#chkResult").html("사용가능한 아이디 입니다.");

						$("#chkResult").css("color", "blue");

					} else if (data == "ng") {

						$("#chkResult").html("이미 사용중인 아이디 입니다.");

						$("#chkResult").css("color", "red");

					}

				},

				error : function() {

					console.log("ajax 통신 실패");

				}

			});

		});
	</script>

</body>