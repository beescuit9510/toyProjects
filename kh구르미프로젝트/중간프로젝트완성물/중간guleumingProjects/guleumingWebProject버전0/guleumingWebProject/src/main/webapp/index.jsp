<%@page import="java.util.ArrayList"%>
<%@page import="table.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <%
    int rewardTotal = (Integer)request.getAttribute("rewardTotal");
  	int noticeTotal = (Integer)request.getAttribute("noticeTotal");
  	int eventTotal = (Integer)request.getAttribute("eventTotal");
    ArrayList<Notice>list = (ArrayList<Notice>) request.getAttribute("list");
   %>
<!DOCTYPE html>
<html>
<style></style>
<link rel="stylesheet" href="/css/KimMinji/index.css">
<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/jquery.ulslide.js"></script>

<head>
<meta charset="UTF-8">
<title>구르밍</title>
<style>
   .btnWrap{
	width:400px;
	margin: 0 auto;
	margin-top:30px;
	}
	
	.moreBtn {
	width: 400px;
	height:50px;
	font-size: 16px;
	font-weight:500;
	background-color:white;
	border: 1px solid #eaebed;
	 border-radius: 5px;
	text-align: center;
	cursor : pointer;
	margin : 0 auto;
	}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
  <div class="container">
		<div class="mainBanner">
            <ul id="bannerImg">
			<li class="slideImg"><a href="#"><img src="/img/KimMinji/bnr_pc_sm%20(1).jpg"></a></li>
			<li class="slideImg"><a href="#"><img src="/img/KimMinji/bnr_pc_sm%20(2).jpg"></a></li>
			<li class="slideImg"><a href="#"><img src="/img/KimMinji/bnr_pc_sm%20(3).jpg"></a></li>
        </ul>
      </div>
      	<div id="goodsWrap">
      	<div id="goodsTitle">
      	<h1><a href="/fundingList">리워드 프로젝트 <img src="/img/KimMinji/right.png" width="20px;"></a></h1>
      	<p>구르밍의 핫한 프로젝트들을 만나보세요</p>
      	</div>
      	<select id="selected">
      	<option value="new" selected>최신순</option>
      	<option value="best">인기순</option>	
      	</select>
      	<div class="goodsList"></div>
     <div class="btnWrap" ><button class="moreBtn"  currentCount="6" totalCount="${rewardTotal }" value="7" id="more">리워드 프로젝트 더보기</button></div>
      	</div>
      	
      	<div id="noticeWrap">
      	<div id="noticeTitle">
      	<h1><a href="/noticeList?reqPage=1">공지사항<img src="/img/KimMinji/right.png" width="20px;"></a></h1>
      	<p>구르밍의 새로운 소식을 전달해드립니다</p>
      	<div class="noticeList">	</div>
      	</div>
     <div class="btnWrap" ><button class="moreBtn" currentCount="4" totalCount="${noticeTotal }" value="5" id="moreNotice">새소식 더보기</button></div>
      	</div>
      	
      	<div id="eventWrap">
      	<div id="eventTitle">
      	<h1><a href="/eventList?reqPage=1">이벤트<img src="/img/KimMinji/right.png" width="20px;"></a></h1>
      	<p>구르밍의 이벤트 소식입니다</p>
      	<div class="eventList">	</div>
      	</div>
      	
     <div class="btnWrap" ><button class="moreBtn" currentCount="2" totalCount="${eventTotal }" value="3" id="moreEvent">이벤트 더보기</button></div>
      	</div>
        </div>

		<script>
		setInterval(function(){ 
		    $(' #bannerImg>li').delay(1500);
		 $(' #bannerImg>li').animate({marginLeft: "-1200px"});
		 $(' #bannerImg>li').delay(1500); 
		 $(' #bannerImg>li').animate({marginLeft: "-2400px"});
		  $(' #bannerImg>li').delay(3000); 
		  $(' #bannerImg>li').animate({marginLeft: "0px"}) 
		});
		  window.onload=function(){
			  var selected = "new";
				$.ajax({
					url:"/indexProduct",
					data : {selected : selected},
					type :"post",
					dataType :"json",
					success : function(data) {
						$(".goodsList").empty();
						for(var i=0;i<data.length;i++){
							var f = data[i];
							var html ="";
							html += " <div class='goods'>";
							html += " <a href='/fundingView?projectNo="+f.projectNo+"&cMemberNo=0'><img src='/upload/project/"+f.filepath+"'></a>";
							html += " <p class='projectTitle'>"+f.projectTitle+"</p>";
							html += " <p ><span class='text'>"+f.totalPrice+"원펀딩중</span><span class='right'>D-"+f.period+"남음</span><spna class='right'>"+f.percent+"%진행</span></p></div> ";
								$(".goodsList").append(html);
						}
					}
				});
				$.ajax({
					url :"/indexNotice",
					type:"post",
					dataType :"json",
					success : function(data) {
						$(".noticeList").empty();
						for(var i=0;i<data.length;i++){
							var n = data[i];
							var html ="";
							html += " <div class='notice'>";
							html += " <a href='/noticeView?noticeNo="+n.noticeNo+ "'>"+n.noticeTitle+"</a></div>";	
								$(".noticeList").append(html);
						}
					}						
				});
				$.ajax({
					url :"/indexEvent",
					type:"post",
					dataType :"json",
					success : function(data) {
						$(".eventList").empty();
						for(var i=0;i<data.length;i++){
							var e = data[i];
							var html ="";
							html += " <div class='event'>";
							html += "<a href='/eventView?eventNo="+e.eventNo + "'><img src='/upload/event/"+e.filepath+"'>"+e.eventTitle+"</a></div>";	
								$(".eventList").append(html);
			
		  }
				}
				});
		  }
			$("#selected").change(function() {
				var selected = $(this).val();
				$.ajax({
					url:"/indexProduct",
					data : {selected : selected},
					type :"post",
					dataType :"json",
					success : function(data) {
						$(".goodsList").empty();
						for(var i=0;i<data.length;i++){
							var f = data[i];
							var html ="";
							html += " <div class='goods'>";
							html += " <a href='/fundingView?projectNo="+f.projectNo+"&cMemberNo=0'><img src='/upload/project/"+f.filepath+"'></a>";
							html += " <p class='projectTitle'>"+f.projectTitle+"</p>";
							html += " <p><span class='text'>"+f.totalPrice+"원펀딩중</span><span class='right'>D-"+f.period+"남음</span><spna class='right'>"+f.percent+"%진행</span></p></div> ";
								$(".goodsList").append(html);
						}
					}
				});
			});
			$("#more").click(function(){//id -> more-btn 눌렀을때 실행할 함수
				var start = $(this).val();
				var seleted = $("#selected").val();
				$.ajax({
					url : "/moreProduct",
					data : {start:start,
						selected:seleted},
					type : "post",
					success : function(data){
						for(var i=0;i<data.length;i++){
							var f = data[i];
							var html="";
							html += " <div class='goods'>";
							html += " <a href='/fundingView?projectNo="+f.projectNo+"&cMemberNo=0'><img src='/upload/project/"+f.filepath+"'></a>";
							html += " <p class='projectTitle'>"+f.projectTitle+"</p>";
							html += " <p><span class='text'>"+f.totalPrice+"원펀딩중</span><span class='right'>D-"+f.period+"남음</span><spna class='right'>"+f.percent+"%진행</span></p></div> ";
								$(".goodsList").append(html);
						}
						$("#more").val(Number(start)+3);
						var curr = Number($("#more").attr("currentCount"));
						$("#more").attr("currentCount", curr + data.length);
						var totalCount = $("#more").attr("totalCount");
						var currCount = $("#more").attr("currentCount");
						if(currCount == totalCount){
							$("#more").css("display", "none");
							$("#more").prop("disabled",false);
						}
					}
				});
			});
			$("#moreNotice").click(function(){//id -> more-btn 눌렀을때 실행할 함수
				var start = $(this).val();
				$.ajax({
					url : "/moreNotice",
					data : {start:start},
					type : "post",
					success : function(data){
						for(var i=0;i<data.length;i++){
							var n = data[i];
							var html="";
							html += " <div class='notice'>";
							html += " <a href='/noticeView?noticeNo="+n.noticeNo+ "'>"+n.noticeTitle+"</a></div>";	
								$(".noticeList").append(html);
						}
						$("#moreNotice").val(Number(start)+1);
						var curr = Number($("#moreNotice").attr("currentCount"));
						$("#moreNotice").attr("currentCount", curr + data.length);
						var totalCount = $("#moreNotice").attr("totalCount");
						var currCount = $("#moreNotice").attr("currentCount");
						if(currCount == totalCount){
							$("#moreNotice").css("display", "none");
							$("#moreNotice").prop("disabled",false);
						}
					}
				});
			});
			$("#moreEvent").click(function(){//id -> more-btn 눌렀을때 실행할 함수
				var start = $(this).val();
				$.ajax({
					url : "/moreEvent",
					data : {start:start},
					type : "post",
					success : function(data){
						for(var i=0;i<data.length;i++){
							var e = data[i];
							var html ="";
							html += " <div class='event'>";
							html += "<a href='/eventView?eventNo="+e.eventNo + "'><img src='/upload/event/"+e.filepath+"'>"+e.eventTitle+"</a></div>";	
								$(".eventList").append(html);
			
						}
						$("#moreEvent").val(Number(start)+2);
						var curr = Number($("#moreEvent").attr("currentCount"));
						$("#moreEvent").attr("currentCount", curr + data.length);
						var totalCount = $("#moreEvent").attr("totalCount");
						var currCount = $("#moreEvent").attr("currentCount");
						if(currCount == totalCount){
							$("#moreEvent").css("display", "none");
							$("#moreEvent").prop("disabled",false);
						}
					}
				});
			});
			</script>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>

</html>

