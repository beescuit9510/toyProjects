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
    String category = (String)request.getAttribute("category");
    long period = (Long)request.getAttribute("period");
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구르밍</title>

<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/css/LimHansol/fundingView.css">
</head>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
		<div class="p_title">
			<span class="sm_category">${category}</span>
			<span class="big_title">${projTitle }</span>
		</div>
		<div class="shopping_top">
			<div class="rewardImg" style="height: 535px;padding-top:0;">
				 <img  src="/img/KimMinji/image.png">
			</div>
		<div class="shopping">
				<div class="reward info_line">
					<span class="large">0원</span>펀딩 모집 중
				</div>				
				<div class="reward info_line">
					<span class="gray">달성률</span><span class="pro_point">0%</span><span class="pro_two">목표금액 ${target } 원</span> 
				</div>
				<div class="reward info_line">
					<span class="gray">남은기간</span><span class="pro_point">${period }일</span><span class="pro_two">${endDate } 종료</span>
				</div>
				<div class="reward info_line">
					<span class="gray">참여자</span><span class="pro_point">0명</span>
				</div>
				<div class="like border_a">
					<div class="heart"  id="like"></div>
					<p><b>0명이 관심</b>을 갖고 있습니다.</p>
				</div>
				<div class="dateinfo">
					<ul class="date">
						<li><img src="/img/KimMinji/calendar.jpg"></li>
						<li><span class="point">${endDate }</span></li>
					</ul>
					<ul class="date">
						<li><img src="/img/KimMinji/card.jpg"></li>
						<li><span class="point">${payDate }</span></li>
					</ul>
					<ul class="date">
						<li><img src="/img/KimMinji/delivary.jpg"></li>
						<li><span class="point">${shipDate }</span></li>
					</ul>
				</div>
				<div class="reward info_line" style="margin-top:10px;">
					<button type ="button" class="shoppingbtn btn btn_100 btn_out btn_rx">펀딩하기</button>
				</div>
				<div class="explain info_line">
					<span class="grayLong">펀딩을 마치면 결제 예약 상태입니다. 종료일에 100% 이상이 달성되었을 경우에만 결제예정일에 결제가 됩니다</span>
				</div>
		</div>
		</div>
	</div>
	<script>

	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>