package com.example.CuoikiLTM.controller;

import com.example.CuoikiLTM.model.*;
import com.example.CuoikiLTM.repository.UserRepository;
import com.example.CuoikiLTM.service.CauHoiService;
import com.example.CuoikiLTM.service.GameService;
import com.example.CuoikiLTM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

  @Autowired
  private  GameService gameService;
    @Autowired
    private UserService userService;
    @Autowired
    private  SimpMessageSendingOperations messagingTemplate;
    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        User user = userService.getUserByUserName(principal.getName());
        model.addAttribute("user",user);
        return "home";
    }

    @GetMapping("/start/{roomname}")
    public String startGame(Principal principal, Model model,@PathVariable("roomname") String roomname) {
        User user = userService.getUserByUserName( principal.getName());
        Player player = new Player(user.getName(),user.getUsername(),user.getImage());
        GameRoom room =gameService.timphong(roomname);
        if (room != null && room.getPlayers().size() == 2) {
            model.addAttribute("player1", room.getPlayers().get(0));
            model.addAttribute("player2", room.getPlayers().get(1));
            model.addAttribute("roomname", room.getRoomName());
            return "play";
        } else {
            return "home";
        }
    }
    @GetMapping("/dangki")
    public void dangkiGame(Principal principal) {
        User user = userService.getUserByUserName( principal.getName());
        Player player = new Player(user.getName(),user.getUsername(),user.getImage());
        gameService.findOpponent(player);
    }
    @MessageMapping("/room/{roomname}")
    public void sendDataToServer(@DestinationVariable String roomname, Principal principal,Submit submit) {
        System.out.println("Received data from client - thoigian: " + submit.getThoigian() + ", Roomname: " + roomname + ", Principal: " + principal.getName() +"lựa chọn"+submit.getLuachon()+"ketqua"+submit.isKetqua());
        GameRoom gameRoom = gameService.submitAnswer(roomname, principal.getName(), submit.getThoigian(),submit.getLuachon(),submit.isKetqua());
        System.out.println("Sent data back to clients: " + gameRoom);
    }
    @GetMapping("/getBXHThang")
    public String getBXHChuoiThang(Model model){
        List<User> users = userService.getBXHChuoiThang();
        model.addAttribute("users",users);
        return "bangxephangthang";
    }
    @GetMapping("/getBXHThua")
    public String getBXHChuoiThua(Model model){
        List<User> users = userService.getBXHChuoiThua();
        model.addAttribute("users",users);
        return "bangxephangthua";
    }
    @GetMapping("/getBXHSoTranThua")
    public String getBXHSoTranThua(Model model){
        List<User> users = userService.getBXHSoTranThua();
        model.addAttribute("users",users);
        return "bxh-so-tran-thua";
    }
    @GetMapping("/getBXHSoTranThang")
    public String getBXHSoTranThang(Model model){
        List<User> users = userService.getBXHSoTranThang();
        model.addAttribute("users",users);
        return "bxh-so-tran-thang";
    }
}