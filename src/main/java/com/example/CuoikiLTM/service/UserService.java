package com.example.CuoikiLTM.service;



import com.example.CuoikiLTM.model.Role;
import com.example.CuoikiLTM.model.User;
import com.example.CuoikiLTM.repository.RoleRepository;
import com.example.CuoikiLTM.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository repo;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User getUserByUserName(String username){
        User user = userRepository.getUserByUsername(username);
        return user;
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public List<User> getBXHChuoiThang(){
        return userRepository.getTop10UsersByWinningStreak();
    }
    public List<User> getBXHSoTranThang(){
        return userRepository.getTop10UsersBySoTranThang();
    }
    public List<User> getBXHSoTranThua(){
        return userRepository.getTop10UsersSoTranThua();
    }
    public List<User> getBXHChuoiThua(){
        return userRepository.getTop10UsersByLossningStreak();
    }


    public void register(User user ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleService.getDefaultRole();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public void removeUser(Integer iduser) {
        userRepository.deleteById(iduser);
    }

}
