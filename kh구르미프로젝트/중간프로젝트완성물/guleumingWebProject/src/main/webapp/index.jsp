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
<!-- <script type="text/javascript" src="./js/jquery.ulslide.js"></script> -->
<!-- 메인 슬라이드 css -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
<!-- Swiper JS -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<head>
<meta charset="UTF-8">
<title>구르밍</title>
<style>
   .btnWrap{
	width: 100%;
    margin: 0 auto;
    margin-top: 30px;
    float: left;
    clear: both;
    text-align: center;
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
	margin: 50px auto;
	}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp"></c:import>
	<div class="main_Swiper" style="margin-top: 60px;">       
        <div class="swiper mySwiper" id="test-slide">
            <div class="swiper-wrapper">
            <div class="swiper-slide">
            	<a href="/fundingView?projectNo=143&cMemberNo=0" class="main_01">
	            	<div class="main_text">
	            		<h3 class="point">NEW</h3>
	            		<h1 class="main_text2">제주딱새우와<br>
	            		마라면의 화려한 만남</h1>
	            		<h2 class="main_text3">보글보글 마성의 국물 맛 라면이 끝내줘요!</h2>
	            	</div>
            	
            	</a>           	
            </div>
            <div class="swiper-slide">
            	<a href="/fundingView?projectNo=146&cMemberNo=0" class="main_02">
	            	<div class="main_text">
	            		<h3 class="point">NEW</h3>
	            		<h1 class="main_text2">모발인장력개선<br>
	            		임상완료!</h1>
	            		<h2 class="main_text3">모발고민, 더 늦기 전 관리해요</h2>
	            	</div>
            	
            	</a>
            </div>
            <div class="swiper-slide">
            	<a href="/fundingView?projectNo=147&cMemberNo=0" class="main_03">
	            	<div class="main_text">
	            		<h3 class="point">NEW</h3>
	            		<h1 class="main_text2">요즘 집사들은<br>
	            		미리 챙겨줘요</h1>
	            		<h2 class="main_text3">[캣터링]반려묘를 위한 건강 관리 서비스</h2>
	            	</div>
            	
            	</a>
            </div>
            <div class="swiper-slide">
            	<a href="/fundingView?projectNo=141&cMemberNo=0" class="main_04">
	            	<div class="main_text">
	            		<h3 class="point">NEW</h3>
	            		<h1 class="main_text2">코코아 향이<br>
	            		피었습니다</h1>
	            		<h2 class="main_text3">정~말 맛있는 효소식품 !</h2>
	            	</div>
            	
            	</a>
            </div>
            </div>
        </div>
    </div>
  <div class="container">
      	<div id="goodsWrap">
      	<div id="goodsTitle">
      	<h1><a href="/fundingList">리워드 프로젝트 <img src="/img/KimMinji/right.png" width="20px;"></a></h1>
      	<p>구르밍의 핫한 프로젝트들을 만나보세요</p>
      	</div>
      	<select id="selected">
      	<option value="new" selected>최신순</option>
      	<option value="best">인기순</option>	
      	</select>
      	<div>
      		<ul class="goodsList">
      		
      		</ul>
      	</div>
     <div class="btnWrap" ><button class="moreBtn"  currentCount="6" totalCount="${rewardTotal }" value="7" id="more">리워드 프로젝트 더보기</button></div>
      	</div>
      	<div class="main_banner">
	      	<a href="/fundingList">      	
	      		<img src="/img/KimMinji/main_b01.png">
	      	</a>
      	</div>
      	 	<div id="eventWrap">
		      	<div id="eventTitle">
			      	<h1><a href="/eventList?reqPage=1">이벤트 <img src="/img/KimMinji/right.png" width="20px;"></a></h1>
			      	<p>구르밍의 이벤트 소식입니다</p>
			      	<div>	
			      		<ul class="eventList">
			      		
			      		</ul>
			      	</div>
			    </div>
			      	
			     	<div class="btnWrap" >
			     		<button class="moreBtn" currentCount="2" totalCount="${eventTotal }" value="3" id="moreEvent">이벤트 더보기</button>
			     	</div>
	      	</div>
      	<div class="main_banner">
	      	<a href="/funder/funderList?reqPage=1&funderCategory=전체">      	
	      		<img src="/img/KimMinji/main_b02.png">
	      	</a>
      	</div>
      	<div id="noticeWrap">
	      	<div id="noticeTitle">
		      	<h1><a href="/noticeList?reqPage=1">공지사항 <img src="/img/KimMinji/right.png" width="20px;"></a></h1>
		      	<p>구르밍의 새로운 소식을 전달해드립니다</p>
		      	<div>
					<ul class="noticeList">
						
					</ul>
				</div>
	      	</div>
	     <div class="btnWrap" ><button class="moreBtn" currentCount="4" totalCount="${noticeTotal }" value="5" id="moreNotice">새소식 더보기</button></div>
      	</div>
     
    </div>

		<script>
		var swiper = new Swiper(".mySwiper", {});
        const slide = new Swiper('#test-slide', {
            //   한 슬라이드에 보여줄 갯수
            slidesPerView : 'auto',
            // 슬라이드 반복여부
            loop : true,
            loopAdditionalSlides : 1,
            slidesPerView : 1,
            autoplay : {  // 자동 슬라이드 설정 , 비 활성화 시 false
                delay : 3000,   // 시간 설정
                disableOnInteraction : false,  // false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
        },
        }); 
		/* setInterval(function(){ 
		    $(' #bannerImg>li').delay(1500);
		 $(' #bannerImg>li').animate({marginLeft: "-1200px"});
		 $(' #bannerImg>li').delay(1500); 
		 $(' #bannerImg>li').animate({marginLeft: "-2400px"});
		  $(' #bannerImg>li').delay(3000); 
		  $(' #bannerImg>li').animate({marginLeft: "0px"}) 
		}); */
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
							html += " <li class='goods'>";
							html += " <a href='/fundingView?projectNo="+f.projectNo+"&cMemberNo=0'>";
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
							html += "</div></a></li>"
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
							html += " <li>";
							html += " <a href='/noticeView?noticeNo="+n.noticeNo+ "'>";
							html += " <span><em class='notice_point'>공지사항</em>"+n.noticeTitle+"<em class='notice_date'>"+n.writeDate+"</em></span>";
							html += " </a></li>";
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
							html += "<li>";
							html += "<a href='/eventView?eventNo="+e.eventNo + "'>";
							html += "<span><em class='event_point'>이벤트</em>"+e.eventTitle+"<em class='notice_date'>"+e.writeDate+"</em></span>";
							html += "</a></li>";
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
							html += " <li class='goods'>";
							html += " <a href='/fundingView?projectNo="+f.projectNo+"&cMemberNo=0'>";
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
							html += "</div></a></li>"
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
							html += " <li class='goods'>";
							html += " <a href='/fundingView?projectNo="+f.projectNo+"&cMemberNo=0'>";
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
							html += "</div></a></li>"
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
							html += " <li>";
							html += " <a href='/noticeView?noticeNo="+n.noticeNo+ "'>";
							html += " <span><em class='notice_point'>공지사항</em>"+n.noticeTitle+"<em class='notice_date'>"+n.writeDate+"</em></span>";
							html += " </a></li>";
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
							html += "<li>";
							html += "<a href='/eventView?eventNo="+e.eventNo + "'>";
							html += "<span><em class='event_point'>이벤트</em>"+e.eventTitle+"<em class='notice_date'>"+e.writeDate+"</em></span>";
							html += "</a></li>";
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

