<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<c:if test="${not empty sessionScope.m }">
<script>
	var ws;
	var memberId = '${sessionScope.m.memberId}';
	$(function () {
		ws = new WebSocket("ws://192.168.0.2:8083/alarm.do");
		ws.onopen = dmStart;
		ws.onmessage = dmMsg;
		ws.onclose = dmEnd;
	});
	function dmStart(){
		var data = {type:"conn", memberId:memberId};
		ws.send(JSON.stringify(data));
		dmCount(memberId);
	}
	function dmMsg(param){
		console.log(param.data)
		$("#dmCount").html(param.data);
		
	}
	function dmEnd(){
		console.log("쪽지 소켓 종료");		
	}
	
	function dmCount(receiver){
		var data = {type:"alarm",memberId:receiver};
		ws.send(JSON.stringify(data));
	}
</script>
<h3>읽지 않은 쪽지 :<span id="dmCount"></span></h3>
</c:if>