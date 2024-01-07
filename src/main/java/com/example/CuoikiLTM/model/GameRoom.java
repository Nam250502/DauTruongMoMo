package com.example.CuoikiLTM.model;

import lombok.Getter;
import lombok.Setter;


import java.util.*;
@Getter
@Setter
public class GameRoom {
    private List<Player> players = new ArrayList<>();
    private String roomName;
    private Integer socauhoi;
    private Integer solangui;
    private Date date;
    public GameRoom(Player player1 , Player player2){
        this.players.add(player1);
        this.players.add(player2);
        this.roomName = generateGameRoom();
        this.socauhoi=1;
        this.date = new Date();

    }
    public void updateDiem(String username, int diem,int luachon){
        for (Player p: players) {
            if (p.getUsername().equals(username)){
                p.updateDiem(diem,luachon);

            }
        }
    }
    private  String generateGameRoom() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder roomCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            roomCode.append(characters.charAt(randomIndex));
        }
        return roomCode.toString();
    }
    public boolean isTimeToSendQuestion() {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - this.date.getTime();
        long timeDifferenceInSeconds = timeDifference / 1000;
        return timeDifferenceInSeconds >= 12;
    }
    public boolean isTimeToUpdate() {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - this.date.getTime();
        long timeDifferenceInSeconds = timeDifference / 1000;
        return timeDifferenceInSeconds >= 10;
    }
    public void restDapAn(){
        players.get(0).restluachon();
        players.get(1).restluachon();

    }

}
