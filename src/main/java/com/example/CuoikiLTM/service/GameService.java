package com.example.CuoikiLTM.service;

import com.example.CuoikiLTM.model.CauHoi;
import com.example.CuoikiLTM.model.GameRoom;
import com.example.CuoikiLTM.model.Player;
import com.example.CuoikiLTM.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GameService {
    private List<GameRoom> gameRooms = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    @Autowired
    private MessageService messageService;
    @Autowired
    private CauHoiService cauHoiService;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private UserService userService;

    private static final int TIMEOUT_SECONDS = 60;

    public void findOpponent(Player player) {
        players.add(player);
        long startTime = System.currentTimeMillis();
        System.out.println(player.getUsername() + " đang tìm đối thủ");
        while (System.currentTimeMillis() - startTime < TIMEOUT_SECONDS * 1000) {
            Iterator<Player> iterator = players.iterator();
            while (iterator.hasNext()) {
                Player p = iterator.next();
                if (!p.getDoithu() && !p.getUsername().equals(player.getUsername())) {
                    GameRoom gameRoom = new GameRoom(player, p);
                    gameRooms.add(gameRoom);
                    // Remove both players from the list
                    iterator.remove();
                    players.remove(player);
                    System.out.println(player.getUsername() + " đấu với " + p.getUsername());
                    // Notify players about the game start
                    messageService.sendRoomNameToUser(player.getUsername(), "/game/start/" + gameRoom.getRoomName());
                    messageService.sendRoomNameToUser(p.getUsername(), "/game/start/" + gameRoom.getRoomName());
                    return;
                }
            }
        }
        // If no opponent found within the timeout
        System.out.println("Không tìm thấy đối thủ trong thời gian quy định.");
        players.remove(player);
        messageService.sendRoomNameToUser(player.getUsername(), "/game/home");
    }


    @Scheduled(fixedRate = 2000)
    public void sendQuestions() {
        if (gameRooms.size()>0){
            for (GameRoom gameRoom : gameRooms) {
                if (gameRoom.isTimeToSendQuestion()){
                    sendQuestion(gameRoom);
                    gameRoom.setSocauhoi(gameRoom.getSocauhoi()+1);
                    gameRoom.setDate(new Date());
                }
            }
        }
    }
    @Scheduled(fixedRate = 3000)
    public void sendUpdate() {
        if (gameRooms.size()>0){
            for (GameRoom gameRoom : gameRooms) {
                if (gameRoom.isTimeToUpdate()){
                    String destination = "/room/" + gameRoom.getRoomName()+ "/update";
                    messagingTemplate.convertAndSend(destination,gameRoom);
                    gameRoom.restDapAn();
                }
            }
        }
    }

    public GameRoom submitAnswer(String nameRoom, String userName,int thoigian,int luachon,boolean ketqua) {
        for (GameRoom g: gameRooms) {
            if (g.getRoomName().equals(nameRoom)){
                int temp=0;
                if (ketqua){
                    temp = thoigian*10;
                }
                g.updateDiem(userName,temp,luachon);
                return g;
            }
        }
        return null;
    }
    public void sendQuestion(GameRoom gameRoom) {
        if (gameRoom.getSocauhoi() <= 5) {
            CauHoi cauHoi = cauHoiService.layCauHoiNgauNhien();
            if(cauHoi!=null){
                messageService.sendCauHoiToGroup(gameRoom.getRoomName(),cauHoi);
                System.out.println("đã gửi câu hỏi tới phòng :" +gameRoom.getRoomName()+"câu hỏi :" +cauHoi);
            }
        } else {
            thongKeDiem(gameRoom);
        }
    }

    private void thongKeDiem(GameRoom gameRoom) {
        Player player1= gameRoom.getPlayers().get(0);
        Player player2= gameRoom.getPlayers().get(1);
        User user1 = userService.getUserByUserName(player1.getUsername());
        User user2 = userService.getUserByUserName(player2.getUsername());
        if(player1.getScore()>player2.getScore()){
            messageService.sendKetQuaToGroup(gameRoom.getRoomName(),player1);
            user1.setChuoithang(user1.getChuoithang()+1);
            user1.setSotranthang(user1.getSotranthang()+1);
            user1.setChuoithua(0);
            user2.setChuoithua(user2.getChuoithua()+1);
            user2.setSotranthua(user2.getSotranthua()+1);
            user2.setChuoithang(0);
            if (user2.getChuoithang()>0){
                user2.setChuoithang(0);
            }
        }if (player1.getScore()<player2.getScore()){
            messageService.sendKetQuaToGroup(gameRoom.getRoomName(),player2);
            user2.setChuoithang(user2.getChuoithang()+1);
            user2.setSotranthang(user2.getSotranthang()+1);
            user2.setChuoithua(0);
            user1.setChuoithua(user1.getChuoithua()+1);
            user1.setSotranthua(user1.getSotranthua()+1);
            user1.setChuoithang(0);
        }
        if (player1.getScore()==player2.getScore()){
            Player player = new Player();
            player.setName("Hòa");
            player.setScore(player1.getScore());
            messageService.sendKetQuaToGroup(gameRoom.getRoomName(),player);
        }
        userService.updateUser(user1);
        userService.updateUser(user2);
        gameRooms.remove(gameRoom);
    }

    public GameRoom timphong(String roomname){
        for (GameRoom g:gameRooms){
            if (g.getRoomName().equals(roomname)){
                return g;
            }
        }
        return null;
    }

}

