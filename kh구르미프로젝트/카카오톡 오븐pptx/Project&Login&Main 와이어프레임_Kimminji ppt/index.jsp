<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      	<h1><a href="#">리워드 프로젝트 <img src="/img/KimMinji/right.png" width="20px;"></a></h1>
      	<p>구르밍의 핫한 프로젝트들을 만나보세요</p>
      	</div>
      	<select id="selected">
      	<option value="new" selected>최신순</option>
      	<option value="best">인기순</option>	
      	</select>
      	<div class="goodsList"></div>
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
		
			</script>
	<c:import url="/WEB-INF/views/common/footer.jsp"></c:import>
</body>

</html>

