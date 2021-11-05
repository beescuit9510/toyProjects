<%@page import="funding.model.vo.FundingCommentTotal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="funding.model.vo.FundingViewTotal"%>
<%@page import="table.model.vo.MakerInfo"%>
<%@page import="table.model.vo.ProjectBasicInfo"%>
<%@page import="table.model.vo.Reward"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Reward r = (Reward)request.getAttribute("r");
    FundingViewTotal fvt = (FundingViewTotal)request.getAttribute("fvt");
    ArrayList<FundingCommentTotal> commentList = (ArrayList<FundingCommentTotal>)request.getAttribute("commentList");
    %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>funding</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<style>
.date {
        width: 50px;
        height: 70px;
        margin-left: 60px;
        list-style: none;
        float: left;
        padding: 0;
        /*border-radius: 10px;*/
        box-shadow :0px 2px 0px 0px rgb(214, 212, 212);
    }
    .modal-wrap{
            /*현재화면크그에 꽉찬 div 크기설정*/
            width: 100vw;
            height: 100vh;
            background-color: rgb(0,0,0,0.5);
            position :absolute;
            top: 0;
            left: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .modal{
            display: none;
            background-color: white;
            width: 40vw;
            height: 40vh;
        }
        .modal-top>h1{
            text-align: center;
            
        }
        .modal-content{
            display: block;
            outline: none;
            margin: 20px auto;
            width: 80%;
            height: 30px;
            border:1px solid #cccccc;
        }
        .rewardImg{
            width: 750px;
            float: left;
        }
        .shopping{
            width: 450px;
            float: left;
        }
</style>
<body>
 <c:import url="/WEB-INF/views/common/header.jsp"></c:import>
 	<div class="container">
		<div class="p_title">
				<span><%=fvt.getProjectTitle()%></span>
		</div>
		<hr>
			<div class="rewardImg">
				<img  src="/img/LimHansol/딸기맛 쿠키.png">
			</div>
		<div class="shopping">
				<div class="reward">
					<h4>리워드</h4>
					<span><%=r.getRewardTitle() %></span>
				</div>
				<div class="recent">
					<span id="bold"><%=fvt.getTotalPrice() %></span>원
				</div>
				<div class="target">
					달성률<span id="bold"><%=fvt.getPercent() %></span>%<span><%=fvt.getTargetPrice() %></span>원
				</div>
				<div class="callender">
					남은기간<span id="dDay"></span><%=fvt.getPeriod() %>일<span id="endDate"><%=fvt.getEndDate() %></span>
				</div>
				<div class="Partici">
					참여자<span id="bold"><%=fvt.getTotal() %></span>명
				</div>
				<div class="b_name">
					<span><%=fvt.getBusinessName() %></span>
					<button id="like" class="btn" value="<%=fvt.getProjectNo()%>">좋아요</button>
				</div>
				<div class="dateinfo">
					<ul class="date">
						<li>이미지구해야 되는데</li>
						<li><%=fvt.getEndDate() %></li>
					</ul>
					<ul class="date">
						<li>이미지구해야 되는데</li>
						<li><%=fvt.getPayDateS() %></li>
					</ul>
					<ul class="date">
						<li>이미지구해야 되는데</li>
						<li><%=r.getShippingDate() %></li>
					</ul>
				</div>
				<div class="explain">
					<br><br><br><br><br><br>
					<span>/펀딩을 마치면 결제 예약 상태입니다. 종료일에 100% 이상이 달성되었을 경우에만 결제예정일에 결제가 됩니다/</span>
				</div>
				<div class="pay">
				<form method="post" action="/selectQuantity?projectNo=<%=fvt.getProjectNo()%>">
					<button type ="submit" id="payment" class="btn">펀딩하기</button>
				</form>	
				</div>
		</div>
		<div class="select_menu">
		<div class="select">
		<br><br>
			<hr>
			<a id="story">스토리</a><a id="comment">댓글</a><a id="refund">환불규정안내</a>
			<hr>
		</div>
		</div>
		<div class="content">
			<div class="story" style="display:none">
				<span><%=fvt.getProjectStory() %></span>
			</div>
			<div class="comment" style="display:block">
				<span>댓글~~</span>	
			</div>
			<div class="information" style="display:none">
				info
			</div>
		</div>
	</div>
<script>
</script>
 <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>