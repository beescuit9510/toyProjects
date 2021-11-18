<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update pw</title>
</head>
<body>
<div id="hide">${m.memberId}</div>
	<div id="form1">
		비밀번호 확인 <input id="pw1" type="password" name="memberPw"/>
		<input type="submit" id="btn1"/>
	</div>	
	<form id="form2" action="/updatePw.do?memberId=${m.memberId}" method="post" id="form2">
		변경 비밀번호 <input type="password" name="memberPw"/>
		<input type="submit"/>
	</form>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$("#hide, #form2").hide();
	
	$("#btn1").click(function(){
		$.ajax({
			url:"/checkPw.do",
			method:"post",
			data:{memberId:$("#hide").html(),memberPw:$("#pw1").val()},
			success:function(data){
				if(data == "1"){
					$("#form1").hide();
					$("#form2").show();
				}else{
					alert("다시 시도해주세요");
				}
			}
		})
	})
</script>
</html>