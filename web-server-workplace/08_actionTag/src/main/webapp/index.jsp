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
	<h3><a class="a" href="/test1">1. test 페이지 가기</a></h3>

	<hr />
	<br />
	<%@ include file="/WEB-INF/views/test1.jsp"%>
	<jsp:include page="/WEB-INF/views/test1.jsp"/>
	
	<hr />
	<br />
	<h3><a href="/forwardTest">jsp:forword테스트</a></h3>
	<hr/>
	<h3><a href="/elTest1">eltest1(기본형)</a></h3>
	<h3><a href="/elTest2">eltest2(객체)</a></h3>
	<h3><a href="/elTest3">eltest3(list(배열도 동일))</a></h3>
	<h3><a href="/elTest4">eltest4(연산자)</a></h3>
	<hr/>
	<h3><a href="/jstlTest1">jstlTest1(JSTL 기초)</a></h3>
	<h3><a href="/jstlTest2">jstlTest2(JSTL if) 하나 검사용</a></h3>
	<h3><a href="/jstlTest3">jstlTest3(JSTL choose) else if 등 여러개</a></h3>
	<h3><a href="/jstlTest4">jstlTest4(JSTL forEach)</a></h3>
	<form action="/jstlTest5">
		<input type="text" name="data">
		<input type="submit" name="데이터전송">
	</form>
</body>
<%-- 
--%>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script>
	var hrefs = [];
	
	$(".a").click(function(){
		alert("/forward?href="+hrefs[$(this).index()]);
		location.href ="/forward?href="+hrefs[$(this).index()];
	})
	
	for(var i=0;i<$(".a").length;i++){
        hrefs.push($(".a").eq(i).attr("href"));
        $(".a").attr({"href":""});    
    }
	
</script>
</html>