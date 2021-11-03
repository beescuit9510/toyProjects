<%@page import="table.model.vo.Reward"%>
<%@page import="table.model.vo.ProjectBasicInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  	int target = (Integer)request.getAttribute("target");
    String projTitle = (String)request.getAttribute("projTitle");
    String reTitle = (String)request.getAttribute("reTitle");
    String shipDate = (String)request.getAttribute("shipDate");
    String endDate = (String)request.getAttribute("endDate");
    String projStory = (String)request.getAttribute("projStory");
    String payDate = (String)request.getAttribute("payDate");
    long period = (Long)request.getAttribute("period");
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/KimMinji/preview.css">
<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>

</head>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
		<div class="p_title">
				<h1>${projTitle }</h1>
		</div>
		<hr>
			<div class="rewardImg">
				 <img  src="/img/KimMinji/image.png">
			</div>
		<div class="shopping">
				<div class="reward">
					<h4>리워드</h4>
					<span id="reTitle">${reTitle }</span>
				</div>
				<div class="reward">
					<span class="bolder">0원</span><span class="bold">펀딩 중</span>
				</div>
				<div class="reward">
					<span class="thin">달성률</span><span class="bold">0%</span><span class="comments">목표금액 ${target } 원</span> 
				</div>
				<div class="reward">
					<span class="thin">남은기간</span><span class="bold">${period }일</span><span class="comments">${endDate } 종료</span>
				</div>
				<div class="reward">
					<span class="thin">참여자</span><span class="bold">0명</span>
				</div>
				<div class="reward">
					<button id="like" class="previewBtn" >좋아요</button>
				</div>
				<div class="reward">
					<ul class="date">
						<li  ><img src="/img/KimMinji/calendar.jpg" width="50px;"></li>
						<li >${endDate }</li>
					</ul>
					<ul class="date">
						<li ><img src="/img/KimMinji/card.jpg" width="50px;"></li>
						<li >${payDate }</li>
					</ul>
					<ul class="date">
						<li ><img src="/img/KimMinji/delivary.jpg" width="50px;"></li>
						<li >${shipDate }</li>
					</ul>
				</div>
				<div class="reward">
					<br><br><br><br><br><br>
					<span id="notice">펀딩을 마치면 결제 예약 상태입니다. 종료일에 100% 이상이 달성되었을 경우에만 결제예정일에 결제가 됩니다</span>
				</div>
				<div class="reward">
					<button type ="button" class="previewBtn">펀딩하기</button>
				</div>
		</div>
		
	</div>
	<script>

	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>