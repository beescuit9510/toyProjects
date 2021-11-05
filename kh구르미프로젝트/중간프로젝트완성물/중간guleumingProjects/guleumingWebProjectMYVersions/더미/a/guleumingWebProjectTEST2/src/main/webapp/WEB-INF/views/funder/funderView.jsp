<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--  JSTL 확장 c 태그 선언문 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/Parksowon/funder.css">
<!-- 지도 -->
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2fe50c2d1b8d26d2ec5e7053eeb12b16&libraries=services"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="container">
		<div class="funder_add">
			<div class="add_left border_a">
                <div class="profileTop">                   
                    <div class="profileImg">
                    	<c:choose>
							<c:when test="${not empty mb.profileFilepath}">
								<img src="/upload/funder/${mb.profileFilepath}">
							</c:when>
							<c:otherwise>							
                        		<img src="/img/Parksowon/profile.png">
							</c:otherwise>
						</c:choose>
                    </div>
                    <span class="profileName">${mb.businessName}</span>
                </div>
                <div class="border_b"></div>
                <div class="profileBottom">
                    <span class="profileText">
                        <p>업종</p>
                        <p>${mb.funderCategory}</p>
                    </span>
                    <span class="profileText">
                        <p>설립일</p>
                        <p>${mb.openDate}</p>
                    </span>
                    <span class="profileText margin_b">
                        <p>제작 프로젝트</p>
                        <p>개</p>
                    </span>
	                    <a href="<c:url value='/funderUpdateFrm?writerNo=${fb.writerNo}' />" class="btn btn_out btn_100 profilebtn">내 프로필 수정</a>
                    <c:if test="${not empty sessionScrope.m && sessionScope.m.cMemberNo eq mb.writerNo}">	                   
                    </c:if>
                    <div class="like_wr border_a">
                        <img src="/img/Parksowon/heart.png">
                        <p><b>${mb.funderCount}명이 관심</b>을 갖고 있습니다.</p>
                    </div>
                </div>
			</div>
		 	<div class="fundercontainer">
                <ul class="tab_title">      
                    <li class="on">기본정보</li>            
                    <li>프로젝트</li>           
                    <li>회사정보</li>               
                </ul>   
            </div>
		 	<div class="add_right2">
                <div class="tab_cont">
                    <div class="tab_div on">
                        <table class="table2 text_align_l">
                        	<span class="view_title"><em class="point">${mb.businessName}</em> 기본정보</span>
                        	<tr class="table-active2">
                        		<th>회사소개</th>
                        	</tr>
                        	<tr class="table-active2">
                        		<td>
                        			<div class="view_com_info">
                        				${mb.companyIntro}
                        			</div>                       		
                        		</td>
                        	</tr>
                        	<tr class="table-active2">
                        		<th>보유 자격증</th>
                        	</tr>
                        	<tr class="table-active2">
                        		<td>
                        			<div class="skill_view_list">                       			
	                        			<ul>                       			
					                        <c:forTokens items="${mb.skillName}" delims="/" var="skillname">
												<li class="border_a">
														<p class="skill_view_name">${skillname}<p>
													<c:forTokens items="${mb.skillLevel}" delims="/" var="skilllevel">
														<p class="skill_view_level">${skilllevel}</p>
													</c:forTokens>
												</li>
											</c:forTokens>
	                        			</ul>
                        			</div>
								</td>
                        	</tr>
                        	<tr class="table-active2">
                        		<th>Special Tag</th>
                        	</tr>
                        	<tr class="table-active2">
                        		<td>
									<c:forTokens items="${mb.searchTag}" delims="/" var="searchtag">
										<p class="view_tag">&#35;${searchtag}</p>									
									</c:forTokens>
								</td>
                        	</tr>
                        </table>
                    </div>            
                    <div class="tab_div">
                    	<span class="view_title"><em class="point">${mb.businessName}</em> 프로젝트 (count)</span>
                        <ul class="table2">
                        	<%-- <c:forEach items="" var=""> --%>
            					<a href="<c:url value='/funderView?writerNo=${fb.writerNo}' />">
            						<li class="border_a funder_listli">
					                    
					                </li>
            					</a>
            				<%-- </c:forEach> --%>
                        </ul>
                    </div>            
                    <div class="tab_div">
                        <table class="table2 text_align_l">
                        	<span class="view_title"><em class="point">${mb.businessName}</em> 회사정보</span>
                        	<tr class="table-active">
                        		<th>법인명</th>
                        		<td>${mb.businessName}</td>
                        	</tr>
                        	<tr class="table-active">
                        		<th>설립일</th>
                        		<td>${mb.openDate}</td>
                        	</tr>
                        	<tr class="table-active">
                        		<th>업종</th>
                        		<td>${mb.funderCategory}</td>
                        	</tr>
                        	<tr class="table-active">
                        		<th>담당자</th>
                        		<td>${m.managerName}</td>
                        	</tr>
                        	<tr class="table-active">
                        		<th>문의사항</th>
                        		<td>${mb.boardEmail}</td>
                        	</tr>
                        	<tr class="table-active">
                        		<th>찾아오시는 길</th>
                        		<td>
                        			<div id="map" style="width:100%;height:400px;"></div>
                        		</td>
                        	</tr>
                        </table>
                    </div>           
                </div> 
            </div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			//다음 지도 문제때문에 밖으로 꺼내놓음..
			$(".tab_cont > .tab_div").hide();
			$(".tab_cont > .tab_div").eq(0).show();
	        $(".tab_title li").click(function() {
	            var idx = $(this).index();
	            $(".tab_title li").removeClass("on");
	            $(".tab_title li").eq(idx).addClass("on");
	            $(".tab_cont > .tab_div").hide();
	            //탭 변경 시 지도 재설정
	            $(".tab_cont > .tab_div").eq(idx).show().ready(relayout());
	        })
	    });
		//map
		 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
			 	center: new kakao.maps.LatLng(38.00994423, 126.9531742), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		// 버튼 클릭에 따라 지도 이동 기능을 막거나 풀고 싶은 경우에는 map.setDraggable 함수를 사용합니다
		var mapTypeControl = new daum.maps.MapTypeControl();
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		var zoomControl = new daum.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);	
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();	
		// 주소로 좌표를 검색합니다 
		var addr = '${mb.companyAddr}';
		var comname = '${mb.businessName}';
		geocoder.addressSearch(addr, function(result, status) {
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {	
		        var coords = new kakao.maps.LatLng(result[0].y,result[0].x);
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+comname+'</div>'
		        });
		        infowindow.open(map, marker);
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(new kakao.maps.LatLng(result[0].y,result[0].x));
		    } 
		});
		function relayout() {    		    
		    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
		    // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
		    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
		    map.relayout();
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>