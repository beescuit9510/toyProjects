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
                    <span class="profileText margin_b2">
                        <p>제작 프로젝트</p>
                        <p>${mb.proCount}개</p>
                    </span>
                    <c:if test="${sessionScope.member.cMemberNo eq mb.writerNo}">	                   
	                    <a href="<c:url value='/funderUpdateFrm?writerNo=${mb.writerNo}' />" class="btn btn_out btn_100 profilebtn">내 프로필 수정</a>
                    </c:if>
                    <div class="like_wr border_a">
                        <c:choose>
		                	<c:when test="${empty sessionScope.member}">						               
								<button onclick="login();" class="heart2"></button>
		                	</c:when>
		                	<c:when test="${mb.likedCheck == '좋아요'}">
		                		<button onclick="likefunder(this,${mb.writerNo});" class="heart2 h_on"></button>
		                	</c:when>
		                	<c:otherwise>                							              
								<button onclick="likefunder(this,${mb.writerNo});" class="heart2"></button>
		                	</c:otherwise>
		                </c:choose>		
                        <p><b id="like_total">${mb.funderCount}</b>명이 관심을 갖고 있습니다.</p>
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
                        				${mb.companyIntroBr}
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
												<li class="skill_list_left">
													<p class="skill_view_name">${skillname}<p>
												</li>
											</c:forTokens>
	                        			</ul>
	                        			<ul>
	                        				<c:forTokens items="${mb.skillLevel}" delims="/" var="skilllevel">
												<li class="skill_list_right">
														<p class="skill_view_name">${skilllevel}<p>
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
                    	<span class="view_title"><em class="point">${mb.businessName}</em> 프로젝트 <b class="total_count">[ 총 ${mb.proCount}개 ]</b></span>
                        <c:choose>
							<c:when test="${mb.proCount == 0 }">
								<span class="nonebox"><img src="/img/Parksowon/search_img.png"><h3>제작한 <em class="point">프로젝트</em>가 없습니다.</h3></span>
							</c:when>
							<c:otherwise>
		                        <ul class="table2 view_list_funder">
		                        
		                        </ul>
		                        <button class="btn" currentCount="0" totalCount="${mb.proCount}" value="1" id="more" style="margin-top:20px;">더보기</button>
							</c:otherwise>
						</c:choose>	
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
		function likefunder(c,obj){
			  var user = "${sessionScope.member.cMemberNo}";
			  var funder = obj;
			  $.ajax({
				   url : "/funderLike",
				   data: {user : user, funder: funder},
				   type: "POST",
				   success : function(data){
					   if(data.like_check == 0){
						    $(c).attr("class","heart2");
				   			$(c).next().children("#like_total").html(data.like_cnt);
						   setTimeout("location.reload()", 21000);
					   }else{
						   $(c).attr("class","heart2 h_on");
				   			$(c).next().children("#like_total").html(data.like_cnt);
					   		setTimeout("location.reload()", 21000);
					   }
				   }
			  })
		  }
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
	        });
	        $("#more").click();
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
		            content: '<div style="width:150px;text-align:center;background:#fff;">'+comname+'</div>'
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
		//프로젝트..
		$("#more").click(function(){
			var start = $(this).val();
			var writerNo = ${mb.writerNo};
			$.ajax({
				url : "funderViewMore",
				data : {start:start, writerNo:writerNo},
				type : "post",
				success : function(data){
					for(var i = 0; i < data.length; i++){
						var pro = data[i];
						var html="";
						html += "<a href='/fundingView?projectNo="+pro.projectNo+"'>";
						html += "<li class='border_a funder_listli2'>";
						html += "<div class='funder_list_img'>";
						html += "<div class='view_dday point_pk'>D-"+pro.period+"</div>"
						html += "<img src='/upload/project/"+pro.filepath+"'>";
						html += "</div>";
						html += "<div class='funder_list_text'>";
						html += "<div class='funder_list_title'>";
						html += "<h1>"+pro.projectTitle+"</h1>";
						html += "</div>";
						html += "<div class='funder_list_date'>";
						html += "<div class='start_date'>";
						html += "<em class='point'>시작일</em>"+pro.startDate+"</div>";
						html += "<div class='end_date'>";
						html += "<em class='point_pk'>마감일</em>"+pro.endDate+"</div>";
						html += "</div>";
						html += "</div>";
						html += "</li>";
						html += "</a>";
						$(".view_list_funder").append(html);
					}
					$("#more").val(Number(start)+2);
					//지금까지 읽어온 게시물의 수를 변경(읽어온 배열의 길이만큼 기존값에 더함)
					var curr = Number($("#more").attr("currentCount"));
					$("#more").attr("currentCount",curr+data.length);
					//전체 게시물 수
					var totalCount = $("#more").attr("totalCount");
					//변경된 지금까지 읽어온 게시물 수 
					var currCount = $("#more").attr("currentCount");
					//지금까지 읽어온 게시물과 전체 게시물 수가 같으면 더보기 버튼 비활성화
					if(currCount == totalCount){
						$("#more").css("display","none");
						$("#more").prop("disabled",true);
					}
				}
			})
		});
		
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>