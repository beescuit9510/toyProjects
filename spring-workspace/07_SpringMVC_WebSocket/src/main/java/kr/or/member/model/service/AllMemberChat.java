package kr.or.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AllMemberChat extends TextWebSocketHandler{
	private ArrayList<WebSocketSession> sessionList;
	private HashMap<WebSocketSession, String> memberList;
	
	
	public AllMemberChat() {
	
		sessionList = new ArrayList<WebSocketSession>();
		memberList = new HashMap<WebSocketSession, String>(); 
	}

	//클라이언트가 최초로 웹소캣 서버에 접속했을때 수행되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("클라이언트 접속!");
		//클라이언트가 새로 접속하면 웹소캣 세션을 리스트에 추가
		sessionList.add(session);
		System.out.println("현재 접속 : "+sessionList.size());
	}

	//클라이언트가 서버에 메세지를 전송했을때 수행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//jsp에서 ws.send()로 보낸 메소드
		System.out.println(message.getPayload());
		
		//문자열을 Json으로 처리하기위한 객체생성
		JsonParser parser = new JsonParser();
		//parser를 이용해서 String -> Json 으로 변환
		JsonElement element = parser.parse(message.getPayload());
		
		//키가 type인 값 추출
		String type = element.getAsJsonObject().get("type").getAsString();
		//키가 msg인 값 추출
		String msg = element.getAsJsonObject().get("msg").getAsString();
		System.out.println(msg);
		
		if(type.equals("enter")) {
			//채팅방에 처음 들어오면 아이디를 map에 저장
			memberList.put(session, msg);
			String sendMsg = "<p>"+msg+"님이 입장하셨습니다</p>";
			for(WebSocketSession s: sessionList) {
				if(!s.equals(session)) { //입장메세지 전송시 본인은 제외
					//클라이언트로 전송할 메세지 객체 생성
					TextMessage tm = new TextMessage(sendMsg);
					//클라이언트에게 전송
					s.sendMessage(tm);
				}
			}
		}else if(type.equals("chat")) {
			String sendMsg = "<div class='chat left'><span class='chatId' >"+memberList.get(session)+"</span> : "+msg+"</div>";
			for(WebSocketSession s: sessionList) {
				if(!s.equals(session)) { //입장메세지 전송시 본인은 제외
					TextMessage tm = new TextMessage(sendMsg);
					s.sendMessage(tm);
				}
			}
		}
	}

	//클리이언트가 연결을 끊을때 수행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		String sendMsg = "<p>"+memberList.get(session)+"님이 퇴장하셨습니다.</p>";
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(sendMsg));
			
		}
		memberList.remove(session);
	}
	
	
	
	
	
	
	

}
