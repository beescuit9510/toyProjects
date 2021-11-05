<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/Yunyoung/mypage.css">
<title>구르밍</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="title">펀딩한 프로젝트</div>
		<div class="navi-wrap">
			<div class="navi">
				<a class="fundedFundings" href="/fundedFundingList">펀딩한 프로젝트</a> 
				<c:if test="${sessionScope.member.cLevel > 2}">
				<a class="myOwnProjects" href="/myOwnProject">제작한 프로젝트</a>
				</c:if>
				<a class="likeList" href="/likeList">관심 펀더 및 펀딩</a>
				<a class="mypage" href="/mypage">설정</a>
			</div>
		</div>
		<div class="project-wrap">
			<div class="project-row">
			</div>
			<c:if test="${totalCount == 0}">
				<div>
					<p class="totalCounEqualsZero">펀딩중인 프로젝트가 없습니다.<p>
				</div>
			</c:if>
			<div class="readMore-div">
				<button class="readMore btn btn-sm">더 보기</button>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
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
	                    <div>판매자 정보</div>
	                    <div>
	                        <p><span>이름</span> <span class="deposit-name"></span></p>
	                        <p><span>은행</span> <span class="tradebank"></span></p>
	                        <p><span>계좌번호</span> <span class="account-number"></span></p>
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
</body>
<script>
	var fundedFundings= [];
	var totalCount = Number($("#totalCount").html());
	var currCount = 0;
	var perPost = 3;

	$(".fundedFundings").attr("id","active-navi")
	$("#inputs").hide();
	totalCount == 0 && $(".readMore-div").hide();


	$(".readMore").click(function () {

		$.ajax({
			url:"/getMoreFundedList",
			data:{currCount:currCount,perPost:perPost},
			method:"POST",
			success:function(ffs){

				start = currCount;
				currCount = currCount+perPost; 
				
				var html = "";
				
				for(var i=0;i<ffs.length;i++){
					var ff = ffs[i];
					fundedFundings.push(ff);
					console.log(ff.projectBasicInfo.projectNo);
					
					html += "<section class='project-box moving-top'>";
					html += "<div class='project-profile'>"

					if(ff.projectBasicInfo.filepath){
						console.log(ff.projectBasicInfo.filepath);
						html += "<a href='/fundingView?projectNo="+ff.projectBasicInfo.projectNo+"'>"
						html += "<div id='background-url' style='background:url(/"+"upload/project"+"/"+ff.projectBasicInfo.filepath+")';></div>";
						html += "<p class='project-title'>"+ff.projectBasicInfo.projectTitle+"</p>";
						html += "</a>"
					}else{
						html += "<a href='/fundingView?projectNo="+ff.projectBasicInfo.projectNo+"'>구르밍"
						html += "<p class='project-title'>"+ff.projectBasicInfo.projectTitle+"</p>";
						html += "</a>"
					}
					
					
					html += "</div><div class='rate'>달성률 <span class='percent point'>"+Math.floor(ff.reward.rewardPrice*ff.total/ff.projectBasicInfo.targetPrice*100)+"%</span></div>";
					html +=	"<div class='target-price'>목표 금액 <span>"+ff.projectBasicInfo.targetPrice+"</span>원</div>";	
					html += "<div class='acc-price'>현재 달성 금액 <span>"+ff.reward.rewardPrice*ff.total+"</span>원</div>";
					html += "<div class='buttons'>";
					html += "<button onclick='openList("+(start+i)+")' class='btn_sm btn_out involved-members'>결제 내역</button>";
					html += "<button id='"+(start+i)+"' class='btn_sm btn_out funding-comments'><a href='/fundingView?projectNo="+ff.projectBasicInfo.projectNo+"' class='point fudnig-gogo'>펀딩 보러가기</a></button>";
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
	
	
	function openList(n) {
	    	$("#simpleModal").css("display","block");

	        window.addEventListener('click',clickModal);
	        
	        var quantity = fundedFundings[n].paymentInfo.quantity;
	        var price = fundedFundings[n].reward.rewardPrice;
	        var totalPrice = quantity*price;
	        
	        $(".start-date").html(fundedFundings[n].projectBasicInfo.startDate);
	        $(".end-date").html(fundedFundings[n].projectBasicInfo.endDate);
	        $(".reward-title").html(fundedFundings[n].reward.rewardTitle);
	        $(".reward-content").html(fundedFundings[n].reward.rewardContent);
	        $(".funding-category").html(fundedFundings[n].projectBasicInfo.fundingCategory);
	        $(".tradebank").html(fundedFundings[n].makerInfo.tradeBank);
	        $(".account-number").html(fundedFundings[n].makerInfo.accountNumber);
	        $(".deposit-name").html(fundedFundings[n].makerInfo.depositName);
	        $(".q-email").html(fundedFundings[n].reward.qEmail);
	        $(".q-phone").html(fundedFundings[n].reward.qPhone);
	        $(".order-date").html(fundedFundings[n].paymentInfo.orderDate);
	        $(".payment-no").html(fundedFundings[n].paymentInfo.paymentNo);
	        $(".reward-price").html(fundedFundings[n].reward.rewardPrice);
	        $(".quantity").html(fundedFundings[n].paymentInfo.quantity);
	        $(".total-price").html(totalPrice);
	        $(".receive-name").html(fundedFundings[n].paymentInfo.receiveName);
	        $(".receive-phone").html(fundedFundings[n].paymentInfo.receivePhone);
	        $(".receive-addr").html(fundedFundings[n].paymentInfo.receiveAddr);
	        $(".shipping-date").html(fundedFundings[n].reward.shippingDate);
			
	}
	
    $(".closeBtn").click(function () {
        $(".modal").css("display","none");
    })
    
    function clickModal(e) {
        if(e.target == document.getElementById("simpleModal")){
            $(".closeBtn").click();
        }else{
            window.addEventListener('click',clickModal);
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
		font-size:17px;
        color:white;
        width:260px;
        display: none;
  		text-overflow: ellipsis;
		white-space: nowrap;
		word-wrap: normal;
		overflow: hidden;
		text-align:left;
		
        
	}

	
	.moving-top:hover > .project-profile:hover > a > p{
        display: block;
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
    
    .modal{
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
    
    .closeBtn{
        float: right;
        font-size: 19px;
        color: white;
        padding-right: 13px;
    }

    .closeBtn:hover, .closeBtn:focus{
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
	.fudnig-gogo:hover{
		color:white;
	}
	
	
    @keyframes modalOpen {
        from{opacity: 0;}
        to{opacity: 1;}
    }
    </style>
</html>