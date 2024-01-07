package com.example.CuoikiLTM.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Player {
    private String username;
    private int score=0;
    private String image;
    private Boolean doithu=false;
    private  int luachon ;
    public Player(String name, String username,String image) {
        this.username=  username;
        this.image=  image;
    }
    public Player() {
    }

    public void updateDiem(int diem,int luachon) {
        this.score+=diem;
        this.luachon=luachon;
    }
    public void restluachon(){
        this.luachon=-1;
    }
}
