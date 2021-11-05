<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int rewardPrice = (Integer)request.getAttribute("rewardPrice");
    int amount =(Integer)request.getAttribute("amount");
    String shippingDate = (String)request.getAttribute("shippingDate");
    int projectNo = (Integer)request.getAttribute("projectNo");
    String rewardTitle = (String)request.getAttribute("rewardTitle");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/css/LimHansol/payment.css">
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
 <c:import url="/WEB-INF/views/common/header.jsp"></c:import>
 <div class="container">
 	<h1>결제 페이지</h1><br>
	<div class="infoTable">
		<table border="1" class ="info" style="width:100%;">
			<tr class="table-primary">
				<th>결제할 금액</th>
				<th>리워드 명</th>
				<th>수량</th>
				<th>예상 배송일</th>
			</tr>
			<tr class="table-light">
				<td><%=rewardPrice %>원</td>
				<td><%=rewardTitle %></td>
				<td><%=amount %>ea</td>
				<td><%=shippingDate %></td>
			</tr>
		</table><br>
	</div>
	<form enctype="multipart/form-data" method="post" id="data" name="data" >
		
			<h2>배송 정보 입력</h2><br>
			<div class="backData" style="display:none">
				<input type="hidden" id="rewardPrice" name="rewardPrice" value="<%=rewardPrice %>">
				<input type="hidden" id="amount" name="amount" value="<%=amount%>">
				<input type="hidden" name="shippingDate" value="<%=shippingDate %>">
				<input type="hidden" name="projectNo" value="<%=projectNo %>"> 
				<input type="hidden" name="rewardTitle" value="<%=rewardTitle %>">
			</div>
			<div class="receiveName">
				<label for="receiveName">리워드 수령인</label>
				<input class="input_01" type="text" name="receiveName" id="receiveName"><br>
			</div>
			<div class="receivePhone">
				<label  for="receivePhone">수령인 전화번호</label>
				<input class="input_01" type="text" name="receivePhone" id="receivePhone" class="form-control"><br>
			</div>
			<div class="receiveAddr">
				<label  for="receiveAddr">주소</label>
				<button type="button" onclick="searchAddr();" class="btn address-btn">주소검색</button><br>
				<input class="input_01" type="text" id="postCode" name="postCode"  placeholder="우편번호" readonly><br>
				<input class="input_01" type="text" id="roadAddr" name="roadAddr"  placeholder="도로명주소">	<br>
				<input class="input_01" type="text" id="detailAddr" name="detalAddr"  placeholder="상세주소">	<br>
			</div>
		
		</form>
		<button id="payment" class="btn">결제하기</button>
</div>
<script>
	function searchAddr(){
		 new daum.Postcode({
		        oncomplete: function(data) {
		            $("#postCode").val(data.zonecode);
		            $("#roadAddr").val(data.roadAddress);
		            $("#detailaddr").focus;
		        }
		    }).open();
	}
	$("#payment").click(function(){
		var price = $("#rewardPrice").val(); // 현재 가격 
		price = 100;
		var postCode = $("#postCode").val();
		var roadAddr = $("#roadAddr").val();
		var detailAddr = $("#detailAddr").val();
		var d = new Date(); // Date 객체 생성
		var date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds(); 
		//Date 객체로 고유식별 번호 생성
		console.log(date);
		console.log(price);
		IMP.init("imp28965926"); //결제 API 사용을 위한 가맹점 식별코드 입력
		IMP.request_pay({
			merchant_uid : date,    // 거래 아이디
			name : "구루미펀딩",     //결제이름설정
			amount : price,
			buyer_email : "limhanhiro@gmail.com", // 구매자 이메일
			buyer_name : "hiro",   			//구매자 이름
			buyer_phone : "010-8827-2821",	//구매자 전화번호
			buyer_addr : roadAddr,	//구매자 주소
			buyer_postcode : postCode		//구매자 우편번호
			
		},function(rsp){ // rsp 는 결재 결과
			if(rsp.success){
				var roadAddr = $("#roadAddr").val();
				var detailAddr = $("#detailAddr").val();
				var receiveName = $("#receiveName").val();
				var receivePhone = $("#receivePhone").val();
				var  quantity = <%=amount %>
				var projectNo = <%=projectNo %>
				 $.ajax({
			            url: "/payment", // 예: https://www.myservice.com/payments/complete
			            method: "POST",
			            //headers: { "Content-Type": "application/json" },
			            data: {
			                imp_uid: rsp.imp_uid,
			                merchant_uid: rsp.merchant_uid,
			                roadAddr:roadAddr,
			                detailAddr:detailAddr,
			                receiveName:receiveName,
			                receivePhone:receivePhone,
			                quantity:quantity,
			                projectNo:projectNo,
			            },
			            success : function(){//비동기 요청 성공시 수행
						}
				 });
				alert("결제성공");
				console.log("카드승인번호:"+rsp.apply_num);
				location.href="/fundingView?projectNo="+${projectNo };
			}else{
				alert("결제실패");
			}
		});
	});
</script>
 <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>