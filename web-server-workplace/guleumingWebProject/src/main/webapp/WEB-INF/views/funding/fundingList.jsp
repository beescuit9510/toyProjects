<%@page import="funding.model.vo.FundingListRecent"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //ArrayList<FundingListRecent> list =  (ArrayList<FundingListRecent>)request.getAttribute("list");
    int count = (Integer)request.getAttribute("count");
    %>
<!DOCTYPE html>
<html>
<!-- 기본 CSS -->
<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<!--  JSTL 확장 c 태그 선언문 -->
<link rel="stylesheet" href="/css/LimHansol/fundingList.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<title>구르밍</title>
</head>
<style>
.category ul{
	width: 100%;
    display: flex;
    justify-content: space-evenly;
    text-align: center;
}
.category ul li{
    float: left;
}
.category>ul>li>a>img{
	border-radius: 50px;
	width:100px;
}
.category a{
	border: 1px solid #eee;
    padding: 10px 30px;
    border-radius: 50px;
    transition: 0.5s;
}
.on a{
	border: 1px solid #00B9CE;
    color: #00B9CE;
}

</style>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
   <div class="container">
		<!-- 카테고리  -->
		<div class="category">
			<ul id="gnb">
				<!-- 현재있는 페이지에만 하이라이트 적용 !! -->
				<li class="cate_btn all">
					<a href="/fundingList">
						# 전체
					</a>
				</li>
				<c:forEach items="${fcList}" var="fc">
					<li class="cate_btn">
						<a class="categorysearch" category="${fc.fundingCategory }">																	
	                    	# ${fc.fundingCategory}
	                    </a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="search">
			<div class="search_text">
				<c:choose>
				<c:when test="${not empty sessionScope.member }">
				<h2>${sessionScope.member.cName}님 원하시는 분야의 <em class="point">펀딩</em>를 지금 찾아보세요.</h2>
				</c:when>
				<c:otherwise>
				<h2>원하시는 분야의 <em class="point">펀딩</em>를 지금 찾아보세요.</h2>
				</c:otherwise>
				</c:choose>
			</div>
				<div class="search_bar">
					<input type="text" id="keyWord" placeholder="원하는 상품 및 주요 키워드를 검색해보세요.">
					<button id="search" type="button" class="search_btn"><img src="/img/icon/search_on.png"></button>
				</div>
		</div>
		
   		<div class="searchquantity"></div>
		<div>
			<ul class="mainmenu">
			
			</ul>
		</div>
		<div class="buttonArea">
			<button class="btn" currentCount="0" totalCount="${count }" value="1" id="more">펀딩 더보기</button>
		</div>
	</div>  
	<script>
	$("#more").click(function(){//id -> more-btn 눌렀을때 실행할 함수
		var reqPage = $(this).val();
		$.ajax({
			url : "/fundingMore",
			data : {reqPage:reqPage},
			type : "post",
			success : function(data){
				$(".searchquantity").empty();
				for(var i=0;i<data.length;i++){
					var f = data[i];
					var html="";
					html += " <li class='goods'>";
					html += " <a href='/fundingView?projectNo="+f.projectNo+"'>";
					html += "<div class='projectImg'>";
					html += "<img src='/upload/project/"+f.filepath+"'>";
					html += "</div>";
					html += "<div class='projectInfo'>";
					html += "<p class='projectCategory'>"+f.fundingCategory+"</p>"
					html += " <p class='projectTitle'>"+f.projectTitle+"</p>";
					html += "<div class='projectLine'>"
					html += " <span class='projectPrice'>"+f.totalPrice+"원 펀딩</span>";
					html += "<span class='projectPeriod'>D-"+f.period+"</span>";
					html += "<spna class='projectPercent'>"+f.percent+"%</span>";
					html += "</div>"
					html += "</div></a></li>";
					$(".mainmenu").append(html);
				}
				console.log(reqPage);
				$("#more").val(Number(reqPage)+3);
				var curr = Number($("#more").attr("currentCount"));
				$("#more").attr("currentCount", curr + data.length);
				var totalCount = $("#more").attr("totalCount");
				var currCount = $("#more").attr("currentCount");
				console.log(totalCount);
				console.log(currCount);
				if(currCount == totalCount){
					$("#more").css("display", "none");
					$("#more").prop("disabled",true);
				}
			}
		});
	});
	$(".categorysearch").click(function(){//class -> categorysearch 눌렀을때 실행할 함수
		var category = $(this).attr("category");
		console.log(this);
		$("#gnb li").removeClass("on");
		$(this).closest("li").addClass("on");
		console.log(category);
		$.ajax({
			url : "/fundingListCategory",
			data : {category:category},
			type : "post",
			success : function(data){
				$(".mainmenu").empty();
				$(".searchquantity").empty();
				var html="";
				if(data.length > 0){
					for(var i=0;i<data.length;i++){
						var fc = data[i];
						html += " <li class='goods'>";
						html += " <a href='/fundingView?projectNo="+fc.projectNo+"'>";
						html += "<div class='projectImg'>";
						html += "<img src='/upload/project/"+fc.filepath+"'>";
						html += "</div>";
						html += "<div class='projectInfo'>";
						html += "<p class='projectCategory'>"+fc.fundingCategory+"</p>"
						html += " <p class='projectTitle'>"+fc.projectTitle+"</p>";
						html += "<div class='projectLine'>"
						html += " <span class='projectPrice'>"+fc.totalPrice+"원 펀딩</span>";
						html += "<span class='projectPeriod'>D-"+fc.period+"</span>";
						html += "<spna class='projectPercent'>"+fc.percent+"%</span>";
						html += "</div>"
						html += "</div></a></li>";
					} 
					}else{
						html += " <span class='nonebox'><img src='/img/Parksowon/search_img.png'><h3><em class='point'>["+category+"]</em> 카테고리 의 상품이 아직 없습니다.</h3></span>";
					}
					$(".mainmenu").append(html);
					$("#more").css("display", "none");
					$("#more").prop("disabled",true);
					
				}
			
		});
	});
	$("#search").click(function(){//id -> search 눌렀을때 실행할 함수
		var keyWord = $("#keyWord").val();
		console.log(keyWord);
		$.ajax({
			url : "/fundingListSearch",
			data : {keyWord:keyWord},
			type : "post",
			success : function(data){
				$(".mainmenu").empty();
				$(".searchquantity").empty();
				var html="";
				if(data.length>0){
					var total = data.length
					var html2 ="";
					html2 += "<span>총<em class='point'>["+total+"건]</em>의검색 결과가  있습니다.</h3></span>";
					
					for(var i=0;i<data.length;i++){
						var fs = data[i];
						html += " <li class='goods'>";
						html += " <a href='/fundingView?projectNo="+fs.projectNo+"'>";
						html += "<div class='projectImg'>";
						html += "<img src='/upload/project/"+fs.filepath+"'>";
						html += "</div>";
						html += "<div class='projectInfo'>";
						html += "<p class='projectCategory'>"+fs.fundingCategory+"</p>"
						html += " <p class='projectTitle'>"+fs.projectTitle+"</p>";
						html += "<div class='projectLine'>"
						html += " <span class='projectPrice'>"+fs.totalPrice+"원 펀딩</span>";
						html += "<span class='projectPeriod'>D-"+fs.period+"</span>";
						html += "<spna class='projectPercent'>"+fs.percent+"%</span>";
						html += "</div>"
						html += "</div></a></li>";
					}
				}else{
					html += " <span class='nonebox'><img src='/img/Parksowon/search_img.png'><h3><em class='point'>["+keyWord+"]</em>의검색 결과가  없습니다.</h3></span>";
				}
				$(".searchquantity").append(html2);
				$(".mainmenu").append(html);
				$("#more").css("display", "none");
				$("#more").prop("disabled",true);
			}
		});
	});
	$(document).ready(function(){
		$("#more").click();
		$("#gnb li").removeClass("on");
		$(".all").closest("li").addClass("on");
	});
	 
	</script>
   <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>