package kr.or.common;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonElement;

import kr.or.dm.model.service.DmService;

public class Alarm extends TextWebSocketHandler{
	@Autowired
	private DmService dmService;//쪽지 갯수를 조회하기 위한 서비스 호출객체
	private HashMap<String, WebSocketSession> memberList;//웹소캣 접속자 관리용 객체(아이디 기준)
	
	public Alarm() {
		super();
		memberList = new HashMap<>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	}

	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		
		JsonElement element = parser.parse(message.getPayload());
		String type = element.getAsJsonObject().get("type").getAsString();
		String memberId = element.getAsJsonObject().get("memberId").getAsString();
		
		if(type.equals("conn")) {
			memberList.put(memberId, session);
		}
		if(type.equals("alarm")) {
			WebSocketSession s = memberList.get(memberId);
			
			if(s != null) {	
				int dmCount = dmService.dmCount(memberId);
				TextMessage tm = new TextMessage(String.valueOf(dmCount));
				s.sendMessage(tm);
				
			}
			
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		memberList.remove(session);
	}
	

	
}
