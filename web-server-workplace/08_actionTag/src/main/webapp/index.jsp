<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메인페이지</h1>
	<h3><a href="/test1">1. test 페이지 가기</a></h3>

	<hr />
	<br />
	<%@ include file="/WEB-INF/views/test1.jsp"%>
	<jsp:include page="/WEB-INF/views/test1.jsp"/>
	
	<hr />
	<br />
	<h3><a href="/forwardTest">jsp:forword테스트</a></h3>
	<h3><a href="/elTest1">eltest1(기본형)</a></h3>
	<h3><a href="/elTest2">eltest2(객체)</a></h3>
	<h3><a href="/elTest3">eltest3(list(배열도 동일))</a></h3>
	<h3><a href="/elTest4">eltest4(연산자)</a></h3>
</body>


<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script>
	$(".a").click(function(){
		var href = $(this).attr("href");
		console.log(href);
		$("<a href='/forward?href="+href+"'").click();
	})
</script>
</html>