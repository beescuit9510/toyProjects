<%@page import="funding.model.vo.FundingCommentTotal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="funding.model.vo.FundingViewTotal"%>
<%@page import="table.model.vo.MakerInfo"%>
<%@page import="table.model.vo.ProjectBasicInfo"%>
<%@page import="table.model.vo.Reward"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //Reward r = (Reward)request.getAttribute("r");
    //FundingViewTotal fvt = (FundingViewTotal)request.getAttribute("fvt");
    //ArrayList<FundingCommentTotal> commentList = (ArrayList<FundingCommentTotal>)request.getAttribute("commentList");
    %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/css/LimHansol/fundingView.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구르밍</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<style>
.reBtn{
	outline: none;
    background: none;
    text-decoration: none;
    cursor: pointer;
    outline: none;
    display: inline-block;
    width: 55px;
    height: 35px;
    font-size: 15px;
    padding: 3px 10px;
    border: 1px solid #00B9CE;
    border-radius: 5px;
    color: #00B9CE;
    margin-right: 2px;
}
.recBtn{
	margin-right:0;
	color: #ff0071;
    border: 1px solid #ff0071;
}
</style>
<body>
 <c:import url="/WEB-INF/views/common/header.jsp"></c:import>
 	<div class="container" style="margin-bottom:50px;">
		<div class="p_title">
				<span class="sm_category">${fvt.fundingCategory}</span>
				<span class="big_title">${fvt.projectTitle }</span>
		</div>
		<div class="shopping_top">
			<div class="rewardImg">
				<img  src="/upload/project/${fvt.filepath}" >
			</div>
			<div class="shopping">			
					<%-- <div class="reward">
						<span class="bold">${fvt.rewardTitle }</span>
					</div> --%>
					<div class="recent info_line">
						<span class="large">${fvt.totalPrice }원 </span> 펀딩 모집 중
					</div>
					<div class="recent info_line">
						<span class="gray">리워드 금액</span><span class="pro_point">${fvt.rewardPrice }</span>원
					</div>
					<div class="target info_line">
						<span class="gray">달성률</span><span class="pro_point">${fvt.percent }%</span><span class="pro_two">목표금액 : ${fvt.targetPrice }원</span>
					</div>
					<div class="callender info_line">
						 <c:choose>
			               <c:when test="${fvt.period ge 0 }">
			                  <span class="gray">남은기간</span><span class="pro_point">${fvt.period }일</span><span class="pro_two">마감일 : ${fvt.endDate } 종료</span>
			               </c:when>
			               <c:when test="${fvt.period le 0 }">
			                  <span class="gray">남은기간</span><span class="pro_point point_pk">마감된 펀딩</span><span class="gray">${fvt.endDate } 종료</span>
			               </c:when>
			            </c:choose>
					</div>
					<div class="Partici info_line">
						<span class="gray">참여자</span><span class="pro_point">${fvt.cnt }</span><span class="pro_two">명</span>
					</div>
					<div class="b_name info_line">
					<span class="gray">제작자</span><span class="pro_name">${fvt.businessName }</span>
					</div>
					<div class="like border_a">
					<c:if test="${not empty sessionScope.member }">
						<c:choose>
							<c:when test="${fvt.likeCheck eq 'like' }">
								<div class="heartReverse"  id="likeCancel"></div>
	                        	<p><b>${sessionScope.member.cName }님을 포함한 ${fvt.likeCount}명이 관심</b>을 갖고 있습니다.</p>
							</c:when>
							<c:when test="${fvt.likeCheck eq 'no' }">
								<div class="heart"  id="like"></div>
	                        	<p><b>${fvt.likeCount}명이 관심</b>을 갖고 있습니다.</p>
							</c:when>
						</c:choose>
					</c:if>	
					<c:if test="${empty sessionScope.member }">
								<div class="heart"  onclick="goLogin();"></div>
	                        	<p><b>${fvt.likeCount}명이 관심</b>을 갖고 있습니다.</p>
					</c:if>
					</div>
					<div class="dateinfo">
						<ul class="date">
							<li><img src="/img/LimHansol/fundingEndDay.jpg"></li>
							<li><span>펀딩 종료일</span></li>
							<li><span class="point">${fvt.endDate }</span></li>
						</ul>
						<ul class="date">
							<li><img src="/img/LimHansol/paymentSchedule.jpg"></li>
							<li><span>결제 예정일</span></li>
							<li><span class="point">${fvt.payDateS }</span></li>
						</ul>
						<ul class="date">
							<li><img src="/img/LimHansol/deliverySchedule.jpg"></li>
							<li><span>발송 예정일</span></li>
							<li><span class="point">${fvt.shippingDate }</span></li>
						</ul>
					</div>
					<div class="pay">
					<c:choose>
					<c:when test="${not empty sessionScope.member }">
					<form method="post" action="/selectQuantity">
						<input type="hidden" name="projectNo" value="${fvt.projectNo }">
						<input type="hidden" name="cMemberNo" value="${sessionScope.member.cMemberNo }">
						<button type ="submit" id="payment" class="shoppingbtn btn btn_100 btn_out btn_rx">펀딩하기</button>
					</form>
					</c:when>
					<c:when test="${empty sessionScope.member }">
						<c:if test="${fvt.period ge 0 }">
							<button onclick="goLogin();" class="shoppingbtn btn btn_100 btn_out btn_rx">펀딩하기</button>
						</c:if>
						<c:if test="${fvt.period  le 0 }">
							<button class="shoppingbtn end_btn" onclick="endFunding();">마감된 펀딩 입니다</button>
						</c:if>
					</c:when>
					</c:choose>	
					</div>
					<div class="explain">
						<span class="grayLong">펀딩을 마치면 결제 예약 상태입니다. 종료일에 100% 이상이 달성되었을 경우에만 결제예정일에 결제가 됩니다</span>
					</div>
			</div>
		</div>
		
		<div class="content">
		<div class="select_menu">
		<div class="select">

			<a id="story" onclick="viewStory();">스토리</a><a id="comment" onclick="viewComment();">댓글</a><a id="refund" onclick="viewRefund();">환불규정안내</a>

			<c:if test="${showComment == true }">
				<script>
				$(document).ready(function(){
					$("#comment").click();
					//${showComment = false}
				});
				</script>
			</c:if>
			
		</div>
		</div>
			<div class="story" style="display:block">
				<span>${fvt.projectStory }</span>
			</div>
			<div class="comment" style="display:none">
			<div class="comment_notice">
				<h1>여러분의 한마디가<br>
				진행자에게 큰 힘이 됩니다</h1>
				<h3>댓글 작성 시 유의사항</h3>
				<p>프로젝트와 관계없는 글, 광고성, 욕설, 비방, 도배 등의 글은 내부 검토 후 삭제됩니다.</p>
				<p>펀딩 관련 문의 및 배송문의는 프로젝트 진행자에게 문의하시면 정확한 답변을 받을 수 있습니다.</p>
			</div>
			<c:if test="${not empty sessionScope.member && sessionScope.member.cLevel eq 2 }">
			<div class="inputCommentBox">
				<form action="/fundingCommentWrite" method="post">
					<ul>
						<li>
							<span>질문</span>
						</li>
						<li>
							<input type="hidden" name="commentLevel" id="commentLevel" value="1">
							<input type="hidden" name="commentWriter"   id="commentWriter" value="${sessionScope.member.cMemberNo }">
							<input type="hidden" name="projectRefNo"  id="projectRefNo" value="${fvt.projectNo }">
							<input type="hidden" name="commentRefNo"  id="commentRefNo" value="0">
							<textarea name="commentContent" class="commentContent"></textarea>
						</li>
						<li>
							<button type="submit" id="commentWrite"class="btn commentsBtn">댓글등록</button>
						</li>
					</ul>
				</form>
			</div>
		</c:if>		
				<c:forEach items="${commentList }" var="cl">
				<c:if test="${cl.commentLevel == 1}">
					<ul class="comments" id="comment${cl.commentNo }">
						<li class="comm_info">
							<p class="comm_name">${cl.cName }</p>
							<p class="comm_date">${cl.writeDate }</p>
						</li>
						<li class="comm_cont">
							<p>${cl.commentContentBr}</p>
							<textarea name="updateCommentContent" class="form-control" style="display:none;">${cl.commentContent }</textarea>
							<p class="commentsBtn">
							<c:if test="${not empty sessionScope.member }">
								<c:if test="${sessionScope.member.cMemberNo eq cl.commentWriter }">
									<a href="javascript:void(0)" 
									onclick="modifyComment(this,'${cl.commentNo }','${cl.projectRefNo }');">수정</a>
									<a href="javascript:void(0)"
									onclick="deleteComment(this,'${cl.commentNo }','${cl.projectRefNo }');">삭제</a>
								</c:if>
								<c:if test="${sessionScope.member.cLevel eq 3 && sessionScope.member.cMemberNo eq fvt.businessNo }">
									<a href="javascript:void(0)"  class="recShow">답글달기</a>
								</c:if>
							</c:if>	
							</p>
								<form action="/fundingCommentWrite" method="get" class="recoment">
									<input type="hidden" name="commentLevel" id="commentLevel" value="2">
									<input type="hidden" name="commentWriter" id="commentWriter" value="${sessionScope.member.cMemberNo }">
									<input type="hidden" name="projectRefNo" id="projectRefNo" value="${cl.projectRefNo }">
									<input type="hidden" name="commentRefNo" id="commentRefNo" value="${cl.commentNo }">
									<textarea name="commentContent" id="commentContent" class="form-control"></textarea>
								<div>
									<button type="submit" class="reBtn">등록</button>
									<button type="reset" class="reBtn recBtn">취소</button>
								</div>
								</form>				
						</li>
					</ul>
				</c:if>	
				<c:forEach items="${commentList }" var="cll">					
					<c:if test="${cll.commentLevel eq 2 && cl.commentNo eq cll.commentRefNo }">
						<div class="replay-dasi"></div>
						<ul class="recomments" id="comment${cll.commentNo }">
							<li class="comm_info">
								<p class="comm_pro">제작자</p>
								<p class="comm_name">${cll.cName }</p>
								<p class="comm_date">${cll.writeDate }</p>
							</li>
							<li class="comm_cont">
								<p>${cll.commentContentBr }</p>
								<textarea name="updateCommentContent" class="form-control" style="display:none;">${cll.commentContent }</textarea>
								<div class="commentsBtn">
									<c:if test="${not empty sessionScope.member}"> 
										<c:if test="${sessionScope.member.cMemberNo eq cll.commentWriter }">
										<a href="javascript:void(0)"
										onclick="modifyComment(this,'${cll.commentNo }','${cll.projectRefNo }');">답글수정</a>
										<a href="javascript:void(0)"
										onclick="deleteComment(this,'${cll.commentNo }','${cll.projectRefNo }');" style="color:#ff0071; border:1px solid #ff0071;">삭제</a>
										</c:if>									
									</c:if>									
								</div>
							</li>
						</ul>
					</c:if>
				</c:forEach>			
			</c:forEach>			
			</div>
			<div class="refund" style="display:none">
				<div class="refund_text">
					<h1>프로젝트 진행 관련<br>안내사항 입니다.</h1>
					<div class="project_date">
						<h3 class="refund_title">펀딩 종료일</h3>
						<h3 class="refund_date">${fvt.endDate }</h3>
						<h3 class="refund_title">결제 예정일</h3>
						<h3 class="refund_date">${fvt.payDateS }</h3>
						<h3 class="refund_title">발송 예정일</h3>
						<h3 class="refund_date">${fvt.shippingDate }</h3>
					</div>
				</div>
				<span>${fvt.cancelPolicyBr }</span>
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
	$(".recBtn").click(function(){
		var idx = $(".recBtn").index(this);
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
	function goLogin(){
		if(confirm("로그인이 필요 합니다 로그인 하시겠습니까?")){
			location.href="/login"
		}
	}
	function endFunding(){
		alert("아쉽게도 마감된 펀딩입니다");
	}
	$("#like").click(function(){//id -> like 눌렀을때 실행할 함수
		var projectNo = ${fvt.projectNo };
		console.log(projectNo);
		var cMemberNo = ${sessionScope.member.cMemberNo }
		console.log(cMemberNo);
		$.ajax({
			url : "/fundingLikeUp",
			data : {projectNo:projectNo,
					cMemberNo:cMemberNo
					},
			type : "post",
			success : function(data){
				if(data > 0){
					alert("감사합니다~~");
					document.location.reload();
				}else{
					alert("좋아요 업데이트 실패");
					
				}
			}
		});
	});
	$("#likeCancel").click(function(){//id -> likeCancel 눌렀을때 실행할 함수
		var projectNo = ${fvt.projectNo };
		console.log(projectNo);
		var cMemberNo = ${sessionScope.member.cMemberNo }
		console.log(cMemberNo);
		$.ajax({
			url : "/fundingLikeCancel",
			data : {projectNo:projectNo,
					cMemberNo:cMemberNo
					},
			type : "post",
			success : function(data){
				if(data > 0){
					alert("한번만더 생각해보세요ㅠㅠ");
					document.location.reload();
				}else{
					alert("좋아요 삭제 실패");
				}
			}
		});
	});
	
</script>
 <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>