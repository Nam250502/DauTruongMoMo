package com.example.CuoikiLTM.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Random;

@Getter
@Setter
public class Verification {
    private String code;
    private Date date;
    public Verification(){
        this.code=generateGameRoom();
        this.date=new Date();
    }
    private  String generateGameRoom() {
        // Tạo một chuỗi ngẫu nhiên từ 5 ký tự
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder roomCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            roomCode.append(characters.charAt(randomIndex));
        }
        return roomCode.toString();
    }
}
