<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String msg = (String)request.getAttribute("msg");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>

<style>
	.container{
		padding-top: 50px;
		text-align: center;
	}
</style>
</head>
<body>
	<div class="container">
		<div><%=msg %></div>
		<button>닫기</button>
	</div>
</body>
<script type="text/javascript" >

	$("button").click(function () {
		self.close();
	})

</script>
</html>