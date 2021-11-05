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
			<ul id="gnb">
				<!-- 현재있는 페이지에만 하이라이트 적용 !! -->
				<li class="cate_btn">
					<a href="<c:url value='/funder/funderList?reqPage=1&funderCategory=전체'/>" data-value="전체">
						# 전체
					</a>
				</li>
				<c:forEach items="${catelist}" var="fc">
					<li class="cate_btn">
						<a href="<c:url value='/funder/funderList?reqPage=1&funderCategory=${fc.funderCategory}'/>" data-value="${fc.funderCategory }">																	
	                    	# ${fc.funderCategory}
	                    </a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="search">
			<div class="search_text">
				<c:choose>
					<c:when test="${not empty sessionScope.member}">
						<h2>${sessionScope.member.cName}님 원하시는 분야의 <em class="point">메이커</em>를 지금 찾아보세요.</h2>
					</c:when>
					<c:otherwise>					
						<h2>구르밍에서 원하시는 분야의 <em class="point">메이커</em>를 지금 찾아보세요.</h2>
					</c:otherwise>
				</c:choose>
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
    	<c:if test="${sessionScope.member.cLevel eq 3 && empty mbk.proCount}">   	
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
				                            <p class="list_opendate">설립일</p>
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
				                        <p>법인명</p>
				                        <p>${fb.businessName}</p>
				                    </span>      
				                    <span class="profileText">
				                        <p>설립일</p>
				                        <p>${fb.openDate}</p>
				                    </span>                 	
				                    <span class="profileText">
				                        <p>업종</p>
				                        <p>${fb.funderCategory}</p>
				                    </span>
									<span class="profileText">
				                        <p>문의사항</p>
				                        <p>${fb.boardEmail}</p>
				                    </span>
				                    <span class="profileText">
				                        <p>제작 프로젝트</p>
				                        <p>${fb.proCount}개</p>
				                    </span>
		                    	</div>
								<div class="like border_a">
								<c:choose>
				                	<c:when test="${empty sessionScope.member}">						               
										<button onclick="login();" class="heart"></button>
				                	</c:when>
				                	<c:when test="${fb.likedCheck == '좋아요'}">
				                		<button onclick="likefunder(this,${fb.writerNo});" class="heart _click"></button>
				                	</c:when>
				                	<c:otherwise>                							              
										<button onclick="likefunder(this,${fb.writerNo});" class="heart"></button>
				                	</c:otherwise>
				                </c:choose>									
			                        <p><b id="like_total">${fb.funderCount}</b>명이 관심을 갖고 있습니다.</p>
			                    </div>
		                    </div>
		                </li>
            		</a>
                </c:forEach>
            </ul>
            <c:if test="${empty list}">
				<span class="nonebox"><img src="/img/Parksowon/search_img.png"><h3><em class="point"> ${keyword} </em> 조회 결과가 없습니다.</h3></span>
			</c:if>
        </div>
        <div id="pageNavi">${pageNavi}</div>
    </div>
  <script>
  $(document).ready(function(){
 		var search = location.search;
 		var params = new URLSearchParams(search);		 
 		var getType= params.get('funderCategory');
 		$("#gnb li a").each(function(){
 			var data = $(this).data("value");
 			if(data == getType){
				$(this).closest("li").addClass("on");
 			}		
 		})
	}); 
  function login(){
		if(confirm("로그인이 필요 합니다 로그인 하시겠습니까?")){
			location.href="/login"
		}
	}
  function likefunder(c,obj){
	  var user = "${sessionScope.member.cMemberNo}";
	  var funder = obj;
	  $.ajax({
		   url : "/funderLike",
		   data: {user : user, funder: funder},
		   type: "POST",
		   success : function(data){
			   if(data.like_check == 0){
				    $(c).attr("class","heart is_animating");
		   			$(c).next().children("#like_total").html(data.like_cnt);
				   setTimeout("location.reload()", 21000);
			   }else{
				   $(c).attr("class","heart is_animating _click");
		   			$(c).next().children("#like_total").html(data.like_cnt);
			   		setTimeout("location.reload()", 21000);
			   }
		   }
	  })
  }
  </script>
  <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>