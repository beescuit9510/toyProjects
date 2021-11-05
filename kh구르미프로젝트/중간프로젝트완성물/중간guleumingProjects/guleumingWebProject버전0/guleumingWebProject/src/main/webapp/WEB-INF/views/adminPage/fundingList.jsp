<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--  JSTL 확장 c 태그 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta charset="UTF-8">
<title>구르밍</title>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>

	<!-- <div class="navi">
        <a href="#" id="active-navi" >펀딩한 프로젝트</a >
        <a href="#" >제작한 프로젝트</a >
        <a href="#" >관심 펀더 및 펀더</a >
        <a href="#" >설정</a >
    </div> -->
	<div class="container">
		 <div class="title">펀딩 목록</div>
	<!--	<div class="navi-wrap">
			<div class="navi">
				<a href="#" id="active-navi">펀딩 목록</a> <a href="#">제작한 프로젝트</a>
				<a href="#">관심 펀더 및 펀더</a> <a href="#">설정</a>
			</div>
		</div> -->
		<div class="project-wrap">
			<div class="project-row">
			<c:forEach items="${list }" var="f" varStatus="i">
				<section class="project-box moving-top">
					<div class="project-profile">
						<c:choose>
						<c:when test="${not empty f.projectTitle }">	
							<a href="/fundingView?projectNo=${f.projectNo }">${f.projectTitle }</a>
						</c:when>
						<c:otherwise>							
							<a href="/fundingView?projectNo=${f.projectNo }">구르밍</a>
						</c:otherwise>
					</c:choose>
					</div>
					<div class="rate">
						달성률 <span class="percent point"><fmt:formatNumber value= "${f.rewardPrice*f.total / f.targetPrice * 100 }" pattern = "00" />%<%-- ${f.rewardPrice*f.total / f.targetPrice * 100 }% --%></span>
					</div>
					<div class="target-price">
						목표 금액 <span><fmt:formatNumber value= "${f.targetPrice }" type="Number" />원</span>
					</div>
					<div class="buttons">
						<!-- <button class="btn_sm btn_out Payment-details"><a href="#none">결제 내역</a></button> -->
						<button class="btn_sm btn_out funding-view"><a href="/fundingView?projectNo=${f.projectNo }<%-- &cMemberNo=${sessionScope.member.cMemberNo } --%>">펀딩 보러가기</a></button>
						<button class="btn_sm btn_out funding-view"><a href="adminPageMemberList?reqPage=1&type=2">등급관리</a></button>
					</div>
				</section>
				</c:forEach>
			</div>
			
			<div id="pageNavi" >${fundingPageNavi }</div>

		</div>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
<style>
:root { -
	-main-color: #00B9CE;
}
.project-profile{
	overflow: hidden;
}
.project-box {
	width: 270px;
	height: 350px;
	border: solid 1px #EAEBED;
	margin: 10px;
	font-size: 13px;
	font-weight: 600;
	font-family: "logo";
	border-radius: 10px;
}

.project-profile>a {
	font-size: 30px;
	text-align: center;
	line-height: 200px;
	color: whitesmoke;
	font-weight: 900;
	display: inline-block;
	width: 100%;
	height: 100%;
	position: relative;
	font-family: "logo";
	
}

.project-profile>a:hover::after {
	content: "";
	width: 100%;
	height: 100%;
	background-color: rgb(0, 0, 0, 15%);
	left: 0;
	top: 0;
	position: absolute;
	z-index: 1;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}

.project-box>* {
	color: #333;
}

.project-row {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
	justify-items: center;
}

.rate>span {
	font-size: 15px;
	color: #00B9CE;
}

.project-wrap {
	width: 900px;
	display: flex;
	flex-direction: column;
	justify-self: center;
	margin: 0 auto;
}

.buttons {
	height: 50px;
	display: flex;
	justify-content: space-between;
	align-items: flex-end;
	margin: 13px;
	margin-bottom: 0px;
}

.project-box {
	position: relative;
}

.moving-top {
	transition: transform 0.4s ease;
	transform: translateY(0px);
}

.moving-top:hover {
	transform: translateY(-5px);
	box-shadow: 2px 2px 5px rgb(0, 0, 0, 15%);
	border: solid 0.5px var(- -main-color);
	box-sizing: border-box;
}

.project-box>div:not(div:last-child):not(div:first-child) {
	margin-top: 10px;
	margin-bottom: 10px;
	margin-left: 5%;
	width: 90%;
	border-bottom: solid 1px #EAEBED;
}

.project-profile {
	padding: 0;
	border: 0;
	box-sizing: border-box;
	width: 100%;
	height: 60%;
	background-color: #00B9CE;
	box-sizing: border-box;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}

/* .navi-wrap {
	display: grid;
	position: relative;
}

.navi {
	width: 100%;
	border-left: 0;
	border-right: 0;
	margin: auto auto;
	grid-column: 2;
	margin-bottom: 50px;
}

.navi>a {
	display: inline-block;
	width: 130px;
	height: 30px;
	text-align: center;
	box-sizing: border-box;
	font-size: 15px;
	font-weight: 800;
	color: #333;
	display: inline-block;
}

#active-navi {
	display: inline-block;
	color: #00B9CE;
	border-bottom: solid 5px #00B9CE;
} */

.title {
	text-align: center;
	font-size: 65px;
	margin-bottom: 50px;
	color: #00B9CE;
	font-family: "logo";
}

.readMore-div {
	display: grid;
	grid-template-columns: 3;
	
}

.readMore-div>.readMore {
	margin-top: 70px;
	grid-column: 2;
	background:white;
	border:0;
	color:#00B9CE;
	font-family: "logo";	
}
.nav_wrap{
   z-index:2;
}

.navi-wrap{
   font-family: "logo";
}

/* #pageNavi{
    margin-bottom: 30px;
}
.pagination{
	width:210px;
	overflow: hidden;
	text-align: center;
	margin: 0 auto;
}
.page-item{
	width: 30px;
	float: left;
} */
#pageNavi{
	display: flex;
    width: 420px;
    margin: 40px auto 20px auto;
    justify-content: center;
}
.page-item{
	width:33px;
	height:33px;
	/* line-height:30px; */
	padding:0px;
	text-align:center;
	margin: auto 5px;
	float: left;
	border: 3px solid transparent;
}
.pagination > .active{
	border: 3px solid #00B9CE;
    border-radius: 100%;
}
.Payment-details>a{
	color: #00B9CE;
}
.Payment-details:hover>a{
	color: white;
}
.funding-view>a{
	color: #00B9CE;
}
.funding-view:hover>a{
	color: white;
}
#pageNavi{
	margin-top: 50px;
}
</style>
</html>
