package com.example.CuoikiLTM.controller;

import com.example.CuoikiLTM.model.CauHoi;
import com.example.CuoikiLTM.model.CauHoiIQ;
import com.example.CuoikiLTM.model.User;
import com.example.CuoikiLTM.service.CauHoiIQService;
import com.example.CuoikiLTM.service.CauHoiService;
import com.example.CuoikiLTM.service.RoleService;
import com.example.CuoikiLTM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CauHoiService cauHoiService;
    @Autowired
    private CauHoiIQService cauHoiIQService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;



    @GetMapping("/tat-ca-cau-hoi")
    public String getALLCauHoi(Model model){
         List<CauHoi> cauHois = cauHoiService.getAll();
         model.addAttribute("cauHois",cauHois);
        model.addAttribute("cauHoi", new CauHoi());
         return "all-cau-hoi";
     }
    @GetMapping("/tat-ca-cau-hoi-iq")
    public String getALLCauHoiIQ(Model model){
        List<CauHoiIQ> cauHois = cauHoiIQService.getAll();
        model.addAttribute("cauHois",cauHois);
        model.addAttribute("cauhoiiq", new CauHoiIQ());
        return "all-cau-hoi-iq";
    }
    @GetMapping("/them-cau-hoi")
    public String hienThiTrangThemCauHoi(Model model) {
        model.addAttribute("cauHoi", new CauHoi());
        return "themcauhoi";
    }
//    @GetMapping("/them-cau-hoi-iq")
//    public String hienThiTrangThemCauHoiiq(Model model) {
//        model.addAttribute("cauhoiiq", new CauHoiIQ());
//        return "cauhoiiq";
//    }
    @PostMapping("/them-cau-hoi-iq")
    public String themCauHoiIQ(@ModelAttribute CauHoiIQ cauHoiIQ) {
        cauHoiIQService.saveCauhoi(cauHoiIQ);
        return "redirect:/admin/tat-ca-cau-hoi-iq";
    }

    @PostMapping("/them-cau-hoi")
    public String themCauHoi(@ModelAttribute CauHoi cauHoi) {
        cauHoiService.saveCauhoi(cauHoi);
        return "redirect:/admin/tat-ca-cau-hoi";
    }
    @GetMapping("/xoa-cau-hoi/{id}")
    public String xoaCauHoi(@PathVariable Integer id) {
        cauHoiService.removeCauhoiById(id);
        return "redirect:/admin/tat-ca-cau-hoi";
    }
    @GetMapping("/xoa-cau-hoi-iq/{id}")
    public String xoaCauHoiIq(@PathVariable Integer id) {
        cauHoiIQService.removeCauhoiById(id);
        return "redirect:/admin/tat-ca-cau-hoi-iq";
    }

    @GetMapping("/removeuser/{iduser}")
    public String removeUser(@PathVariable ("iduser") Integer iduser){
        userService.removeUser(iduser);
        return "redirect:/admin/alluser";
    }
    @GetMapping("/alluser")
    public String getAllUser(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users",users);
        return "admin-user";
    }
    @GetMapping("/blockuser/{username}")
    public String blockUser(Model model,@PathVariable("username") String username){
        userService.blockUser(username);
        return "redirect:/admin/alluser";
    }
    @GetMapping("/unlockuser/{username}")
    public String unlockUser(Model model,@PathVariable("username") String username){
        userService.unlockUser(username);
        return "redirect:/admin/alluser";
    }
    @GetMapping("/alluseronline")
    public String getAllUserOnline(Model model){
        List<User> users = userService.findUserOnline();
        model.addAttribute("users",users);
        model.addAttribute("soluong",users.size());
        return "onlineUsers";
    }


}
