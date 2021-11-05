<%@page import="table.model.vo.MakerInfo"%>
<%@page import="funding.model.vo.FundingViewTotal"%>
<%@page import="table.model.vo.Reward"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Reward r = (Reward)request.getAttribute("r");
    //ProjectBasicInfo pbi = (ProjectBasicInfo)request.getAttribute("pbi");
    //MakerInfo mi = (MakerInfo)request.getAttribute("mi");
    FundingViewTotal fvt = (FundingViewTotal)request.getAttribute("fvt");
    //int total = (Integer)request.getAttribute("total");
    //int recent = total*r.getRewardPrice(); 
  	//int target = recent/fvt.getTargetPrice()*100;
  	//long period = (long)request.getAttribute("period");
  	//String payDateS = (String)request.getAttribute("payDateS");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectQuantity</title>
<style>
.amount{
	 width: 35px;
    display: inline-block;
    text-align: center;
}
.count_btn1{
	width: 35px;
    align-self: center;
    border: 1px solid #eee;
}
.btn{
	float :right;
}
</style>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
 <c:import url="/WEB-INF/views/common/header.jsp"></c:import>
 	<div class="container">
 		<form action="/updatePayment" method="post" name="selectQuantity">
 			<legend>수량선택</legend>
 			<div class="price" style="display:block">
					<span><%=r.getRewardPrice() %></span>원
					<input  id="rewardPrice" name="rewardPrice" value="<%=r.getRewardPrice() %>">
					<input  id="amount" name="amount" value="1">
					<input  name="shippingDate" value="<%=r.getShippingDate() %>">
					<input  name="projectNo" value="<%=fvt.getProjectNo() %>"> 
					<input  name="rewardTitle" value="<%=r.getRewardTitle() %>">
				</div>
			<div class="total" >
				<span>결제금액 </span><span id="totalPrice"><%=r.getRewardPrice() %></span>원
			</div>
			<div class="subscrip">
				현재 달성률<span><%=fvt.getPercent() %>%</span> 현재 참여인원<span> <%=fvt.getTotal() %>명</span> 예상 배송일<span> <%=r.getShippingDate() %></span>
			</div>
			<div class="rewardTitle">
				<span><%=r.getRewardTitle() %></span>
			</div>
			<div class="rewardContent">
				<span>리워드 :</span><span> <%=r.getRewardContent() %></span>
			</div>
			<div class="count">
					<span>수량</span>
					<button type="button" class="count_btn1">-</button>
					<span class="amount">1</span>
					<button type="button" class="count_btn1">+</button>
			</div>
			<button type="submit" class="btn">결제하기</button>
 		</form>
 	</div>
<script>
	$(".count>button").click(function(){
		var currAmount = Number($(".amount").html()); 
		if($(this).html() == "+") { 
			$(".amount").html(++currAmount);
		}else{
			if(currAmount != 1){ 
				$(".amount").html(--currAmount);					
			}
		}
		var price = Number($(".price>span").first().html()); 
		$("#totalPrice").html(currAmount*price);
		$("#rewardPrice").val(currAmount*price);
		$("#amount").val(currAmount);
		
	});
</script>
 <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>