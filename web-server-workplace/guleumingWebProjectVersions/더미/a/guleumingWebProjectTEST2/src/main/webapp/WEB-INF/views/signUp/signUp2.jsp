<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
  int memNo = (Integer)request.getAttribute("memNo");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<form action="/joinFrm2" method="post">
		<fieldset>
			<input type="hidden" id="busiNo" name="busiNo" value="${memNo }">
			<div class="join-form">
			<label for="busiName">법인명</label> 
			<input type="text" id="busiName"	name="busiName" >
			</div>
			<div class="join-form">
			<label for="busiCode">사업자 등록번호</label>
			 <input type="text" id="busiCode"	name="busiCode">
			</div>
			<div class="join-form">
			<label for="managerName">담당자명</label>
			 <input type="text"	id="managerName" name="managerName">
			 </div>
			<input type="submit" value="회원가입 " name="joinSub" onclick="return clickTest();">
		</fieldset>
	</form>
	</div>
	</div>
	<script>
	$(function() {
		var nameVal = $("#busiName").val();
		var codeVal = $("#busiCode").val();
		var managerVal = $("#managerName").val();
		$("input[type=submit]").click(function() {
			if(nameVal==null && codeVal==null && managerVal==null){
				 alert("빈 칸을 입력해주세요");    
	               return false;   
			}
		}
	});
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>