<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=q8ruutisf3&submodules=geocoder"></script>
<script>
	window.onload = function(){
		//아무 설정없이 지도 객체를 만드는 경우 서울시청이 중심
		//var map = new naver.maps.Map("map");
		var map = new naver.maps.Map("map",{
			center : new naver.maps.LatLng(37.533837,126.896836),
			zoom : 17,
			zoomControl: true,
			zoomControlOptions : {
				position : naver.maps.Position.TOP_RIGHT,
				style:naver.maps.ZoomControlStyle.SMALL
			}
		});
		
		var marker = new naver.maps.Marker({
			position : new naver.maps.LatLng(37.533837,126.896836),
			map : map
		})
		
		var contentString = [
			"<div class='iw_inner'>",
			"	<h3>KH정보교육원</h3>",
			"	<p>서울시 영등포구 선유동 2로 57 이레빌딩 19F, 20F</p>",
			"</div>"
		].join("");
		
		var infoWindow = new naver.maps.InfoWindow();
		
		naver.maps.Event.addListener(marker, "click", function (e) {
			if(infoWindow.getMap()){ //infoWindow가 지도에 존재하면
				infoWindow.close() //inforWindow 닫기
			}else{ //infoWindow가 지도에 존재하지 않으면
				//미리 만들어둔 주소로 infoWindow 를 새로 생성
				infoWindow = new naver.maps.InfoWindow({
					content : contentString
				})
				// 생성된 infoWindow를 map의 market위치;
				infoWindow.open(map, marker);
			}
		})
		
		naver.maps.Event.addListener(map,"click",function(e){
			marker.setPosition(e.coord);
			if(infoWindow.getMap()){
				infoWindow.close();
			}
			
			naver.maps.Service.reverseGeocode({
				location : new naver.maps.LatLng(e.coord.lat(),e.coord.lng())
			},function(status, response){
				if(status != naver.maps.Service.Status.OK){
					return alert("주소를 찾을 수 없습니다.");
				}
				var result = response.result;
				var items = result.items;
				var address = items[1].address;
				contentString = [
					"<div class='iw_inner'>",
					"<p>"+address+"</p></div>"
				].join("");
			});
			
		})
	}
	

	function addrSearch(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            console.log(data);
	            $("#postCode").val(data.zoneCode);
	            $("#roadAddr").val(data.roadAddress);
	            $("#jibunAddr").val(data.jibunAddress);
	            $("#detailAddr").focus();
	        }
	    }).open();
	}
	
	
	//https://navermaps.github.io/maps.js/docs/index.html
	//샘플코드 볼수있는 곳 ! 
	
	// 네이버 ncloud application 삭제로 작동안될것임.
	// 다시 이용하고 싶을땐 네이버 nclound 콘솔로 가서 다시 map 전체 재발급후
	// ncpClientId=q8ruutisf3
	// ncpClinetId 부분을 재발급받고 스크립트 부분 변경후 사용해야함.
</script>
<meta charset="UTF-8">
<title>MAP API</title>
</head>
<body>
<script src="https://console.ncloud.com/mc/solution/naverService/application?version=v2" ></script>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="container">
		<h3>1. 다음 주소찾기 API</h3>
		<div>
			<input type="text" id="postCode" class="short form-control" readonly>
			<button onclick="addrSearch();" class="btn btn-primary">주소검색</button>
		</div>
		<div>
			<input type="text" id="roadAddr" class="form-control" placeholder="도로명주소">
		</div>
		<div>
			<input type="text" id="jibunAddr" class="form-control" placeholder="지번주소">
		</div>
		<div>
			<input type="text" id="detailAddr" class="form-control" placeholder="상세주소">
		</div>
		<hr>
		<h3>2. 네이버 지도 API</h3>
		<div id="map" style="width:100%;height:500px;"></div>
	</div>
<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>