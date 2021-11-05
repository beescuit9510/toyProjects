<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--  JSTL 확장 c 태그 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/Parksowon/funder.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <div class="container">
		<!-- 카테고리  -->
		<div class="category">
			<ul>
				<!-- 현재있는 페이지에만 하이라이트 적용 !! -->
				<li>
					<a href="<c:url value='/funder/funderList?reqPage=1&funderCategory=전체'/>">
						<img src>
						<span>전체</span>
					</a>
				</li>
				<c:forEach items="${catelist}" var="fc">
					<li>
						<a href="<c:url value='/funder/funderList?reqPage=1&funderCategory=${fc.funderCategory}'/>">
							<c:choose>
								<c:when test="${fc.funderCategory == '가전'}">
	                        		<img src="/img/Parksowon/category01.jpg">
								</c:when>
								<c:when test="${fc.funderCategory == '도서'}">
	                        		<img src="/img/Parksowon/category02.jpg">
								</c:when>
								<c:when test="${fc.funderCategory == '리빙'}">
	                        		<img src="/img/Parksowon/category03.jpg">
								</c:when>
								<c:when test="${fc.funderCategory == '반려동물'}">
	                        		<img src="/img/Parksowon/category04.jpg">
								</c:when>
								<c:when test="${fc.funderCategory == '악세서리'}">
	                        		<img src="/img/Parksowon/category05.jpg">
								</c:when>
								<c:when test="${fc.funderCategory == '여행레저'}">
	                        		<img src="/img/Parksowon/category06.jpg">
								</c:when>
								<c:when test="${fc.funderCategory == '푸드'}">
	                        		<img src="/img/Parksowon/category07.jpg">
								</c:when>
							</c:choose>
	                        <span>${fc.funderCategory}</span>
	                    </a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="search">
			<div class="search_text">
				<h2>${sessionScope.member.cName}님 원하시는 분야의 <em class="point">메이커</em>를 지금 찾아보세요.</h2>
			</div>
			<form action="/searchFunder" method="post">
				<div class="search_bar">
					<input type="text" name="keyword" value="${keyword}" placeholder="원하는 기술 및 주요 키워드를 검색해보세요.">
					<button type="submit" class="search_btn"><img src="/img/icon/search_on.png"></button>
				</div>
			</form>
		</div>
	</div>   
    <div class="container">
    	<c:if test="${cMemberNo eq 2}">   	
	        <div class="float_right">
	            <a class="btn btn_sm btn_b" href="/funderWriteFrm">펀더 등록</a>
	        </div>
    	</c:if>
        <div class="funder_list">
            <ul>
            	<c:forEach items="${list}" var="fb">
            		<a href="<c:url value='/funderView?writerNo=${fb.writerNo}'/>">
		                <li class="border_a funder_listli">
		                    <div class="list_left">
		                    	<div class="list_pro_left">	                    	
			                    	<div class="list_profileImg">
			                    		<img src="/upload/funder/${fb.profileFilepath}">
			                    	</div>
			                    	<div class="list_profile_text">		                    	
				                        <span>
				                            <p class="list_businessName">${fb.businessName}</p>
				                            <p class="list_cate_pro">${fb.funderCategory}</p>
				                        </span>
				                        <span>
				                            <p class="list_opendate">설립 일자</p>
				                            <p>${fb.openDate}</p>
				                        </span>
			                    	</div>
		                    	</div>
		                        <span>
		                            <p class="list_companyinfo">${fb.companyIntro}</p>
		                        </span>
		                        <div class="list_skileName">
			                        <c:forTokens items="${fb.skillName}" delims="/" var="skillname">
										<p>${skillname}</p>									
									</c:forTokens>
									<a href="<c:url value='/funderView?writerNo=${fb.writerNo}' />">+ 기술 더 보기</a>
		                        </div>
		                    </div>
		                    <div class="list_right">
		                    	<div class="list_proright">
		                    		<span class="profileText">
				                        <p>담당자</p>
				                        <p>${m.managerName}</p>
				                    </span>                  	
				                    <span class="profileText">
				                        <p>이메일</p>
				                        <p>${fb.boardEmail}</p>
				                    </span>
									<span class="profileText">
				                        <p>문의전화</p>
				                        <p>${m.cPhone}</p>
				                    </span>
				                    <span class="profileText">
				                        <p>제작 프로젝트</p>
				                        <p>개</p>
				                    </span>
		                    	</div>
								<div class="like border_a">
								<c:choose>
									<c:when test="${not empty sessionScope.member}">
			                        	<div class="heart" id="like_btn"></div>
									</c:when>
									<c:otherwise>
			                        	<div class="heart" id="heart_login"></div>		
									</c:otherwise>
								</c:choose>
			                        <p><b>${fb.funderCount}명이 관심</b>을 갖고 있습니다.</p>
			                    </div>
		                    </div>
		                </li>
            		</a>
                </c:forEach>
            </ul>
            <c:if test="${empty list}">
				<span class="nonebox"><img src="/img/Parksowon/search_img.png"><h3><em class="point">[ ${keyword} ]</em> 조회 결과가 없습니다.</h3></span>
			</c:if>
        </div>
        <div id="pageNavi">${pageNavi}</div>
    </div>
  <script>
	  $(document).ready(function() {
			$(".category>ul>li").eq(0).addClass("on_img");
	      $(".category>ul>li").click(function() {
	          var idx = $(this).index();
	          $(".category>ul>li").removeClass("on_img");
	          $(".category>ul>li").eq(idx).addClass("on_img");
	      });
	  });
     /* $(function(){
         $(".search_bar>input").focusin(function(){
        	 $(this).parent().css("border-bottom","2px solid #00B9CE");
        	 $(this).next().children().attr("src","/img/icon/search_on.png");
         });
         $(".search_bar>input").focusout(function(){
        	 $(this).parent().css("border-bottom","2px solid #333");
         });
     }); */
/*    //하트 애니메이션
     $(function(){
         $(".heart").on('click touchstart', function(){
             $(this).toggleClass('is_animating _click');
         });
          $(".heart").on('animationend', function(){
             $(this).toggleClass('is_animating');
         });
     }); */
   //로그인 안한 상태서 하트
   $("#heart_login").click(function(){
	   alert("로그인이 필요한 기능입니다.");
	   
	});
   //로그인 상태 서 하트
   $("#like_btn").click(function(){   
	 	//변수에 저장해두고 써야함!!!! 
	   	var user = ${sessionScope.member.cMemberNo};
	 	var funder = ${fb.writerNo};
	   $.ajax({
		   url : "/funderLike",
		   data: {user : user, funder: funder},
		   type: "POST",
		   success : function(data){
			   if(data.status == 404){
			   		$(".heart").removeClass("_click");
			   }else{
			   		$(".heart").addClass("is_animating _click");
			   }
		   }
	   });
   });
  </script>
  <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>