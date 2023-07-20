package com.bs.spring.common.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChattingServer extends TextWebSocketHandler{
	//문자 데이터를 실시간으로 처리하는 TextWebSocketHadler를 재정의 해서 사용
	
	private Map<String,WebSocketSession> clients=new HashMap(); //key=userId
	//닫힘 여부를 판단하기 위해서 map으로 저장해서 관리한다.
	
	private ObjectMapper mapper;
	public ChattingServer(ObjectMapper mapper) {
		this.mapper=mapper;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("채팅 서버 입장");
		log.info(session.getId()+" "+session.getRemoteAddress());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("{}",message);
		log.info(message.getPayload()); //클라이언트가 보낸 데이터 ->send에서 매개변수로 받은 값
		ChattingMessage msg=mapper.readValue(message.getPayload(), ChattingMessage.class); //클래스 매핑
		//session.getAttributes().put("msg", message);
		System.out.println(msg);
		switch(msg.getType()) {
			case "open" : addClient(session,msg.getSender()); break;
			case "msg" : sendMessage(msg); break;
			case "system" : break;
		}
	}
	
	private void addClient(WebSocketSession session, String sender) {
		clients.put(sender, session); //key값으로 아이디가 저장되기 때문에 동일한 아이디가 들어와도 덮어쓰기 되면서 새로운 session값이 저장된다.
		log.info("현재 접속자  : "+clients.size());
		//List<String> attence=new ArrayList(clients.keySet());
		String attence=clients.keySet().stream().map(e->e+",").collect(Collectors.joining());
		systemMessage(attence);
	}
	
	private void systemMessage(String msg) {
		try {
			Set<Map.Entry<String,WebSocketSession>> clients=this.clients.entrySet();
			for(Map.Entry<String,WebSocketSession> client:clients) {
				client.getValue().sendMessage(
						new TextMessage(mapper.writeValueAsString(
								ChattingMessage.builder().type("system").msg(msg).build()
								))
						);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(ChattingMessage msg) throws JsonProcessingException, IOException {
		Set<Map.Entry<String,WebSocketSession>> clients=this.clients.entrySet();
		try {
			if(msg.getReciever().isBlank()) {
				//전체전송
				for(Map.Entry<String,WebSocketSession> client:clients) {
					//String userId=client.getKey();
					client.getValue().sendMessage(
							new TextMessage(mapper.writeValueAsString(msg))
							);
				}
			}else{
				for(Map.Entry<String,WebSocketSession> client:clients) {
					String userId=client.getKey();
					if(userId.equals(msg.getReciever())||userId.equals(msg.getSender())) {
						client.getValue().sendMessage(
								new TextMessage(mapper.writeValueAsString(msg))
								);
					}
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
	}
	
	
	
}
