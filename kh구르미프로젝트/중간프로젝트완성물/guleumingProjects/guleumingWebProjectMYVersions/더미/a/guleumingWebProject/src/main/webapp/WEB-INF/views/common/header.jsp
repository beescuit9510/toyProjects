<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<!-- 기본 CSS -->
<link rel="stylesheet" href="/defaultCss/default.css">
<!-- 폰트 CSS -->
<link rel="stylesheet" href="/defaultCss/font.css">
<!-- jQuery라이브러리 -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<!--  JSTL 확장 c 태그 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><head>

 <div class="back_dark"></div>
    <div class="nav_wrap">
        <nav class="nav">
            <div class="brand_logo">
                <a href="/">구르밍</a>
            </div>
            <ul class="nav_left">
                <li>
                    <a class="a" href="/funding/fundingList">펀딩</a>
                </li>
                <li>
                    <a class="a" href="/funder/funderList">펀더찾기</a>
                </li>
                <li class="nav_more">
                    <a href="#">더보기 메뉴</a>
                    <ul class="submenu">
                        <li><a class="a" href="/readMore/notice/noticeList">공지사항</a></li>
                        <li><a class="a" href="/readMore/event/eventList">이벤트</a></li>
                    </ul>
                </li>
                <li>
                    <a href="/project/projectFrm" class="nav_point a"><span>프로젝트 만들기</span></a>
                </li>
            </ul>
            <ul class="nav_right">
                <c:choose>
                	<%-- 로그아웃 시 --%>
                	<c:when test="${empty sessionScope.member}">
		                <li>
		                    <a class="a" href="/login/login">로그인</a>
		                </li>
		                <li>
		                    <a class="a" href="/signUp/signUp">회원가입</a>
		                </li>
                	</c:when>
                	<c:otherwise>                	
		                <li>
		                     <%-- 로그인 시 --%>
		                    <a id="nav_mypage_wrapper">
		                        <span id="nav_mypage">마이페이지</span>
		                    </a>    
		                </li>
                	</c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </div>
    <c:if test="${not empty sessionScope.member}">
    <!-- 로그인 하고 마이페이지 누를경우 오른쪽에서 나타나는 슬라이딩 메뉴 -->
    <c:choose>
    	<%-- 회원등급 일반 회원 일 시 --%>
    	<c:when test="${sessionScope.member.cLevel eq 2}">
			<nav id="sidenav">
		        <span id="close_sidenav">&times;</span>
		        <header>
		            <img src="img/icon/user.png">
		            <p class="name"></p>
		            <p class="id"></p>
		        </header>
		        <ul>
		          <li><a class="a" href="/mypage/member/fundingList">펀딩한 프로젝트</a></li>
		          <li><a class="a" href="/mypage/member/bookmark">북마크</a></li>
		          <li><a class="a" href="/mypage/member/mypage">설정</a></li>
		          <li><a class="a" href="#">로그아웃</a></li>
		        </ul>
		    </nav>	
		</c:when>
		<%-- 펀더 일 시 --%>
		<c:when test="${sessionScope.member.cLevel eq 3}">
			<nav id="sidenav">
		        <span id="close_sidenav">&times;</span>
		        <header>
		            <img src="img/icon/user.png">
		            <p class="name"></p>
		            <p class="id"></p>
		        </header>
		        <ul>
			      <li><a class="a" href="/mypage/funder/myFundingProjectList">제작한 프로젝트</a></li>	        
		          <li><a class="a" href="/mypage/funder/fundingList">펀딩한 프로젝트</a></li>
		          <li><a class="a" href="/mypage/funder/mypage">설정</a></li>
		          <li><a class="a" href="#">로그아웃</a></li>
		        </ul>
		    </nav>	
		</c:when>
		<%-- 관리자 일 시 --%>
		<c:when test="${sessionScope.member.cLevel eq 1}">
			<nav id="sidenav">
		        <span id="close_sidenav">&times;</span>
		        <header>
		            <img src="img/icon/user.png">
		            <p class="name">admin</p>
		            <p class="id">admin@guleming.com</p>
		        </header>
		        <ul>
		          <li><a class="a" href="/adminPage/fundingList">펀딩 목록</a></li>
		          <li><a class="a" href="/adminPage/funderList">펀더 목록</a></li>
		          <li><a class="a" href="/adminPage/memberList">회원 목록</a></li>
		          <li><a class="a" href="#">로그아웃</a></li>
		        </ul>
		    </nav>
		</c:when>
    </c:choose>
      <a href="#" class="top"><img src="img/icon/top.png"></a>
    </c:if>
      
<script>
  $("#nav_mypage").click(function() {
      $("#sidenav").css("right", "0");
    $(".back_dark").show();
  });
  $("#close_sidenav").click(function() {
      $("#sidenav").css("right", "-350px");
    $(".back_dark").hide();
  });
  // 백그라운드 클릭 시 메뉴 닫기 
  $(".back_dark").click(function(){
      $('#sidenav').css("right", "-350px");
      $(".back_dark").hide();
  }); 
  $(function(){
      $(".submenu").prev().append("<span class='more'><img src='img/icon/nav_icon.png'></span>");
      $(".more").parent().click(function(){
          $(this).next().slideToggle();
          $(this).children(".more").toggleClass("active");
      });
  });
  // top 버튼 
  $( document ).ready( function() {
      // 처음에는 안보이게 숨기기
      $(".top").hide();
      //  스크롤 탑 + fadein 효과 
      $(window).scroll(function(){
      if(	$(this).scrollTop() > 200){	
          $(".top").fadeIn();	
      }
      else{	
          $(".top").fadeOut();	
      }			
      });
      //클릭했을 때 스르륵 올라가도록 애니메이션 효과
      $(".top").click(function(){
      $('body,html').animate({
          scrollTop:0 
      },400 );
      return false;
      });
  });
  </script>
  <script>
  		//필터 사용을 위해 넣어둠
	   var hrefs = [];
	   $(".a").click(function(){
	      location.href ="/forward?href="+hrefs[$(".a").index(this)];
	   })
	   for(var i=0;i<$(".a").length;i++){
	        hrefs.push($(".a").eq(i).attr("href"));
	    }
	   $(".a").removeAttr("href"); 
	</script>
  