package com.example.CuoikiLTM.repository;

import com.example.CuoikiLTM.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}