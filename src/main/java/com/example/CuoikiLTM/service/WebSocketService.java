package com.example.CuoikiLTM.service;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebSocketService extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Xử lý khi một phiên kết nối mới được thiết lập
        sessions.add(session);
        System.out.println("New connection established. Total connections: " + sessions.size());
    }
    public List<WebSocketSession> getSessions(){
        return sessions;
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Xử lý khi một phiên kết nối bị đóng
        sessions.remove(session);
        System.out.println("Connection closed. Total connections: " + sessions.size());
    }

    @GetMapping("/connected-users")
    public String getConnectedUsers() {
        // Trả về danh sách các user đang kết nối
        // (Có thể xử lý logic cần thiết để trả về thông tin chi tiết hơn)
        return "Connected users: " + sessions.size();
    }
}
