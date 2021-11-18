<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button onclick="initChat('${sessionScope.m.memberId }')">채팅 시작</button>
	<hr/>
	<div class="chatting">
		<div class="messageArea"></div>
		<div class="sendBox">
			<input type="text" id="sendMsg"/>
			<button id="sendBtn" onclick="sendMsg();">전송</button>
		</div>
	</div>
</body>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	var ws;
	var memberId;
	function initChat(param){
		memberId = param;
		//웹소캣 연결 시도
//		ws = new WebSocket("ws://192.168.10.96/chat.do");
//		ws = new WebSocket("ws://192.168.0.2/chat.do");
		ws = new WebSocket("ws://192.168.0.2:8083/chat.do");
		//웹소켓 연결이 성공하면 실행할 함수지정
		ws.onopen = startChat;
		//서버에서 화면으로 데이터를 전송하면 처리할 함수
		ws.onmessage = receiveMsg;
		//웹소캣 연결이 종료되면 실행할 함수 지정
		ws.onclose = endChat;
		$(".chatting").slideDown();
	}
	
	function startChat() {
		var data = {type:"enter", msg:memberId};
		ws.send(JSON.stringify(data));
		appendChat("<p>채팅방에 입장했습니다.</p>")
		
	}
	function receiveMsg(param) {
		appendChat(param.data)
		
	}
	function endChat() {	
		
	}
	function appendChat(msg) {	
		$(".messageArea").append(msg);
		$(".messageArea").scrollTop($(".messageArea")[0].scrollHeight);
	}
	function sendMsg(){
		var msg = $("#sendMsg").val();
		if(msg != ""){
			var data = {type:"chat",msg:msg};
			ws.send(JSON.stringify(data));
			appendChat("<div class='chat right'>"+msg+"</div>");
			$("#sendMsg").val("");
		}
	}
	
	$(function(){
		$("#sendMsg").keyup(function(key) {
			if(key.keyCode == 13){
				sendMsg();
			}
		})
	})
	
</script>
<style>
	.chatting{
		width:500;
		display:none;
	}
	.messageArea{
		overflow-y:auto;
		border: 1px solid black;
		height: 500px;
		display:flex;
		flex-direction:column;
		background-color:#b2c7d9;
	}
	.messageArea>p{
		text-align:center;
		width :100%;
	}
	#sendMsg{
		width:75%;
	}
	#sendBtn{
		width:20%;
	}
	.chat{
		margin-bottom:10px;
		padding:8px;
		border-radius:3px;
	}
	.right{
		background-color:#ffeb33;
		align-self:flex-end;
	}
	.left{
		background-color:#ffffff;
		align-self:flex-start;
		
	}
</style>
</html>