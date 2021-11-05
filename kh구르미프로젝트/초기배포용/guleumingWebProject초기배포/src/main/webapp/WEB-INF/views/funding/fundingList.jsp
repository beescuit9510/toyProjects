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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<title>펀딩페이지</title>
</head>
<style>
    .menu_wrap{ 
    margin-top: 100px;
    }
    .mainmenu{
        width: 1180px; 
        margin: 0 auto;
        overflow: hidden;
    }
   .fundingList {
    width: 300px;
    height: 400px;
    margin-left: 60px;
    list-style: none;
    float: left;
    padding: 0;
    border-radius: 10px;
    box-shadow :2px 2px 2px 2px rgb(214, 212, 212);
    }
    .text{
        color: rgb(52, 152, 219);
    }
    .fundingList img{
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        height : 300px;
        width: 304px;
    }

    #plus{
        margin: 0 auto;
        display: block;
    }
    #more-btn{
        margin-top : 100px;
    }
    .projectTitle{
        text-align: center;
    }
    .firstLi{
        margin-top: 30px;
        margin-bottom: 20px;
        margin-left: 10px;
    }
    .secondLi{
        margin-left: 10px;
    }
    .left{
        float: right;
    }

</style>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
   <div class="container">
		<div class="catagory">
			<button class="categorysearch" value="반려동물"><img src='/img/LimHansol/star-on.png'></button>
			<button class="categorysearch" value="가전"><img src='/img/LimHansol/star-on.png'></button>
			<button><img src='/img/LimHansol/star-on.png'></button>
			<button><img src='/img/LimHansol/star-on.png'></button>
			<button><img src='/img/LimHansol/star-on.png'></button>
			<button><img src='/img/LimHansol/star-on.png'></button>
		</div>		
		<legend>모집중인 펀딩 </legend>
		<div class="mainmenu"></div>
		<button class="btn btn-outline-info btn-block" currentCount="0" totalCount="<%=count %>" value="1" id="more-btn"> 더보기</button>
		<!-- 
			totalCount 는 전체개시물수
			currentCount : 실제로 가져온 게시물수
			value: 요청한 게시물 시작 번호(=reqPage)
		 -->

	</div>
	<script>
	$("#more-btn").click(function(){//id -> more-btn 눌렀을때 실행할 함수
		var reqPage = $(this).val();
		$.ajax({
			url : "/fundingMore",
			data : {reqPage:reqPage},
			type : "post",
			success : function(data){
				for(var i=0;i<data.length;i++){
					var p = data[i];
					//var totalPrice = p.total * p.rewardPrice;
					//console.log(p.total);
					//var percent = total / p.targetPrice * 100; 
					var html="";
					html += " <ul class='fundingList'>";
					html += "<li><a href='/fundingView?projectNo="+p.projectNo+"'><img src="+"'/img/LimHansol/딸기맛 쿠키.png'"+" height="+"'300px'></a></li>";
					html += " <li class='firstLi'><span class='projectTitle'>"+p.projectTitle+"</span></li>";
					html += " <li class='secondLi'><span class='text'>"+p.totalPrice+"원펀딩중</span><span class='left'>D-"+p.period+"남음</span><spna class='left'>"+p.percent+"%진행</span></li></ul>";
					
					$(".mainmenu").append(html);
				}
				
				$("#more-btn").val(Number(reqPage)+3);
				var curr = Number($("#more-btn").attr("currentCount"));
				$("#more-btn").attr("currentCount", curr + data.length);
				var totalCount = $("#more-btn").attr("totalCount");
				var currCount = $("#more-btn").attr("currentCount");
				if(currCount == totalCount){
					$("#more-btn").css("display", "none");
					//$("#more-btn").prop("disabled",true);
				}
			}
		});
	});
	$(".categorysearch").click(function(){//id -> categorysearch 눌렀을때 실행할 함수
		var category = $(this).val();
		$.ajax({
			url : "/fundingListCategory",
			data : {category:category},
			type : "post",
			success : function(data){
				$(".mainmenu").empty();
				for(var i=0;i<data.length;i++){
					var p = data[i];
					var html="";
					html += " <ul class='fundingList'>";
					html += "<li><a href='/fundingView?projectNo="+p.projectNo+"'><img src="+"'/img/LimHansol/딸기맛 쿠키.png'"+" height="+"'300px'></a></li>";
					html += " <li class='firstLi'><span class='projectTitle'>"+p.projectTitle+"</span></li>";
					html += " <li class='secondLi'><span class='text'>"+p.totalPrice+"원펀딩중</span><span class='left'>D-"+p.period+"남음</span><spna class='left'>"+p.percent+"%진행</span></li></ul>";
					
					$(".mainmenu").append(html);
					$("#more-btn").css("display", "none");
				}
				
				
				
			}
		});
	});
	</script>
   <c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>
</html>