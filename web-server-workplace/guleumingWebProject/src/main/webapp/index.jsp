<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--  JSTL 확장 c 태그 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<title>구르밍</title>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
	<div class="container">
		메인작성해주실 분이 작성할 부분
		
	</div>
	<a class="a" href="/adminPage/funderList">1</a>
	<a class="a" href="/adminPage/fundingList">2</a>
	<a class="a" href="/mypage/funder/fundingList">3</a>
	<a class="a" href="/mypage/funder/fundingView">4</a>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script>
	var hrefs = [];
	
	$(".a").click(function(){
		var a =$("<form action='/forward' method='get'></form>").submit();
		a.append("<input type='text' name='href' value="+hrefs[$(".a").index(this)]+">");
		a.submit();
	//	location.href ="/forward?href="+hrefs[$(".a").index(this)];
	})
	
	
	for(var i=0;i<$(".a").length;i++){
        hrefs.push($(".a").eq(i).attr("href"));
    }
    
	$(".a").removeAttr("href"); 

</script>
</html>
