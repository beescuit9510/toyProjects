<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/Yunyoung/mypage.css">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
	<div class="container">
		<div class="title">펀딩한 프로젝트</div>
		<div class="navi-wrap">
			<div class="navi">
				<a class="fundedFundings" href="/fundedFundingList">펀딩한 프로젝트</a> 
				<a class="myOwnProjects" href="/myOwnProject">제작한 프로젝트</a>
				<a class="likeList" href="/likeList">관심 펀더 및 펀더</a>
				<a class="mypage" href="/mypage">설정</a>
			</div>
		</div>
		<div class="project-wrap">
			<div class="project-row">
			</div>
			<c:if test="${totalCount == 0}">
				<div>
					<p class="totalCounEqualsZero">제작중인 프로젝트가 없습니다.<p>
				</div>
			</c:if>
			<div class="readMore-div">
				<button class="readMore btn btn-sm">더 보기</button>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
	<div id="inputs">
		<div id="totalCount">${totalCount }</div>
	</div>
	 
	 <button id="simpleModal" class="modal"> 
        <div class="modal-content">
            <span class="closeBtn">x</span>
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <p class="modal-small"><span class="start-date"></span><span> ~ </span><span class="end-date"></span></p>
                <div class="reward-info">
                    <div>
                        <p><span class="reward-title"></span><span class="funding-category"></span></p>
                        <p class="reward-content"></p>
                    </div>
                </div>
                <div class="modal-divs">
	                <div class="maker-info default-modal-css">
	                    <div>구매자 정보</div>
	                    <div>
	                        <p><span>이름</span> <span class="deposit-name"></span></p>
	                        <p><span>Email</span> <span class="q-email"></span></p>
	                        <p><span>전화번화</span> <span class="q-phone"></span></p>
	                    </div>
	                </div>
	                <div class="payment-info default-modal-css">
	                    <div>결제정보</div>
	                    <div>
	                        <p><span>주문일자</span><span class="order-date"></span></p>
	                        <p><span>주문번호</span><span class="payment-no"></span></p>
	                        <p><span>상품가격</span><span class="reward-price"></span></p>
	                        <p><span>수량</span><span class="quantity"></span></p>
	                        <p><span>총 결재금액</span><span class="total-price"></span></p>
	                    </div>
	                </div>
	                <div class="buyer-info default-modal-css">
	                    <div>받는사람 정보</div>
	                    <div>
	                        <p><span>받는사람</span><span class="receive-name"></span></p>
	                        <p><span>연락처</span><span class="receive-phone"></span></p>
	                        <p><span>받는주소</span><span class="receive-addr"></span></p>
	                    </div>
	                </div>
                </div>
            </div>
            <div class="modal-last">
	            <p class="modal-small" >예상배송일 <span class="shipping-date"></span> ~</p>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </button>
    	 <button id="simpleModal2" class="modal2"> 
        <div class="modal-content">
            <span class="closeBtn">x</span>
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <p class="modal-small"><span class="start-date">2021-12-30</span><span> ~ </span><span class="end-date">2022-12-30</span></p>
                <div class="reward-info">
                    <div>
                        <p class="reward-title">키르시 미들<span class="funding-category">푸드</span></p>
                        <p class="reward-content">상품 설명 설명...</p>
                    </div>
                </div>
                <div class="maker-info default-modal-css">
                    <div>펀딩 참여 회원 목록</div>
                    <div class="customer-list">
                    </div>
                </div>
              <div class="paging">
              </div>
            </div>
	            <p class="modal-small modal-customer-last" >예상배송일 <span class="shipping-date">2010-12-31</span> ~</p>
            <div class="modal-footer">
            </div>
        </div>
    </button>
</body>
<script>
	var fundedFundings= [];
	var totalCount = Number($("#totalCount").html());
	var currCount = 0;
	var perPost = 3;

	$(".myOwnProjects").attr("id","active-navi")
	$("#inputs").hide();
	totalCount == 0 && $(".readMore-div").hide();

	
	
	$(".readMore").click(function () {

		$.ajax({
			url:"/getMoreMyOwnProjectList",
			data:{currCount:currCount,perPost:perPost},
			method:"POST",
			success:function(ffs){
				
				start = currCount;
				currCount = currCount+perPost; 
				
				console.log(ffs);
				var html = "";
				
				for(var i=0;i<ffs.length;i++){
					var ff = ffs[i];
					fundedFundings.push(ff);
//					console.log(ff);
					
					html += "<section class='project-box moving-top'>";
					html += "<div class='project-profile'>"

					if(ff.funding.projectBasicInfo.filepath){
						html += "<a href='/한솔님 페이지 이동'>"
						html += "<div id='background-url' style='background:url('"+ff.funding.projectBasicInfo.filepath+"');></div>";
						html += "<p class='project-title'>"+ff.funding.projectBasicInfo.projectTitle+"</p>";
						html += "</a>"
					}else{
						html += "<a href='/한솔님 페이지 이동'>구르밍"
						html += "<p class='project-title'>"+ff.funding.projectBasicInfo.projectTitle+"</p>";
						html += "</a>"
					}
					
					html += "</div><div class='rate'>달성률 <span class='percent point'>"+Math.floor((ff.funding.projectBasicInfo.targetPrice)*(ff.funding.total)/(ff.funding.projectBasicInfo.targetPrice)*100)+"%</span></div>";
					html +=	"<div class='target-price'>목표 금액 <span>"+ff.funding.projectBasicInfo.targetPrice+"</span>원</div>";	
					html += "<div class='acc-price'>현재 달성 금액 <span>"+ff.funding.projectBasicInfo.targetPrice*ff.funding.total+"</span>원</div>";
					html += "<div class='buttons'>";
					html += "<button onclick='openList("+(start+i)+","+1+")' class='btn_sm btn_out involved-members'>참여 회원 보기</button>";
					html += "<button id='"+(start+i)+"' class='btn_sm btn_out funding-comments'>댓글 보기</button>";
					html += "</div>";
					html += "</section>";
				}
			
			    $(".project-wrap").find(".project-row").append(html);
			    
			    isThereMorePosts();
				
			}
		})
	})
		
	function isThereMorePosts() {
		currCount>=totalCount && $(".readMore-div").hide();
		currCount>=totalCount && $(".project-wrap").css("margin-bottom","120px");
	};
	

	function openList(n, currPage) {

		
	        $("#simpleModal").css("display","none");
	        $("#simpleModal2").css("display","block");
	        window.addEventListener('click',clickModal);
	        
	        $(".customer-list").find("p").remove();
	        $(".paging").find("p").remove();
	        $(".backward").remove();
	        

			var html = "";
			var page = ""

			if(fundedFundings[n].myOwnProjectCustomers == null){
				$(".customer-list").append($("<p>참여회원이 없습니다.</p>"));
				$(".customer-list").first().css("color","#EAEBED");
	        }
			else{
				var start = currPage==0? 0:(currPage-1)*10;
				var end = ((currPage)*10);
		        for(var i=start; i<end; i++){
		        	if(fundedFundings[n].myOwnProjectCustomers[i] != null ){
				        var payinfo = fundedFundings[n].myOwnProjectCustomers[i].paymentInfo;
				        var m = fundedFundings[n].myOwnProjectCustomers[i].member;
						html += "<p onclick='openSpeci("+n+","+i+")'><span class='customer-name'>"+m.cName+"</span><span class='customer-payment-no'>"+payinfo.paymentNo+"</span><span class='customer-order-date'>"+payinfo.orderDate+"</span></p>";
				    	console.log(payinfo.paymentNo);

		        	} 
				
				
		        }
		        
		        
		        var pageNum = Math.ceil(fundedFundings[n].myOwnProjectCustomers.length/10);
		        
		        if(pageNum > 10){
		        	if(currPage > 5){
			        	
		        		var startPage = currPage-4;
			        	var endPage = currPage+4;
			        	
			        	endPage = endPage >= pageNum? pageNum:endPage;
			        	startPage = 1>endPage-9? endPage-9:startPage;
			        	startPage = (endPage-9)<startPage? endPage-9:startPage

			        			if(startPage > 1){			        	
				        	page += "<p onclick='openList("+n+","+(startPage-1)+")''>"+"<"+"</p>";
			        	}
			        	
			        	var i = 0;
				        for(var j=startPage;j<=endPage;j++){
				        	page += "<p class='paing-no"+(j)+"' onclick='openList("+n+","+j+")''>"+j+"</p>";
				        }
			        	
			        	if(endPage < pageNum){
				        	page += "<p onclick='openList("+n+","+(endPage+1)+")''>"+">"+"</p>";
			        		
			        	}
			        	
		        	}
		        	else{
				        for(var j=1;j<=10;j++){
				        	page += "<p class='paing-no"+(j)+"' onclick='openList("+n+","+j+")''>"+j+"</p>";
				        }
			        	page += "<p onclick='openList("+n+","+11+")''>"+">"+"</p>";
		        		
		        	}
		        	
		        }else{		        	
			        for(var j=1;j<=pageNum;j++){
			        	page += "<p class='paing-no"+(j)+"' onclick='openList("+n+","+j+")''>"+j+"</p>";
			        }
		        }
		        
				$(".paging").append(page);
				$(".customer-list").append(html);
				activePage(currPage);

			}
		        //댓글 0/참여회원 0인 거.
	    }		
	
			
	
	
	
	
	function activePage(currPage) {
		$(".paing-no"+currPage).css("font-size", "12px");
		$(".paing-no"+currPage).css("line-height", "14px");

		
	}
	function openSpeci(n,j) {
	        $("#simpleModal2").css("display","none");
	        $("#simpleModal").css("display","block");
	        $(".backward").remove();
	        $(".closeBtn").before("<span class='backward' onclick='openList("+n+","+(Math.ceil((j+1)/10))+")'>"+"<"+"</span>");
	        $(".backward").css("padding-left","14px");
	        
	        
	        var ff = fundedFundings[n];
	        var payinfo = fundedFundings[n].myOwnProjectCustomers[j].paymentInfo;
	        
	        var m = fundedFundings[n].myOwnProjectCustomers[j].member;
	        var pbi = fundedFundings[n].funding.projectBasicInfo;
	        var r = fundedFundings[n].funding.reward;
	        
	        var quantity = payinfo.quantity;
	        var price = r.rewardPrice;
	        var totalPrice = quantity*price;
	        

	        
	        $(".start-date").html(pbi.startDate);
	        $(".end-date").html(pbi.endDate);
	        $(".reward-title").html(r.rewardTitle);
	        $(".reward-content").html(r.rewardContent);
	        $(".funding-category").html(pbi.fundingCategory);

	        $(".deposit-name").html(m.cName);
	        $(".q-email").html(m.cEmail);
	        $(".q-phone").html(m.cPhone);
	        
	        $(".order-date").html(payinfo.orderDate);
	        $(".payment-no").html(payinfo.paymentNo);
	        $(".reward-price").html(r.rewardPrice);
	        $(".quantity").html(payinfo.quantity);
	        $(".total-price").html(totalPrice);
	        $(".receive-name").html(payinfo.receiveName);
	        $(".receive-phone").html(payinfo.receivePhone);
	        $(".receive-addr").html(payinfo.receiveAddr);
	        $(".shipping-date").html(r.shippingDate);
		
	}
	
    $(".closeBtn").click(function () {
        $(".modal2").css("display","none");
        $(".modal").css("display","none");
    })
    
    function clickModal(e) {
        if(e.target == document.getElementById("simpleModal2")){
            $(".closeBtn").click();
            return;
        }
        if(e.target == document.getElementById("simpleModal")){
            $(".closeBtn").click();
            return;
        }
    }
    
	isThereMorePosts();	
	$(".readMore").click();
	
</script>
<style>
	.default-modal-css >div> p{
		height:30px;
		line-height:30px;
	}
	.project-box {
		height: 400px;
	}

	#background-url{
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        width: 100%;
        height: 100%;
        background-position: center !important;
        background-repeat: no-repeat !important;
        background-size: cover !important;
    }
	
	.project-profile a{
		line-height:270px;
		font-size:75px;
	}
	
	.project-title{
		font-size:15px;
	}	
	
	.projcet-title{
		position:absolute;
		margin-top:-30px;
		padding-left:12px;
		font-size:15px;
		color:white;
		z-index:1;
		display:none;
	}
	
	.project-profile a::after > .projcet-title{
		display:block;
	}
	
	.buttons {
		height:45px;
	}
	
    .project-title{
		position:absolute;
        bottom:-110px;
		padding-left:12px;
		font-size:15px;
		z-index:1;
        color:white;
        font-size: 17px;
        display: none;
	}
	
	.moving-top:hover > .project-profile:hover > a > p{
        display: block;
	}
	
	.navi-wrap{
		height:30px;
	}
	
	
	
</style>
<style>
    .reward-info > div > p:first-child > span:last{
        padding-left: 5px;
        font-size: 12px;
        color:#00B9CE;
    }
    .reward-info > div > p:first-child > span{
        color:#00B9CE;
        padding-left:5px;
        font-family: "logo";
    }
    .reward-info > div > p{
        font-weight:200;
    }
    .default-modal-css{
        margin-bottom: 17px;
    }
    .default-modal-css > div:first-child{
        color: #00B9CE;
        font-size: 15px;
        padding-left: 10px;
        font-weight:600;

    }
    .reward-title{
        font-family: "logo";
        font-size: 25px;
        font-weight: bolder;
        color: #00B9CE;
    }
    .default-modal-css > div:last-child{
        display: flex;
        flex-direction: column;
    
        border-top: 2px solid #00B9CE;
        border-bottom: 2px solid #00B9CE;
    }
    
    .default-modal-css > div:last-child > *{
        border-bottom: 0.2px dotted #EAEBED;
        text-align: left;
        font-size: 12px;
        padding-left: 10px;
    }
    .default-modal-css > div:last-child > * span:first-child{
        display: inline-block;
        width: 80px;
        font-size: 11px;
    }
    .default-modal-css > div:last-child > * span:{
        color: violet;
        padding-left: 20px;
    }
    .default-modal-css > *:first-child{
        border-bottom: 0.2px dotted #EAEBED;
        text-align: left;        
    }
    .modal-header{
        background-color: #00B9CE;
        padding: 15px;
        color: #fff;
        
    }
    
    .modal-header{
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
    }
    
    .modal-footer{
        border-bottom-left-radius: 10px;
        border-bottom-right-radius: 10px;
    }

    .modal-content{
        border-radius: 10px;

    }
        
    .modal-body{
        padding: 10px 20px;
    }
    
    .modal-footer{
        background-color: #00B9CE;
        padding: 10px;
        color: white;
        text-align: center;
    }


    .modal-small{
        font-size: 10px;
        line-height: 25px;
        color: rgb(85, 85, 85);
        border:none;
        margin-top: 10px;
        
    }
    
    .big{
        font-size: 15px;
        font-weight: 600;
    }
    
    .modal, .modal2{
    	display:none;
        position: fixed;
        z-index: 3;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgb(0,0,0,.5);
        overflow: auto;
        line-height: 1.6;
    }
    
    .modal-content{
        background-color: white;
        margin: 20% auto;
        width: 400px;
        box-shadow: 0 5px 8px 0 rgb(0,0,0,.2), 0 7px 20px 0 rgb(0,0,0,.2);
        animation: modalOpen 0.1s;
    }
    
    .closeBtn, .backward{
        float: right;
        font-size: 19px;
        color: white;
        padding-right: 13px;
    }
    
    .backward{
        float: left;
    }

    .closeBtn:hover, .closeBtn:focus , .backward:hover, .backward:focus{
        cursor: pointer;
        text-decoration: none;
        color: #000;
    }

    .modal-header, .modal-footer{
        margin: 0;
        height: 30px;
  
    }
    
    .default-modal-css{
    	background-color:'red';
    	margin-bottom:0px;
    }
	
	.modal-last{
		display:block;
		background-color:'red';
		margin-bottom:10px;
		margin-top:-40px important!;
	}
	.modal-divs > div{
		margin-top:35px !important;
	}
	
	.totalCounEqualsZero{
		text-align:center;
		font-size:20px;
		color:#EAEBED ;
		font-weight:600;
		font-family:"logo";
		margin-bottom: 150px;
		
	}
	
	.involved-members{
		font-size: 14px;
	}
    @keyframes modalOpen {
        from{opacity: 0;}
        to{opacity: 1;}
    }
    </style>
<style>
.customer-order-date{
    float: right;
    display: block;
    padding-right: 7px;
}
.customer-list > p:hover{
    cursor: pointer;
}
.paging{
    display: flex;
    justify-content: center;
    margin-top:5px !important;
}
.paging > p{
    padding: 5px;
    font-size: 9px;
    font-weight: 600;
    color: #00B9CE;
}
.paging > p:hover{
    cursor: pointer;
}
.customer-list{
	min-height:100px;
}
.modal-customer-last{
	display:block;
	margin:0;
	margin-bottom:-42px !important;
}
</style>
    
</html>