package com.example.CuoikiLTM.service;



import com.example.CuoikiLTM.model.Role;
import com.example.CuoikiLTM.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getDefaultRole() {
        String roleName = "user";
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
    }
    public Role geRoleById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
    }
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
}
