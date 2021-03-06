<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	boolean result = (Boolean)request.getAttribute("result");
    	String memberId = (String)request.getAttribute("memberId");
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
</head>
<style>
	.check-container{
		padding-top: 50px;
		text-align: center;
	}
</style>
<body>

	<div class="check-container">
		<%if(result) {%>
		[<span><%=memberId %></span>]는 사용이 가능한 아이디입니다.		
		<br><br>
		<button onclick="setMemberId('<%=memberId %>');">닫기</button>

		<%} else{ %>
		[<span id="checked"><%=memberId %></span>]는 이미 사용중인 아이디입니다.		
		<br><br>
		
		<form action="/checkId" method="post">
			<input type="text" name="checkId" placeholder="사용할 아이디를 입력하세요."><br>
			<input type="submit" value="중복검사">
		</form>
		<%} %>
	</div>

</body>
<script>
	function setMemberId(memberId) {
		// 해당 브라우저(checkId.jsp)을 오픈한 브라우저(join.jsp)의 #idChk를 찾아서 처리함!
		$("#idChk", opener.document).prev().val(memberId);
		//현재 본인창 닫기
		self.close();
	}
</script>
</html>