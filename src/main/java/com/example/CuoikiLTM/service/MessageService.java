package com.example.CuoikiLTM.service;
import com.example.CuoikiLTM.model.CauHoi;
import com.example.CuoikiLTM.model.Player;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void sendRoomNameToUser(String username, String nameRoom) {
        messagingTemplate.convertAndSendToUser(username, "/queue/thongbao", nameRoom);
    }
    public void sendCauHoiToGroup(String groupName, CauHoi cauHoi) {
        String destination = "/room/" + groupName;
        messagingTemplate.convertAndSend(destination, cauHoi);
    }
    public void sendKetQuaToGroup(String groupName, Player player) {
        String destination = "/ketqua/" + groupName;
        messagingTemplate.convertAndSend(destination, player);
    }
}
