<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/css/Yunyoung/mypage.css">
<title>Insert title here</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
	<div class="container">
		<div class="title">펀딩한 프로젝트</div>
		<div class="navi-wrap">
			<div class="navi">
				<a href="/FundedFundingList" id="active-navi">펀딩한 프로젝트</a> 
				<a href="/MyOwnProject">제작한 프로젝트</a>
				<a href="/LikeList">관심 펀더 및 펀더</a>
				<a href="/Mypage">설정</a>
			</div>
		</div>
		<div class="project-wrap">
			<div class="project-row">
				<section class="project-box moving-top">
					<div class="project-profile">
						<a href="#">구르밍</a>
					</div>
					<div class="rate">
						달성률 <span class="percent point">0%</span>
					</div>
					<div class="target-price">
						목표 금액 <span>0</span>
					</div>
					<div class="buttons">
						<button class="btn_sm btn_out involved-members">참가된 회원</button>
						<button class="btn_sm btn_out funding-comments">문의 댓글</button>
					</div>
				</section>
				<section class="project-box moving-top">
					<div class="project-profile"></div>
					<div class="rate">
						달성률 <span>0 %</span>
					</div>
					<div class="target-price">
						목표 금액 <span>0</span>
					</div>
					<div class="buttons">
						<button class="btn_sm btn_out involved-members">참가된 회원</button>
						<button class="btn_sm btn_out funding-comments">문의 댓글</button>
					</div>
				</section>
				<section class="project-box moving-top">
					<div class="project-profile"></div>
					<div class="rate">
						달성률 <span>0 %</span>
					</div>
					<div class="target-price">
						목표 금액 <span>0</span>
					</div>
					<div class="buttons">
						<button class="btn_sm btn_out involved-members">참가된 회원</button>
						<button class="btn_sm btn_out funding-comments">문의 댓글</button>
					</div>
				</section>
			</div>
			<div class="project-row">
				<section class="project-box moving-top">
					<div class="project-profile"></div>
					<div class="rate">
						달성률 <span>0 %</span>
					</div>
					<div class="target-price">
						목표 금액 <span>0</span>
					</div>
					<div class="buttons">
						<button class="btn_sm btn_out involved-members">참가된 회원</button>
						<button class="btn_sm btn_out funding-comments">문의 댓글</button>
					</div>
				</section>
				<section class="project-box moving-top">
					<div class="project-profile"></div>
					<div class="rate">
						달성률 <span>0 %</span>
					</div>
					<div class="target-price">
						목표 금액 <span>0</span>
					</div>
					<div class="buttons">
						<button class="btn_sm btn_out involved-members">참가된 회원</button>
						<button class="btn_sm btn_out funding-comments">문의 댓글</button>
					</div>
				</section>
			</div>
			<div class="readMore-div">
				<button class="readMore btn btn-sm">더 보기</button>
			</div>

		</div>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>