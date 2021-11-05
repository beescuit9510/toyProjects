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
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container">
		<div class="title">제작한 프로젝트</div>
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
					<p class="totalCounEqualsZero">제작중인 프로젝트가 없습니다.<p>
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
                <p class="modal-small"><span class="start-date"></span><span> ~ </span><span class="end-date"></span></p>
                <div class="reward-info">
                    <div>
                        <p class="reward-title"><span class="funding-category"></span></p>
                        <p class="reward-content"></p>
                    </div>
                </div>
                <div class="maker-info default-modal-css">
                    <div class="modal2-title"></div>
                    <div class="customer-list"></div>
                </div>
              <div class="paging">
              </div>
            </div>
	            <p class="modal-small modal-customer-last" >예상배송일 <span class="shipping-date"></span> ~</p>
            <div class="modal-footer">
            </div>
        </div>
    </button>
     <button id="simpleModal3" class="modal3"> 
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
	                    <div>[ <span class="comment-writer-name"></span> ] 님의 댓글</div>
	                    <div class="comment-wrap">
	                        <p class="comment-content comment-body"/><p>
	                    </div>
	                </div>
	                <div class="payment-info default-modal-css">
	                    <div>나의 답변</div>
	                    <div class="comment-wrap comment-body2">
	                    </div>
	                </div>
	                <div class="buyer-info default-modal-css">
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
				
				console.log(start);
				console.log(currCount);
				
				for(var i=0;i<ffs.length;i++){
					var ff = ffs[i];
					
					fundedFundings.push(ff);
					
					html += "<section class='project-box moving-top'>";
					html += "<div class='project-profile'>"
					
					var path = "/upload/project/";
					var a = path+ff.funding.projectBasicInfo.filepath;
					if(ff.funding.projectBasicInfo.filepath){
						html += "<a href='/fundingView?projectNo="+ff.funding.projectBasicInfo.projectNo+"'>"
							///fundingView?projectNo=
						html += "<div id='background-url' style='background:url("+a+")';></div>";
						html += "<p class='project-title'>"+ff.funding.projectBasicInfo.projectTitle+"</p>";
						html += "</a>"
					}else{
						html += "<a href='/fundingView?projectNo="+ff.funding.projectBasicInfo.projectNo+"'>구르밍"
						html += "<p class='project-title'>"+ff.funding.projectBasicInfo.projectTitle+"</p>";
						html += "</a>"
					}
					
					console.log(ff.funding.reward.rewardPrice);
					console.log(ff.total);
					console.log(ff);
					

					html += "</div><div class='rate'>달성률 <span class='percent point'>"+Math.floor(ff.funding.reward.rewardPrice*ff.funding.total/ff.funding.projectBasicInfo.targetPrice*100)+"%</span></div>";
					html +=	"<div class='target-price'>목표 금액 <span>"+ff.funding.projectBasicInfo.targetPrice+"</span>원</div>";	
					html += "<div class='acc-price'>현재 달성 금액 <span>"+ff.funding.reward.rewardPrice*ff.funding.total+"</span>원</div>";
					html += "<div class='buttons'>";
					html += "<button onclick='openList("+(start+i)+","+1+")' class='btn_sm btn_out involved-members'>참여 회원 보기</button>";
					html += "<button onclick='openComment("+(start+i)+","+1+")' class='btn_sm btn_out funding-comments'>댓글 보기</button>";
//					html += "<button onclick='openComment("+(start+i)+","+1+")' class='btn_sm btn_out funding-comments'>프로젝트 보러가기</button>";
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
	        
	        var r = fundedFundings[n].funding.reward;
	        var pbi = fundedFundings[n].funding.projectBasicInfo;
	        $(".modal2-title").html("펀딩 참여 회원 목록");
	        $(".reward-title").html(r.rewardTitle);
	        $(".reward-content").html(r.rewardContent);
	        $(".start-date").html(pbi.startDate);
	        $(".shipping-date").html(r.shippingDate);
	        $(".end-date").html(pbi.endDate);
	        $(".funding-category").html(pbi.fundingCategory);




	        $(".customer-list").find("a").remove();
	        $(".customer-list").find("p").remove();
	        $(".paging").find("p").remove();
	        $(".backward").remove();
	        

			var html = "";
			var page = ""

			if(fundedFundings[n].myOwnProjectCustomers.length == 0){
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
	
			
	function openComment(n, currPage) {
		
			
	        $("#simpleModal").css("display","none");
	        $("#simpleModal3").css("display","none");
	        $("#simpleModal2").css("display","block");
	        window.addEventListener('click',clickModal);
	        
	        var pbi = fundedFundings[n].funding.projectBasicInfo;
	        var r = fundedFundings[n].funding.reward;

	        $(".modal2-title").html("댓글 모음");
	        $(".start-date").html(pbi.startDate);
	        $(".end-date").html(pbi.endDate);
	        $(".shipping-date").html(r.shippingDate);
	        $(".reward-title").html(pbi.projectTitle);
	        $(".reward-content").html("");
	        $(".funding-category").html("");

	        
	        
	        $(".customer-list").find("p").remove();
	        $(".customer-list").find("a").remove();
	        $(".paging").find("p").remove();
	        $(".backward").remove();
	        

	
			var html = "";
			var page = ""
			var postsNum = 0;
	
			
			
			if(fundedFundings[n].myOwnProjectComment.length == 0){
				$(".customer-list").append($("<p>달린 댓글이 없습니다.</p>"));
				$(".customer-list").first().css("color","#EAEBED");
	        }
			else{
//				for(var count=0;count<=fundedFundings[n].myOwnProjectComment.length;count++){
//		        	if(fundedFundings[n].myOwnProjectComment[count] != null ){
//				        var fc = fundedFundings[n].myOwnProjectComment[count].fundingComment;
//						if(fc.commentLevel <2){
//				        	postsNum++;
//				        }		        		
//		        	}
//				}
				


	//			var limits = postsNum;
				var start = currPage==0? 0:(currPage-1)*10;
				

				var end = ((currPage)*10);
//				var startstart = start;
		//		console.log("limits"+limits);
		//		console.log("start"+start);
		//		console.log("startstart"+startstart);
				
				var endend = 10;
//		        for(var i=start; limits>0&&endend>0; i++){
		        for(var i=start; i<end; i++){
		        	if(fundedFundings[n].myOwnProjectComment[i] != null ){
				        var fc = fundedFundings[n].myOwnProjectComment[i].fundingComment;
				        var m = fundedFundings[n].myOwnProjectComment[i].member;
				        var date = fc.writeDate;
				        var content = fc.commentContent.substring(0,17);
				        content = fc.commentContent.length > 17 ? content+" ...":content;
				        date = date.replace(/-/,"년 ").replace(/-/,"월 ");
				        if(fc.commentLevel ==2){
							html += "<a href='/fundingView?projectNo="+fc.projectRefNo+"&showComment=true#comment"+fc.commentNo+"'><span class='customer-name my-comment'>나의 댓글</span><span class='customer-payment-no my-comment'>"+content+"</span><span class='customer-order-date my-comment'>"+fc.writeDate+"</span></a>";
				        	
				        }else{
							html += "<a href='/fundingView?projectNo="+fc.projectRefNo+"&showComment=true#comment"+fc.commentNo+"'><span class='customer-name'>"+m.cName+"</span><span class='customer-payment-no'>"+content+"</span><span class='customer-order-date'>"+fc.writeDate+"</span></a>";
				        	
				        	
				        }
				//        	limits--;
			//	        	endend--;
			//	        	if(limits <=0 ){
			//	        		break;
			//	        	}
			//	        	if(endend <=0){
			//	        		break;
			//	        	}
//							html += "<p onclick='openSpeci2("+n+","+i+")'><span class='customer-name'>"+m.cName+"</span><span class='customer-payment-no'>"+content+"</span><span class='customer-order-date'>"+fc.writeDate+"</span></p>";
				        	
			//	        }
	
		        	} 
				
				
		        }
	//			console.log("limits:"+limits);
//				console.log("endend:"+endend);
		        
//		        var pageNum = Math.ceil(postsNum/10);
		        var pageNum = Math.ceil(fundedFundings[n].myOwnProjectComment.length/10);

		        if(pageNum > 10){
		        	if(currPage > 5){
			        	
		        		var startPage = currPage-4;
			        	var endPage = currPage+4;
			        	
			        	endPage = endPage >= pageNum? pageNum:endPage;
			        	startPage = 1>endPage-9? endPage-9:startPage;
			        	startPage = (endPage-9)<startPage? endPage-9:startPage
	
			        			if(startPage > 1){			        	
				        	page += "<p onclick='openComment("+n+","+(startPage-1)+")''>"+"<"+"</p>";
			        	}
			        	
			        	var i = 0;
				        for(var j=startPage;j<=endPage;j++){
				        	page += "<p class='paing-no"+(j)+"' onclick='openComment("+n+","+j+")''>"+j+"</p>";
				        }
			        	
			        	if(endPage < pageNum){
				        	page += "<p onclick='openList("+n+","+(endPage+1)+")''>"+">"+"</p>";
			        		
			        	}
			        	
		        	}
		        	else{
				        for(var j=1;j<=10;j++){
				        	page += "<p class='paing-no"+(j)+"' onclick='openComment("+n+","+j+")''>"+j+"</p>";
				        }
			        	page += "<p onclick='openList("+n+","+11+")''>"+">"+"</p>";
		        		
		        	}
		        	
		        }else{		        	
			        for(var j=1;j<=pageNum;j++){
			        	page += "<p class='paing-no"+(j)+"' onclick='openComment("+n+","+j+")''>"+j+"</p>";
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
//	        $(".funding-category").html(pbi.fundingCategory);
	        $(".funding-category").remove();

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

	function openSpeci2(n,j) {
	        $("#simpleModal2").css("display","none");
	        $("#simpleModal").css("display","none");
	        $("#simpleModal3").css("display","block");
	        $(".backward").remove();
	        $(".comment-body").find(".comment-reply").remove();
	        $(".comment-body2").find(".comment-reply").remove();
	        $(".closeBtn").before("<span class='backward' onclick='openComment("+n+","+(Math.ceil((j+1)/10))+")'>"+"<"+"</span>");
	        $(".backward").css("padding-left","14px");

	        
	        	        
	        var fc = fundedFundings[n].myOwnProjectComment[j].fundingComment;
	        var m = fundedFundings[n].myOwnProjectComment[j].member;

	        var pbi = fundedFundings[n].funding.projectBasicInfo;
	        var r = fundedFundings[n].funding.reward;

	        
	        $(".start-date").html(pbi.startDate);
	        $(".end-date").html(pbi.endDate);
	        $(".reward-title").html(pbi.projectTitle);

	        $(".comment-writer-name").html(m.cName);
//	        $(".comment-content").html(fc.commentContent);
	        $(".comment-body").append($("<p class='comment-reply comment-body'><span>질문 </span><span class='classssss'><a href=''>"+fc.commentContent+"</a><span/></p>"));
	        $(".comment-content").next().remove();
	        
	        var comments = fundedFundings[n].myOwnProjectComment;
	        var j = 1;
	        for(var i = 0; i<comments.length;i++){
	        	if(comments[i].fundingComment.commentRefNo == fc.commentNo){
	        		var cccc = comments[i].fundingComment.commentContent;
	        		cccc = 	cccc.substring(0,14);
	        		cccc = 	cccc+"....";
			        $(".comment-body2").prepend($("<p class='comment-reply comment-body'><span class='reply-order'></span><span ><a href=''>"+cccc+"</a></span></p>"));
	        	}	
	        }
	        
	        for(var i = 0; i<$(".reply-order").length;i++){
	       		$(".reply-order").eq(i).html("답변 "+(i+1));
	        }
//		        $(".comment-reply").html("답변중");
	        
	        $(".shipping-date").html(r.shippingDate);
	        $(".comment-body2").append("<p class='comment-reply'><span onclick='replyComment("+n+","+fc.commentNo+")' class='comment-btn'>댓글달기</span><input class='reply-comment-css comment-reply comment-body comment-input-input' type='text' placeholder='질문에 대한 답변을 적어보세요'></></p>")
	        
	}
	
    $(".closeBtn").click(function () {
        $(".modal2").css("display","none");
        $(".modal3").css("display","none");
        $(".modal").css("display","none");
    })
    
    function eventPrevent(e) {
//    	event.stopPropagation();
	}
    
    document.addEventListener("click",eventPrevent);
    
    function clickModal(e) {
	       if(e.target == document.getElementById("simpleModal2")){
	           $(".closeBtn").click();
	           return;
	       }
	       if(e.target == document.getElementById("simpleModal3")){
	           $(".closeBtn").click();
	           return;
	       }
	       if(e.target == document.getElementById("simpleModal")){
	           $(".closeBtn").click();
	           return;
	       }
    }
    
    function replyComment(n,j) {
    	
    	var input = $(".comment-input-input").val();
    	console.log(input);
		$.ajax({			
		url:"/insertComment",
		data:{comment:input,projectNo:n,commentRefNo:j, currCount:currCount},
		method:"POST",
		success:function(ffs){
				alert("댓글 달기 성공");
				fundedFundings = new Array();
				for(var i=0;i<ffs.length;i++){
					var ff = ffs[i];
					fundedFundings.push(ff);
				}
				console.log(fundedFundings)
					

			}
		})		
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
	
	.project-profile{
		position: relative;
	}
	
	.projcet-title{
		position:absolute;
		margin-top:-30px;
		padding-left:12px;
		font-size:15px;
		color:white;
		z-index:1;
		display:none;
		word-break: break-all;
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
    
    .modal, .modal2, .modal3{
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

.comment-writer-name{
	font-size:20px;
    color:#00B9CE;

}

.comment-wrap{
	display:flex !important;
	justify-content:center !important;
}

.comment-reply{
    outline: none;
	border:none;
}
.comment-btn{
     color:#00B9CE;
     font-family: "logo";
	font-size:13px !important;

}
.comment-btn:hover{
    cursor: pointer;

}

.comment-input-input::placeholder{
	font-size:12px;
	color:#00B9CE !important;
	font-weight:600px;

}
.comment-input-input{
	font-size:12px;
	color:#00B9CE !important;
	font-weight:600px;

}
.comment-reply .comment-body{
}

.reply-comment-css{
	margin-right:0;
}

.my-comment{
	color:#00B9CE !important;

}

.modal-footer{
	margin-top:50px;
}

</style>
    
</html>