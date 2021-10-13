<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제창</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<h3>결제모듈</h3>
		<hr>
		<div class="shopping">
			<div>
				<img id="goods" src="/img/mintChoco.jpg">
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
					<div>총
						<span id="totalPrice">2000</span>원
					</div>
					<button id="payment" class="btn btn-outline-danger btn-block">결제하기</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>