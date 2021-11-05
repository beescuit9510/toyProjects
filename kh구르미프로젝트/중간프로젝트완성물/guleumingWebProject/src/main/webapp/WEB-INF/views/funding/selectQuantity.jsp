<%@page import="table.model.vo.MakerInfo"%>
<%@page import="funding.model.vo.FundingViewTotal"%>
<%@page import="table.model.vo.Reward"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/css/LimHansol/selectQuantity.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구르밍</title>
<style>
</style>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
 <c:import url="/WEB-INF/views/common/header.jsp"></c:import>
 	<div class="container">
 		<form action="/updatePayment" method="post" name="selectQuantity" class="form-data">
 			<legend>수량선택</legend>
 			<div class="price" style="display:block">
					<input  id="rewardPrice" type="hidden" name="rewardPrice" value="${fvt.rewardPrice }">
					<input  id="amount" type="hidden" name="amount" value="1">
					<input  name="shippingDate" type="hidden" value="${fvt.shippingDate }">
					<input  name="projectNo" type="hidden" value="${fvt.projectNo }"> 
					<input  name="rewardTitle" type="hidden" value="${fvt.rewardTitle }">
				</div>
		<div class="quantity-data">
					<div class="reward-price">
					<span>${fvt.rewardPrice }</span>원
					</div>
					<div class="total" >
						<span>총 결제금액 </span><span id="totalPrice">${fvt.rewardPrice }</span>원
					</div>
					<div class="subscrip">
						현재 달성률<span> ${fvt.percent}% | </span> 현재 참여인원<span> ${fvt.total }명 |</span> 예상 배송일<span>${fvt.shippingDate }</span>
					</div>
					<div class="rewardTitle">
						<span>${fvt.rewardTitle }</span>
					</div>
					<div class="rewardContent">
						<span>리워드 :</span><span>${fvt.rewardContent }</span>
					</div>
					<div class="count">
							<span>수량</span>
							<button type="button" class="count_btn1">-</button>
							<span class="amount">1</span>
							<button type="button" class="count_btn1">+</button>
					</div>
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
		var price = Number($(".reward-price>span").first().html()); 
		$("#totalPrice").html(currAmount*price);
		$("#rewardPrice").val(currAmount*price);
		$("#amount").val(currAmount);
		
	});
</script>
 <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>