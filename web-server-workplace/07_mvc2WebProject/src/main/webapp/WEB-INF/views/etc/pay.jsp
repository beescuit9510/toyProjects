<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제창</title>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<h3>결제모듈</h3>
		<hr>
		<div class="shopping">
			<div>
				<img id="goods" src="/img/mintchoco.jpg">
			</div>
			<div>
				<h4>민트초코</h4>
				<div class="star">
					<img src="/img/star-on.png"> <img src="/img/star-off.png">
					<img src="/img/star-off.png"> <img src="/img/star-off.png">
					<img src="/img/star-off.png">
					<div>1.0</div>
				</div>
				<hr>
				<div class="price">
					<span>2000</span>원 <span>(1개당 가격)</span>
				</div>
				<div class="count">
					<span>수량</span>
					<button class="btn btn-secondary btn-sm">-</button>
					<span class="amount">1</span>
					<button class="btn btn-secondary btn-sm">+</button>
				</div>
				<div class="total">
					<div>
						총 <span id="totalPrice">2000</span>원
					</div>
					<button id="payment" class="btn btn-outline-danger btn-block">결제하기</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
	$(".count>button").click(function(){
		var currAmount = $(".amount").html();
		var newAmount = eval(currAmount+$(this).html()+1);
		$(".amount").html(newAmount);

		var price = $(".price>span").first().html();
		$("#totalPrice").html(price*newAmount);
		
	})
	
	
	$("#payment").click(function(){
		var price = $("#totalPrice").html();
		var d = new Date();
		var date = d.getFullYear()+""
					+(d.getMonth()+1)+""
					+d.getDate()+""
					+d.getHours()+""
					+d.getSeconds();
		
		console.log(date);
		console.log(price);
		
		/*
		
		IMP.init("imp43256257") //결제 API 사용을 위해 가맹점 식별코드 입력;
		IMP.request_pay({
			merchant_id : "상품명_"+date, 		//거래아이디
			name : "결제 테스트",					//결제 이름 설정
			amount : price,						// 결제 금액
			buyer_email :"pengsoon20@gmail.com",//구매자 이메일
			buyer_name : "변덕",					//구매자 이름
			buyer_phone : "010-2170-5555", 		//구매자 전화번호
			buyer_addr : "서울시 강서구 화곡동", 		//구매자 주소
			buyer_postcode : "488-41" 			//구매자 우편번호
		},function(rsp){
			if(rsp.success){
				//결제 성공시 로직 구현(ex. db결제정보 insert -> 사용자 화면처리);
				alert("결제성공");
				console.log("카드 승인 번호 : "+rsp.apply_num);
				
			}else{
				//결제 실패 시 로직 구현(ex. 장바구니에 저장 -> 사용자 화면 처리);
				alert("결제실패");
			}
			
		});
		*/
	});
</script>
</html>