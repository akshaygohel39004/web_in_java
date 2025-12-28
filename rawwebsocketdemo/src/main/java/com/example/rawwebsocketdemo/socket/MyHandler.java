package com.example.rawwebsocketdemo.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
public class MyHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws  Exception{
        log.info(session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)throws  Exception {
        log.info(message.getPayload());
        session.sendMessage(new TextMessage("receiver: "+message.getPayload()));
        log.info("i reached here");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws  Exception{
        log.info(closeStatus.getReason());
        log.info(session.getId());
    }

}
