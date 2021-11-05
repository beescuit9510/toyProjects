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
        .comments,.recomments{
	list-style-type: none;
	padding : 0;
	display: flex;
	background-color: #f2f2f2;
}
.comments>li>p,.recomments>li>p{
	margin: 0;
}
.comments>li:first-child{
	display: flex;
	flex-flow:column;
	justify-content: flex-start;
	align-items: center;
	width:15%;
	padding : 5px 0px 5px 0px;
}

.comments>li:last-child{
	padding : 5px 0px 5px 0px;
	width : 85%;
	display:flex;
	flex-flow:column;
	justify-content: space-between;	
	
}
.commentsBtn{	
	text-align: right;	
}
.recoment{
	display: none;	
	justify-content: space-around;
}
.recoment>textarea{
	width: 85%;
	resize: none;
}
textarea.form-control{
	height:100%;
	resize:none;
}
.recoment>div{
	width:10%;
}
.recomments>li:first-child{
	display: flex;	
	justify-content: center;
	align-items: center;
	width:15%;
	padding : 5px 0px 5px 0px;
}
.recomments>li:nth-child(2){
	display: flex;
	flex-flow:column;	
	justify-content: center;
	align-items: center;
	width:10%;
	padding : 5px 0px 5px 0px;
}
.recomments>li:last-child{
	padding : 5px 0px 5px 0px;
	width : 75%;
	display:flex;
	flex-flow:column;
	justify-content: space-between;	
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
			<a id="story" onclick="viewStory();">스토리</a><a id="comment" onclick="viewComment();">댓글</a><a id="refund" onclick="viewRefund();">환불규정안내</a>
			
			<c:if test="${showComment == true }">
				<script>
				$(document).ready(function(){
					$("#comment").click();
					//${showComment = false}
				});
				</script>
			</c:if>
			<hr>
		</div>
		</div>
		<div class="content">
			<div class="story" style="display:block">
				<span><%=fvt.getProjectStory() %></span>
			</div>
			<div class="comment" style="display:none">
			<c:if test="${not empty sessionScope.member && sessionScope.member.cLevel eq 1 }">
			<div class="inputCommentBox">
				<form action="/fundingCommentWrite" method="post">
					<ul>
						<li>
							<input type="hidden" name="commentLevel" id="commentLevel" value="1">
							<input type="hidden" name="commentWriter"   id="commentWriter" value="${sessionScope.member.cMemberNo }">
							<input type="hidden" name="projectRefNo"  id="projectRefNo" value="${fvt.projectNo }">
							<input type="hidden" name="commentRefNo"  id="commentRefNo" value="0">
							<textarea name="commentContent" class="form-control"></textarea>
						</li>
						<li>
							<button type="submit" id="commentWrite"class="btn btn-primary btn-lg btn-block">댓글등록</button>
						</li>
					</ul>
				</form>
			</div>
		</c:if>		
				<c:forEach items="${commentList }" var="cl">
				<c:if test="${cl.commentLevel == 1}">
					<ul class="comments" id="comment${cl.commentNo }">
						<li>
							<p>${cl.cName }</p>
							<p>${cl.writeDate }</p>
						</li>
						<li>
							<p>${cl.commentContent}</p>
							<textarea name="updateCommentContent" class="form-control" style="display:none;">${cl.commentContent }</textarea>
							<p class="commentsBtn">
							<c:if test="${not empty sessionScope.member }">
								<c:if test="${sessionScope.member.cMemberNo eq cl.commentWriter }">
									<a href="javascript:void(0)" 
									onclick="modifyComment(this,'${cl.commentNo }','${cl.projectRefNo }');">수정</a>
									<a href="javascript:void(0)"
									onclick="deleteComment(this,'${cl.commentNo }','${cl.projectRefNo }');">삭제</a>
								</c:if>
										<c:if test="${sessionScope.member.cLevel eq 3 }">
											<a href="javascript:void(0)"  class="recShow">답글달기</a>
												<form action="/fundingCommentWrite" method="get" class="recoment">
													<input type="hidden" name="commentLevel" id="commentLevel" value="2">
													<input type="hidden" name="commentWriter" id="commentWriter" value="${sessionScope.member.cMemberNo }">
													<input type="hidden" name="projectRefNo" id="projectRefNo" value="${cl.projectRefNo }">
													<input type="hidden" name="commentRefNo" id="commentRefNo" value="${cl.commentNo }">
													<textarea name="commentContent" id="commentContent" class="form-control"></textarea>
													<div>
														<button type="submit" class="btn ">등록</button>
														<button type="reset" class="btn ">취소</button>
													</div>
											</form>
										</c:if>							
							</c:if>
							</p>
						</li>
					</ul>
				</c:if>	
				<c:forEach items="${commentList }" var="cll">
					<c:if test="${cll.commentLevel eq 2 && cl.commentNo eq cll.commentRefNo }">
						<ul class="recomments" id="comment${cll.commentNo }">
							<li>
								<p>comment${cll.commentNo }</p>
								<p>${cll.cName }</p>
								<p>${cll.writeDate }</p>
							</li>
							<li>
								<p>${cll.commentContent }</p>
								<textarea name="updateCommentContent" class="form-control" style="display:none;">${cll.commentContent }</textarea>
								<p class="commentsBtn">
									<c:if test="${not empty sessionScope.member && sessionScope.member.cMemberNo eq cll.commentWriter}">
										<a href="javascript:void(0)"
										onclick="modifyComment(this,'${cll.commentNo }','${cll.projectRefNo }');">${cll.projectRefNo }수정</a>
										<a href="javascript:void(0)"
										onclick="deleteComment(this,'${cll.commentNo }','${cll.projectRefNo }');">삭제</a>
									</c:if>									
								</p>
							</li>
						</ul>
					</c:if>
				</c:forEach>			
			</c:forEach>			
			</div>
			<div class="refund" style="display:none">
				refund
			</div>
		</div>
	</div>
<script>
	function viewStory(){
		$(".story").css("display", "block");
		$(".comment").css("display","none");
		$(".refund").css("display","none");
	}
	function viewComment(){
		$(".story").css("display", "none");
		$(".comment").css("display","block");
		$(".refund").css("display","none");
	}
	function viewRefund(){
		$(".story").css("display", "none");
		$(".comment").css("display","none");
		$(".refund").css("display","block");
	}
	$(".recShow").click(function(){
		//몇번째 답글달기 버튼을 클릭했는지
		var idx = $(".recShow").index(this);
		$(this).hide();
		$(".recoment").eq(idx).css("display","flex");
	});
	$(".recCancel").click(function(){
		var idx = $(".recCancel").index(this);
		$(".recoment").eq(idx).css("display","none");
		$(".recShow").eq(idx).show();
	});
	function modifyComment(obj,commentNo,projectNo){
		$(obj).parent().prev().show();
		$(obj).parent().prev().prev().hide();
		$(obj).html('수정완료');
		$(obj).attr("onclick","modifyComplete(this,'"+commentNo+"','"+projectNo+"');");
		console.log(projectNo);
		console.log(commentNo)
		$(obj).next().html('취소');
		$(obj).next().attr("onclick","modifyCancel(this,'"+commentNo+"','"+projectNo+"');");
		$(obj).next().next().hide();
	}
	function modifyCancel(obj,commentNo,projectNo){
		$(obj).parent().prev().hide();
		$(obj).parent().prev().prev().show();
		$(obj).prev().html("수정");
		$(obj).prev().attr("onclick","modifyComment(this,'"+commentNo+"','"+projectNo+"');");
		$(obj).html("삭제");
		$(obj).attr("onclick","deleteComment(this,'"+commentNo+"','"+projectNo+"');");
		$(obj).next().show();
	}
	function modifyComplete(obj,commentNo,projectNo){
		//새로운 form태그를 생성
		var form = $("<form action='/fundingCommentUpdate' method='post'></form>");
		form.append($("<input type='text' name='commentNo' value='"+commentNo+"'>"));
		form.append($("<input type='text' name='projectNo' value='"+projectNo+"'>"));
		form.append($(obj).parent().prev());
		//전송할 form태그를 현재 페이지에 추가
		$("body").append(form);
		//form태그 전송
		form.submit();
		
	}
	function deleteComment(obj,commentNo,projectRefNo){
		if(confirm("댓글을 정말삭제하시겠습니까?")){
			location.href="/fundingCommentDelete?commentNo="+commentNo+"&projectRefNo="+projectRefNo;
		}
	}
</script>
 <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>